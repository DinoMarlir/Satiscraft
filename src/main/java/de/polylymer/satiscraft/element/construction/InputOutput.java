package de.polylymer.satiscraft.element.construction;

import de.polylymer.satiscraft.element.InventoryHolder;

public abstract class InputOutput implements InventoryHolder {

    private final int inputs;
    private final int outputs;

    public InputOutput(int inputs, int outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public <T> T getRecipe() {
        return null;
    }

}
