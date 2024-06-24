import java.util.*;

// O que foi mudado: a condição de termino sera quando a energia for menor que 0 ou se percorreu o conjunto
// Foi que alterado que antes ele so nao incluia o lance atual, agora ele nao inclui e segue para o proximo
// Finalizando ao incluir o lance atual ele so fazia isso, agora ele inclui se a energia permitir
// Conjunto 1 - Tempo:0.292seg , Valor: 26725
// Conjunto 2 - Tempo:0.337seg , Valor: 40348
// Assim melhorando o algoritmo
// Se caso executar os testes sem ser dos conjuntos 1 e 2, o tempo e provalvemente o conjunto pode vim diferente

public class Backtracking {
    public List<Lance> melhorCombinacao;
    public int melhorValorTotal; // Atributo para armazenar o melhor valor total;

    public Backtracking() {
        this.melhorCombinacao = new ArrayList<>();
        this.melhorValorTotal = 0;
    }

    public int resolver(List<Lance> lances, int energiaTotal) {
        melhorValorTotal = 0; // Reinicia o melhor valor total a cada chamada;
        melhorCombinacao.clear();

        // Ordena os lances por energia (crescente);
        Collections.sort(lances, Comparator.comparingInt(Lance::getEnergia));

        backtracking(lances, energiaTotal, 0, 0, new ArrayList<>());
        return melhorValorTotal;
    }

    private void backtracking(List<Lance> lances, int energiaRestante, int valorAtual, int indiceAtual,
                              List<Lance> combinacaoAtual) {
        // Condição de término: se a energia restante é menor que zero ou se percorreu todos os lances
        if (energiaRestante < 0 || indiceAtual >= lances.size()) {
            if (valorAtual > melhorValorTotal) {
                melhorCombinacao = new ArrayList<>(combinacaoAtual);
                melhorValorTotal = valorAtual;
            }
            return;
        }

        // Não inclui o lance atual e segue para o próximo
        backtracking(lances, energiaRestante, valorAtual, indiceAtual + 1, combinacaoAtual);

        // Inclui o lance atual se a energia permitir
        Lance lance = lances.get(indiceAtual);
        if (energiaRestante >= lance.getEnergia()) {
            combinacaoAtual.add(lance);
            backtracking(lances, energiaRestante - lance.getEnergia(), valorAtual + lance.getValor(), indiceAtual + 1, combinacaoAtual);
            combinacaoAtual.remove(combinacaoAtual.size() - 1); // backtrack
        }
    }

    public void imprimirMelhorCombinacao() {
        System.out.println("Lances Selecionados:");
        if (melhorCombinacao.isEmpty()) {
            System.out.println("Nenhum lance selecionado.");
        } else {
            for (Lance lance : melhorCombinacao) {
                System.out
                        .println("- Energia: " + lance.getEnergia() + " MW, Valor: " + lance.getValor() + " dinheiros");
            }
        }
    }

    // Obter melhor combinação de lances;
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