import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        StartTerminal startTerminal = new StartTerminal();
        Scanner sc = new Scanner(System.in);
        List<Purchase> produce = DataFactory.produce();

        System.out.println("Witaj ! Wpisz start aby rozpocząć");

        while (sc.hasNextLine()){
            String task = sc.nextLine().trim().toLowerCase();
            startTerminal.start(task, sc);
        }


    }
}