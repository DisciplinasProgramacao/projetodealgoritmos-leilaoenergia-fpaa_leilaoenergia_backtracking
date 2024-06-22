import java.util.List;

public class Backtracking {

    public int resolver(List<Lance> lances, int energiaTotal) {
        return backtracking(lances, energiaTotal, 0, 0, 0);
    }

    private int backtracking(List<Lance> lances, int energiaRestante, int valorAtual, int melhorValor,
            int indiceAtual) {
        if (energiaRestante == 0 || indiceAtual >= lances.size()) {
            return Math.max(melhorValor, valorAtual);
        }

        for (int i = indiceAtual; i < lances.size(); i++) {
            Lance lance = lances.get(i);
            if (lance.getEnergia() <= energiaRestante) {
                int valorPossivel = valorAtual + lance.getValor();
                if (valorPossivel > melhorValor) {
                    melhorValor = backtracking(lances, energiaRestante - lance.getEnergia(),
                            valorAtual + lance.getValor(), melhorValor, i + 1);
                }
            }
        }

        return melhorValor;
    }
}