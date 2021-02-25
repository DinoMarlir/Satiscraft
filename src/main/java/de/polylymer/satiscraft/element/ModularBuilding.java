package de.polylymer.satiscraft.element;

import de.polylymer.satiscraft.element.PlaceableElement;

import java.lang.annotation.*;
import java.lang.reflect.Array;
import java.util.Collection;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ModularBuilding {

    int elementOne();
    int elementTwo() default 0;
    int elementThree() default 0;
    int elementFour() default 0;
    int elementFive() default 0;
    int elementSix() default 0;
    int elementSeven() default 0;

}
