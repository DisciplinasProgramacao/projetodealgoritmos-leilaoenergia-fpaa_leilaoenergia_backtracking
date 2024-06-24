import java.util.ArrayList;
import java.util.List;

public class Guloso {
    public void resolverEstrategia1(List<Lance> lances, int energiaDisponivel) {
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
        //imprimirLancesSelecionados("Maior Valor Total Primeiro", lancesSelecionados, 1, energiaDisponivel);
        System.out.println("Energia: " + energiaVendida + "Valor: " + valorTotal);
        return;
    }

    // Algoritmo guloso 2: Maior Valor por Megawatt Primeiro;
    public void resolverEstrategia2(List<Lance> lances, int energiaDisponivel) {
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
        System.out.println("Energia: " + energiaVendida + "Valor: " + valorTotal);
    }

    private void imprimirLancesSelecionados(String estrategia, List<Lance> lancesSelecionados, int num, int energiaTotal) {
        int energiaTotalGuloso = 0;
        System.out.println("Estratégia: " + estrategia);
        System.out.println("Lances Selecionados:");
        for (Lance lance : lancesSelecionados) {
            System.out.println("  Energia: " + lance.getEnergia() + " MW, Valor: " + lance.getValor() + " reais, Valor por MW: " + ((double)lance.getValor() / lance.getEnergia()));
            energiaTotalGuloso += lance.getEnergia();
        }
        System.out.println("Energia Total Vendida (Guloso " + num + "): " + energiaTotalGuloso + " MW");
        System.out.println("Energia Não Vendida (Guloso " + num + "): " + (energiaTotal - energiaTotalGuloso) + " MW");
    }
}