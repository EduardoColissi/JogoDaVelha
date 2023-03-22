public class JogoDaVelha {

    private char[][] tabuleiro;

    public JogoDaVelha(int tamanho) {
        this.tabuleiro = new char[tamanho][tamanho];
    }

    public boolean realizaJogada(int linha, int coluna, char jogada) {
        if (tabuleiro[linha][coluna] != 0) {
            return false;
        }
        tabuleiro[linha][coluna] = jogada;
        return true;
    }

    public boolean verificaGanhador() {
        for (int i = 0; i < tabuleiro.length; i++) {
            if (tabuleiro[i][0] != 0 && tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][0] == tabuleiro[i][2]) {
                return true;
            }
            if (tabuleiro[0][i] != 0 && tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[0][i] == tabuleiro[2][i]) {
                return true;
            }
        }
        if (tabuleiro[0][0] != 0 && tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[0][0] == tabuleiro[2][2]) {
            return true;
        }
        if (tabuleiro[0][2] != 0 && tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[0][2] == tabuleiro[2][0]) {
            return true;
        }
        return false;
    }

    public boolean tabuleiroCompleto() {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                if (tabuleiro[i][j] == '\u0000') {
                    return false;
                }
            }
        }
        return true;
    }

    public void limpaTabuleiro() {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                tabuleiro[i][j] = 0;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                sb.append(tabuleiro[i][j]);
                if (j != tabuleiro[i].length - 1) {
                    sb.append("|");
                }
            }
            sb.append("\n");
            if (i != tabuleiro.length - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
