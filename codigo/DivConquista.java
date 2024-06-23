import java.util.List;

public class DivConquista {

    /**
     * Resolve o problema de maximização de lucro usando divisão e conquista;
     *
     * @param lances Lista de lances disponíveis;
     * @param energiaTotal Quantidade total de energia disponível para venda;
     * @return Um array onde [0] é o maior valor possível obtido pelas vendas,
     * e [1] é a energia total vendida para alcançar esse valor.
     */
    public int[] resolver(List<Lance> lances, int energiaTotal) {
        return divisaoEConquista(lances, energiaTotal);
    }

    /**
     * Método recursivo que implementa a divisão e conquista;
     *
     * @param lances Lista de lances disponíveis;
     * @param energiaRestante Quantidade de energia restante para venda;
     * @return Um array onde [0] é o maior valor possível obtido pelas vendas,
     * e [1] é a energia total vendida para alcançar esse valor com a energia restante.
     */
    private int[] divisaoEConquista(List<Lance> lances, int energiaRestante) {
        // Caso base: Se não há energia restante ou lances disponíveis, o valor é zero;
        if (energiaRestante == 0 || lances.isEmpty()) {
            return new int[] { 0, 0 };
        }

        // Lance atual (o primeiro da lista);
        Lance lanceAtual = lances.get(0);
        // Lista de lances restantes (exceto o primeiro);
        List<Lance> lancesRestantes = lances.subList(1, lances.size());

        // Valor ao incluir o lance atual, se possível;
        int[] incluirLance = new int[] { 0, 0 };
        if (lanceAtual.getEnergia() <= energiaRestante) {
            incluirLance = divisaoEConquista(lancesRestantes, energiaRestante - lanceAtual.getEnergia());
            incluirLance[0] += lanceAtual.getValor(); // Adiciona o valor do lance atual
            incluirLance[1] += lanceAtual.getEnergia(); // Adiciona a energia vendida pelo lance atual
        }

        // Valor ao excluir o lance atual;
        int[] excluirLance = divisaoEConquista(lancesRestantes, energiaRestante);

        // Retorna o máximo entre incluir ou excluir o lance atual;
        if (incluirLance[0] > excluirLance[0]) {
            return incluirLance;
        } else {
            return excluirLance;
        }
    }
}