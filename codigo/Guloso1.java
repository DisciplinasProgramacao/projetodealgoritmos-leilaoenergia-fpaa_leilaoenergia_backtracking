import java.util.ArrayList;
import java.util.List;

public class Guloso1 {

    public int resolverEstrategia2(List<Lance> lances, int energiaDisponivel) {
        // Ordenar lances pelo maior valor por megawatt (valor / energia)
        lances.sort((o1, o2) -> Double.compare((double)o2.getValor() / o2.getEnergia(), (double)o1.getValor() / o1.getEnergia()));

    int valorTotal = 0;
        int energiaVendida = 0;

        List<Lance> lancesSelecionados = new ArrayList<>();

        for (Lance lance : lances) {
            if (energiaVendida + lance.getEnergia() <= energiaDisponivel) {
                lancesSelecionados.add(lance);
                energiaVendida += lance.getEnergia();
                valorTotal += lance.getValor();
            }
        }

        // Imprimir os lances selecionados
       imprimirLancesSelecionados1("Maior valor por MW primeiro", lancesSelecionados, 2, energiaDisponivel);
        return valorTotal;
    }

    private void imprimirLancesSelecionados1(String estrategia, List<Lance> lancesSelecionados, int num, int energiaTotal) {
        int valorTotal = 0;
        int energiaTotalGuloso = 0;
        System.out.println("Estratégia: " + estrategia);
        System.out.println("Lances Selecionados:");
        for (Lance lance : lancesSelecionados) {
            System.out.println("  Energia: " + lance.getEnergia() + " MW, Valor: " + lance.getValor() + " reais, Valor por MW: " + ((double)lance.getValor() / lance.getEnergia()));
            energiaTotalGuloso += lance.getEnergia();
            valorTotal += lance.getValor();
        }
        System.out.println("Energia Total Vendida (Guloso " + num + "): " + energiaTotalGuloso + " MW");
        System.out.println("Energia Não Vendida (Guloso " + num + "): " + (energiaTotal - energiaTotalGuloso) + " MW");
        System.out.println("Valor Total Vendido (Guloso " + num + "): " + valorTotal + " reais");
    }
}
