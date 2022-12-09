import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Purchase> produce = DataFactory.produce();

        long clientCounter = ClientCounter(produce);
        System.out.println("Ilość unikalnych klientów w sklepie: "+ clientCounter);

        long blikPayment = BlikPaymentCounter(produce);
        System.out.println("Ilość unikalnych płatność blikiem: "  + blikPayment);

        long creditCardPayment = CreditCardPaymentCounter(produce);
        System.out.println("Ilość unikalnych płatności kartą kredytową: "+creditCardPayment);

        long purchaseInEuro = PurchaseInEuroCounter(produce);
        System.out.println("Ilość transakcji w walucie Euro: " + purchaseInEuro);

        long uniqueTransactionInEuro = UniqueTransactionInEuro(produce);
        System.out.println("Ilość unikalnych produktów kupionych za walutę Euro" + uniqueTransactionInEuro);
    }

    private static long UniqueTransactionInEuro(List<Purchase> produce) {
        return produce.stream()
                .map(Purchase::getProduct)
                .distinct()
                .map(Product::getPrice)
                .filter(p->Money.Currency.EUR.equals(p.getCurrency()))
                .count();
    }

    private static long PurchaseInEuroCounter(List<Purchase> produce) {
        return produce.stream()
                .map(Purchase::getProduct)
                .map(Product::getPrice)
                .filter(p->Money.Currency.EUR.equals(p.getCurrency()))
                .count();
    }


    private static long ClientCounter(List<Purchase> produce) {
       return produce.stream()
               .map(Purchase::getBuyer)
               .distinct()
               .count();
    }

    private static long BlikPaymentCounter(List<Purchase> produce) {
        return produce.stream()
                .filter(p->Purchase.Payment.BLIK.equals(p.getPayment()))
                .map(Purchase::getBuyer)
                .distinct()
                .count();
    }

    private static long CreditCardPaymentCounter(List<Purchase> produce) {
        return produce.stream()
                .filter(p->Purchase.Payment.CREDIT_CARD.equals(p.getPayment()))
                .map(Purchase::getBuyer)
                .distinct()
                .count();
    }
}