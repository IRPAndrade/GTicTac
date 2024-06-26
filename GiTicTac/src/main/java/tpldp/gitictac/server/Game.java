package tpldp.gitictac.server;

import tpldp.gitictac.game.Move;

import java.util.Arrays;

public class Game {
    private String[][] board = new String[5][5];
    private Player[] players = new Player[2];
    private int currentPlayerIndex = 0;

    public Game() {
        for (String[] row : board) {
            Arrays.fill(row, "");
        }
    }

    public void addPlayer(Player player) {
        for (int i = 0; i < players.length; i++) {
            if (players[i] == null) {
                players[i] = player;
                break;
            }
        }
    }

    public Player[] getPlayers() {
        return players;
    }

    public synchronized void processMove(Move move, Player player) {
        if (players[currentPlayerIndex] != player) {
            return; // Não é a vez desse jogador
        }

        int row = move.getRow();
        int col = move.getCol();

        if (board[row][col].isEmpty()) {
            board[row][col] = move.getPlayer();
            broadcastMove(move);

            if (checkWin(row, col, move.getPlayer())) {
                broadcastMessage("Player " + move.getPlayer() + " wins!");
            } else {
                currentPlayerIndex = (currentPlayerIndex + 1) % 2;
            }
        }
    }

    private void broadcastMove(Move move) {
        for (Player player : players) {
            player.sendMessage(move);
        }
    }

    private void broadcastMessage(String message) {
        for (Player player : players) {
            player.sendMessage(new Move(-1, -1, message));
        }
    }

    private boolean checkWin(int row, int col, String player) {
        //Verificar todas as direções para uma vitória
        return countConsecutive(row, col, 0, 1, player) + countConsecutive(row, col, 0, -1, player) + 1 >= 5 ||
                countConsecutive(row, col, 1, 0, player) + countConsecutive(row, col, -1, 0, player) + 1 >= 5 ||
                countConsecutive(row, col, 1, 1, player) + countConsecutive(row, col, -1, -1, player) + 1 >= 5 ||
                countConsecutive(row, col, 1, -1, player) + countConsecutive(row, col, -1, 1, player) + 1 >= 5;
    }

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

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < 5 && col >= 0 && col < 5;
    }
}

