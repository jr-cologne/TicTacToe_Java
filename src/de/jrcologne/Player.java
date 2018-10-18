package de.jrcologne;

class Player {

    private String name;
    private String symbol;

    Player() {
        this.name = null;
        this.symbol = null;
    }

    Player setName(String name) {
        this.name = name;

        return this;
    }

    String getName() {
        return this.name;
    }

    Player setSymbol(String symbol) {
        this.symbol = symbol;

        return this;
    }

    String getSymbol() {
        return this.symbol;
    }

}
