import java.util.List;

public class DivConquista {

    /**
     * Resolve o problema de maximização de lucro usando divisão e conquista.
     *
     * @param lances Lista de lances disponíveis.
     * @param energiaTotal Quantidade total de energia disponível para venda.
     * @return O maior valor possível obtido pelas vendas.
     */
    public int resolver(List<Lance> lances, int energiaTotal) {
        return divisaoEConquista(lances, energiaTotal);
    }

    /**
     * Método recursivo que implementa a divisão e conquista.
     *
     * @param lances Lista de lances disponíveis.
     * @param energiaRestante Quantidade de energia restante para venda.
     * @return O maior valor possível obtido pelas vendas com a energia restante.
     */
    private int divisaoEConquista(List<Lance> lances, int energiaRestante) {
        // Caso base: Se não há energia restante ou lances disponíveis, o valor é zero
        if (energiaRestante == 0 || lances.isEmpty()) {
            return 0;
        }

        // Lance atual (o primeiro da lista)
        Lance lanceAtual = lances.get(0);
        // Lista de lances restantes (exceto o primeiro)
        List<Lance> lancesRestantes = lances.subList(1, lances.size());

        // Valor ao incluir o lance atual, se possível
        int incluirLance = 0;
        if (lanceAtual.getEnergia() <= energiaRestante) {
            incluirLance = lanceAtual.getValor() + divisaoEConquista(lancesRestantes, energiaRestante - lanceAtual.getEnergia());
        }

        // Valor ao excluir o lance atual
        int excluirLance = divisaoEConquista(lancesRestantes, energiaRestante);

        // Retorna o máximo entre incluir ou excluir o lance atual
        return Math.max(incluirLance, excluirLance);
    }
}