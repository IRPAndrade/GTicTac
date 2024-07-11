package tpldp.gitictac.server;

import tpldp.gitictac.game.Move;
import tpldp.gitictac.utils.server.ServerUtils;

import java.util.Arrays;

/**
 * Contém toda a lógica do jogo
 */
public class Game {
    private String[][] board = new String[ServerUtils.boardSize][ServerUtils.boardSize];
    private Player[] players = new Player[2];
    private int currentPlayerIndex;

    public Game() {
        for (String[] row : board) {
            Arrays.fill(row, "");
        }
    }

    /**
     * Adiciona um jogador ao array de jogadores.
     * @param player
     */
    public void addPlayer(Player player) {
        for (int i = 0; i < players.length; i++) {
            if (players[i] == null) {
                players[i] = player;

                break;
            }
        }
    }

    /**
     * Retorna os jogadores no array de jogadores.
     * @return
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Recebe um movimento e faz o seu processamento, verificando as condições de empate e/ou vitória.
     * @param move
     * @param player
     */
    public synchronized void processMove(Move move, Player player) {
        if (players[currentPlayerIndex] != player) {
            return; // Não é a vez desse jogador
        }

        int row = move.getRow();
        int col = move.getCol();

        if (board[row][col].isEmpty()) {
            board[row][col] = move.getMessage();
            broadcastMove(move);
            System.out.println("Broadcast Move");

            if(checkDraw()){
                broadcastMessage("Empate");
                System.out.println("Draw");
                resetGame();
            } else if (checkWin(row, col, move.getMessage())) {
                broadcastMessage("Jogador " + move.getMessage() + " venceu!");
                System.out.println("Player " + move.getMessage() + " wins!");
                resetGame();
            } else {
                currentPlayerIndex = (currentPlayerIndex + 1) % 2;
            }
        }
    }

    /**
     * Verifica se existe empate.
     * @return
     */
    private boolean checkDraw() {
        for (String[] row : board) {
            for (String s : row) {
                if(s.equals("")) {
                return false;
                }
            }
        }
        return true;
    }

    /**
     * Dá reset a todo o tabuleiro.
     */
    private void resetGame() {
            for (String[] row : board) {
                Arrays.fill(row, "");
            }
        currentPlayerIndex = (currentPlayerIndex + 1) % 2;
    }

    /**
     * Envia a jogada feita para os jogadores.
     * @param move
     */
    private void broadcastMove(Move move) {
        for (Player player : players) {
            player.sendMessage(move);
        }
    }

    /**
     * Envia uma mensagem para os jogadores.
     * @param message
     */
    private void broadcastMessage(String message) {
        for (Player player : players) {
            player.sendMessage(new Move(-1, -1, message));
        }
    }

    /**
     * Verifica as condições de vitória.
     * @param row
     * @param col
     * @param player
     * @return
     */
    private boolean checkWin(int row, int col, String player) {
        //Verificar todas as direções para uma vitória
        return countConsecutive(row, col, 0, 1, player) + countConsecutive(row, col, 0, -1, player) + 1 >= 4 ||
                countConsecutive(row, col, 1, 0, player) + countConsecutive(row, col, -1, 0, player) + 1 >= 4 ||
                countConsecutive(row, col, 1, 1, player) + countConsecutive(row, col, -1, -1, player) + 1 >= 4 ||
                countConsecutive(row, col, 1, -1, player) + countConsecutive(row, col, -1, 1, player) + 1 >= 4;
    }

    /**
     * Conta o número de peças iguais que estão seguidas no tabuleiro.
     * @param row
     * @param col
     * @param rowIncrement
     * @param colIncrement
     * @param player
     * @return
     */
    private int countConsecutive(int row, int col, int rowIncrement, int colIncrement, String player) {
        int count = 0;
        int newRow = row + rowIncrement;
        int newCol = col + colIncrement;

        while (isValidPosition(newRow, newCol) && board[newRow][newCol].equals(player)) {
            count++;
            newRow += rowIncrement;
            newCol += colIncrement;
        }

        return count;
    }

    /**
     * Verifica se a posição está dentro do tabuleiro.
     * @param row
     * @param col
     * @return
     */
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < 5 && col >= 0 && col < 5;
    }

    /**
     * Define o indice dos jogadores, sendo isso usado para controlar os turnos.
     * @param currentPlayerIndex
     */
    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }
}

