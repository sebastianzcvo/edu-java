package annotations.customAnnotations;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface XMLElement {
    // each element is written similar to an abstract method.
    // Only primitive types and arrays are allowed
    // Default denotes optional elements
    String tag() default "";
}
