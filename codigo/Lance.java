public class Lance {
    int energia;
    int valor;

    public Lance(int energia, int valor) {
        this.energia = energia;
        this.valor = valor;
    }

    public int getEnergia() {
        return energia;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Lance{" +
                "energia=" + energia +
                ", valor=" + valor +
                '}';
    }
}
