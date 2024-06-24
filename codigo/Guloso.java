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
       imprimirLancesSelecionados("Maior valor total primeiro", lancesSelecionados,  energiaDisponivel);
        return;
    }



    private void imprimirLancesSelecionados(String estrategia, List<Lance> lancesSelecionados, int energiaTotal) {
        int valorTotal = 0;
        int energiaTotalGuloso = 0;
        System.out.println("Estratégia: " + estrategia);
        System.out.println("Lances Selecionados:");
        for (Lance lance : lancesSelecionados) {
            System.out.println("  Energia: " + lance.getEnergia() + " MW, Valor: " + lance.getValor() + " reais, Valor por MW: " + ((double)lance.getValor() / lance.getEnergia()));
            energiaTotalGuloso += lance.getEnergia();
            valorTotal += lance.getValor();

        }
        System.out.println("Energia Total Vendida (Guloso " + 1 + "): " + energiaTotalGuloso + " MW");
        System.out.println("Energia Não Vendida (Guloso " + 1 + "): " + (energiaTotal - energiaTotalGuloso) + " MW");
        System.out.println("Valor Total Vendido (Guloso " + 2 + "): " + valorTotal + " reais");
    }
}