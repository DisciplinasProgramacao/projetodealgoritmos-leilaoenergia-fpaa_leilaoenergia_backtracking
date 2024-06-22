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

    // Algoritmo guloso 2: Maior Valor por Megawatt Primeiro;
    public int resolverEstrategia2(List<Lance> lances, int energiaDisponivel) {
        // Ordenar lances pelo maior valor por megawatt (valor / energia);
        lances.sort((o1, o2) -> Double.compare((double)o2.getValor() / o2.getEnergia(), (double)o1.getValor() / o1.getEnergia()));

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
        imprimirLancesSelecionados("Maior Valor por Megawatt Primeiro", lancesSelecionados);
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
