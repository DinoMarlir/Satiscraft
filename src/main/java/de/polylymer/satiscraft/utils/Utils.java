package de.polylymer.satiscraft.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Utils {

    @SafeVarargs
    public static <T> Collection<T> listOf(T... t) {
        return new ArrayList<>(Arrays.asList(t));
    }

}
