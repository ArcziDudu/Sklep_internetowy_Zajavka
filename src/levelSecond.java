import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class levelSecond extends Level{


    public void Task1() {
        TreeMap<String, BigDecimal> collect = produce.stream()
                .filter(p -> Money.Currency.PLN.equals(p.getProduct().getPrice().getCurrency()))
                .collect(Collectors.groupingBy(
                        p -> p.getBuyer().getId(),
                        TreeMap::new,
                        Collectors.mapping(
                                p -> p.getProduct()
                                        .getPrice()
                                        .getValue()
                                        .multiply(BigDecimal.valueOf(p.getQuanity())),
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)
                        )
                ));
        collect.forEach((k,v)-> System.out.println("osoba: "+ k+" | suma wydanych pieniędzy: "+ v));
    }

    public void Task2(Product.Category category) {
        Map<String, Long> collect = produce.stream()
                .filter(p -> p.getQuanity() > 1 && category.equals(p.getProduct().getCategory()))
                .collect(Collectors.groupingBy(
                        p -> p.getBuyer().getId(),
                        Collectors.mapping(
                                Purchase::getQuanity,
                                Collectors.reducing(0L, Long::sum))
                ));
        collect.forEach((k,v)->
                System.out.println("osoba: "+k+"| kupiła następującą ilość produktów z wybranej kategorii: "+v));
    }

    public String Task3() {
        return null;
    }


    public String Task4() {
        return null;
    }


    public String Task5() {
        return null;
    }


}
