import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Lance> lances = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        lances.add(new Lance(500, 500));
        lances.add(new Lance(500, 510));
        lances.add(new Lance(400, 520));
        lances.add(new Lance(300, 400));
        lances.add(new Lance(200, 220));
        lances.add(new Lance(900, 1110));

        int energiaTotal = 1000;

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
                Guloso algoritmoGuloso1 = new Guloso();
                long inicioGuloso1 = System.currentTimeMillis();
                int melhorValorGuloso1 = algoritmoGuloso1.resolverEstrategia1(lances, energiaTotal);
                long fimGuloso1 = System.currentTimeMillis();
                System.out.println("Melhor valor obtido (Guloso 1): " + melhorValorGuloso1);
                System.out.println("Tempo de execução (Guloso 1): " + (fimGuloso1 - inicioGuloso1) + "ms");
                break;
            case 3:
                // Testando algoritmo guloso 2 (melhor razão valor/energia)
                Guloso algoritmoGuloso2 = new Guloso();
                long inicioGuloso2 = System.currentTimeMillis();
                int melhorValorGuloso2 = algoritmoGuloso2.resolverEstrategia2(lances, energiaTotal);
                long fimGuloso2 = System.currentTimeMillis();
                System.out.println("Melhor valor obtido (Guloso 2): " + melhorValorGuloso2);
                System.out.println("Tempo de execução (Guloso 2): " + (fimGuloso2 - inicioGuloso2) + "ms");
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