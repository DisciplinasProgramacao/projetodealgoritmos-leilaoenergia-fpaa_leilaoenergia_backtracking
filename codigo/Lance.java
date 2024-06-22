public class Lance {
    int energia;// energia do lote em megawatts;
    int valor;// valor oferecido pelo lote;

    // Construtor;
    public Lance(int energia, int valor) {
        this.energia = energia;
        this.valor = valor;
    }

    // Getters;
    public int getEnergia() {
        return energia;
    }

    public int getValor() {
        return valor;
    }

    // Imprime os valores do lance na forma de uma String;
    @Override
    public String toString() {
        return "Lance{" +
                "energia=" + energia +
                ", valor=" + valor +
                '}';
    }
}