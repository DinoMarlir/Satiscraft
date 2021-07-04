package de.polylymer.satiscraft.event;

import de.polylymer.satiscraft.element.ModularBuilding;
import de.polylymer.satiscraft.internal.FactoryGameCrashReport;
import de.polylymer.satiscraft.internal.eventmanager.EventDisplay;
import de.polylymer.satiscraft.internal.exception.FactoryGameEventDisplayException;
import de.polylymer.satiscraft.modding.ModLoader;
import de.polylymer.satiscraft.modding.event.Action;
import de.polylymer.satiscraft.modding.event.player.PlayerPlaceModularBuildingAction;
import java.lang.reflect.Method;

public enum Event {

    ELEMENT_PLACED(PlayerPlaceModularBuildingAction.class),
    MODULAR_ELEMENT_PLACED(PlayerPlaceModularBuildingAction.class);

    Event(Class<? extends Action> clazz) {
        this.clazz = clazz;
    }

    private Class<? extends Action> clazz;

    public Class<? extends Action> getClazz() {
        return clazz;
    }

    public static void triggerBefore(Event event, Method method) {
        EventDisplay.addToStash("_");
        EventDisplay.addToDisplay(event.name() + " getting triggered by " + method.getName() + "\n");
    }

    public static void trigger(Event event, Method method, Action action) {
        ModLoader.getInstance().triggerEvent(event, action);
        if(event == MODULAR_ELEMENT_PLACED) {
            if(method.getDeclaringClass().getAnnotation(ModularBuilding.class) == null) {
                EventDisplay.addToStash("-\\");
                new FactoryGameCrashReport(new FactoryGameEventDisplayException("Cannot display an modular event which isn't annotated which @Modular?"), method, method.getDeclaringClass());
                //TODO write illegal placing is causing game crash
            }
        } else if(event == ELEMENT_PLACED) {
            EventDisplay.addToStash("-");
            EventDisplay.addToDisplay(event.name() + " in Method " + method.getName()+ " is unmodular, this will decrease server or clients stability.");
            if(method.getDeclaringClass().getAnnotation(ModularBuilding.class) != null) {
                EventDisplay.addToStash("-\\");
                //TODO write illegal placing is causing game crash
                new FactoryGameCrashReport(new FactoryGameEventDisplayException("Cannot display an unmodular event which is annotated which @Modular?"), method, method.getDeclaringClass());
            }
        }
    }

}
