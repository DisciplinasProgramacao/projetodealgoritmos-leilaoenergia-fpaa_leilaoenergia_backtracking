import java.util.List;
import java.util.ArrayList;

public class Backtracking {
    private List<Lance> melhorCombinacao;

    public int resolver(List<Lance> lances, int energiaTotal) {
        melhorCombinacao = new ArrayList<>();
        return backtracking(lances, energiaTotal, 0, 0, 0, new ArrayList<>());
    }

    private int backtracking(List<Lance> lances, int energiaRestante, int valorAtual, int melhorValor,
            int indiceAtual, List<Lance> combinacaoAtual) {
        if (indiceAtual >= lances.size() || energiaRestante < 0) {
            if (valorAtual > melhorValor && energiaRestante >= 0) {
                melhorCombinacao = new ArrayList<>(combinacaoAtual);
                melhorValor = valorAtual;
            }
            return melhorValor;
        }

        // Não inclui o lance atual
        melhorValor = backtracking(lances, energiaRestante, valorAtual, melhorValor, indiceAtual + 1,
                combinacaoAtual);

        Lance lance = lances.get(indiceAtual);

        // Inclui o lance atual se a energia permitir
        if (lance.getEnergia() <= energiaRestante) {
            combinacaoAtual.add(lance);
            melhorValor = backtracking(lances, energiaRestante - lance.getEnergia(),
                    valorAtual + lance.getValor(), melhorValor, indiceAtual + 1, combinacaoAtual);
            combinacaoAtual.remove(combinacaoAtual.size() - 1);
        }

        return melhorValor;
    }

    public void imprimirMelhorCombinacao() {
        System.out.println("\nMelhor Combinação de Lances:");
        if (melhorCombinacao.isEmpty()) {
            System.out.println("Nenhum lance selecionado.");
        } else {
            for (Lance lance : melhorCombinacao) {
                System.out.println("- Energia: " + lance.getEnergia() + ", Valor: " + lance.getValor());
            }
        }
    }
}
