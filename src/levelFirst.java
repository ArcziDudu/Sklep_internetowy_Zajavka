import java.util.List;

public class levelFirst extends Level{

    private final String clientCounter = ClientCounter(produce);
    private final String blikCounter = BlikPaymentCounter(produce);
    private final String cardCounter = CreditCardPaymentCounter(produce);
    private final String euroCounter = PurchaseInEuroCounter(produce);
    private final String uniqueEuro = UniqueTransactionInEuro(produce);
    private final String all = ShowAll(produce,clientCounter,blikCounter,cardCounter,euroCounter,uniqueEuro);

    public String getClientCounter() {
        return clientCounter;
    }

    public String getBlikCounter() {
        return blikCounter;
    }

    public String getCardCounter() {
        return cardCounter;
    }

    public String getEuroCounter() {
        return euroCounter;
    }

    public String getUniqueEuro() {
        return uniqueEuro;
    }

    public String getAll() {
        return all;
    }

    public  String ClientCounter(List<Purchase> produce) {
        return "Ilość unikalnych klientów sklepu: "+produce.stream()
                .map(Purchase::getBuyer)
                .distinct()
                .count();
    }

    public String BlikPaymentCounter(List<Purchase> produce) {
        return "Ilość klientów płącących za zakupy blikiem: "+ produce.stream()
                .filter(p-> Purchase.Payment.BLIK.equals(p.getPayment()))
                .map(Purchase::getBuyer)
                .distinct()
                .count();
    }
    public String CreditCardPaymentCounter(List<Purchase> produce) {
        return "Ilość klientów płącących za zakupy kartą kredytową: "+ produce.stream()
                .filter(p-> Purchase.Payment.CREDIT_CARD.equals(p.getPayment()))
                .map(Purchase::getBuyer)
                .distinct()
                .count();
    }
    public String PurchaseInEuroCounter(List<Purchase> produce) {
        return "Ilość zakupów opłaconych walutą Euro: "+ produce.stream()
                .map(Purchase::getProduct)
                .map(Product::getPrice)
                .filter(p-> Money.Currency.EUR.equals(p.getCurrency()))
                .count();
    }
    public String UniqueTransactionInEuro(List<Purchase> produce) {
        return "Ilość unikalnych transakcji w walucie euro: "+produce.stream()
                .map(Purchase::getProduct)
                .distinct()
                .map(Product::getPrice)
                .filter(p-> Money.Currency.EUR.equals(p.getCurrency()))
                .count();
    }

   public String ShowAll(
            List<Purchase> produce,
            String s,
            String blikCounter,
            String cardCounter,
            String euroCounter,
            String uniqueEuro) {
        return s +"\n"+ blikCounter
                +"\n"+cardCounter
                +"\n"+euroCounter
                +"\n"+uniqueEuro;
    }
}
