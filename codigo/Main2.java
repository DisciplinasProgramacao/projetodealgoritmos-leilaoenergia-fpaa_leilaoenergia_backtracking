import java.util.*;
public class Main2 {
    static final int energiaTotal = 8000; // Quantidade X de energia, medida em megawatts, para vender;
    //**************************************************************************************/
    /*
     * Gerar conjuntos de teste de tamanho crescente, a partir de 10 interessadas e incrementando de 1 em 1,
     * até atingir um tamanho T que não consiga ser resolvido em até 30 segundos pelo algoritmo.
     * Na busca do tempo limite de 30 segundos, faça o teste com 10 conjuntos de cada tamanho,
     * contabilizando a média das execuções.
     */
    static List<List<Lance>> preencherBacktracking() {
        List<List<Lance>> conjuntosDeLances = new ArrayList<>();
        int tamanho = 10; // Começando com 10 interessadas;
        long maxTempo = 30 * 1000; // 30 segundos;
        long tempoTotal;
        while (true) {
            tempoTotal = 0;
            for (int i = 0; i < 10; i++) {
                List<Lance> conjuntoTeste = gerarConjuntoTeste(tamanho);
                long tempoInicio = System.currentTimeMillis();
                // Simulação de execução - substituir pela chamada do método real;
                simularExecucao(conjuntoTeste, 8000);
                long tempoFim = System.currentTimeMillis();
                tempoTotal += (tempoFim - tempoInicio);
            }
            long tempoMedio = tempoTotal / 10;
            if (tempoMedio > maxTempo) {
                break;
            } else {
                // Adiciona o conjunto somente se ainda estiver dentro do limite de tempo;
                List<Lance> conjuntoValido = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    conjuntoValido = gerarConjuntoTeste(tamanho);
                }
                conjuntosDeLances.add(conjuntoValido);
                tamanho++;
            }
        }
        return conjuntosDeLances;
    }
    /*
     * Para este teste, utilize os mesmos conjuntos de tamanho T encontrados no backtracking.
     * Em seguida, aumente os tamanhos dos conjuntos de T em T até atingir o tamanho 10T,
     * sempre executando 10 testes de cada tamanho para utilizar a média.
     */
    static List<List<Lance>> preencherGuloso(List<List<Lance>> lancesBacktracking) {
        List<List<Lance>> conjuntosDeLances = new ArrayList<>(lancesBacktracking);
        int T = lancesBacktracking.size() > 0 ? lancesBacktracking.get(lancesBacktracking.size() - 1).size() : 10;
        for (int i = 1; i <= 10; i++) {
            for (int j = 0; j < 10; j++) {
                conjuntosDeLances.add(gerarConjuntoTeste(T * (i + 1)));
            }
        }
        return conjuntosDeLances;
    }
    // Função para gerar um conjunto de teste de tamanho específico;
    static List<Lance> gerarConjuntoTeste(int tamanho) {
        Random random = new Random();
        List<Lance> lances = new ArrayList<>();
        for (int i = 0; i < tamanho; i++) {
            int tamanhoLote = 100 + random.nextInt(600); // Gerar lotes entre 100 e 700 MW;
            int valor = 1000 + random.nextInt(2000); // Gerar valores entre 1000 e 3000;
            lances.add(new Lance(tamanhoLote, valor));
        }
        return lances;
    }
    // Método de simulação de execução para cálculo de tempo;
    static void simularExecucao(List<Lance> lances, int energiaTotal) {
        // Simulação de execução - ajuste para seu método real;
        try {
            Thread.sleep(1); // Simular tempo de execução;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    // Conjunto de empresas interessadas 1;
    static List<Lance> preencherInteressadas1(){
        List<Lance> lances = new ArrayList<>();
        lances.add(new Lance(430, 1043));
        lances.add(new Lance(428, 1188));
        lances.add(new Lance(410, 1565));
        lances.add(new Lance(385, 1333));
        lances.add(new Lance(399, 1214));
        lances.add(new Lance(382, 1498));
        lances.add(new Lance(416, 1540));
        lances.add(new Lance(436, 1172));
        lances.add(new Lance(416, 1386));
        lances.add(new Lance(423, 1097));
        lances.add(new Lance(400, 1463));
        lances.add(new Lance(406, 1353));
        lances.add(new Lance(403, 1568));
        lances.add(new Lance(390, 1228));
        lances.add(new Lance(387, 1542));
        lances.add(new Lance(390, 1206));
        lances.add(new Lance(430, 1175));
        lances.add(new Lance(397, 1492));
        lances.add(new Lance(392, 1293));
        lances.add(new Lance(393, 1533));
        lances.add(new Lance(439, 1149));
        lances.add(new Lance(403, 1277));
        lances.add(new Lance(415, 1624));
        lances.add(new Lance(387, 1280));
        lances.add(new Lance(417, 1330));
        return lances;
    }
    // Conjunto de empresas interessadas 2;
    static List<Lance> preencherInteressadas2(){
        List<Lance> lances = new ArrayList<>();
        lances.add(new Lance(313, 1496));
        lances.add(new Lance(398, 1768));
        lances.add(new Lance(240, 1210));
        lances.add(new Lance(433, 2327));
        lances.add(new Lance(301, 1263));
        lances.add(new Lance(297, 1499));
        lances.add(new Lance(232, 1209));
        lances.add(new Lance(614, 2342));
        lances.add(new Lance(558, 2983));
        lances.add(new Lance(495, 2259));
        lances.add(new Lance(310, 1381));
        lances.add(new Lance(213, 961));
        lances.add(new Lance(213, 1115));
        lances.add(new Lance(346, 1552));
        lances.add(new Lance(385, 2023));
        lances.add(new Lance(240, 1234));
        lances.add(new Lance(483, 2828));
        lances.add(new Lance(487, 2617));
        lances.add(new Lance(709, 2328));
        lances.add(new Lance(358, 1847));
        lances.add(new Lance(467, 2038));
        lances.add(new Lance(363, 2007));
        lances.add(new Lance(279, 1311));
        lances.add(new Lance(589, 3164));
        lances.add(new Lance(476, 2480));
        return lances;
    }
    //**************************************************************************************/
    static void TesteBacktracking(List<List<Lance>> lancesBacktracking){

    }
    static void TesteBacktracking2(List<Lance> lancesInteressadas1, List<Lance> lancesInteressadas2){
        // Empresas interessadas 1;
        Backtracking backtracking = new Backtracking();
        long inicioBacktracking = System.currentTimeMillis();
        backtracking.resolver(lancesInteressadas1, energiaTotal);
        int energiaTotalBacktracking = 0;
        for (Lance lance : backtracking.getMelhorCombinacao()) {
            energiaTotalBacktracking += lance.getEnergia();
        }
        backtracking.imprimirMelhorCombinacao();
        long fimBacktracking = System.currentTimeMillis();
        double timeBacktracking = (fimBacktracking - inicioBacktracking) / 1000.0;
        // Imprime os valores;
        System.out.println("Energia Total Vendida (Backtracking): " + energiaTotalBacktracking + " MW");
        System.out.println("Melhor Valor Obtido (Backtracking): " + backtracking.melhorValorTotal + " dinheiros");
        System.out.println("Tempo de execução (Backtracking): " + timeBacktracking + " segundos");
    }
    public static void main(String[] args){
        // Listas de lances;
        List<List<Lance>> lancesBacktracking = new ArrayList<>();
        List<List<Lance>> lancesGuloso = new ArrayList<>();
        List<List<Lance>> lancesDivConquista = new ArrayList<>();
        List<List<Lance>> lancesProgDinamica = new ArrayList<>();
        List<Lance> lancesInteressadas1 = new ArrayList<>();
        List<Lance> lancesInteressadas2 = new ArrayList<>();

        // Preencher as listas segundo os requisitos pedidos;
        //lancesBacktracking = preencherBacktracking();
        //lancesGuloso = preencherGuloso(lancesBacktracking);
        //lancesDivConquista = lancesBacktracking;
        //lancesProgDinamica = lancesGuloso;
        lancesInteressadas1 = preencherInteressadas1();
        lancesInteressadas2 = preencherInteressadas2();

        // Realizando os Algoritmos;
        // Testando backtracking;
        TesteBacktracking(lancesBacktracking);
        TesteBacktracking2(lancesInteressadas1, lancesInteressadas2);
        // Testando algoritmo guloso 1 (maior valor total);
        // Testando algoritmo guloso 2 (melhor razão valor/energia);
        // Testando divisão e conquista;
        // Testando programação dinâmica;
    }
}