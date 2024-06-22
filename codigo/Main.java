import java.util.*;

public class Main {
    // Conjunto de empresas interessadas 1;
    static List<Lance> EmpresasInteressadas1(List<Lance> lances){
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
        return lances;
    }
    // Conjunto de empresas interessadas 2;
    static List<Lance> EmpresasInteressadas2(List<Lance> lances){
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
        return lances;
    }
    // Realiza operaçãos com nosso conjunto;
    static void NossoConjunto(){

    }
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            List<Lance> lances = new ArrayList<>();
            Random random = new Random();
            int energiaTotal = 8000; // Quantidade X de energia, medida em megawatts, para vender;
            int opcao1; // Determina o conjunto de empresas interessadas;
            int opcao2; // Determina o algoritmo escolhido;
            // Seleção do Conjunto de empresas interessadas;
            System.out.println("Escolha um conjunto de empresas interessadas:");
            System.out.println("1. Nosso conjunto");
            System.out.println("2. Conjunto 1");
            System.out.println("3. Conjunto 2");
            opcao1 = scanner.nextInt();
            if (opcao1 == 1) {
                NossoConjunto();
                // Criar aqui nosso conjunto;
                System.out.println("Escolha uma opção de algoritmo:");
                System.out.println("1. Backtracking");
                System.out.println("2. Algoritmo guloso 1");
                System.out.println("3. Algoritmo guloso 2");
                System.out.println("4. Divisão e conquista");
                System.out.println("5. Programação dinâmica");
                opcao2 = scanner.nextInt();
                switch (opcao2) {
                    case 1:
                        for (int i = 10; i <= energiaTotal / 100; i++) {
                            lances = new ArrayList<>();
                            for (int j = 0; j < i; j++) {
                                int energia = 100 + random.nextInt(400);
                                int valor = 1000 + random.nextInt(1000);
                                lances.add(new Lance(energia, valor));
                            }

                            Backtracking backtracking = new Backtracking();
                            long inicio = System.currentTimeMillis();
                            backtracking.resolver(lances, energiaTotal);
                            long fim = System.currentTimeMillis();

                            double timeElapsed = (fim - inicio) / 1000.0;
                            System.out
                                    .println("Tempo de execução em segundos para " + i + " lances: " + timeElapsed);

                            // Imprimir o valor total gasto, o valor total de energia obtido e o conjunto de
                            // lances selecionados
                            System.out.println("Valor total gasto: " + backtracking.getMelhorValor());
                            System.out.println("Valor total de energia obtido: "
                                    + backtracking.getEnergiaTotalMelhorCombinacao());
                            System.out.println("Conjunto de lances selecionados:");
                            for (Lance lance : backtracking.getMelhorCombinacao()) {
                                System.out.println("- Energia: " + lance.getEnergia() + " MW, Valor: "
                                        + lance.getValor() + " dinheiros");
                            }

                            if (timeElapsed > 30.0) {
                                System.out.println(
                                        "O algoritmo demorou mais de 30 segundos para resolver o problema com " + i
                                                + " lances.");
                                break;
                            }
                            break;
                        }
                    default:
                        break;
                }
            } else if (opcao1 == 2) {
                // Conjunto de empresas interessadas 1;
                lances = EmpresasInteressadas1(lances);
            } else if (opcao1 == 3) {
                // Conjunto de empresas interessadas 2;
                lances = EmpresasInteressadas2(lances);
            } else {
                System.out.println("Opção inválida");
                scanner.close();
            }

            // Escolha do algoritmo;
            System.out.println("Escolha uma opção de algoritmo:");
            System.out.println("1. Backtracking");
            System.out.println("2. Algoritmo guloso 1");
            System.out.println("3. Algoritmo guloso 2");
            System.out.println("4. Divisão e conquista");
            System.out.println("5. Programação dinâmica");
            opcao2 = scanner.nextInt();
            // Switch para a escolha do Algoritmo;
            switch (opcao2) {
                case 1:
                    // Testando backtracking;
                    Backtracking backtracking = new Backtracking();
                    long inicio = System.currentTimeMillis();
                    backtracking.resolver(lances, energiaTotal);
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
                    // Testando algoritmo guloso 1 (maior valor total);
                    Guloso algoritmoGuloso1 = new Guloso();
                    long inicioGuloso1 = System.currentTimeMillis();
                    int melhorValorGuloso1 = algoritmoGuloso1.resolverEstrategia1(lances, energiaTotal);
                    long fimGuloso1 = System.currentTimeMillis();
                    System.out.println("Melhor valor obtido (Guloso 1): " + melhorValorGuloso1);
                    System.out.println("Tempo de execução (Guloso 1): " + (fimGuloso1 - inicioGuloso1) + "ms");
                    break;
                case 3:
                    // Testando algoritmo guloso 2 (melhor razão valor/energia);
                    Guloso algoritmoGuloso2 = new Guloso();
                    long inicioGuloso2 = System.currentTimeMillis();
                    int melhorValorGuloso2 = algoritmoGuloso2.resolverEstrategia2(lances, energiaTotal);
                    long fimGuloso2 = System.currentTimeMillis();
                    System.out.println("Melhor valor obtido (Guloso 2): " + melhorValorGuloso2);
                    System.out.println("Tempo de execução (Guloso 2): " + (fimGuloso2 - inicioGuloso2) + "ms");
                    break;
                case 4:
                    // Testando divisão e conquista;
                    DivConquista divisaoEConquista = new DivConquista();
                    long inicioDivConquista = System.currentTimeMillis();
                    int melhorValorDivisaoConquista = divisaoEConquista.resolver(lances, energiaTotal);
                    long fimDivConquista = System.currentTimeMillis();
                    System.out.println("Melhor valor obtido (Divisão e Conquista): " + melhorValorDivisaoConquista);
                    System.out.println("Tempo de execução (Divisão e Conquista): " 
                    + (fimDivConquista - inicioDivConquista) + "ms");
                    break;
                case 5:
                    // Testando programação dinâmica;
                    ProgDinamica programacaoDinamica = new ProgDinamica();
                    long inicioProgramacaoDinamica = System.currentTimeMillis();
                    int melhorValorProgramacaoDinamica = programacaoDinamica.resolver(lances, energiaTotal);
                    long fimProgramacaoDinamica = System.currentTimeMillis();
                    System.out.println("Melhor valor obtido (Programação Dinâmica): " + melhorValorProgramacaoDinamica);
                    System.out.println("Tempo de execução (Programação Dinâmica): "
                    + (fimProgramacaoDinamica - inicioProgramacaoDinamica) + "ms");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}