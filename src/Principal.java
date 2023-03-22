import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do primeiro jogador: ");
        Jogador jogador1 = new Jogador(scanner.nextLine());

        System.out.print("Digite o nome do segundo jogador: ");
        Jogador jogador2 = new Jogador(scanner.nextLine());

        System.out.print("Digite o tamanho do tabuleiro: ");
        int tamanho = scanner.nextInt();
        scanner.nextLine();

        JogoDaVelha jogo = new JogoDaVelha(tamanho);

        Jogador jogadorAtual = jogador1;
        char simboloAtual = 'X';
        boolean jogando = true;

        while (jogando) {
            System.out.println(jogo);

            System.out.printf("É a vez de %s (%s)%n", jogadorAtual.getNome(), simboloAtual);
            System.out.print("Digite a linha da jogada: ");
            int linha = scanner.nextInt();

            System.out.print("Digite a coluna da jogada: ");
            int coluna = scanner.nextInt();

            boolean jogadaValida = jogo.realizaJogada((linha -1), (coluna -1), simboloAtual);

            if (!jogadaValida) {
                System.out.println("Jogada inválida. Tente novamente.");
            } else {

                boolean vitoria = jogo.verificaGanhador();

                if (vitoria) {
                    jogadorAtual.incrementaPontos();
                    System.out.printf("Parabéns, %s! Você venceu o jogo.%n", jogadorAtual.getNome());
                    System.out.print("Desejam jogar novamente? (S/N) ");
                    String resposta = scanner.next();
                    if (resposta.equalsIgnoreCase("N")) {
                        jogando = false;
                    } else {
                        jogo.limpaTabuleiro();
                    }
                } else if (jogo.tabuleiroCompleto()) {
                    System.out.println("Empate!");
                } else {
                    jogadorAtual = (jogadorAtual == jogador1) ? jogador2 : jogador1;
                    simboloAtual = (simboloAtual == 'X') ? 'O' : 'X';
                }
            }

        }

        if (jogador1.getPontos() > jogador2.getPontos()) {
            Jogador vencedor = jogador1;
            try {
                PrintWriter writer = new PrintWriter(new File("resultado.txt"));

                writer.printf("O vencedor é %s, com %d pontos.%n", vencedor.getNome(), vencedor.getPontos());

                writer.close();
            } catch (FileNotFoundException e) {
                System.out.println("Erro ao criar arquivo de resultado.");
            }
        } else if (jogador2.getPontos() > jogador1.getPontos()) {
            Jogador vencedor = jogador2;
            try {
                PrintWriter writer = new PrintWriter(new File("resultado.txt"));

                writer.printf("O vencedor é %s, com %d pontos.%n", vencedor.getNome(), vencedor.getPontos());

                writer.close();
            } catch (FileNotFoundException e) {
                System.out.println("Erro ao criar arquivo de resultado.");
            }
        } else {
            try {
                PrintWriter writer = new PrintWriter(new File("resultado.txt"));

                writer.printf("Houve empate!");

                writer.close();
            } catch (FileNotFoundException e) {
                System.out.println("Erro ao criar arquivo de resultado.");
            }
        }



        scanner.close();
    }
}

