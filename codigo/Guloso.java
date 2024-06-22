import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class Guloso {
    public int resolverEstrategia1(List<Lance> lances, int energiaDisponivel) {
        // Ordenar lances pelo maior valor total oferecido (valor);
        lances.sort((o1, o2) -> Integer.compare(o2.getValor(), o1.getValor()));

        int energiaVendida = 0;
        int valorTotal = 0;
        List<Lance> lancesSelecionados = new ArrayList<>();

        for (Lance lance : lances) {
            if (energiaVendida + lance.getEnergia() <= energiaDisponivel) {
                lancesSelecionados.add(lance);
                energiaVendida += lance.getEnergia();
                valorTotal += lance.getValor();
            }
        }

        // Imprimir os lances selecionados;
        imprimirLancesSelecionados("Maior Valor Total Primeiro", lancesSelecionados);
        return valorTotal;
    }

    private void imprimirLancesSelecionados(String estrategia, List<Lance> lancesSelecionados) {
        System.out.println("Estratégia: " + estrategia);
        System.out.println("Lances Selecionados:");
        for (Lance lance : lancesSelecionados) {
            System.out.println("  Energia: " + lance.getEnergia() + " MW, Valor: " + lance.getValor() + " reais, Valor por MW: " + ((double)lance.getValor() / lance.getEnergia()));
        }
        System.out.println();
    }
}
