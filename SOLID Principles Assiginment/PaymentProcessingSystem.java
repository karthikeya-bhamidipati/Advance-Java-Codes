// to follow SRP, creating a abstract payment method interface
interface PaymentMethod {
    void processPayment(double amount);
}

// to implement OCP and LSP
// concrete implementations for different payment methods
class Paytm implements PaymentMethod {
    public void processPayment(double amount) {
        System.out.println("Processing Paytm payment of $" + amount);
    }
}

class gpay implements PaymentMethod {
    public void processPayment(double amount) {
        System.out.println("Processing Google Pay payment of $" + amount);
    }
}

class creditCard implements PaymentMethod {
    public void processPayment(double amount) {
        System.out.println("Processing Credit Card payment of $" + amount);
    }
}

// following SRP, separate class for payment validation
class PaymentValidation {
    public boolean validate(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid payment amount.");
            return false;
        }
        return true;
    }
}

// followig SRP, separate class for logging payments
class PaymentLogger {
    public void logPayment(double amount, String method) {
        System.out.println("Logged: " + method + " payment of $" + amount);
    }
}

// following the DIP, PaymentProcessor depends on abstraction
// and not concrete classes
// this class depends on abstractions rather than concrete classes.
class PaymentProcessor {
    private PaymentMethod paymentMethod; // accepts any type of payment method object
    private PaymentValidation validator;
    private PaymentLogger logger;

    public PaymentProcessor(PaymentMethod pm) {
        paymentMethod = pm;
        validator = new PaymentValidation();
        logger = new PaymentLogger();
    }

    // process method to process the payment
    public void process(double amount) {
        if (validator.validate(amount)) {
            paymentMethod.processPayment(amount);
            logger.logPayment(amount, paymentMethod.getClass().getSimpleName());
        }
    }
}

public class PaymentProcessingSystem {
    public static void main(String[] args) {
        // creating objects of different payment methods
        PaymentProcessor creditCard = new PaymentProcessor(new creditCard());
        PaymentProcessor Paytm = new PaymentProcessor(new Paytm());
        PaymentProcessor gpay = new PaymentProcessor(new gpay());

        // using the process method of different payment methods
        creditCard.process(100);
        Paytm.process(200);
        gpay.process(300);
    }
}
