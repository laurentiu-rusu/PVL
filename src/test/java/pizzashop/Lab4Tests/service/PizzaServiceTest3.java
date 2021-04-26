package pizzashop.Lab4Tests.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;
import pizzashop.service.PizzaService;

import static org.mockito.Mockito.mock;

class PizzaServiceTest3 {
    Payment payment;

    @Mock
    private PaymentRepository repository;

    @InjectMocks
    private PizzaService pizzaService;

    @BeforeEach
    void setUp() {
        payment = mock(Payment.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addPayment() throws Exception {
        Mockito.when(repository.getAll()).thenReturn(null);
        assert repository.getAll() == null;

        Mockito.when(payment.getTableNumber()).thenReturn(1);
        Mockito.when(payment.getType()).thenReturn(PaymentType.Card);
        Mockito.when(payment.getAmount()).thenReturn(50d);

        pizzaService.addPayment(1, PaymentType.Cash, 20d);
        assert pizzaService.getPayments().get(0).getTableNumber() == 1;

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
    public void testPaymentRepository() {
        PaymentRepository paymentRepository = mock(PaymentRepository.class);

        Payment payment1 = new Payment(1, PaymentType.Card, 10d);
        Payment payment2 = new Payment(2, PaymentType.Card, 20d);
        Payment payment3 = new Payment(3, PaymentType.Card, 30d);

        Mockito.doNothing().when(paymentRepository).add(payment1);
        Mockito.doNothing().when(paymentRepository).add(payment2);
        Mockito.doNothing().when(paymentRepository).add(payment3);

        paymentRepository.add(payment1);
        paymentRepository.add(payment2);
        paymentRepository.add(payment3);

        Mockito.verify(paymentRepository).add(payment1);
        Mockito.verify(paymentRepository).add(payment2);
        Mockito.verify(paymentRepository).add(payment3);
    }

    @Test
    public void totalAmount() throws Exception {
        pizzaService = mock(PizzaService.class);

        pizzaService.addPayment(1, PaymentType.Card, 10d);
        pizzaService.addPayment(2, PaymentType.Card, 20d);
        pizzaService.addPayment(3, PaymentType.Cash, 30d);

        Mockito.when(pizzaService.getTotalAmount(PaymentType.Card)).thenReturn(30d);

        pizzaService.getTotalAmount(PaymentType.Card);

        Mockito.verify(pizzaService).getTotalAmount(PaymentType.Card);
    }

} 
