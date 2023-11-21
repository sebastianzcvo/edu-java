package annotations;

import java.lang.annotation.*;

// Annotations are a special type of modifiers that decorate the code to add metadata or indicate additional functionality, like
//   functionality provided by the compiler, to show error or warning messages
//   functionality provided by tools/libraries/frameworks, like lombok, jpa-buddy, hibernate or spring
@Documented// If the annotation @Documented is present on the declaration of an annotation interface A, then any @A annotation on an element is considered part of the element's public contract
@Inherited // Indicates that an annotation interface is automatically inherited
@Target(value = { // The elements this annotation is meant to be used on
        ElementType.TYPE, // Class, interface (including annotation interface), enum, or record declaration
        ElementType.FIELD, // Field declaration (includes enum constants)
        ElementType.METHOD, // Method declaration
        ElementType.PARAMETER, // Formal parameter declaration
        ElementType.CONSTRUCTOR, // Constructor declaration
        ElementType.LOCAL_VARIABLE, // Local variable declaration
        ElementType.ANNOTATION_TYPE, // Annotation interface declaration (Formerly known as an annotation type.)
        ElementType.PACKAGE, // Package declaration
        ElementType.TYPE_PARAMETER, //? Type parameter declaration Since: 1.8
        ElementType.TYPE_USE, //? Use of a type Since: 1.8
        ElementType.MODULE, // Module declaration Since: 9
        ElementType.RECORD_COMPONENT // Record component Since: 16
})
// Annotations can be read from source files, class files, or reflectively at run time
//@Retention(RetentionPolicy.SOURCE) Annotations are to be discarded by the compiler
//@Retention(RetentionPolicy.CLASS) Annotations are to be recorded in the class file by the compiler but need not be retained by the VM at run time. This is the default behavior.
@Retention(RetentionPolicy.RUNTIME) // Annotations are to be recorded in the class file by the compiler and retained by the VM at run time, so they may be read reflectively.
public @interface MyAnnotation {
    // Each annotation's element is declared similar to an abstract method.
    // Allowed types: primitives, strings, enums, and arrays of primitives, strings or enums
    int value();
    String[] arrElement();
    Value[] optionalElement() default {Value.VALUE1, Value.VALUE2}; // default for optional elements

    enum Value {
        VALUE1, VALUE2
    }
}

//@MyAnnotation all elements can be omitted if no elements are expected
//@MyAnnotation() also valid
//@MyAnnotation(1) when only one element ¡NAMED value! is expected, name can be omitted
//@MyAnnotation(value = 1) also valid
//@MyAnnotation(value = 1, arrElement = "value1") a single value for an array, is inferred as an array containing a single value
//@MyAnnotation(value = 1, arrElement = {"value1"}) also valid
@MyAnnotation(value = 1, arrElement = {"value1"}, optionalElement = {MyAnnotation.Value.VALUE1})
class Person {
}