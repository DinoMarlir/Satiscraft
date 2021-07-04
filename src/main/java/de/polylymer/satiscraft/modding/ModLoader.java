package de.polylymer.satiscraft.modding;

import de.polylymer.satiscraft.audio.SoundTrack;
import de.polylymer.satiscraft.event.Event;
import de.polylymer.satiscraft.internal.FactoryGameCrashReport;
import de.polylymer.satiscraft.modding.event.Action;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

public final class ModLoader {

    private final static Collection<Method> modifyingMethods = new ArrayList<>();
    private final static Collection<Method> subscribedMethods = new ArrayList<>();
    private final static ModLoader instance = new ModLoader();

    public static ModLoader getInstance() {
        return instance;
    }

    public void loadMods() throws IOException {

    }

    public void registerModPartClass(Class<?> clazz) {
        for (Method method : clazz.getMethods()) {
            Modify modify = method.getAnnotation(Modify.class);
            if(modify != null) {
                modifyingMethods.add(method);
            } else {
                Subscribe subscribe = method.getAnnotation(Subscribe.class);
                if(subscribe != null) {
                    subscribedMethods.add(method);
                }
            }
        }
    }

    public void triggerEvent(Event event, Action action) {
        for (Method method : subscribedMethods) {
            Subscribe subscribe = method.getAnnotation(Subscribe.class);
            if(subscribe.value() == event) {
                try {
                    method.invoke(null, action);
                } catch (Exception e) {
                    new FactoryGameCrashReport(e, subscribe, method.getDeclaringClass());
                }
            }
        }
    }

}
