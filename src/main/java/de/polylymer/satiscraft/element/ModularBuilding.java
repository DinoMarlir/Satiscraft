package de.polylymer.satiscraft.element;

import de.polylymer.satiscraft.element.PlaceableElement;

import java.lang.annotation.*;
import java.lang.reflect.Array;
import java.util.Collection;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ModularBuilding {

    Element elementOne();
    Element elementTwo() default Element.AIR;
    Element elementThree() default Element.AIR;
    Element elementFour() default Element.AIR;
    Element elementFive() default Element.AIR;
    Element elementSix() default Element.AIR;
    Element elementSeven() default Element.AIR;

}
