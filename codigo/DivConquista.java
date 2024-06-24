import java.util.ArrayList;
import java.util.List;

public class DivConquista {

    private List<Lance> melhorCombinacao;
    private int melhorValorTotal;
    private int[][] memo;
    private boolean[][] tomarLance;

    public DivConquista() {
        this.melhorCombinacao = new ArrayList<>();
        this.melhorValorTotal = 0;
    }

    /**
     * Resolve o problema de maximização de lucro usando programação dinâmica.
     *
     * @param lances       Lista de lances disponíveis.
     * @param energiaTotal Quantidade total de energia disponível para venda.
     * @return Um array onde [0] é o maior valor possível obtido pelas vendas,
     * e [1] é a energia total vendida para alcançar esse valor.
     */
    public int[] resolver(List<Lance> lances, int energiaTotal) {
        int n = lances.size();
        memo = new int[n + 1][energiaTotal + 1];
        tomarLance = new boolean[n + 1][energiaTotal + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= energiaTotal; w++) {
                memo[i][w] = -1;
            }
        }

        melhorValorTotal = divisaoEConquista(lances, energiaTotal, 0);
        reconstruirMelhorCombinacao(lances, energiaTotal);
        imprimirMelhorCombinacao();

        int energiaVendida = melhorCombinacao.stream().mapToInt(Lance::getEnergia).sum();
        return new int[]{melhorValorTotal, energiaVendida};
    }

    /**
     * Método recursivo com memoização que implementa a programação dinâmica.
     *
     * @param lances          Lista de lances disponíveis.
     * @param energiaRestante Quantidade de energia restante para venda.
     * @param indiceLance     Índice do lance atual sendo considerado.
     * @return O maior valor total possível obtido com os lances disponíveis.
     */
    private int divisaoEConquista(List<Lance> lances, int energiaRestante, int indiceLance) {
        if (indiceLance == lances.size()) {
            return 0;
        }

        if (memo[indiceLance][energiaRestante] != -1) {
            return memo[indiceLance][energiaRestante];
        }

        Lance lanceAtual = lances.get(indiceLance);
        int incluirLance = 0;
        if (lanceAtual.getEnergia() <= energiaRestante) {
            incluirLance = lanceAtual.getValor() + divisaoEConquista(lances, energiaRestante - lanceAtual.getEnergia(), indiceLance + 1);
        }
        int excluirLance = divisaoEConquista(lances, energiaRestante, indiceLance + 1);

        if (incluirLance > excluirLance) {
            tomarLance[indiceLance][energiaRestante] = true;
            memo[indiceLance][energiaRestante] = incluirLance;
        } else {
            memo[indiceLance][energiaRestante] = excluirLance;
        }

        return memo[indiceLance][energiaRestante];
    }

    /**
     * Reconstrói a melhor combinação de lances com base na matriz tomarLance.
     *
     * @param lances       Lista de lances disponíveis.
     * @param energiaTotal Quantidade total de energia disponível para venda.
     */
    private void reconstruirMelhorCombinacao(List<Lance> lances, int energiaTotal) {
        melhorCombinacao.clear();
        int energiaRestante = energiaTotal;

        for (int i = 0; i < lances.size(); i++) {
            if (tomarLance[i][energiaRestante]) {
                melhorCombinacao.add(lances.get(i));
                energiaRestante -= lances.get(i).getEnergia();
            }
        }
    }

    /**
     * Imprime a melhor combinação de lances encontrada.
     */
    public void imprimirMelhorCombinacao() {
        System.out.println("Lances Selecionados:");
        if (melhorCombinacao.isEmpty()) {
            System.out.println("Nenhum lance selecionado.");
        } else {
            for (Lance lance : melhorCombinacao) {
                System.out.println("- Energia: " + lance.getEnergia() + " MW, Valor: " + lance.getValor() + " dinheiros");
            }
            System.out.println("Valor Total: " + melhorValorTotal);
        }
    }
}
