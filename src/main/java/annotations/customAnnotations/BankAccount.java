package annotations.customAnnotations;

import java.lang.reflect.Field;

public class BankAccount extends Account { // inherits @XMLSerializable from Account cuz of the @Inherited meta-annotation
    @XMLElement
    private final int id;
    @XMLElement
    private final String name;
    @XMLElement(tag = "balance")
    private final double bal;

    public BankAccount(int id, String name, double bal) {
        this.id = id;
        this.name = name;
        this.bal = bal;
    }

    public static void main(String[] args) throws IllegalAccessException {
        String xml = getXMLString(new BankAccount(1, "account1", 1.2));
        System.out.println(xml);
    }

    private static String getXMLString(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        if (!clazz.isAnnotationPresent(XMLSerializable.class))
            throw new IllegalArgumentException("Object's class is not XMLSerializable");

        StringBuilder fieldsBuilder = new StringBuilder();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(XMLElement.class)) {
                String fieldTag = getTag(field);
                fieldsBuilder.append("\t<").append(fieldTag).append(">")
                        .append(field.get(object).toString())
                        .append("</").append(fieldTag).append(">\n");

            }
        }
        StringBuilder classBuilder = new StringBuilder();
        String classTag = getTag(clazz);
        return classBuilder.append("<").append(classTag).append(">\n")
                .append(fieldsBuilder)
                .append("</").append(classTag).append(">")
                .toString();
    }

    private static String getTag(Field field) {
        String tag = field.getAnnotation(XMLElement.class).tag();
        return tag.isEmpty() ? field.getName() : tag;
    }

    private static String getTag(Class<?> clazz) {
        String tag = clazz.getAnnotation(XMLSerializable.class).tag();
        return tag.isEmpty() ? clazz.getSimpleName() : tag;
    }
}
