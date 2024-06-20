import java.util.List;
public class ProgDinamica {

    public int resolver(List<Lance> lances, int energiaTotal) {
        int n = lances.size();
        int[][] dp = new int[n + 1][energiaTotal + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= energiaTotal; w++) {
                if (lances.get(i - 1).getEnergia() <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - lances.get(i - 1).getEnergia()] + lances.get(i - 1).getValor());
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][energiaTotal];
    }
}
