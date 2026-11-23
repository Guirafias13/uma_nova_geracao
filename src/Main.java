import java.util.Scanner;

public class Main {

    private static final Scanner entrada = new Scanner(System.in);

    // Lê um número inteiro; repete até receber um válido.
    // Se a entrada terminar, devolve o valor "sair" para o jogo não travar.
    private static int lerNumero(int valorSeAcabar) {
        while (entrada.hasNextLine()) {
            String texto = entrada.nextLine().trim();
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.print("⚠️  Digite apenas números: ");
            }
        }
        return valorSeAcabar;
    }

    private static void mostrarMenu(String nome) {
        System.out.println("\nO que vamos fazer com " + nome + "?");
        System.out.println("  1 - Alimentar");
        System.out.println("  2 - Brincar");
        System.out.println("  3 - Descansar");
        System.out.println("  4 - Levar ao banheiro");
        System.out.println("  5 - Dar banho");
        System.out.println("  6 - Aguardar um ciclo");
        System.out.println("  0 - Sair");
        System.out.print("Opção: ");
    }

    public static void main(String[] args) {
        System.out.println("🐾 Bem-vindo ao Simulador de Animal de Estimação Virtual!");
        System.out.print("Como vai se chamar o seu bichinho? ");
        String nome = entrada.hasNextLine() ? entrada.nextLine().trim() : "";
        if (nome.isEmpty()) {
            nome = "Bichinho";
        }

        BichinhoVirtual pet = new BichinhoVirtual(nome);
        System.out.println("🎯 Objetivo: fazer " + nome + " chegar à idade "
                + BichinhoVirtual.IDADE_OBJETIVO + " sem deixar nenhum medidor chegar ao limite!");

        boolean jogando = true;
        while (jogando) {
            pet.verificarStatus();
            mostrarMenu(pet.getNome());

            int opcao = lerNumero(0);
            boolean gastouTempo = true;

            switch (opcao) {
                case 1 -> pet.alimentar();
                case 2 -> pet.brincar();
                case 3 -> {
                    System.out.print("Por quantas horas (1 a 8)? Com 8 horas ele fica totalmente descansado: ");
                    pet.descansar(lerNumero(1));
                }
                case 4 -> pet.irAoBanheiro();
                case 5 -> pet.tomarBanho();
                case 6 -> System.out.println("⏳ O tempo passou...");
                case 0 -> {
                    System.out.println("👋 Até a próxima!");
                    jogando = false;
                    gastouTempo = false;
                }
                default -> {
                    System.out.println("⚠️  Opção inválida, tente novamente.");
                    gastouTempo = false;
                }
            }

            if (!gastouTempo) {
                continue;
            }

            pet.passarTempo();

            String derrota = pet.motivoDerrota();
            if (derrota != null) {
                pet.verificarStatus();
                System.out.println("\n💀 Fim de jogo! " + derrota);
                jogando = false;
            } else if (pet.ganhou()) {
                pet.verificarStatus();
                System.out.println("\n🏆 Parabéns! " + nome + " chegou à idade "
                        + pet.getIdade() + " e você venceu!");
                jogando = false;
            }
        }

        entrada.close();
    }
}
