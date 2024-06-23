import java.util.ArrayList;
import java.util.List;

public class ProgDinamica {

    private List<Lance> melhorCombinacao;
    private int melhorValorTotal;

    public ProgDinamica() {
        this.melhorCombinacao = new ArrayList<>();
        this.melhorValorTotal = 0;
    }

    /**
     * Método para resolver o problema utilizando programação dinâmica;
     *
     * @param lances Lista de lances disponíveis;
     * @param energiaTotal Quantidade total de energia disponível para venda;
     * @return Um array onde [0] é o maior valor possível obtido pelas vendas,
     * e [1] é a energia total vendida para alcançar esse valor.
     */
    public int[] resolver(List<Lance> lances, int energiaTotal) {
        int n = lances.size(); // Número total de lances;
        int[][] dp = new int[n + 1][energiaTotal + 1]; // Matriz dp para armazenar os valores máximos;

        // A matriz dp é preenchida usando programação dinâmica;
        for (int i = 1; i <= n; i++) { // Iterando sobre todos os lances;
            for (int w = 0; w <= energiaTotal; w++) { // Iterando sobre todas as quantidades de energia até energiaTotal;
                // Verifica se o lance atual pode ser incluído (energia <= w);
                if (lances.get(i - 1).getEnergia() <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - lances.get(i - 1).getEnergia()] + lances.get(i - 1).getValor());
                } else {
                    // Se a energia do lance atual exceder a capacidade, não incluí-lo;
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Recuperar o valor máximo obtido e a energia total vendida;
        melhorValorTotal = dp[n][energiaTotal];
        melhorCombinacao.clear();

        // Determinar quais lances foram selecionados para atingir o valor máximo;
        int energiaRestante = energiaTotal;
        for (int i = n; i > 0 && melhorValorTotal > 0; i--) {
            if (melhorValorTotal != dp[i - 1][energiaRestante]) {
                // O lance atual (lances.get(i - 1)) foi incluído na solução ótima;
                melhorCombinacao.add(lances.get(i - 1));
                melhorValorTotal -= lances.get(i - 1).getValor();
                energiaRestante -= lances.get(i - 1).getEnergia();
            }
        }

        // Retorna o resultado como um array de inteiros [melhorValorTotal, energiaTotalVendida];
        return new int[] { dp[n][energiaTotal], getEnergiaTotalMelhorCombinacao() };
    }

    /**
     * Imprime a melhor combinação de lances encontrada.
     */
    public void imprimirMelhorCombinacao() {
        System.out.println("\nMelhor Combinação de Lances:");
        if (melhorCombinacao.isEmpty()) {
            System.out.println("Nenhum lance selecionado.");
        } else {
            for (Lance lance : melhorCombinacao) {
                System.out.println("- Energia: " + lance.getEnergia() + " MW, Valor: " + lance.getValor() + " dinheiros");
            }
        }
    }

    /**
     * Retorna a energia total vendida na melhor combinação de lances.
     *
     * @return Energia total vendida na melhor combinação.
     */
    public int getEnergiaTotalMelhorCombinacao() {
        int energiaTotal = 0;
        for (Lance lance : melhorCombinacao) {
            energiaTotal += lance.getEnergia();
        }
        return energiaTotal;
    }
}