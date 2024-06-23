import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Lance> lances = new ArrayList<>();
        Random random = new Random();
        Guloso guloso = new Guloso();
        Guloso1 guloso1 = new Guloso1();

        List<List<Lance>> conjuntoT = new ArrayList<>();

        int opcao1;
        int opcao2;
        int opcao3;
        int opcao4;

        System.out.println("Escolha um conjunto: ");
        System.out.println("1. Nosso conjunto (testes)");
        System.out.println("2. Conjunto empresas interessadas (aula)");
        opcao1 = scanner.nextInt();
        switch (opcao1) {
            case 1:
                System.out.println("Escolha uma opção de algoritmo:");
                System.out.println("1. Backtracking");
                System.out.println("2. Algoritmo guloso 1");
                System.out.println("3. Algoritmo guloso 2");
                System.out.println("4. Divisão e conquista");
                System.out.println("5. Programação dinâmica");
                System.out.println("6. Imprimir conjunto");
                opcao2 = scanner.nextInt();
                int energiaTotal = 15000;
                switch (opcao2) {
                    case 1:
                        Backtracking backtracking = new Backtracking();
                        List<List<Lance>> conjuntoAux = new ArrayList<>();
                        double tempoMedioFinal = 0;
                        double tempoMedio = 0;
                        int tamanhoConjunto = 10;
                        while (true) {
                            double tempoTeste = 0;
                            conjuntoAux.clear();

                            for (int j = 0; j < 10; j++ ) {

                                List<Lance> lancesTeste = new ArrayList<>();
                                for (int i = 0; i < tamanhoConjunto; i++) {
                                    int energia = 100 + random.nextInt(700);
                                    int valor = 1000 + random.nextInt(1200);
                                    lancesTeste.add(new Lance(energia, valor));
                                }

                                conjuntoAux.add(new ArrayList<>(lancesTeste));

                                long inicio = System.currentTimeMillis();
                                backtracking.resolver(lancesTeste, energiaTotal);
                                long fim = System.currentTimeMillis();
                                double tempoDecorrido  = (fim - inicio) / 1000.0;
                                tempoTeste += tempoDecorrido ;
                            }
                            tempoMedio = tempoTeste/10;



                            if (tempoMedio < 30.0) {
                                tamanhoConjunto += 1;
                                conjuntoT = new ArrayList<>(conjuntoAux);
                                tempoMedioFinal = tempoMedio;
                            } else {

                                System.out.println("Tamanho conjunto: " + tamanhoConjunto + " Tempo médio: " + tempoMedioFinal);
                                System.out.println("Conjunto de lances selecionados:");
                                for (Lance lance : backtracking.getMelhorCombinacao()) {
                                    System.out.println("- Energia: " + lance.getEnergia() + " MW, Valor: "
                                            + lance.getValor() + " dinheiros");
                                }
                                System.out.println("Valor total gasto: " + backtracking.getMelhorValor());
                                System.out.println("Valor total de energia obtido: " + backtracking.getEnergiaTotalMelhorCombinacao());
                                break;
                            }
                        }
                        break;
                    case 2:
                        int energiaDisponivel = 8000; // Capacidade de energia fixa para todos os testes
                        long inicioGuloso1 = System.currentTimeMillis();
                        for (int tamanho = 10; tamanho <= 100; tamanho += 10) {
                            System.out.println("Tamanho do conjunto de lances: " + tamanho);
                            System.out.println("==============================");

                            double mediaValor = 0;

                            for (int teste = 0; teste < 10; teste++) {
                                // Gerar conjunto de lances aleatórios
                                lances = gerarConjuntoLances(tamanho, random);

                                // Executar estratégia gulosa 1
                                int valorEstrategia = guloso.resolverEstrategia1(new ArrayList<>(lances), energiaDisponivel);

                                // Somar os valores para cálculo da média
                                mediaValor += valorEstrategia;

                                System.out.println("Teste " + (teste + 1) + ":");
                                System.out.println("Valor obtido: " + valorEstrategia);
                                System.out.println();
                            }

                            // Calcular média dos valores obtidos
                            mediaValor /= 10;
                            long fimGuloso1 = System.currentTimeMillis();
                            System.out.println("Média dos valores obtidos: " + mediaValor);
                            System.out.println("==============================");
                            System.out.println();

                            System.out.println("Tempo de execução (Guloso 1): " + (fimGuloso1 - inicioGuloso1) + "ms");
                        }

                        break;
                    case 3:
                        int energiaDisponivel1 = 8000; // Capacidade de energia fixa para todos os testes
                        long inicioGuloso2 = System.currentTimeMillis();
                        for (int tamanho = 10; tamanho <= 100; tamanho += 10) {
                            System.out.println("Tamanho do conjunto de lances: " + tamanho);
                            System.out.println("==============================");

                            double mediaValor = 0;

                            for (int teste = 0; teste < 10; teste++) {
                                // Gerar conjunto de lances aleatórios
                                lances = gerarConjuntoLances(tamanho, random);

                                // Executar estratégia gulosa 2
                                int valorEstrategia = guloso1.resolverEstrategia2(new ArrayList<>(lances), energiaDisponivel1);

                                // Somar os valores para cálculo da média
                                mediaValor += valorEstrategia;
                                long fimGuloso2 = System.currentTimeMillis();
                                System.out.println("Teste " + (teste + 1) + ":");
                                System.out.println("Valor obtido: " + valorEstrategia);
                                System.out.println();
                            }

                            // Calcular média dos valores obtidos
                            mediaValor /= 10;
                            long fimGuloso2 = System.currentTimeMillis();
                            System.out.println("Média dos valores obtidos: " + mediaValor);
                            System.out.println("==============================");
                            System.out.println();
                            System.out.println("Tempo de execução (Guloso 1): " + (fimGuloso2 - inicioGuloso2) + "ms");
                        }

                        break;

                    case 4:
                        // Testando divisão e conquista;
                        DivConquista divisaoEConquista = new DivConquista();
                        long inicioDivConquista = System.currentTimeMillis();
                        int[] melhorValorDivisaoConquista = divisaoEConquista.resolver(lances, energiaTotal);
                        long fimDivConquista = System.currentTimeMillis();
                        System.out.println(
                                "Melhor valor obtido (Divisão e Conquista): " + melhorValorDivisaoConquista);
                        System.out.println("Tempo de execução (Divisão e Conquista): "
                                + (fimDivConquista - inicioDivConquista) + "ms");

                        break;
                    case 5:

                        break;
                    case 6:
                        if (!conjuntoT.isEmpty()) {
                            for (List<Lance> list : conjuntoT) {
                                for (Lance lance : list) {
                                    System.out.println("- Energia: " + lance.getEnergia() + " MW, Valor: "
                                            + lance.getValor() + " dinheiros");
                                }
                            }
                        }
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        scanner.close();
                        break;
                }
                break;
            case 2:
                System.out.println("Escolha um conjunto de empresas interessadas: ");
                System.out.println("1. Conjunto 1");
                System.out.println("2. Conjunto 2");
                opcao4 = scanner.nextInt();
                int energiaTotalAula = 8000;
                if (opcao4 == 1) {
                    // Conjunto de empresas interessadas 1
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
                    // Conjunto de empresas interessadas 2
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
                        long inicio = System.currentTimeMillis();
                        backtracking.resolver(lances, energiaTotalAula);
                        int energiaTotalVendida = 0;
                        for (Lance lance : backtracking.getMelhorCombinacao()) {
                            energiaTotalVendida += lance.getEnergia();
                        }
                        backtracking.imprimirMelhorCombinacao();
                        long fim = System.currentTimeMillis();
                        System.out.println("Energia Total Vendida: " + energiaTotalVendida + " MW");
                        System.out.println("Melhor Valor Obtido: " + backtracking.melhorValorTotal + " dinheiros");
                        double timeElapsed = (fim - inicio) / 1000.0;
                        System.out.println("Tempo de execução em segundos: " + timeElapsed);

                        break;
                    case 2:
                        // Testando algoritmo guloso 1 (maior valor total)
                        Guloso algoritmoGuloso1 = new Guloso();
                        long inicioGuloso1 = System.currentTimeMillis();
                        int melhorValorGuloso1 = algoritmoGuloso1.resolverEstrategia1(lances, energiaTotalAula);
                        long fimGuloso1 = System.currentTimeMillis();
                        System.out.println("Melhor valor obtido (Guloso 1): " + melhorValorGuloso1);
                        System.out.println("Tempo de execução (Guloso 1): " + (fimGuloso1 - inicioGuloso1) + "ms");
                        break;
                    case 3:
                        // Testando algoritmo guloso 2 (melhor razão valor/energia)
                        long inicioGuloso2 = System.currentTimeMillis();
                        int melhorValorGuloso2 = guloso1.resolverEstrategia2(lances, energiaTotalAula);
                        long fimGuloso2 = System.currentTimeMillis();
                        System.out.println("Melhor valor obtido (Guloso 2): " + melhorValorGuloso2);
                        System.out.println("Tempo de execução (Guloso 2): " + (fimGuloso2 - inicioGuloso2) + "ms");
                        break;
                    case 4:
                        // Testando divisão e conquista;
                        DivConquista divisaoEConquista = new DivConquista();
                        long inicioDivConquista = System.currentTimeMillis();
                        int[] melhorValorDivisaoConquista = divisaoEConquista.resolver(lances, energiaTotalAula);
                        long fimDivConquista = System.currentTimeMillis();
                        System.out.println(
                                "Melhor valor obtido (Divisão e Conquista): " + melhorValorDivisaoConquista);
                        System.out.println("Tempo de execução (Divisão e Conquista): "
                                + (fimDivConquista - inicioDivConquista) + "ms");

                        break;
                    case 5:
                        // Testando programação dinâmica;
                        ProgDinamica programacaoDinamica = new ProgDinamica();
                        long inicioProgramacaoDinamica = System.currentTimeMillis();
                        int[] melhorValorProgramacaoDinamica = programacaoDinamica.resolver(lances, energiaTotalAula);
                        long fimProgramacaoDinamica = System.currentTimeMillis();
                        System.out.println(
                                "Melhor valor obtido (Programação Dinâmica): " + melhorValorProgramacaoDinamica);
                        System.out.println("Tempo de execução (Programação Dinâmica): "
                                + (fimProgramacaoDinamica - inicioProgramacaoDinamica) + "ms");

                        break;

                    default:
                        System.out.println("Opção inválida.");
                        scanner.close();
                        break;
                }
                scanner.close();
        }
    }

    // Função para gerar um conjunto de lances aleatórios
    private static List<Lance> gerarConjuntoLances(int tamanho, Random random) {
        List<Lance> lances = new ArrayList<>();
        for (int i = 0; i < tamanho; i++) {
            int energia = random.nextInt(100) + 1; // Energia entre 1 e 100 MW
            int valor = random.nextInt(2000) + 1; // Valor entre 1 e 2000 reais
            lances.add(new Lance(energia, valor));
        }
        return lances;
    }
}
