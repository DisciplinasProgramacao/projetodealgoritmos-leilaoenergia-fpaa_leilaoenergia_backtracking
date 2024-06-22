import java.util.*;

public class Backtracking {
    public List<Lance> melhorCombinacao;
    public int melhorValorTotal; // Atributo para armazenar o melhor valor total

    public Backtracking() {
        this.melhorCombinacao = new ArrayList<>();
        this.melhorValorTotal = 0;
    }

    public int resolver(List<Lance> lances, int energiaTotal) {
        melhorValorTotal = 0; // Reinicia o melhor valor total a cada chamada
        melhorCombinacao.clear();

        // Ordena os lances por energia (crescente)
        Collections.sort(lances, Comparator.comparingInt(Lance::getEnergia));

        backtracking(lances, energiaTotal, 0, 0, new ArrayList<>());
        return melhorValorTotal;
    }

    private void backtracking(List<Lance> lances, int energiaRestante, int valorAtual, int indiceAtual,
            List<Lance> combinacaoAtual) {
        if (indiceAtual >= lances.size() || energiaRestante < 0) {
            if (valorAtual > melhorValorTotal && energiaRestante >= 0) {
                melhorCombinacao = new ArrayList<>(combinacaoAtual);
                melhorValorTotal = valorAtual;
            }
            return;
        }

        Lance lance = lances.get(indiceAtual);

        // Poda: se a energia do lance atual é maior que a energia restante, não precisa
        // continuar
        if (lance.getEnergia() > energiaRestante) {
            return;
        }

        // Não inclui o lance atual
        backtracking(lances, energiaRestante, valorAtual, indiceAtual + 1, combinacaoAtual);

        // Inclui o lance atual
        combinacaoAtual.add(lance);
        backtracking(lances, energiaRestante - lance.getEnergia(), valorAtual + lance.getValor(), indiceAtual + 1,
                combinacaoAtual);
        combinacaoAtual.remove(combinacaoAtual.size() - 1);
    }

    public void imprimirMelhorCombinacao() {
        System.out.println("\nMelhor Combinação de Lances:");
        if (melhorCombinacao.isEmpty()) {
            System.out.println("Nenhum lance selecionado.");
        } else {
            for (Lance lance : melhorCombinacao) {
                System.out
                        .println("- Energia: " + lance.getEnergia() + " MW, Valor: " + lance.getValor() + " dinheiros");
            }
        }
    }

    public List<Lance> getMelhorCombinacao() {
        return melhorCombinacao;
    }

    public int getEnergiaTotalMelhorCombinacao() {
        int energiaTotal = 0;
        for (Lance lance : melhorCombinacao) {
            energiaTotal += lance.getEnergia();
        }
        return energiaTotal;
    }

    public int getMelhorValor() {
        return melhorValorTotal;
    }
}