import java.util.List;
import java.util.Scanner;
public class StartTerminal {
    private final levelFirst levelFirst = new levelFirst();
    private final levelSecond levelSecond = new levelSecond();
    List<Purchase> produce = DataFactory.produce();
    public void start(String task, Scanner sc){
        if(task.equals("start")){
            letsStart(sc);
        } else {
            System.out.println("nie rozumiem");
        }
    }

    private void letsStart(Scanner sc) {
        System.out.println("Wybierz poziom");
        System.out.println("poziom 1");
        System.out.println("poziom 2");
        System.out.println("poziom 3");
        System.out.println("poziom 4");
        System.out.println("poziom 5");
        levelValidator(sc);
    }

    private void levelValidator(Scanner sc) {
        while (sc.hasNextLine()){
            String level = sc.nextLine();

            switch (level){
                case "poziom 1"
                        -> startLevel1(sc);
                case "poziom 2"
                        ->startLevel2(sc);
                default
                        -> System.out.println("nie rozumiem");
            }
        }
    }


    private void startLevel1(Scanner sc) {
        System.out.println("wpisz task 1 aby wyświetlić ilość osób która dokonała zakupów w sklepie");
        System.out.println("wpisz task 2 aby wyświetlić jaka ilość klientów płaciła Blikiem");
        System.out.println("wpisz task 3 aby wyświetlić jaka ilość klientów płaciła kartą kredytową.");
        System.out.println("wpisz task 4 aby wyświetlić jaka ilość zakupów została wykonana w walucie EUR.");
        System.out.println("wpisz task 5 aby wyświetlić ile unikalnych kupionych produktów zostało zakupionych w EUR");
        System.out.println("Wpisz \"wszystko\" aby zobaczyć wszystkie wyniki");
        while (sc.hasNextLine()){
            String level1 = sc.nextLine().trim();
            switch (level1) {
                case "task 1"
                        -> System.out.println(levelFirst.getClientCounter());
                case "task 2"
                        -> System.out.println(levelFirst.getBlikCounter());
                case "task 3"
                        -> System.out.println(levelFirst.getCardCounter());
                case "task 4"
                        -> System.out.println(levelFirst.getEuroCounter());
                case "task 5"
                        -> System.out.println(levelFirst.getUniqueEuro());
                case "wszystko"
                        -> System.out.println(levelFirst.getAlls());
                default -> System.out.println("nie rozumiem");
            }

        }
    }

    private void startLevel2(Scanner sc) {
        System.out.println("wpisz task 1 aby zobaczyć ile PLN wydała każda z osób");
        System.out.println
                ("wpisz task 2 aby zobaczyć ilość kupionych przedmiotów z danej kategorii przez poszczególne osoby");
        TaskChecker(sc);
    }

    private void TaskChecker(Scanner sc) {
        while (sc.hasNextLine()){
            String level2Scanner = sc.nextLine();

            switch (level2Scanner){
                case "task 1"->
                        levelSecond.Task1();
                case "task 2" -> {
                    System.out.println
                            ("""
                                    wybierz kategorie (wpisz cyfre):
                                    1: Ogród\s
                                    2: motoryzacja\s
                                    3: moda
                                    4: hobby""");
                    EnumValidator(sc);
                }
                default -> System.out.println("nie rozumiem");

            }
        }
    }
    private void EnumValidator(Scanner sc) {
        while (sc.hasNextLine()){
            String category = sc.nextLine();
            Product.Category kategoria = null;

            switch (category){
                case "1"
                        -> kategoria=Product.Category.GARDEN;
                case "2"
                        -> kategoria=Product.Category.AUTOMOTIVE;
                case "3"
                        ->kategoria=Product.Category.CLOTHES;
                case "4"
                        ->kategoria=Product.Category.HOBBY;
                default -> {
                    System.out.println("nie rozumiem. Wybierz kategorie od 1 - 4" );
                    continue;
                }

            }
            levelSecond.Task2(kategoria);
        }
    }

}
