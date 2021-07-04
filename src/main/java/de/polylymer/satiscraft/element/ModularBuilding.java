package de.polylymer.satiscraft.element;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ModularBuilding {

    Element[] buildingCosts();

}
