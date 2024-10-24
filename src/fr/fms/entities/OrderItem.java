package fr.fms.entities;

/**
 * The OrderItem class represents an individual item in an order.
 * It contains details about the training course, quantity ordered, unitary price,
 * and the associated order ID.
 */
public class OrderItem {
    /**
     * Unique identifier for the order item.
     */
    private int idOrderItem;
    
    /**
     * Unique identifier for the training course associated with this order item.
     */
    private int idTraining;
    
    /**
     * The quantity of the training course ordered.
     */
    private int quantity;
    
    /**
     * The unitary price of the training course.
     */
    private double unitaryPrice;
    
    /**
     * Unique identifier for the order that this item belongs to.
     */
    private int idOrder;

    /**
     * Constructs an OrderItem with the specified details.
     *
     * @param idOrderItem the unique identifier of the order item
     * @param idTraining the unique identifier of the training course
     * @param quantity the quantity of the training course ordered
     * @param unitaryPrice the unitary price of the training course
     * @param idOrder the unique identifier of the order this item belongs to
     */
    public OrderItem(int idOrderItem, int idTraining, int quantity, double unitaryPrice, int idOrder) {
        this.idOrderItem = idOrderItem;
        this.idTraining = idTraining;
        this.quantity = quantity;
        this.unitaryPrice = unitaryPrice;
        this.idOrder = idOrder;
    }

	public int getIdOrderItem() {
		return idOrderItem;
	}

	public void setIdOrderItem(int idOrderItem) {
		this.idOrderItem = idOrderItem;
	}

	public int getIdTraining() {
		return idTraining;
	}

	public void setIdTraining(int idTraining) {
		this.idTraining = idTraining;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitaryPrice() {
		return unitaryPrice;
	}

	public void setUnitaryPrice(double unitaryPrice) {
		this.unitaryPrice = unitaryPrice;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	@Override
	public String toString() {
		return "OrderItem [idOrderItem=" + idOrderItem + ", idTraining=" + idTraining + ", quantity=" + quantity
				+ ", unitaryPrice=" + unitaryPrice + ", idOrder=" + idOrder + "]";
	}

    
}
