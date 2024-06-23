import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Lance> lances = new ArrayList<>();
        Random random = new Random();
        int energiaTotal = 8000; // Quantidade X de energia, medida em megawatts, para vender;
        int opcao1;
        int opcao2;
        int opcao3;
        int opcao4;

        System.out.println("Escolha um conjunto: ");
        System.out.println("1. Nosso conjunto");
        System.out.println("2. Conjunto empresas interessadas");
        opcao1 = scanner.nextInt();
        switch (opcao1) {
            // INICIA AQUI O CASE 1;
            case 1:
                System.out.println("Escolha uma opção de algoritmo:");
                System.out.println("1. Backtracking");
                System.out.println("2. Algoritmo guloso 1");
                System.out.println("3. Algoritmo guloso 2");
                System.out.println("4. Divisão e conquista");
                System.out.println("5. Programação dinâmica");
                opcao2 = scanner.nextInt();
                switch (opcao2) {
                    case 1:
                        // Cria uma nova instância da classe Backtracking
                        Backtracking backtracking = new Backtracking();

                        // Loop externo: começa com 10 lances e incrementa de 1 em 1 até que o tempo
                        // médio de execução ultrapasse 30 segundos
                        for (int i = 10;; i++) {
                            // Variável para armazenar a soma total dos tempos de execução para cada
                            // conjunto de testes
                            double totalTempo = 0.0;

                            // Loop interno: repete o teste 10 vezes para cada tamanho de conjunto
                            for (int k = 0; k < 10; k++) {
                                // Cria uma nova lista de lances
                                lances = new ArrayList<>();

                                // Adiciona 'i' lances à lista, cada um com energia e valor aleatórios
                                for (int j = 0; j < i; j++) {
                                    int energia = 100 + random.nextInt(400);
                                    int valor = 1000 + random.nextInt(1000);
                                    lances.add(new Lance(energia, valor));
                                }

                                // Registra o tempo de início do algoritmo
                                long inicio = System.currentTimeMillis();

                                // Executa o algoritmo
                                backtracking.resolver(lances, energiaTotal);

                                // Registra o tempo de fim do algoritmo
                                long fim = System.currentTimeMillis();

                                // Calcula o tempo de execução em segundos e adiciona ao total
                                double timeElapsed = (fim - inicio) / 1000.0;
                                totalTempo += timeElapsed;
                            }

                            // Calcula a média dos tempos de execução
                            double averageTime = totalTempo / 10;

                            // Imprime o tempo médio de execução
                            System.out.println(
                                    "Tempo médio de execução em segundos para " + i + " lances: " + averageTime);

                            // Se o tempo médio de execução ultrapassou 30 segundos, imprime uma mensagem e
                            // sai do loop externo
                            if (averageTime > 30.0) {
                                System.out.println(
                                        "O algoritmo demorou mais de 30 segundos em média para resolver o problema com "
                                                + i + " lances.");
                                break;
                            }
                            // Imprime os detalhes dos lances selecionados pelo algoritmo
                            System.out.println("Conjunto de lances selecionados:");
                            for (Lance lance : backtracking.getMelhorCombinacao()) {
                                System.out.println("- Energia: " + lance.getEnergia() + " MW, Valor: "
                                        + lance.getValor() + " dinheiros");
                            }

                            // Imprime o valor total gasto e a energia total obtida
                            System.out.println("Valor total gasto: " + backtracking.getMelhorValor());
                            System.out.println("Valor total de energia obtido: "
                                    + backtracking.getEnergiaTotalMelhorCombinacao());

                        }
                    case 2:
                        // Algoritmo guloso 1
                        for (int t = 10; t <= 100; t += 10) {
                            int totalValor = 0;
                            double totalTempo = 0.0;
                            for (int k = 0; k < 10; k++) {
                                // PARA DE CRIAR METODOS!!!!!! FAZ TUDO NO SWITCH
                                // lances = gerarLancesAleatorios(t, random);
                                Guloso algoritmoGuloso1 = new Guloso();
                                long inicioGuloso1 = System.currentTimeMillis();
                                int melhorValorGuloso1 = algoritmoGuloso1.resolverEstrategia1(lances, energiaTotal);
                                long fimGuloso1 = System.currentTimeMillis();
                                totalValor += melhorValorGuloso1;
                                totalTempo += (fimGuloso1 - inicioGuloso1) / 1000.0;
                            }
                            double mediaValor = totalValor / 10.0;
                            double mediaTempo = totalTempo / 10.0;
                            System.out.println("Tamanho do conjunto: " + t);
                            System.out.println("Média do valor obtido (Guloso 1): " + mediaValor);
                            System.out.println("Média do tempo de execução (Guloso 1): " + mediaTempo + "s");
                        }
                        break;
                    case 3:
                        // Algoritmo guloso 2
                        for (int t = 10; t <= 100; t += 10) {
                            int totalValor = 0;
                            double totalTempo = 0.0;
                            for (int k = 0; k < 10; k++) {
                                // PARA DE CRIAR METODOS!!!!!! FAZ TUDO NO SWITCH
                                // lances = gerarLancesAleatorios(t, random);
                                Guloso algoritmoGuloso2 = new Guloso();
                                long inicioGuloso2 = System.currentTimeMillis();
                                int melhorValorGuloso2 = algoritmoGuloso2.resolverEstrategia2(lances, energiaTotal);
                                long fimGuloso2 = System.currentTimeMillis();
                                totalValor += melhorValorGuloso2;
                                totalTempo += (fimGuloso2 - inicioGuloso2) / 1000.0;
                            }
                            double mediaValor = totalValor / 10.0;
                            double mediaTempo = totalTempo / 10.0;
                            System.out.println("Tamanho do conjunto: " + t);
                            System.out.println("Média do valor obtido (Guloso 2): " + mediaValor);
                            System.out.println("Média do tempo de execução (Guloso 2): " + mediaTempo + "s");
                        }
                        break;
                    case 4:
                        /*
                         * // Case 4: Divisão e Conquista
                         * for (int i = 10;; i++) {
                         * double totalTime = 0;
                         * DivConquista divisaoEConquista = new DivConquista();
                         * for (int test = 0; test < 10; test++) {
                         * lances = new ArrayList<>();
                         * for (int j = 0; j < i; j++) {
                         * int energia = 100 + random.nextInt(400);
                         * int valor = 1000 + random.nextInt(1000);
                         * lances.add(new Lance(energia, valor));
                         * }
                         * long inicioDivConquista = System.currentTimeMillis();
                         * divisaoEConquista.resolver(lances, energiaTotal);
                         * long fimDivConquista = System.currentTimeMillis();
                         * totalTime += (fimDivConquista - inicioDivConquista) / 1000.0;
                         * }
                         * double averageTime = totalTime / 10.0;
                         * if (averageTime > 30.0) {
                         * System.out.println(
                         * "O algoritmo demorou mais de 30 segundos para resolver o problema com "
                         * + i + " lances.");
                         * break;
                         * }
                         * System.out.println("Tempo de execução médio em segundos para " + i +
                         * " lances: " + averageTime);
                         * System.out.println("Valor total gasto: " +
                         * divisaoEConquista.getMelhorValor());
                         * System.out.println("Valor total de energia obtido: "
                         * + divisaoEConquista.getEnergiaTotalMelhorCombinacao());
                         * System.out.println("Conjunto de lances selecionados:");
                         * for (Lance lance : divisaoEConquista.getMelhorCombinacao()) {
                         * System.out.println("- Energia: " + lance.getEnergia() + " MW, Valor: "
                         * + lance.getValor() + " dinheiros");}
                         * }
                         */
                        break;
                    case 5:
                        /*
                         * // Case 5: Programação Dinâmica
                         * for (int i = 10;; i++) {
                         * double totalTime = 0;
                         * ProgDinamica programacaoDinamica = new ProgDinamica();
                         * for (int test = 0; test < 10; test++) {
                         * lances = new ArrayList<>();
                         * for (int j = 0; j < i; j++) {
                         * int energia = 100 + random.nextInt(400);
                         * int valor = 1000 + random.nextInt(1000);
                         * lances.add(new Lance(energia, valor));
                         * }
                         * long inicioProgDinamica = System.currentTimeMillis();
                         * programacaoDinamica.resolver(lances, energiaTotal);
                         * long fimProgDinamica = System.currentTimeMillis();
                         * totalTime += (fimProgDinamica - inicioProgDinamica) / 1000.0;
                         * }
                         * double averageTime = totalTime / 10.0;
                         * if (averageTime > 30.0) {
                         * System.out.println(
                         * "O algoritmo demorou mais de 30 segundos para resolver o problema com "
                         * + i + " lances.");
                         * break;}
                         * System.out.println("Tempo de execução médio em segundos para "
                         * + i + " lances: " + averageTime);
                         * System.out.println("Melhor valor obtido (Programação Dinâmica): "
                         * + programacaoDinamica.getMelhorValor());
                         * System.out.println("Tempo de execução total (Programação Dinâmica): "
                         * + (totalTime) + "ms");
                         * System.out.println("Valor total de energia obtido (Programação Dinâmica): "
                         * + programacaoDinamica.getEnergiaTotalMelhorCombinacao());
                         * System.out.println("Conjunto de lances selecionados (Programação Dinâmica):"
                         * );
                         * for (Lance lance : programacaoDinamica.getMelhorCombinacao()) {
                         * System.out.println("- Energia: " + lance.getEnergia() + " MW, Valor: "
                         * + lance.getValor() + " dinheiros");}
                         * }
                         */
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        scanner.close();
                        System.exit(0);
                        break;
                }
            break;
            // FINALIZA AQUI O CASE 1;
            // INICIA AQUI O CASE 2;
            case 2:
                System.out.println("Escolha um conjunto de empresas interessadas: ");
                System.out.println("1. Conjunto 1");
                System.out.println("2. Conjunto 2");
                opcao4 = scanner.nextInt();
                
                // Conjunto de empresas interessadas 1;
                if (opcao4 == 1) {
                    lances.add(new Lance(430, 1043));
                    lances.add(new Lance(428, 1188));
                    lances.add(new Lance(410, 1565));
                    lances.add(new Lance(385, 1333));
                    lances.add(new Lance(399, 1214));
                    lances.add(new Lance(382, 1498));
                    lances.add(new Lance(416, 1540));
                    lances.add(new Lance(436, 1172));
                    lances.add(new Lance(416, 1386));
                    lances.add(new Lance(423, 1097));
                    lances.add(new Lance(400, 1463));
                    lances.add(new Lance(406, 1353));
                    lances.add(new Lance(403, 1568));
                    lances.add(new Lance(390, 1228));
                    lances.add(new Lance(387, 1542));
                    lances.add(new Lance(390, 1206));
                    lances.add(new Lance(430, 1175));
                    lances.add(new Lance(397, 1492));
                    lances.add(new Lance(392, 1293));
                    lances.add(new Lance(393, 1533));
                    lances.add(new Lance(439, 1149));
                    lances.add(new Lance(403, 1277));
                    lances.add(new Lance(415, 1624));
                    lances.add(new Lance(387, 1280));
                    lances.add(new Lance(417, 1330));
                } else if (opcao4 == 2) {
                    // Conjunto de empresas interessadas 2;
                    lances.add(new Lance(313, 1496));
                    lances.add(new Lance(398, 1768));
                    lances.add(new Lance(240, 1210));
                    lances.add(new Lance(433, 2327));
                    lances.add(new Lance(301, 1263));
                    lances.add(new Lance(297, 1499));
                    lances.add(new Lance(232, 1209));
                    lances.add(new Lance(614, 2342));
                    lances.add(new Lance(558, 2983));
                    lances.add(new Lance(495, 2259));
                    lances.add(new Lance(310, 1381));
                    lances.add(new Lance(213, 961));
                    lances.add(new Lance(213, 1115));
                    lances.add(new Lance(346, 1552));
                    lances.add(new Lance(385, 2023));
                    lances.add(new Lance(240, 1234));
                    lances.add(new Lance(483, 2828));
                    lances.add(new Lance(487, 2617));
                    lances.add(new Lance(709, 2328));
                    lances.add(new Lance(358, 1847));
                    lances.add(new Lance(467, 2038));
                    lances.add(new Lance(363, 2007));
                    lances.add(new Lance(279, 1311));
                    lances.add(new Lance(589, 3164));
                    lances.add(new Lance(476, 2480));
                } else {
                    System.out.println("Opção inválida.");
                    scanner.close();
                    System.exit(0);
                    break;
                }
                // Escolha do algoritmo;
                System.out.println("Escolha uma opção de algoritmo:");
                System.out.println("1. Backtracking");
                System.out.println("2. Algoritmo guloso 1");
                System.out.println("3. Algoritmo guloso 2");
                System.out.println("4. Divisão e conquista");
                System.out.println("5. Programação dinâmica");
                opcao3 = scanner.nextInt();
                switch (opcao3) {
                    case 1:
                        // Testando backtracking;
                        Backtracking backtracking = new Backtracking();
                        long inicioBacktracking = System.currentTimeMillis();
                        backtracking.resolver(lances, energiaTotal);
                        int energiaTotalBacktracking = 0;
                        for (Lance lance : backtracking.getMelhorCombinacao()) {
                            energiaTotalBacktracking += lance.getEnergia();
                        }
                        backtracking.imprimirMelhorCombinacao();
                        long fimBacktracking = System.currentTimeMillis();
                        double timeBacktracking = (fimBacktracking - inicioBacktracking) / 1000.0;
                        // Imprime os valores;
                        System.out.println("Energia Total Vendida (Backtracking): " + energiaTotalBacktracking + " MW");
                        System.out.println("Melhor Valor Obtido (Backtracking): " + backtracking.melhorValorTotal + " dinheiros");
                        System.out.println("Tempo de execução (Backtracking): " + timeBacktracking + " segundos");
                        break;
                    case 2:
                        // Testando algoritmo guloso 1 (maior valor total);
                        Guloso algoritmoGuloso1 = new Guloso();
                        long inicioGuloso1 = System.currentTimeMillis();
                        int melhorValorGuloso1 = algoritmoGuloso1.resolverEstrategia1(lances, energiaTotal);
                        long fimGuloso1 = System.currentTimeMillis();
                        double timeGuloso1 = (fimGuloso1 - inicioGuloso1) / 1000.0;
                        // Imprime os valores;
                        System.out.println("Melhor valor obtido (Guloso 1): " + melhorValorGuloso1 + " dinheiros");
                        System.out.println("Tempo de execução (Guloso 1): " + timeGuloso1 + " segundos");
                        break;
                    case 3:
                        // Testando algoritmo guloso 2 (melhor razão valor/energia);
                        Guloso algoritmoGuloso2 = new Guloso();
                        long inicioGuloso2 = System.currentTimeMillis();
                        int melhorValorGuloso2 = algoritmoGuloso2.resolverEstrategia2(lances, energiaTotal);
                        long fimGuloso2 = System.currentTimeMillis();
                        double timeGuloso2 = (fimGuloso2 - inicioGuloso2) / 1000.0;
                        // Imprime os valores;
                        System.out.println("Melhor valor obtido (Guloso 2): " + melhorValorGuloso2 + " dinheiros");
                        System.out.println("Tempo de execução (Guloso 2): " + timeGuloso2 + " segundos");
                        break;
                    case 4:
                        // Testando divisão e conquista;
                        DivConquista divisaoEConquista = new DivConquista();
                        long inicioDivConquista = System.currentTimeMillis();
                        int[] resultadoDivConquista = divisaoEConquista.resolver(lances, energiaTotal);
                        long fimDivConquista = System.currentTimeMillis();
                        double timeDivConquista = (fimDivConquista - inicioDivConquista) / 1000.0;
                        // Imprime os valores;
                        System.out.println("Energia Total Vendida (Divisão e Conquista): " + resultadoDivConquista[1] + " MW");
                        System.out.println("Melhor valor obtido (Divisão e Conquista): " + resultadoDivConquista[0] + " dinheiros");
                        System.out.println("Tempo de execução (Divisão e Conquista): " + timeDivConquista + " segundos");
                        break;
                    case 5:
                        // Testando programação dinâmica;
                        ProgDinamica programacaoDinamica = new ProgDinamica();
                        long inicioProgramacaoDinamica = System.currentTimeMillis();
                        int[] resultadoProgramacaoDinamica = programacaoDinamica.resolver(lances, energiaTotal);
                        long fimProgramacaoDinamica = System.currentTimeMillis();
                        double timeProgramacaoDinamica = (fimProgramacaoDinamica - inicioProgramacaoDinamica) / 1000.0;
                        // Imprime os valores;
                        programacaoDinamica.imprimirMelhorCombinacao();
                        System.out.println("Energia Total Vendida (Programação Dinâmica): " + resultadoProgramacaoDinamica[1] + " MW");
                        System.out.println("Melhor valor obtido (Programação Dinâmica): " + resultadoProgramacaoDinamica[0] + " dinheiros");
                        System.out.println("Tempo de execução (Programação Dinâmica): " + timeProgramacaoDinamica + " segundos");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        scanner.close();
                        System.exit(0);
                        break;
                }
                break;
            default:
                System.out.println("Opção inválida.");
                scanner.close();
                System.exit(0);
                break;
        }
    }
}