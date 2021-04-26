package pizzashop.service;

import pizzashop.Lab4Tests.model.MenuDataModel;
import pizzashop.Lab4Tests.model.Payment;
import pizzashop.Lab4Tests.model.PaymentType;
import pizzashop.Lab4Tests.repository.MenuRepository;
import pizzashop.Lab4Tests.repository.PaymentRepository;
import pizzashop.validator.PaymentValidator;

import java.util.List;

public class PizzaService {

    private MenuRepository menuRepo;
    private PaymentRepository payRepo;
    private PaymentValidator validator;

    public PizzaService(MenuRepository menuRepo, PaymentRepository payRepo){
        this.menuRepo=menuRepo;
        this.payRepo=payRepo;
        validator = new PaymentValidator();
    }

    public List<MenuDataModel> getMenuData(){return menuRepo.getMenu();}

    public List<Payment> getPayments(){
        if (payRepo == null) return null;

        return payRepo.getAll();
    }

    public boolean addPayment(int table, PaymentType type, double amount) throws Exception {
        validator.validateTable(table);
        validator.validateAmount(amount);
        Payment payment= new Payment(table, type, amount);
        payRepo.add(payment);
        return true;
    }

    public void setPayment(PaymentRepository payRepo) {
        this.payRepo = payRepo;
    }

    public double getTotalAmount(PaymentType type){
        double total=0.0f;
        List<Payment> l=getPayments();
        if ((l==null) ||(l.size()==0)) return total;
        for (Payment p:l){
            if (p.getType().equals(type))
                total+=p.getAmount();
        }
        return total;
    }

}
