package tryWithResources;

// In
class Resource {
    public Resource() {
        System.out.println("Creating external resource");
    }

    public void op1() {
        System.out.println("op1");
    }

    public void finalize() {
        System.out.println("cleanup external resources...");
    }
}

public class TryWithResources {
    public static void main(String[] args) {
        Resource resource = new Resource();
        resource.op1();
    }
}
