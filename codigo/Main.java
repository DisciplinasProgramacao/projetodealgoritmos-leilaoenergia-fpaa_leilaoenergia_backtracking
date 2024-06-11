import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Lance> lances = new ArrayList<>();
        lances.add(new Lance(500, 500));
        lances.add(new Lance(500, 510));
        lances.add(new Lance(400, 520));
        lances.add(new Lance(300, 400));
        lances.add(new Lance(200, 220));
        lances.add(new Lance(900, 1110));

        int energiaTotal = 1000;

        for (Lance lance : lances) {
            System.out.println("Energia: " + lance.getEnergia() + " Valor: " + lance.getValor());
        }
    }
}
