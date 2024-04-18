package tictactoe;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * UI class
 */
public class UI
{

    Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);         
    }

    // Utility methods
    public String getXOrO(int whoseMove) {
        if (whoseMove == -1) {
            return "R";
        } else if (whoseMove == 1) {
            return "Y";
        } else {
            return " ";
        }
    }

    public String getPlayerName(int whoseMove, String REDName, String YELLOWName) {
        return (whoseMove == -1) ? REDName : YELLOWName;
    }

    public boolean isLegalMove(State state, int row, int col) {
        return 1 <= row && row <= Constants.BOARD_ROW &&
        1 <= col && col <= Constants.BOARD_COLUMNS &&
        state.getBoardCell(row - 1, col - 1) == Constants.BLANK;
    }

    // Prompt for input methods
    public String promptForName(String player) {
        System.out.printf(Constants.GET_PLAYER_NAME, player);
        return scanner.next();
    }

    public int getMoveRow(int whoseMove, String REDName, String YELLOWName) {

        return 0;
    }

    public int getMoveCol(int whoseMove, String REDName, String YELLOWName) {
        int col = 0;
        System.out.printf(Constants.GET_COL_MOVE, getXOrO(whoseMove), getPlayerName(whoseMove, REDName, YELLOWName));
        try {
            col = scanner.nextInt();
            if (col <= 0 || col > Constants.BOARD_COLUMNS) {
                printInvalidColumn();
                System.out.println();
                scanner.nextLine();
                col = getMoveCol(whoseMove, REDName, YELLOWName);
            }
        } catch (InputMismatchException error) {
            printInvalidColumn();
            System.out.println();
            scanner.nextLine();
            getMoveCol(whoseMove, REDName, YELLOWName);
        }
        return col;
    }

    private void printInvalidColumn() {
        System.out.println(Constants.INVALID_ROW_OR_COLUMN);
    }

    public boolean startNewGame() {
        System.out.println(Constants.START_NEW_GAME);
        String yesOrNo = scanner.next();
        return yesOrNo.equals("Y") || yesOrNo.equals("y");
    }
    // Printing text methods
    public void printWelcome() {
        System.out.println(Constants.TITLE);
    }

    public void printBoard(State state) {
        System.out.println(Constants.DIVIDER_STRING);
        for (int row = 0; row < Constants.BOARD_ROW; row++) {
            for (int col = 0; col < Constants.BOARD_COLUMNS; col++) {
                System.out.printf("| %s ", getXOrO(state.getBoardCell(row, col)));
            }
            System.out.println("|");
            System.out.println(Constants.DIVIDER_STRING);
        }
    }

    public void printInvalidRowOrColumn() {
        System.out.printf(Constants.INVALID_ROW_OR_COLUMN);
    }

    public void printInvalidMove(int row, int col) {
        System.out.printf(Constants.INVALID_MOVE_ERROR, row, col);
    }

    public void printMove(State state, int row, int col) {
        System.out.printf(
            Constants.PRINT_MOVE, 
            getXOrO(state.getWhoseMove()), 
            getPlayerName(state.getWhoseMove(), state.getREDName(), state.getYELLOWName()), 
            row, 
            col
        );
        System.out.println();
    } 

    public void printWinner(State state) {
        System.out.printf
        (Constants.WINNER, 
            getXOrO(state.getWhoseMove()), 
            getPlayerName(state.getWhoseMove(), state.getREDName(), state.getYELLOWName())
        );
        System.out.println();
    }

    public void printTieGame() {
        System.out.println(Constants.TIE_GAME);
    }
}

