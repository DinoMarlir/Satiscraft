package de.polylymer.satiscraft.modding;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public final class ModLoader {

    private final Collection<Method> modifyMethods = new ArrayList<Method>();


    public void loadMods() throws IOException {

    }

    public void registerModPartClass(Class<? extends ModPart> clazz) {
        for (Method method : clazz.getMethods()) {
            Modify modify = method.getAnnotation(Modify.class);
            if(modify != null) {
                modifyMethods.add(method);
            }
        }
    }

}
