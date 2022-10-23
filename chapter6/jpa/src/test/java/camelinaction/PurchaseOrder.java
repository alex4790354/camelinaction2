package camelinaction;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PurchaseOrder implements Serializable {
    
	private static final long serialVersionUID = 414632787969243627L;

    @Id // TODO: check
    private String name;
    private double amount;
    private String customer;

    public PurchaseOrder() {
    }
    
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCustomer() {
        return customer;
    }
}
