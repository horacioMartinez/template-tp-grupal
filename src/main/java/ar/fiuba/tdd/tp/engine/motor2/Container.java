package ar.fiuba.tdd.tp.engine.motor2;

import java.util.ArrayList;
import java.util.HashMap;

public class Container {

    private HashMap<String, Container> componentsContained = new HashMap<String, Container>();
    private ArrayList<ContainerDependant> dependencies = new ArrayList<>();
    State states = null;
    private String name;

    public Container(String name) {
        this.name = name;
    }

    public void setDependencies(ContainerDependant dependency) {
        this.dependencies.add(dependency);
    }

    public String getDependantMessage() {
        for (ContainerDependant dependant : dependencies) {
            if (!dependant.isDependantAvailable()) {
                return dependant.getUnsuccessfulMessage();
            }
        }
        return "";
    }

    public void setState(State state) {
        this.states = state;
    }

    public String getName() {
        return this.name;
    }

    public void setComponent(Container component) {
        this.componentsContained.put(component.getName(), component);
    }

    public String changeStatus(String state) {
        return this.states.changeStates(this.componentsContained,state);
    }

    public boolean contains(Container component) {
        return this.componentsContained.containsValue(component);
    }

    public void removeComponent(Container component) {
        this.componentsContained.remove(component.getName());
    }

    public boolean checkStatus(String state) {
        if (this.states != null ) {
            return this.states.checkStatus(state);
        }
        return false;
    }
}