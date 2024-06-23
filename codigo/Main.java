import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Guloso guloso = new Guloso();
        Guloso1 guloso1 = new Guloso1();
        List<List<Lance>> conjuntoT = new ArrayList<>();
        int energiaTotal = 15000;

        // BACKTRACKING
        System.out.println("BACKTRACKING");
        Backtracking backtracking = new Backtracking();
        List<List<Lance>> conjuntoAux = new ArrayList<>();
        double tempoMedioFinal = 0;
        double tempoMedio = 0;
        int tamanhoConjunto = 10;
        int tamanhoConjuntoFinal = 0;  // Armazenar o tamanho do conjunto

        while (true) {
            double tempoTeste = 0;
            conjuntoAux.clear();
            for (int j = 0; j < 10; j++) {
                List<Lance> lancesTeste = gerarConjuntoLances(tamanhoConjunto, random);
                conjuntoAux.add(new ArrayList<>(lancesTeste));
                long inicio = System.currentTimeMillis();
                backtracking.resolver(lancesTeste, energiaTotal);
                long fim = System.currentTimeMillis();
                double tempoDecorrido = (fim - inicio) / 1000.0;
                tempoTeste += tempoDecorrido;
            }
            tempoMedio = tempoTeste / 10;
            if (tempoMedio < 30.0) {
                tamanhoConjunto += 1;
                conjuntoT = new ArrayList<>(conjuntoAux);
                tamanhoConjuntoFinal = tamanhoConjunto - 1; // Salvar o tamanho do conjunto
                tempoMedioFinal = tempoMedio;
            } else {
                System.out.println("Tamanho conjunto: " + tamanhoConjuntoFinal + " Tempo médio: " + tempoMedioFinal);
                for (List<Lance> list : conjuntoT) {
                    backtracking.resolver(list, energiaTotal);
                    System.out.println("Energia: " + backtracking.getEnergiaTotalMelhorCombinacao() + " Valor: " + backtracking.getMelhorValor());
                }
                break; // Sair do loop após encontrar o tamanho adequado
            }
        }

        // GULOSO 1
        System.out.println("GULOSO 1");
        for (int tamanho = tamanhoConjuntoFinal; tamanho <= tamanhoConjuntoFinal * 10; tamanho += tamanhoConjuntoFinal) {
            System.out.println("Tamanho do conjunto de lances: " + tamanho);
            System.out.println("==============================");
            double mediaValor = 0;
            for (int teste = 0; teste < 10; teste++) {
                // Utilizar o mesmo conjunto de lances do tamanho T e aumentar gradualmente
                List<Lance> lancesTeste = gerarConjuntoLances(tamanho, random);
                int valorEstrategia = guloso.resolverEstrategia1(new ArrayList<>(lancesTeste), energiaTotal);
                mediaValor += valorEstrategia;
                System.out.println("Teste " + (teste + 1) + ":");
                System.out.println("Valor obtido: " + valorEstrategia);
                System.out.println();
            }
            mediaValor /= 10;
            System.out.println("Média dos valores obtidos: " + mediaValor);
            System.out.println("==============================");
            System.out.println();
        }

        // GULOSO 2
        System.out.println("GULOSO 2");
        for (int tamanho = tamanhoConjuntoFinal; tamanho <= tamanhoConjuntoFinal * 10; tamanho += tamanhoConjuntoFinal) {
            System.out.println("Tamanho do conjunto de lances: " + tamanho);
            System.out.println("==============================");
            double mediaValor = 0;
            for (int teste = 0; teste < 10; teste++) {
                // Utilizar o mesmo conjunto de lances do tamanho T e aumentar gradualmente
                List<Lance> lancesTeste = gerarConjuntoLances(tamanho, random);
                int valorEstrategia = guloso1.resolverEstrategia2(new ArrayList<>(lancesTeste), energiaTotal);
                mediaValor += valorEstrategia;
                System.out.println("Teste " + (teste + 1) + ":");
                System.out.println("Valor obtido: " + valorEstrategia);
                System.out.println();
            }
            mediaValor /= 10;
            System.out.println("Média dos valores obtidos: " + mediaValor);
            System.out.println("==============================");
            System.out.println();
        }

        // DIVISÃO E CONQUISTA
        System.out.println("DIVISÃO E CONQUISTA");
        DivConquista divisaoEConquista = new DivConquista();
        double tempoMedioDC = 0;
        int[] melhorValorDivisaoConquista = new int[0];
        for (List<Lance> list : conjuntoT) {
            long inicioDivConquista = System.currentTimeMillis();
            melhorValorDivisaoConquista = divisaoEConquista.resolver(list, energiaTotal);
            long fimDivConquista = System.currentTimeMillis();
            tempoMedioDC = (fimDivConquista - inicioDivConquista) / 1000.0;
        }
        System.out.println("Melhor valor obtido (Divisão e Conquista): " + Arrays.toString(melhorValorDivisaoConquista));
        System.out.println("Tempo de execução (Divisão e Conquista): " + tempoMedioDC + "ms");

        // PROGRAMAÇÃO DINAMICA
        System.out.println("PROGRAMAÇÃO DINAMICA");
        double tempoPd = 0;
        for (int tamanho = tamanhoConjuntoFinal; tamanho <= tamanhoConjuntoFinal * 10; tamanho += tamanhoConjuntoFinal) {
            for (int teste = 0; teste < 10; teste++) {
                List<Lance> lancesTeste = gerarConjuntoLances(tamanho, random);
                ProgDinamica pd = new ProgDinamica();
                long inicioPd = System.currentTimeMillis();
                int[] resultado = pd.resolver(lancesTeste, energiaTotal);
                long fimPd = System.currentTimeMillis();
                tempoPd += (fimPd - inicioPd) / 1000.0;
                System.out.println("Energia: " + resultado[1] + " Melhor valor PD: " + resultado[0]);
            }
            System.out.println("Tempo médio para conjunto de " + tamanho + ": " + (tempoPd / 10) + "s");
            tempoPd = 0; // Resetar tempo médio para o próximo tamanho
        }

        scanner.close();
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