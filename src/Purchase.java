import java.time.LocalDate;

public class Purchase {
    private final Client buyer;
    private final Product product;
    private final long quanity;
    private final Delivery delivery;
    private final Payment payment;
    private final LocalDate when;
    private Status status = Status.PAID;

    public Purchase(Client buyer, Product product, long quanity, Delivery delivery, Payment payment, LocalDate when, Status status) {
        this.buyer = buyer;
        this.product = product;
        this.quanity = quanity;
        this.delivery = delivery;
        this.payment = payment;
        this.when = when;
        this.status = status;
    }


    public Purchase(Client buyer, Product product, long quanity, Delivery delivery, Payment payment, LocalDate when) {
        this.buyer = buyer;
        this.product = product;
        this.quanity = quanity;
        this.delivery = delivery;
        this.payment = payment;
        this.when = when;
    }

    public Client getBuyer() {
        return buyer;
    }

    public Product getProduct() {
        return product;
    }

    public long getQuanity() {
        return quanity;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public Payment getPayment() {
        return payment;
    }

    public LocalDate getWhen() {
        return when;
    }

    public Status getStatus() {
        return status;
    }

    public enum Delivery{
        IN_POST,
        UPS,
        DHL
    }
    public enum Payment{
        CASH,
        BLIK,
        CREDIT_CARD
    }
    public enum Status{
        PAID,
        SENT,
        DONE
    }

}
