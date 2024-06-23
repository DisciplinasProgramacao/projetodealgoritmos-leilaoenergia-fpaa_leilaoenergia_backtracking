import java.util.List;

public class ProgDinamica {
    // Método para resolver o problema utilizando programação dinâmica;
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
        int melhorValor = dp[n][energiaTotal];
        int energiaTotalVendida = 0;
        int energiaRestante = energiaTotal;
        int Valor = melhorValor;

        // Determinar quais lances foram selecionados para atingir o valor máximo;
        for (int i = n; i > 0 && melhorValor > 0; i--) {
            if (Valor != dp[i - 1][energiaRestante]) {
                // O lance atual (lances.get(i - 1)) foi incluído na solução ótima;
                energiaTotalVendida += lances.get(i - 1).getEnergia();
                Valor -= lances.get(i - 1).getValor();
                energiaRestante -= lances.get(i - 1).getEnergia();
            }
        }

        return new int[] { melhorValor, energiaTotalVendida };
    }
}