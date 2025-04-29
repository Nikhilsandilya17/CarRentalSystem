public class UPI implements PaymentStrategy{

    @Override
    public void processPayment(double amount) {
        System.out.println("Making payment of "+ amount+" through UPI");
    }
}
