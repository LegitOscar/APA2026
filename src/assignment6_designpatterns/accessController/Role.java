package assignment6_designpatterns.accessController;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Role {
    String value(); // fx "admin", "user"
}
