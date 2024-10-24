package fr.fms.entities;

import java.sql.Date;

/**
 * The Order class represents an order placed by a customer.
 * It contains details about the order such as the order ID, total amount,
 * date of the order, and the associated customer ID.
 */
public class Order {
    /**
     * Unique identifier for the order.
     */
    private int idOrder;
    
    /**
     * The total amount of the order.
     */
    private double amount;
    
    /**
     * The date when the order was placed.
     */
    private Date date;
    
    /**
     * Unique identifier for the customer who placed the order.
     */
    private int idCustomer;

    /**
     * Constructs a new Order with the specified details.
     *
     * @param idOrder the unique identifier of the order
     * @param amount the total amount of the order
     * @param date the date when the order was placed
     * @param idCustomer the unique identifier of the customer who placed the order
     */
    public Order(int idOrder, double amount, Date date, int idCustomer) {
        this.idOrder = idOrder;
        this.amount = amount;
        this.date = date;
        this.idCustomer = idCustomer;
    }

	public Order(double amount, Date date, int idCustomer) {
		super();
		this.amount = amount;
		this.date = date;
		this.idCustomer = idCustomer;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}

	@Override
	public String toString() {
		return "Order [idOrder=" + idOrder + ", amount=" + amount + ", date=" + date + ", idCustomer=" + idCustomer
				+ "]";
	}
}
