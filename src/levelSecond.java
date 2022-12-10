import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public void Task3() {
        long count = produce.stream()
                .map(p -> new Purchase(p, OrderService.checkOrderStatus(p)))
                .filter(c -> Purchase.Status.WYKONANO.equals(c.getStatus()))
                .count();
        Map<Purchase.Status, List<Purchase>> collect = produce.stream()
                .map(p -> new Purchase(p, OrderService.checkOrderStatus(p)))
                .collect(Collectors.toMap(
                        Purchase::getStatus,
                        List::of,
                        (List<Purchase> cl, List<Purchase> nl) ->
                                Stream.concat(cl.stream(), nl.stream())
                                        .collect(Collectors.toList()))
                );
        System.out.println("ilość zamówień ze statusem wykonano: "+count);
       collect.forEach((k,v)
               -> System.out.println("status: "+k+" "+v));
    }

    public void Task4() {
       List<Purchase> listaEur = produce.stream()
               .filter(p -> Money.Currency.EUR.equals(p.getProduct().getPrice().getCurrency())).toList();

        Map<String, List<Purchase>> euroPurchases = listaEur.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getBuyer().getId()
                ));

        long count = listaEur.stream()
                .map(Purchase::getBuyer)
                .distinct()
                .count();
        System.out.println("ilość unikalnych klientów którzy kupili produkty wycenione w euro: " + count);
        euroPurchases.forEach((k,v)->
                System.out.println("klient: "+k+"| zakupy: "+ v));
    }
    public void Task5() {
        TreeMap<String, List<Product>> productsPerClientYear = produce.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getBuyer().getPesel().toString().substring(0, 2),
                        TreeMap::new,
                        Collectors.mapping(Purchase::getProduct, Collectors.toList())
                ));
        productsPerClientYear.forEach((k,v)-> System.out.println("rocznik: "+k+"| zakupy: "+v));
    }
    public void Task6(){
        Map<String, Set<Product.Category>> unique = produce.stream()
                .collect(Collectors.toMap(
                        p -> p.getBuyer().getPesel().toString().substring(0, 2),
                        p -> new HashSet<>(List.of(p.getProduct().getCategory())),
                        (currentSet, nextSet)-> Stream.concat(currentSet.stream(), nextSet.stream())
                                .collect(Collectors.toSet())
                ));
        unique.forEach((k,v)->
                System.out.println("rocznik: "+k+"| kategorie: "+v));
    }
    }
