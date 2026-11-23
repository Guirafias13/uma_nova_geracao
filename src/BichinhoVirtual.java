public class BichinhoVirtual {

    // Limites e meta do jogo
    public static final int LIMITE = 100;
    public static final int IDADE_OBJETIVO = 50;
    public static final int HORAS_PARA_DESCANSO_TOTAL = 8;

    // Estado do bichinho (encapsulado: só muda através dos métodos)
    private final String nome;
    private int idade;
    private int fome;
    private int felicidade;
    private int cansaco;
    private int vontadeBanheiro;
    private int sujeira;

    public BichinhoVirtual(String nome) {
        this.nome = nome;
        this.idade = 0;
        this.fome = 50;
        this.felicidade = 50;
        this.cansaco = 0;
        this.vontadeBanheiro = 0;
        this.sujeira = 0;
    }

    // Mantém qualquer medidor entre 0 e o limite
    private static int limitar(int valor) {
        return Math.max(0, Math.min(LIMITE, valor));
    }

    // ---------------- Getters ----------------
    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public int getFome() { return fome; }
    public int getFelicidade() { return felicidade; }
    public int getCansaco() { return cansaco; }
    public int getVontadeBanheiro() { return vontadeBanheiro; }
    public int getSujeira() { return sujeira; }

    // ---------------- Ações ----------------
    public void alimentar() {
        fome = limitar(fome - 10);
        vontadeBanheiro = limitar(vontadeBanheiro + 15);
        System.out.println("🍖 " + nome + " comeu! A fome diminuiu, mas a vontade de ir ao banheiro aumentou.");
    }

    public void brincar() {
        felicidade = limitar(felicidade + 10);
        cansaco = limitar(cansaco + 15);
        sujeira = limitar(sujeira + 15);
        System.out.println("🎾 " + nome + " brincou! Está mais feliz, porém mais cansado e sujo.");
    }

    // De 1 a 8 horas; com 8 horas o cansaço zera
    public void descansar(int horas) {
        horas = Math.max(1, Math.min(HORAS_PARA_DESCANSO_TOTAL, horas));
        int recuperado = LIMITE * horas / HORAS_PARA_DESCANSO_TOTAL;
        cansaco = limitar(cansaco - recuperado);
        if (horas == HORAS_PARA_DESCANSO_TOTAL) {
            System.out.println("😴 " + nome + " dormiu 8 horas e acordou totalmente descansado!");
        } else {
            System.out.println("💤 " + nome + " descansou " + horas + " hora(s) e o cansaço diminuiu.");
        }
    }

    public void irAoBanheiro() {
        vontadeBanheiro = 0;
        System.out.println("🚽 " + nome + " foi ao banheiro e está aliviado.");
    }

    public void tomarBanho() {
        sujeira = 0;
        System.out.println("🛁 " + nome + " tomou banho e está limpinho.");
    }

    // Um ciclo de tempo: fome +3, felicidade -3, cansaço +10, idade +1
    public void passarTempo() {
        fome = limitar(fome + 3);
        felicidade = limitar(felicidade - 3);
        cansaco = limitar(cansaco + 10);
        idade++;
    }

    // ---------------- Status ----------------
    private static String barra(int valor) {
        int cheios = valor / 10;
        return "█".repeat(cheios) + "░".repeat(10 - cheios);
    }

    private void linha(String rotulo, int valor) {
        System.out.printf("%-12s %s %3d/%d%n", rotulo, barra(valor), valor, LIMITE);
    }

    public void verificarStatus() {
        System.out.println("\n===== Status de " + nome + " =====");
        System.out.println("Idade:       " + idade + " (meta: " + IDADE_OBJETIVO + ")");
        linha("Fome", fome);
        linha("Felicidade", felicidade);
        linha("Cansaço", cansaco);
        linha("Banheiro", vontadeBanheiro);
        linha("Sujeira", sujeira);
        System.out.println("==============================");
    }

    // ---------------- Fim de jogo ----------------
    // Devolve o motivo da derrota, ou null se o jogo continua
    public String motivoDerrota() {
        if (fome >= LIMITE) return nome + " passou fome demais!";
        if (cansaco >= LIMITE) return nome + " desmaiou de cansaço!";
        if (felicidade <= 0) return nome + " ficou triste demais!";
        if (vontadeBanheiro >= LIMITE) return nome + " não aguentou segurar a vontade de ir ao banheiro!";
        if (sujeira >= LIMITE) return nome + " ficou sujo demais e adoeceu!";
        return null;
    }

    public boolean ganhou() {
        return idade >= IDADE_OBJETIVO;
    }
}
