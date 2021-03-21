package de.polylymer.satiscraft.modding;

import de.polylymer.satiscraft.event.Event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscribe {


    //ERST EVENT INITTEN, DANN TRIGGERN, DANN AUSFÜHREN

    Event value();

}
