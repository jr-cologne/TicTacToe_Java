package de.jrcologne;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Game {

    private static BufferedReader in;

    private Player player1;
    private Player player2;
    private Board board;
    private Player winner;

    Game() {
        in = new BufferedReader(new InputStreamReader(System.in));

        this.player1 = null;
        this.player2 = null;
        this.board = null;
        this.winner = null;

        boot();
    }

    private void boot() {
        printWelcomeScreen();
        playerNamesDialog();
        printStartScreen();
        initBoard();
        start();
        printEndScreen();
    }

    private void printWelcomeScreen() {
        System.out.println("Welcome to this TicTacToe game!");
        System.out.println("This game can currently only be played by two players.");
        System.out.println("Please enter your names in order to start the game.");
        System.out.println();
    }

    private void playerNamesDialog() {
        String player1 = "X";
        String player2 = "O";

        System.out.print("Player \"X\", please enter your name > ");

        try {
            player1 = in.readLine().trim();
        } catch (IOException e) {
            System.out.println("Something went wrong processing your entered name. Your name stays \"X\".");
        }

        if (player1.length() <= 1) {
            player1 = "X";
            System.out.println("Your entered name is too short. Your name stays \"X\".");
        }

        System.out.print("Player \"O\", please enter your name > ");

        try {
            player2 = in.readLine().trim();
        } catch (IOException e) {
            System.out.println("Something went wrong processing your entered name. Your name stays \"O\".");
        }

        if (player2.length() <= 1) {
            player2 = "O";
            System.out.println("Your entered name is too short. Your name stays \"O\".");
        }

        createPlayers(player1, player2);
    }

    private void createPlayers(String player1, String player2) {
        this.player1 = (new Player()).setName(player1).setSymbol("X");
        this.player2 = (new Player()).setName(player2).setSymbol("O");
    }

    private void printStartScreen() {
        System.out.println();
        System.out.println("Alright, " + this.player1.getName() + " and " + this.player2.getName() + ". Have fun!");
        System.out.println("The game is starting now.");
    }

    private void initBoard() {
        this.board = (new Board()).init();
    }

    private void start() {
        this.board.print();
        System.out.println();

        play();

        restartDialog();
    }

    private void play() {
        Player currentPlayer = this.player1;

        while (!gameHasEnded()) {
            move(currentPlayer);

            this.board.print();
            System.out.println();

            currentPlayer = switchPlayer(currentPlayer);
        }

        if (this.winner != null) {
            System.out.println("Congratulations! " + this.winner.getName() + " has won the game.");
            return;
        }

        System.out.println("Game is over. Nobody has won the game.");
    }

    private boolean gameHasEnded() {
        return this.board.isFull() || playerHasWon();
    }

    private boolean playerHasWon() {
        if (this.board.checkWin("X")) {
            this.winner = this.player1;
            return true;
        } else if (this.board.checkWin("O")) {
            this.winner = this.player2;
            return true;
        }

        return false;
    }

    private void move(Player player) {
        Tile tile = new Tile();
        Integer x, y;

        do {
            System.out.print(player.getName() + ", please enter the x-coordinate (1-3) > ");
        } while ((x = tile.readCoordinate()) == null);

        do {
            System.out.print(player.getName() + ", please enter the y-coordinate (1-3) > ");
        } while ((y = tile.readCoordinate()) == null);

        tile.setX(x);
        tile.setY(y);

        setTile(tile, player);
    }

    private void setTile(Tile tile, Player player) {
        if ( tile.getX() < 0 || tile.getX() > 2 || tile.getY() < 0 || tile.getY() > 2 || !this.board.cellEmpty(tile.getX(), tile.getY()) ) {
            System.out.println("The entered coordinates are not valid. The next player is up.");
            return;
        }

        tile.setSymbol(player.getSymbol());

        this.board.setTile(tile);
    }

    private Player switchPlayer(Player player) {
        switch (player.getSymbol()) {
            case "X":
                return this.player2;

            case "O":
                return this.player1;
        }

        return this.player1;
    }

    private void restartDialog() {
        System.out.println();
        System.out.print("Would you like to restart the game (write yes/y or no/n)? > ");

        boolean restart;

        try {
            String input = in.readLine();
            restart = input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y");
        } catch (IOException e) {
            System.out.println("Something went wrong processing your answer. The game is not restarted.");
            return;
        }

        if (restart) {
            restart();
        }
    }

    private void restart() {
        System.out.println();
        System.out.println("The game is restarted. Have fun!");

        clearOldGameSettings();
        initBoard();
        start();
    }

    private void clearOldGameSettings() {
        this.board = null;
        this.winner = null;
    }

    private void printEndScreen() {
        System.out.println();
        System.out.println("Thanks for playing. See you!");
    }

}
