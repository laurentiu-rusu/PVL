package pizzashop.Lab4Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pizzashop.Lab4Tests.model.Payment;
import pizzashop.Lab4Tests.model.PaymentType;
import pizzashop.Lab4Tests.repository.MenuRepository;
import pizzashop.Lab4Tests.repository.PaymentRepository;
import pizzashop.service.PizzaService;

public class SRE_Integration {
    private PizzaService pizzaService;

    @BeforeEach
    void setUp() {
        PaymentRepository paymentRepository = new PaymentRepository();
        MenuRepository menuRepository = new MenuRepository();
        this.pizzaService = new PizzaService(menuRepository, paymentRepository);
    }

    @Test
    public void addPayment() {

        try {
            pizzaService.addPayment(1, PaymentType.Cash, 100d);
            Payment insertedPayment = pizzaService.getPayments().get(0);
            assert insertedPayment.getTableNumber() == 1;
            assert insertedPayment.getType() == PaymentType.Cash;
            assert insertedPayment.getAmount() == 100d;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getTotalAmount() {

        try {
            pizzaService.addPayment(1, PaymentType.Card, 100d);
            pizzaService.addPayment(2, PaymentType.Card, 50d);
            assert pizzaService.getTotalAmount(PaymentType.Cash) == 0d;
            assert pizzaService.getTotalAmount(PaymentType.Card) == 150d;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
