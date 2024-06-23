import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// Main para testar os algoritmos com os conjuntos propostos pelo professor;
public class MainProfessor {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int energiaTotalAula = 8000;// Energia sendo vendida (valor proposto pelo professor);
        List<Lance> lances = new ArrayList<>();

        // Escolha o conjunto de empresas interessadas;
        System.out.println("Escolha um conjunto de empresas interessadas: ");
        System.out.println("1. Conjunto 1");
        System.out.println("2. Conjunto 2");
        int opcao1 = scanner.nextInt();
        if(opcao1 == 1){
            // Conjunto de empresas interessadas 1;
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
        }
        else if(opcao1 == 2){
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
            System.exit(0);
        }
        // Escolha do algoritmo;
        System.out.println("Escolha uma opção de algoritmo:");
        System.out.println("1. Backtracking");
        System.out.println("2. Algoritmo guloso 1");
        System.out.println("3. Algoritmo guloso 2");
        System.out.println("4. Divisão e conquista");
        System.out.println("5. Programação dinâmica");
        int opcao2 = scanner.nextInt();
        switch (opcao2) {
            case 1:
                // Testando backtracking;
                System.out.println("Backtracking");
                Backtracking backtrackingAula = new Backtracking();
                long inicioBacktracking = System.currentTimeMillis();
                backtrackingAula.resolver(lances, energiaTotalAula);
                int energiaTotalBacktracking = 0;
                for (Lance lance : backtrackingAula.getMelhorCombinacao()) {
                    energiaTotalBacktracking += lance.getEnergia();
                }
                backtrackingAula.imprimirMelhorCombinacao();
                long fimBacktracking = System.currentTimeMillis();
                double timeBacktracking = (fimBacktracking - inicioBacktracking) / 1000.0;
                // Imprime os valores;
                System.out.println("Energia Total Vendida (Backtracking): " + energiaTotalBacktracking + " MW");
                System.out.println("Energia Não Vendida (Backtracking): " + (energiaTotalAula - energiaTotalBacktracking) + " MW");
                System.out.println("Melhor Valor Obtido (Backtracking): " + backtrackingAula.melhorValorTotal + " dinheiros");
                System.out.println("Tempo de execução (Backtracking): " + timeBacktracking + " segundos\n");
                break;
            case 2:
                // Testando algoritmo guloso 1 (maior valor total);
                System.out.println("Guloso 1");
                Guloso algoritmoGuloso1 = new Guloso();
                long inicioGuloso1Aula = System.currentTimeMillis();
                int melhorValorGuloso1 = algoritmoGuloso1.resolverEstrategia1(lances, energiaTotalAula);
                long fimGuloso1 = System.currentTimeMillis();
                double timeGuloso1 = (fimGuloso1 - inicioGuloso1Aula) / 1000.0;
                // Imprime os valores;
                System.out.println("Melhor valor obtido (Guloso 1): " + melhorValorGuloso1 + " dinheiros");
                System.out.println("Tempo de execução (Guloso 1): " + timeGuloso1 + " segundos\n");
                break;
            case 3:
                // Testando algoritmo guloso 2 (melhor razão valor/energia);
                System.out.println("Guloso 2");
                Guloso algoritmoGuloso2 = new Guloso();
                long inicioGuloso2Aula = System.currentTimeMillis();
                int melhorValorGuloso2 = algoritmoGuloso2.resolverEstrategia2(lances, energiaTotalAula);
                long fimGuloso2 = System.currentTimeMillis();
                double timeGuloso2 = (fimGuloso2 - inicioGuloso2Aula) / 1000.0;
                // Imprime os valores;
                System.out.println("Melhor valor obtido (Guloso 2): " + melhorValorGuloso2 + " dinheiros");
                System.out.println("Tempo de execução (Guloso 2): " + timeGuloso2 + " segundos\n");
                break;
            case 4:
                // Testando divisão e conquista;
                System.out.println("Divisão e Conquista");
                DivConquista divisaoEConquistaAula = new DivConquista();
                long inicioDivConquistaAula = System.currentTimeMillis();
                int[] resultadoDivConquista = divisaoEConquistaAula.resolver(lances, energiaTotalAula);
                long fimDivConquistaAula = System.currentTimeMillis();
                double timeDivConquista = (fimDivConquistaAula - inicioDivConquistaAula) / 1000.0;
                // Imprime os valores;
                System.out.println("Energia Total Vendida (Divisão e Conquista): " + resultadoDivConquista[1] + " MW");
                System.out.println("Energia Não Vendida (Divisão e Conquista): " + (energiaTotalAula - resultadoDivConquista[1]) + " MW");
                System.out.println("Melhor valor obtido (Divisão e Conquista): " + resultadoDivConquista[0] + " dinheiros");
                System.out.println("Tempo de execução (Divisão e Conquista): " + timeDivConquista + " segundos\n");
                break;
            case 5:
                // Testando programação dinâmica;
                System.out.println("Programação Dinâmica");
                ProgDinamica programacaoDinamica = new ProgDinamica();
                long inicioProgramacaoDinamica = System.currentTimeMillis();
                int[] resultadoProgramacaoDinamica = programacaoDinamica.resolver(lances, energiaTotalAula);
                long fimProgramacaoDinamica = System.currentTimeMillis();
                double timeProgramacaoDinamica = (fimProgramacaoDinamica - inicioProgramacaoDinamica) / 1000.0;
                // Imprime os valores;
                programacaoDinamica.imprimirMelhorCombinacao();
                System.out.println("Energia Total Vendida (Programação Dinâmica): " + resultadoProgramacaoDinamica[1] + " MW");
                System.out.println("Energia Não Vendida (Programação Dinâmica): " + (energiaTotalAula - resultadoProgramacaoDinamica[1]) + " MW");
                System.out.println("Melhor valor obtido (Programação Dinâmica): " + resultadoProgramacaoDinamica[0] + " dinheiros");
                System.out.println("Tempo de execução (Programação Dinâmica): " + timeProgramacaoDinamica + " segundos");
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
        scanner.close();
    }
}
