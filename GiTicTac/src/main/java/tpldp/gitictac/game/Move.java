package tpldp.gitictac.game;

import java.io.Serializable;

/**
 * Contém todos os atributos de uma jogada.
 */
public class Move implements Serializable {
    private int row;
    private int col;
    private String player;

    public Move(int row, int col, String player) {
        this.row = row;
        this.col = col;
        this.player = player;
    }

    /**
     * Retorna a linha onde o movimento foi feito.
     * @return
     */
    public int getRow() {
        return row;
    }

    /**
     * Retorna a coluna onde o movimento foi feito.
     * @return
     */
    public int getCol() {
        return col;
    }

    /**
     * Retorna o jogador que fez o movimento ou uma mensagem de empate ou vitoria.
     * @return
     */
    public String getMessage() {
        return player;
    }
}
