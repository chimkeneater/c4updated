package tictactoe;

/**
 * Tic-Tac-Toe state variables.
 */
public class State
{
    private int gameState = Constants.STANDBY;
    private int whoseMove = Constants.RED;
    private String REDName = "";
    private String YELLOWName = "";
    private int[][] board = new int[Constants.BOARD_ROW][Constants.BOARD_COLUMNS];

    public boolean isWinner() {
        // Check rows
        for (int row = 0; row < Constants.BOARD_ROW; row++) {
            for (int col = 0; col <= Constants.BOARD_COLUMNS - 4; col++) {
                if (checkConsecutiveFour(row, col, 0, 1)) {
                    return true;
                }
            }
        }

        // Check columns
        for (int row = 0; row <= Constants.BOARD_ROW - 4; row++) {
            for (int col = 0; col < Constants.BOARD_COLUMNS; col++) {
                if (checkConsecutiveFour(row, col, 1, 0)) {
                    return true;
                }
            }
        }

        // Check diagonals
        for (int row = 0; row <= Constants.BOARD_ROW - 4; row++) {
            for (int col = 0; col <= Constants.BOARD_COLUMNS - 4; col++) {
                if (checkConsecutiveFour(row, col, 1, 1) || checkConsecutiveFour(row, col + 3, 1, -1)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkConsecutiveFour(int startRow, int startCol, int rowIncrement, int colIncrement) {
        int player = getBoardCell(startRow, startCol);
        if (player == Constants.BLANK) {
            return false;
        }

        for (int i = 1; i < 4; i++) {
            if (getBoardCell(startRow + i * rowIncrement, startCol + i * colIncrement) != player) {
                return false;
            }
        }

        return true;
    }

    public boolean isTie() {
        for (int row = 0; row < Constants.BOARD_ROW; row++) {
            for (int col = 0; col < Constants.BOARD_COLUMNS; col++) {
                if (getBoardCell(row, col) == Constants.BLANK) {
                    return false; // If there's an empty cell, the game is not a tie
                }
            }
        }
        return true; // All cells are filled, and no player has won
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public int getWhoseMove() {
        return whoseMove;
    }

    public void setWhoseMove(int whoseMove) {
        this.whoseMove = whoseMove;
    }

    public String getREDName() {
        return REDName;
    }

    public void setREDName(String REDName) {
        this.REDName = REDName;
    }

    public String getYELLOWName() {
        return YELLOWName;
    }

    public void setYELLOWName(String YELLOWName) {
        this.YELLOWName = YELLOWName;
    }

    public int getBoardCell(int row, int col) {
        return this.board[row][col];
    }

    public void setBoardCell(int row, int col, int value) {
        this.board[row][col] = value;
    }

    public int findLowestEmptyRow(int col) {
        for (int row = Constants.BOARD_ROW - 1; row >= 0; row--) {
            if (getBoardCell(row, col) == Constants.BLANK) {
                return row;
            }
        }
        return -1;  // Column is full
    }

    public void resetGame() {
        // Clear the board
        for (int i = 0; i < Constants.BOARD_ROW; i++) {
            for (int j = 0; j < Constants.BOARD_COLUMNS; j++) {
                board[i][j] = Constants.BLANK;
            }
        }

        // Reset game-related variables
        gameState = Constants.STANDBY;
        whoseMove = Constants.RED;
        REDName = "";
        YELLOWName = "";
    }

}
