import java.util.List;
public class DivConquista {

    public int resolver(List<Lance> lances, int energiaTotal) {
        return divisaoEConquista(lances, energiaTotal);
    }

    private int divisaoEConquista(List<Lance> lances, int energiaRestante) {
        if (energiaRestante == 0 || lances.isEmpty()) {
            return 0;
        }

        Lance lanceAtual = lances.get(0);
        List<Lance> lancesRestantes = lances.subList(1, lances.size());

        int incluirLance = 0;
        if (lanceAtual.getEnergia() <= energiaRestante) {
            incluirLance = lanceAtual.getValor() + divisaoEConquista(lancesRestantes, energiaRestante - lanceAtual.getEnergia());
        }

        int excluirLance = divisaoEConquista(lancesRestantes, energiaRestante);

        return Math.max(incluirLance, excluirLance);
    }
}
