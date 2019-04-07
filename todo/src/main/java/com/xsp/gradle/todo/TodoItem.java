package com.xsp.gradle.todo;

public class TodoItem {
    private String name;
    private boolean hasDone;
    public TodoItem(String name) {
        this.name = name;
    }
    public String getName() {
        return  name;
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "name='" + name + '\'' +
                ", hasDone=" + hasDone +
                '}';
    }

    public boolean isHasDone() {
        return hasDone;
    }

    public void setHasDone(boolean hasDone) {
        this.hasDone = hasDone;
    }

    public void setName(String name) {
        this.name = name;
    }
}
