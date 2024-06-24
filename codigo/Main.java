import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Conjunto 1 professor
        List<Lance> lances = new ArrayList<>();
        //Conjunto 2 professor
        List<Lance> lances1 = new ArrayList<>();
        Random random = new Random();
        Guloso guloso = new Guloso();
        Guloso1 guloso1 = new Guloso1();

        int opcao1;
        int opcao3;
        int opcao4;

        System.out.println("Escolha um conjunto: ");
        System.out.println("1. Nosso conjunto (testes)");
        System.out.println("2. Conjunto empresas interessadas (aula)");
        opcao1 = scanner.nextInt();
        switch (opcao1) {
            case 1:
                System.out.println("Executando...");
                List<List<Lance>> conjuntoT = new ArrayList<>();
                int energiaTotal = 11000;

                //BACKTRACKING
                System.out.println("BACKTRACKING");
                Backtracking backtracking = new Backtracking();
                List<List<Lance>> conjuntoAux = new ArrayList<>();
                double tempoMedioFinal = 0;
                double tempoMedio = 0;
                int tamanhoConjunto = 10;
                while (true) {
                    double tempoTeste = 0;
                    conjuntoAux.clear();

                    for (int j = 0; j < 10; j++) {
                        List<Lance> lancesTeste = gerarConjuntoLances(tamanhoConjunto, random);

                        conjuntoAux.add(new ArrayList<>(lancesTeste));

                        long inicio = System.currentTimeMillis();
                        backtracking.resolver(lancesTeste, energiaTotal);
                        long fim = System.currentTimeMillis();
                        double tempoDecorrido = (fim - inicio) / 1000.0;
                        tempoTeste += tempoDecorrido;
                    }
                    tempoMedio = tempoTeste / 10;
                    if (tempoMedio < 30.0) {
                        tamanhoConjunto += 1;
                        conjuntoT = new ArrayList<>(conjuntoAux);
                        tempoMedioFinal = tempoMedio;
                    } else {
                        // Foi alterado a linha para imprimir o tamanho do conjunto. Estava imprimindo o tamanho que passou do tempo limite.
                        System.out.println("Tamanho conjunto: " + (tamanhoConjunto - 1) + " Tempo médio: " + tempoMedioFinal);
                        for (List<Lance> list : conjuntoT) {
                            backtracking.resolver(list, energiaTotal);
                            System.out.println("Energia: " + backtracking.getEnergiaTotalMelhorCombinacao() + " Valor: " + backtracking.getMelhorValor());
                        }
                        break;
                    }
                }

                //GULOSO 1
                System.out.println("GULOSO 1");
                int size = conjuntoT.get(0).size();
                double tempoTotalT = 0;
                for (List<Lance> list : conjuntoT) {
                    long inicioGuloso1 = System.currentTimeMillis();
                    guloso.resolverEstrategia1(list, energiaTotal);
                    long fimGuloso1 = System.currentTimeMillis();
                    double tempo = (fimGuloso1 - inicioGuloso1) / 1000.0;
                    tempoTotalT += tempo;
                }
                System.out.println("Tempo medio para conjunto de " + size + ": " + tempoTotalT/10);

                int c = 2;
                for (int i = 1; i <= 10; i++) {
                    double tempoTotal = 0;
                    int auxSize = size * c;
                    for (int j = 0; j < 10; j++) {
                        List<Lance> lista = gerarConjuntoLances(auxSize, random);
                        long inicioGuloso1 = System.currentTimeMillis();
                        guloso.resolverEstrategia1(lista, energiaTotal);
                        long fimGuloso1 = System.currentTimeMillis();
                        double tempo = (fimGuloso1 - inicioGuloso1) / 1000.0;
                        tempoTotal += tempo;
                    }
                    System.out.println("Tempo medio para conjunto de " + auxSize + ": " + tempoTotal/10);
                    c++;
                }
                
                //GULOSO 2
                System.out.println("GULOSO 2");
                double tempoTotalG2 = 0;
                for (List<Lance> list : conjuntoT) {
                    long inicioGuloso2 = System.currentTimeMillis();
                    guloso1.resolverEstrategia2(list, energiaTotal);
                    long fimGuloso2 = System.currentTimeMillis();
                    double tempo = (fimGuloso2 - inicioGuloso2) / 1000.0;
                    tempoTotalG2 += tempo;
                }
                System.out.println("Tempo medio para conjunto de " + size + ": " + tempoTotalG2/10);

                int g = 2;
                for (int i = 1; i <= 10; i++) {
                    double tempoTotal = 0;
                    int auxSize = size * g;
                    for (int j = 0; j < 10; j++) {
                        List<Lance> lista = gerarConjuntoLances(auxSize, random);
                        long inicioGuloso2 = System.currentTimeMillis();
                        guloso1.resolverEstrategia2(lista, energiaTotal);
                        long fimGuloso2 = System.currentTimeMillis();
                        double tempo = (fimGuloso2 - inicioGuloso2) / 1000.0;
                        tempoTotal += tempo;
                    }
                    System.out.println("Tempo medio para conjunto de " + auxSize + ": " + tempoTotal/10);
                    g++;
                }

                //DIVISÃO E CONQUISTA
                System.out.println("DIVISÃO E CONQUISTA");
                DivConquista divisaoEConquista = new DivConquista();
                double tempoMedioDC = 0;
                int[] melhorValorDivisaoConquista = new int[0];
                for (List<Lance> list : conjuntoT) {
                    long inicioDivConquista = System.currentTimeMillis();
                    melhorValorDivisaoConquista = divisaoEConquista.resolver(list, energiaTotal);
                    long fimDivConquista = System.currentTimeMillis();
                    System.out.println(
                            "Melhor valor obtido (Divisão e Conquista): " + Arrays.toString(melhorValorDivisaoConquista));
                    tempoMedioDC = (fimDivConquista - inicioDivConquista) / 1000.0;
                }
                System.out.println("Tempo de execução (Divisão e Conquista): "
                        + tempoMedioDC + "s");

                //PROGRAMAÇÃO DINAMICA
                System.out.println("PROGRAMAÇÃO DINAMICA");
                double tempoTotalPd = 0;
                for (List<Lance> list : conjuntoT) {
                    ProgDinamica pd = new ProgDinamica();
                    long inicio = System.currentTimeMillis();
                    int[] resultado = pd.resolver(list, energiaTotal);
                    long fim = System.currentTimeMillis();
                    System.out.println("Energia: " + resultado[1] + " Melhor valor PD: " + resultado[0]);
                    double tempo = (fim - inicio) / 1000.0;
                    tempoTotalPd += tempo;
                }
                System.out.println("Tempo medio para conjunto de " + size + ": " + tempoTotalPd/10);

                int p = 2;
                for (int i = 1; i <= 10; i++) {
                    double tempoTotal = 0;
                    int auxSize = size * p;
                    for (int j = 0; j < 10; j++) {
                        ProgDinamica pd = new ProgDinamica();
                        List<Lance> lista = gerarConjuntoLances(auxSize, random);
                        long inicio = System.currentTimeMillis();
                        int[] resultado = pd.resolver(lista, energiaTotal);
                        long fim = System.currentTimeMillis();
                        System.out.println("Energia: " + resultado[1] + " Melhor valor PD: " + resultado[0]);
                        double tempo = (fim - inicio) / 1000.0;
                        tempoTotal += tempo;
                    }
                    System.out.println("Tempo medio para conjunto de " + auxSize + ": " + tempoTotal/10);
                    p++;
                }


                break;
            case 2:
                int energiaTotalAula = 8000;

                    // Conjunto de empresas interessadas 1
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


                    // Conjunto de empresas interessadas 2
                    lances1.add(new Lance(313, 1496));
                    lances1.add(new Lance(398, 1768));
                    lances1.add(new Lance(240, 1210));
                    lances1.add(new Lance(433, 2327));
                    lances1.add(new Lance(301, 1263));
                    lances1.add(new Lance(297, 1499));
                    lances1.add(new Lance(232, 1209));
                    lances1.add(new Lance(614, 2342));
                    lances1.add(new Lance(558, 2983));
                    lances1.add(new Lance(495, 2259));
                    lances1.add(new Lance(310, 1381));
                    lances1.add(new Lance(213, 961));
                    lances1.add(new Lance(213, 1115));
                    lances1.add(new Lance(346, 1552));
                    lances1.add(new Lance(385, 2023));
                    lances1.add(new Lance(240, 1234));
                    lances1.add(new Lance(483, 2828));
                    lances1.add(new Lance(487, 2617));
                    lances1.add(new Lance(709, 2328));
                    lances1.add(new Lance(358, 1847));
                    lances1.add(new Lance(467, 2038));
                    lances1.add(new Lance(363, 2007));
                    lances1.add(new Lance(279, 1311));
                    lances1.add(new Lance(589, 3164));
                    lances1.add(new Lance(476, 2480));

                System.out.println("Escolha um conjunto de empresas interessadas que deseja executar: ");
                System.out.println("1. Conjunto 1");
                System.out.println("2. Conjunto 2");
                opcao4 = scanner.nextInt();

                switch (opcao4) {
                    case 1:
                        // Testando backtracking;
                        System.out.println("Backtracking");
                        Backtracking backtrackingAula = new Backtracking();
                        long inicioBacktracking = System.currentTimeMillis();
                        backtrackingAula.resolver(lances, energiaTotalAula);
                        int energiaTotalBacktracking = 0;
                        for (Lance lance : backtrackingAula.getMelhorCombinacao()) {
                            energiaTotalBacktracking += lance.getEnergia();
                        }
                        backtrackingAula.imprimirMelhorCombinacao();
                        long fimBacktracking = System.currentTimeMillis();
                        double timeBacktracking = (fimBacktracking - inicioBacktracking) / 1000.0;
                        // Imprime os valores;
                        System.out.println("Energia Total Vendida (Backtracking): " + energiaTotalBacktracking + " MW");
                        System.out.println("Energia Não Vendida (Backtracking): " + (energiaTotalAula - energiaTotalBacktracking) + " MW");
                        System.out.println("Melhor Valor Obtido (Backtracking): " + backtrackingAula.melhorValorTotal + " dinheiros");
                        System.out.println("Tempo de execução (Backtracking): " + timeBacktracking + " segundos\n");


                        // Testando algoritmo guloso 1 (maior valor total)
                        System.out.println("Guloso 1");
                        Guloso algoritmoGuloso1 = new Guloso();
                        long inicioGuloso1Aula = System.currentTimeMillis();
                        algoritmoGuloso1.resolverEstrategia1(lances, energiaTotalAula);
                        long fimGuloso1 = System.currentTimeMillis();
                        double timeGuloso1 = (fimGuloso1 - inicioGuloso1Aula) / 1000.0;
                        // Imprime os valores;
                        //System.out.println("Melhor valor obtido (Guloso 1): " + melhorValorGuloso1 + " dinheiros");
                        System.out.println("Tempo de execução (Guloso 1): " + timeGuloso1 + " segundos\n");

                        // Testando algoritmo guloso 2 (melhor razão valor/energia)
                        System.out.println("Guloso 2");
                        Guloso1 algoritmoGuloso2 = new Guloso1();
                        long inicioGuloso2Aula = System.currentTimeMillis();
                        algoritmoGuloso2.resolverEstrategia2(lances, energiaTotalAula);
                        long fimGuloso2 = System.currentTimeMillis();
                        double timeGuloso2 = (fimGuloso2 - inicioGuloso2Aula) / 1000.0;
                        // Imprime os valores;
                        //System.out.println("Melhor valor obtido (Guloso 2): " + melhorValorGuloso2 + " dinheiros");
                        System.out.println("Tempo de execução (Guloso 2): " + timeGuloso2 + " segundos\n");

                        // Testando divisão e conquista;
                        System.out.println("Divisão e Conquista");
                        DivConquista divisaoEConquistaAula = new DivConquista();
                        long inicioDivConquistaAula = System.currentTimeMillis();
                        int[] resultadoDivConquista = divisaoEConquistaAula.resolver(lances, energiaTotalAula);
                        long fimDivConquistaAula = System.currentTimeMillis();
                        double timeDivConquista = (fimDivConquistaAula - inicioDivConquistaAula) / 1000.0;
                        // Imprime os valores;
                        System.out.println("Energia Total Vendida (Divisão e Conquista): " + resultadoDivConquista[1] + " MW");
                        System.out.println("Energia Não Vendida (Divisão e Conquista): " + (energiaTotalAula - resultadoDivConquista[1]) + " MW");
                        System.out.println("Melhor valor obtido (Divisão e Conquista): " + resultadoDivConquista[0] + " dinheiros");
                        System.out.println("Tempo de execução (Divisão e Conquista): " + timeDivConquista + " segundos\n");

                        // Testando programação dinâmica;
                        System.out.println("Programação Dinâmica");
                        ProgDinamica programacaoDinamica = new ProgDinamica();
                        long inicioProgramacaoDinamica = System.currentTimeMillis();
                        int[] resultadoProgramacaoDinamica = programacaoDinamica.resolver(lances, energiaTotalAula);
                        long fimProgramacaoDinamica = System.currentTimeMillis();
                        double timeProgramacaoDinamica = (fimProgramacaoDinamica - inicioProgramacaoDinamica) / 1000.0;
                        // Imprime os valores;
                        programacaoDinamica.imprimirMelhorCombinacao();
                        System.out.println("Energia Total Vendida (Programação Dinâmica): " + resultadoProgramacaoDinamica[1] + " MW");
                        System.out.println("Energia Não Vendida (Programação Dinâmica): " + (energiaTotalAula - resultadoProgramacaoDinamica[1]) + " MW");
                        System.out.println("Melhor valor obtido (Programação Dinâmica): " + resultadoProgramacaoDinamica[0] + " dinheiros");
                        System.out.println("Tempo de execução (Programação Dinâmica): " + timeProgramacaoDinamica + " segundos");

                        break;
                    case 2:
                        // Testando backtracking;
                        System.out.println("Backtracking");
                        backtrackingAula = new Backtracking();
                        inicioBacktracking = System.currentTimeMillis();
                        backtrackingAula.resolver(lances1, energiaTotalAula);
                        energiaTotalBacktracking = 0;
                        for (Lance lance : backtrackingAula.getMelhorCombinacao()) {
                            energiaTotalBacktracking += lance.getEnergia();
                        }
                        backtrackingAula.imprimirMelhorCombinacao();
                        fimBacktracking = System.currentTimeMillis();
                        timeBacktracking = (fimBacktracking - inicioBacktracking) / 1000.0;
                        // Imprime os valores;
                        System.out.println("Energia Total Vendida (Backtracking): " + energiaTotalBacktracking + " MW");
                        System.out.println("Energia Não Vendida (Backtracking): " + (energiaTotalAula - energiaTotalBacktracking) + " MW");
                        System.out.println("Melhor Valor Obtido (Backtracking): " + backtrackingAula.melhorValorTotal + " dinheiros");
                        System.out.println("Tempo de execução (Backtracking): " + timeBacktracking + " segundos\n");


                        // Testando algoritmo guloso 1 (maior valor total)
                        System.out.println("Guloso 1");
                        algoritmoGuloso1 = new Guloso();
                        inicioGuloso1Aula = System.currentTimeMillis();
                        algoritmoGuloso1.resolverEstrategia1(lances1, energiaTotalAula);
                        fimGuloso1 = System.currentTimeMillis();
                        timeGuloso1 = (fimGuloso1 - inicioGuloso1Aula) / 1000.0;
                        // Imprime os valores;
                        //System.out.println("Melhor valor obtido (Guloso 1): " + melhorValorGuloso1 + " dinheiros");
                        System.out.println("Tempo de execução (Guloso 1): " + timeGuloso1 + " segundos\n");

                        // Testando algoritmo guloso 2 (melhor razão valor/energia)
                        System.out.println("Guloso 2");
                        algoritmoGuloso2 = new Guloso1();
                        inicioGuloso2Aula = System.currentTimeMillis();
                        algoritmoGuloso2.resolverEstrategia2(lances1, energiaTotalAula);
                        fimGuloso2 = System.currentTimeMillis();
                        timeGuloso2 = (fimGuloso2 - inicioGuloso2Aula) / 1000.0;
                        // Imprime os valores;
                        //System.out.println("Melhor valor obtido (Guloso 2): " + melhorValorGuloso2 + " dinheiros");
                        System.out.println("Tempo de execução (Guloso 2): " + timeGuloso2 + " segundos\n");

                        // Testando divisão e conquista;
                        System.out.println("Divisão e Conquista");
                        divisaoEConquistaAula = new DivConquista();
                        inicioDivConquistaAula = System.currentTimeMillis();
                        resultadoDivConquista = divisaoEConquistaAula.resolver(lances1, energiaTotalAula);
                        fimDivConquistaAula = System.currentTimeMillis();
                        timeDivConquista = (fimDivConquistaAula - inicioDivConquistaAula) / 1000.0;
                        // Imprime os valores;
                        System.out.println("Energia Total Vendida (Divisão e Conquista): " + resultadoDivConquista[1] + " MW");
                        System.out.println("Energia Não Vendida (Divisão e Conquista): " + (energiaTotalAula - resultadoDivConquista[1]) + " MW");
                        System.out.println("Melhor valor obtido (Divisão e Conquista): " + resultadoDivConquista[0] + " dinheiros");
                        System.out.println("Tempo de execução (Divisão e Conquista): " + timeDivConquista + " segundos\n");

                        // Testando programação dinâmica;
                        System.out.println("Programação Dinâmica");
                        programacaoDinamica = new ProgDinamica();
                        inicioProgramacaoDinamica = System.currentTimeMillis();
                        resultadoProgramacaoDinamica = programacaoDinamica.resolver(lances1, energiaTotalAula);
                        fimProgramacaoDinamica = System.currentTimeMillis();
                        timeProgramacaoDinamica = (fimProgramacaoDinamica - inicioProgramacaoDinamica) / 1000.0;
                        // Imprime os valores;
                        programacaoDinamica.imprimirMelhorCombinacao();
                        System.out.println("Energia Total Vendida (Programação Dinâmica): " + resultadoProgramacaoDinamica[1] + " MW");
                        System.out.println("Energia Não Vendida (Programação Dinâmica): " + (energiaTotalAula - resultadoProgramacaoDinamica[1]) + " MW");
                        System.out.println("Melhor valor obtido (Programação Dinâmica): " + resultadoProgramacaoDinamica[0] + " dinheiros");
                        System.out.println("Tempo de execução (Programação Dinâmica): " + timeProgramacaoDinamica + " segundos");

                        break;
                    default:
                        System.out.println("Opção inválida.");
                        scanner.close();
                        break;
                }
                scanner.close();
        }
    }

    // Função para gerar um conjunto de lances aleatórios
    private static List<Lance> gerarConjuntoLances(int tamanho, Random random) {
        List<Lance> lances = new ArrayList<>();
        for (int i = 0; i < tamanho; i++) {
            int energia = random.nextInt(1000) + 1; // Energia entre 1 e 100 MW
            int valor = random.nextInt(2000) + 1; // Valor entre 1 e 2000 reais
            lances.add(new Lance(energia, valor));
        }
        return lances;
    }

    public static <T> List<List<T>> duplicateListOfLists(List<List<T>> originalList) {
        List<List<T>> newList = new ArrayList<>();
        for (List<T> sublist : originalList) {
            List<T> newSublist = new ArrayList<>(sublist);
            newList.add(newSublist);
        }
        return newList;
    }
}
