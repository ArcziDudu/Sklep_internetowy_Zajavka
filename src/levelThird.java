import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class levelThird extends Level{
    static final Integer CURRENT_YEAR = 2020;
    public void task1(){
        Map<String, Map<Product.Category, Long>> collect = produce.stream()
                .filter(p -> CURRENT_YEAR - (1900 + getAnInt(p)) > 50)
                .collect(Collectors.groupingBy(
                        p -> p.getBuyer().getPesel().toString().substring(0, 2),
                        Collectors.groupingBy(
                                p -> p.getProduct().getCategory(),
                                Collectors.counting()
                        )
                ));
    }

    private static int getAnInt(Purchase p) {
        return Integer.parseInt(p.getBuyer().getPesel().toString().substring(0, 2));
    }

}
