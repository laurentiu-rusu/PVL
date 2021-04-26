package pizzashop.Lab4Tests.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pizzashop.Lab4Tests.model.PaymentType;
import pizzashop.service.PizzaService;

import static org.mockito.Mockito.mock;

class PizzaServiceTest3 {
    private PizzaService pizzaService;

    @Test
    public void testAdd() throws Exception {
        pizzaService = mock(PizzaService.class);

        Mockito.when(pizzaService.addPayment(1, PaymentType.Card, 10d)).thenReturn(true);
        Mockito.when(pizzaService.addPayment(2, PaymentType.Card, 20d)).thenReturn(true);
        Mockito.when(pizzaService.addPayment(3, PaymentType.Cash, 30d)).thenReturn(true);

        pizzaService.addPayment(1, PaymentType.Card, 10d);
        pizzaService.addPayment(2, PaymentType.Card, 20d);
        pizzaService.addPayment(3, PaymentType.Cash, 30d);

        Mockito.verify(pizzaService).addPayment(1, PaymentType.Card, 10d);
        Mockito.verify(pizzaService).addPayment(2, PaymentType.Card, 20d);
        Mockito.verify(pizzaService).addPayment(3, PaymentType.Cash, 30d);

    }

    @Test
    public void testTotalAmount() throws Exception {
        pizzaService = mock(PizzaService.class);

        pizzaService.addPayment(1, PaymentType.Card, 10d);
        pizzaService.addPayment(2, PaymentType.Card, 20d);
        pizzaService.addPayment(3, PaymentType.Cash, 30d);

        Mockito.when(pizzaService.getTotalAmount(PaymentType.Card)).thenReturn(30d);

        pizzaService.getTotalAmount(PaymentType.Card);

        Mockito.verify(pizzaService).getTotalAmount(PaymentType.Card);
    }

} 