# Simulador de Animal de Estimação Virtual

Projeto em Java para praticar Programação Orientada a Objetos (POO). Você cuida de um bichinho virtual alimentando, brincando, deixando descansar e mantendo ele limpo, com o objetivo de fazê-lo chegar à **idade 50**.

## Estrutura do projeto

```
Java-bichinho/
├── .idea/                     # Configuração do IntelliJ IDEA
├── src/
│   ├── BichinhoVirtual.java   # Classe do pet (estado e comportamentos)
│   └── Main.java              # Menu interativo e loop do jogo
├── .gitignore
├── Horadecodar5.iml
└── README.md
```

## Como executar

Requisito: JDK 17 ou superior.

Pelo terminal, na pasta do projeto:

```bash
javac -encoding UTF-8 -d out src/*.java
java -cp out Main
```

Ou abra a pasta no IntelliJ IDEA (ou no VS Code com o *Extension Pack for Java*) e execute o `Main`. Digite as respostas no terminal do editor.

> O jogo roda no terminal. O "Go Live" do VS Code serve apenas páginas HTML e não executa Java.

## Como jogar

Ao iniciar, dê um nome ao seu pet. A cada turno o jogo mostra o status (com barras) e o menu:

| Opção | Ação | Efeito |
|-------|------|--------|
| 1 | Alimentar | Fome -10 e vontade de ir ao banheiro +15 |
| 2 | Brincar | Felicidade +10, cansaço +15 e sujeira +15 |
| 3 | Descansar | Pede as horas (1 a 8); com 8 horas o cansaço zera |
| 4 | Levar ao banheiro | Zera a vontade de ir ao banheiro |
| 5 | Dar banho | Zera a sujeira |
| 6 | Aguardar | Apenas deixa um ciclo de tempo passar |
| 0 | Sair | Encerra o jogo |

Cada ação de 1 a 6 gasta **um ciclo de tempo**. Opção inválida não gasta tempo.

## Passagem do tempo (por ciclo)

- Fome: **+3**
- Felicidade: **-3**
- Cansaço: **+10**
- Idade: **+1**

## Regras

**Vitória:** o pet chegar à idade **50**.

**Derrota:** qualquer uma das condições abaixo.

- Fome chegar a 100
- Cansaço chegar a 100
- Felicidade chegar a 0
- Vontade de ir ao banheiro chegar a 100 (desafio extra)
- Sujeira chegar a 100 (desafio extra)

Todos os medidores ficam sempre entre 0 e 100.

## Conceitos de POO praticados

- **Classe e objeto:** `BichinhoVirtual` modela o pet e `Main` cria a instância.
- **Encapsulamento:** os atributos são `private` e só mudam pelos métodos da classe; o `Main` lê os valores por *getters*.
- **Construtor:** inicializa o nome e os valores iniciais do pet.
- **Constantes:** limites e meta do jogo (`LIMITE`, `IDADE_OBJETIVO`) em um único lugar.
- **Responsabilidade única:** as regras do pet ficam na classe do pet; o `Main` cuida só da interação com o jogador.

## Desafios extras implementados

- [x] `descansar` pede por quantas horas o pet vai descansar (8 horas = totalmente descansado)
- [x] Medidor de vontade de ir ao banheiro (aumenta ao alimentar)
- [x] Medidor de sujeira (aumenta ao brincar)
- [x] Limites para os novos medidores: ultrapassar causa derrota
