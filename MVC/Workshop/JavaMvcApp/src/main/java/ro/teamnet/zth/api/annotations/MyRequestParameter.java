package ro.teamnet.zth.api.annotations;

import java.lang.annotation.*;

/**
 * Created by cizuss94 on 7/16/2016.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestParameter {
    String name();
}
