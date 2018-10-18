package de.jrcologne;

class Board {

    private Cell[][] board;

    Board() {
        this.board = null;
    }

    Board init() {
        board = new Cell[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new Cell();
            }
        }

        return this;
    }

    void print() {
        System.out.println();
        System.out.println("  1 2 3");

        for (int i = 0; i < 3; i++) {
            System.out.print((i + 1) + " ");

            for (int j = 0; j < 3; j++) {
                System.out.print(this.board[i][j].getValue() + " ");
            }

            System.out.println();
        }
    }

    void setTile(Tile tile) {
        this.board[tile.getY()][tile.getX()].setValue(tile.getSymbol());
    }

    boolean cellEmpty(Integer x, Integer y) {
        return this.board[y][x].empty();
    }

    boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cellEmpty(j, i)) {
                    return false;
                }
            }
        }

        return true;
    }

    boolean checkWin(String symbol) {
        int horizontalCount = 0;
        int verticalCount = 0;

        // check for horizontal/vertical win
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.board[i][j].getValue().equals(symbol)) {
                    horizontalCount++;
                }

                if (this.board[j][i].getValue().equals(symbol)) {
                    verticalCount++;
                }
            }

            if (horizontalCount == 3 || verticalCount == 3) {
                return true;
            }

            horizontalCount = verticalCount = 0;
        }

        // check for diagonal win
        return
            (this.board[0][0].getValue().equals(symbol) && this.board[1][1].getValue().equals(symbol) && this.board[2][2].getValue().equals(symbol))
            ||
            (this.board[0][2].getValue().equals(symbol) && this.board[1][1].getValue().equals(symbol) && this.board[2][0].getValue().equals(symbol))
        ;

    }

}
