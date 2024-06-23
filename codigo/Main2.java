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
    static List<Lance> preencherBacktracking() {
        List<Lance> conjuntoDeLances = new ArrayList<>();
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
                conjuntoDeLances.addAll(conjuntoValido);
                tamanho++;
            }
        }
        return conjuntoDeLances;
    }
    /*
     * Para este teste, utilize os mesmos conjuntos de tamanho T encontrados no backtracking.
     * Em seguida, aumente os tamanhos dos conjuntos de T em T até atingir o tamanho 10T,
     * sempre executando 10 testes de cada tamanho para utilizar a média.
     */
    static List<Lance> preencherGuloso(List<Lance> lancesBacktracking) {
        List<Lance> conjuntoDeLances = new ArrayList<>(lancesBacktracking);
        int T = lancesBacktracking.size();
        for (int i = 1; i <= 10; i++) {
            for (int j = 0; j < 10; j++) {
                List<Lance> conjuntoTeste = gerarConjuntoTeste(T * (i + 1));
                conjuntoDeLances.addAll(conjuntoTeste);
            }
        }
        return conjuntoDeLances;
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
    // Testando backtracking;
    static void TesteBacktracking(List<Lance> lancesBacktracking){
        System.out.println("Backtracking");
        Backtracking backtracking = new Backtracking();
        long inicioBacktracking = System.currentTimeMillis();
        backtracking.resolver(lancesBacktracking, energiaTotal);
        int energiaTotalBacktracking = 0;
        for (Lance lance : backtracking.getMelhorCombinacao()) {
            energiaTotalBacktracking += lance.getEnergia();
        }
        backtracking.imprimirMelhorCombinacao();
        long fimBacktracking = System.currentTimeMillis();
        double timeBacktracking = (fimBacktracking - inicioBacktracking) / 1000.0;
        // Imprime os valores;
        System.out.println("Energia Total Vendida (Backtracking): " + energiaTotalBacktracking + " MW");
        System.out.println("Energia Não Vendida (Backtracking): " + (energiaTotal - energiaTotalBacktracking) + " MW");
        System.out.println("Melhor Valor Obtido (Backtracking): " + backtracking.melhorValorTotal + " dinheiros");
        System.out.println("Tempo de execução (Backtracking): " + timeBacktracking + " segundos\n");
    }
    // Testando algoritmo guloso 1 (maior valor total);
    static void TesteGuloso1(List<Lance> gulosoLances){
        System.out.println("Guloso 1");
        Guloso algoritmoGuloso1 = new Guloso();
        long inicioGuloso1 = System.currentTimeMillis();
        int melhorValorGuloso1 = algoritmoGuloso1.resolverEstrategia1(gulosoLances, energiaTotal);
        long fimGuloso1 = System.currentTimeMillis();
        double timeGuloso1 = (fimGuloso1 - inicioGuloso1) / 1000.0;
        // Imprime os valores;
        System.out.println("Melhor valor obtido (Guloso 1): " + melhorValorGuloso1 + " dinheiros");
        System.out.println("Tempo de execução (Guloso 1): " + timeGuloso1 + " segundos\n");
    }
    // Testando algoritmo guloso 2 (melhor razão valor/energia);
    static void TesteGuloso2(List<Lance> gulosoLances){
        System.out.println("Guloso 2");
        Guloso algoritmoGuloso2 = new Guloso();
        long inicioGuloso2 = System.currentTimeMillis();
        int melhorValorGuloso2 = algoritmoGuloso2.resolverEstrategia2(gulosoLances, energiaTotal);
        long fimGuloso2 = System.currentTimeMillis();
        double timeGuloso2 = (fimGuloso2 - inicioGuloso2) / 1000.0;
        // Imprime os valores;
        System.out.println("Melhor valor obtido (Guloso 2): " + melhorValorGuloso2 + " dinheiros");
        System.out.println("Tempo de execução (Guloso 2): " + timeGuloso2 + " segundos\n");
    }
    // Testando divisão e conquista;
    static void TesteDivConquista(List<Lance> divLances){
        System.out.println("Divisão e Conquista");
        DivConquista divisaoEConquista = new DivConquista();
        long inicioDivConquista = System.currentTimeMillis();
        int[] resultadoDivConquista = divisaoEConquista.resolver(divLances, energiaTotal);
        long fimDivConquista = System.currentTimeMillis();
        double timeDivConquista = (fimDivConquista - inicioDivConquista) / 1000.0;
        // Imprime os valores;
        System.out.println("Energia Total Vendida (Divisão e Conquista): " + resultadoDivConquista[1] + " MW");
        System.out.println("Energia Não Vendida (Divisão e Conquista): " + (energiaTotal - resultadoDivConquista[1]) + " MW");
        System.out.println("Melhor valor obtido (Divisão e Conquista): " + resultadoDivConquista[0] + " dinheiros");
        System.out.println("Tempo de execução (Divisão e Conquista): " + timeDivConquista + " segundos\n");
    }
    // Testando programação dinâmica;
    static void TesteProgDinamica(List<Lance> proLances){
        System.out.println("Programação Dinâmica");
        ProgDinamica programacaoDinamica = new ProgDinamica();
        long inicioProgramacaoDinamica = System.currentTimeMillis();
        int[] resultadoProgramacaoDinamica = programacaoDinamica.resolver(proLances, energiaTotal);
        long fimProgramacaoDinamica = System.currentTimeMillis();
        double timeProgramacaoDinamica = (fimProgramacaoDinamica - inicioProgramacaoDinamica) / 1000.0;
        // Imprime os valores;
        programacaoDinamica.imprimirMelhorCombinacao();
        System.out.println("Energia Total Vendida (Programação Dinâmica): " + resultadoProgramacaoDinamica[1] + " MW");
        System.out.println("Energia Não Vendida (Programação Dinâmica): " + (energiaTotal - resultadoProgramacaoDinamica[1]) + " MW");
        System.out.println("Melhor valor obtido (Programação Dinâmica): " + resultadoProgramacaoDinamica[0] + " dinheiros");
        System.out.println("Tempo de execução (Programação Dinâmica): " + timeProgramacaoDinamica + " segundos\n");
    }
    //**************************************************************************************/
    // Main do projeto;
    public static void main(String[] args){
        // Listas de lances;
        List<Lance> lancesBacktracking = new ArrayList<>();
        List<Lance> lancesGuloso = new ArrayList<>();
        List<Lance> lancesDivConquista = new ArrayList<>();
        List<Lance> lancesProgDinamica = new ArrayList<>();
        List<Lance> lancesInteressadas1 = new ArrayList<>();
        List<Lance> lancesInteressadas2 = new ArrayList<>();

        // Preencher as listas segundo os requisitos pedidos;
        //lancesBacktracking = preencherBacktracking();
        //lancesGuloso = preencherGuloso(lancesBacktracking);
        lancesDivConquista = lancesBacktracking;
        lancesProgDinamica = lancesGuloso;
        lancesInteressadas1 = preencherInteressadas1();
        lancesInteressadas2 = preencherInteressadas2();

        // Realizando os Algoritmos;
        System.out.println("\nEmpresas Interessadas 1\n");
        TesteBacktracking(lancesInteressadas1);
        TesteGuloso1(lancesInteressadas1);
        TesteGuloso2(lancesInteressadas1);
        TesteDivConquista(lancesInteressadas1);
        TesteProgDinamica(lancesInteressadas1);

        System.out.println("Empresas Interessadas 2\n");
        TesteBacktracking(lancesInteressadas2);
        TesteGuloso1(lancesInteressadas2);
        TesteGuloso2(lancesInteressadas2);
        TesteDivConquista(lancesInteressadas2);
        TesteProgDinamica(lancesInteressadas2);

        System.out.println("Outros\n");

    }
}