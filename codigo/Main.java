import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Lance> lances = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
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

        int energiaTotal = 8000;

        System.out.println("Escolha uma opção:");
        System.out.println("1. Backtracking");
        System.out.println("2. Algoritmo guloso 1");
        System.out.println("3. Algoritmo guloso 2");
        System.out.println("4. Divisão e conquista");
        System.out.println("5. Programação dinâmica");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                // Testando backtracking
                Backtracking backtracking = new Backtracking();
                long inicioBacktracking = System.currentTimeMillis();
                int melhorValorBacktracking = backtracking.resolver(lances, energiaTotal);
                long fimBacktracking = System.currentTimeMillis();
                System.out.println("Melhor valor obtido (Backtracking): " + melhorValorBacktracking);
                System.out
                        .println("Tempo de execução (Backtracking): " + (fimBacktracking - inicioBacktracking) + "ms");
                break;
            case 2:
                // Testando algoritmo guloso 1 (maior valor)
//                Guloso algoritmoGuloso1 = new Guloso();
//                long inicioGuloso1 = System.currentTimeMillis();
//                int melhorValorGuloso1 = algoritmoGuloso1.resolverEstrategia1(lances, energiaTotal);
//                long fimGuloso1 = System.currentTimeMillis();
//                System.out.println("Melhor valor obtido (Guloso 1): " + melhorValorGuloso1);
//                System.out.println("Tempo de execução (Guloso 1): " + (fimGuloso1 - inicioGuloso1) + "ms");
                break;
            case 3:
                // Testando algoritmo guloso 2 (melhor razão valor/energia)
//                Guloso algoritmoGuloso2 = new Guloso();
//                long inicioGuloso2 = System.currentTimeMillis();
//                int melhorValorGuloso2 = algoritmoGuloso2.resolverEstrategia2(lances, energiaTotal);
//                long fimGuloso2 = System.currentTimeMillis();
//                System.out.println("Melhor valor obtido (Guloso 2): " + melhorValorGuloso2);
//                System.out.println("Tempo de execução (Guloso 2): " + (fimGuloso2 - inicioGuloso2) + "ms");
                break;
            case 4:
                // Testando divisão e conquista
                DivConquista divisaoEConquista = new DivConquista();
                long inicioDivConquista = System.currentTimeMillis();
                int melhorValorDivisaoConquista = divisaoEConquista.resolver(lances, energiaTotal);
                long fimDivConquista = System.currentTimeMillis();
                System.out.println("Melhor valor obtido (Divisão e Conquista): " + melhorValorDivisaoConquista);
                System.out.println(
                        "Tempo de execução (Divisão e Conquista): " + (fimDivConquista - inicioDivConquista) + "ms");
                break;
            case 5:
                // Testando programação dinâmica
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