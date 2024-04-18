package tictactoe;
//Credits to Roger Jaffe, the author of this code.
public class EventLoop {

    // Instance variables for the UI and State classes
    State state = new State();
    UI ui = new UI();
    int row, col;
    
    public static void main(String[] args) {
        EventLoop eventLoop = new EventLoop();
        eventLoop.run();
    }
    
    public void run() {
        while (state.getGameState() != Constants.QUIT_PROGRAM) {
            int gameState = state.getGameState();
            if (gameState == Constants.STANDBY) {
                state.setGameState(Constants.GET_RED_NAME);

            } else if (gameState == Constants.GET_RED_NAME) {
                state.setREDName(ui.promptForName("RED"));
                //I altered the code, replacing what was previously X with RED
                state.setGameState(Constants.GET_YELLOW_NAME);

            } else if (gameState == Constants.GET_YELLOW_NAME) {
                state.setYELLOWName(ui.promptForName("YELLOW"));
                //I altered the code, replacing what was previously O with YELLOW
                state.setGameState(Constants.GET_RED_MOVE);

            } else if (gameState == Constants.GET_RED_MOVE) {
                ui.printBoard(state);
                col = ui.getMoveCol(state.getWhoseMove(), state.getREDName(), state.getYELLOWName());
                row = state.findLowestEmptyRow(col - 1);  // Adjust index to start from 0
                if (row != -1) {
                    state.setGameState(Constants.MAKE_MOVE);
                } else {
                    System.out.println("Column is full. Please choose another column.");
                }

            } else if (gameState == Constants.GET_YELLOW_MOVE) {
                ui.printBoard(state);
                col = ui.getMoveCol(state.getWhoseMove(), state.getREDName(), state.getYELLOWName());
                row = state.findLowestEmptyRow(col - 1);  // Adjust index to start from 0
                if (row != -1) {
                    state.setGameState(Constants.MAKE_MOVE);
                } else {
                    System.out.println("Column is full. Please choose another column.");
                }

            } else if (gameState == Constants.MAKE_MOVE) {
                ui.printMove(state, row, col);
                System.out.println();
                state.setBoardCell(row, col - 1, state.getWhoseMove());  // Adjust index to start from 0
                state.setGameState(Constants.CHECK_IF_WINNER);

            } else if (gameState == Constants.CHECK_IF_WINNER) {
                if (state.isWinner()) {
                    if (state.getWhoseMove() == Constants.RED) {
                        state.setGameState(Constants.RED_WINS);
                    } else {
                        state.setGameState(Constants.YELLOW_WINS);
                    }
                } else {
                    state.setGameState(Constants.CHECK_IF_TIE);
                }

            } else if (gameState == Constants.CHECK_IF_TIE) {
                if (state.isTie()) {
                    ui.printTieGame();
                    state.setGameState(Constants.GAME_OVER);
                } else {
                    state.setWhoseMove(state.getWhoseMove() * -1);
                    if (state.getWhoseMove() == Constants.RED) {
                        state.setGameState(Constants.GET_RED_MOVE);
                    } else {
                        state.setGameState(Constants.GET_YELLOW_MOVE);
                    }
                }

            } else if (gameState == Constants.RED_WINS) {
                ui.printBoard(state);
                ui.printWinner(state);
                state.setGameState(Constants.GAME_OVER);

            } else if (gameState == Constants.YELLOW_WINS) {
                ui.printBoard(state);
                ui.printWinner(state);
                state.setGameState(Constants.GAME_OVER);

            } else if (gameState == Constants.GAME_OVER) {
                if (ui.startNewGame()) {
                    state.resetGame();
                    state.setGameState(Constants.STANDBY);
                } else {
                    state.setGameState(Constants.QUIT_PROGRAM);
                }
            }
        }
    }
}

