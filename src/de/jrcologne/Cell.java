package de.jrcologne;

class Cell {

    private String value;

    Cell() {
        this.value = " ";
    }

    String getValue() {
        return this.value;
    }

    void setValue(String value) {
        this.value = value;
    }

    boolean empty() {
        return this.value.equals(" ");
    }
}
