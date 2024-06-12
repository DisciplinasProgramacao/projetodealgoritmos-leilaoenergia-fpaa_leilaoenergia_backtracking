import java.util.List;

public class Backtracking {

    public int resolver(List<Lance> lances, int energiaTotal) {
        return backtracking(lances, energiaTotal, 0, 0);
    }

    private int backtracking(List<Lance> lances, int energiaRestante, int valorAtual, int melhorValor) {
        if (energiaRestante == 0) {
            return Math.max(melhorValor, valorAtual);
        }

        for (Lance lance : lances) {
            if (lance.getEnergia() <= energiaRestante) {
                int valorPossivel = valorAtual + lance.getValor();
                if (valorPossivel > melhorValor) {
                    melhorValor = backtracking(lances, energiaRestante - lance.getEnergia(),
                            valorAtual + lance.getValor(), melhorValor);
                }
            }
        }

        return melhorValor;
    }
}