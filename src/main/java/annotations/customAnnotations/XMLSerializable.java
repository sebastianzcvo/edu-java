package annotations.customAnnotations;

import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)// SOURCE discarded before compilation, CLASS (default) discarded after compilation, RUNTIME retained until execution
public @interface XMLSerializable {
    // each element is written similar to an abstract method.
    // Only primitive types and arrays are allowed
    // Default denotes optional elements
    String tag() default "";
}
