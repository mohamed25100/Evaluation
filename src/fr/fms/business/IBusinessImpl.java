package fr.fms.business;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;

import fr.fms.dao.Dao;
import fr.fms.dao.DaoFactory;
import fr.fms.dao.TrainingDao;
import fr.fms.entities.Category;
import fr.fms.entities.Customer;
import fr.fms.entities.Order;
import fr.fms.entities.OrderItem;
import fr.fms.entities.Training;

public class IBusinessImpl implements IBusiness {
	private HashMap<Integer,Training> cart;
	private Dao<Training> trainingDao = DaoFactory.getTrainingDao();
	//private TrainingDao trainingDao = new TrainingDao();
	//private Dao<User> userDao = DaoFactory.getUserDao();
	private Dao<Category> categoryDao = DaoFactory.getCategoryDao();
	private Dao<Order> orderDao = DaoFactory.getOrderDao();
	private Dao<OrderItem> orderItemDao = DaoFactory.getOrderItemDao();
	private Dao<Customer> customerDao = DaoFactory.getCustomerDao();
	
	public IBusinessImpl() {
		this.cart = new HashMap<Integer,Training>();
	}

	@Override
	public void addToCart(Training training) {
		Training tra = cart.get(training.getIdTraining());
		if(tra != null) {
			tra.setQuantity(tra.getQuantity() + 1);
		}
		else cart.put(training.getIdTraining(), training);
	}

	@Override
	public void rmFromCart(int id) {
		Training training = cart.get(id);
		if(training != null) {
			if(training.getQuantity() == 1)	cart.remove(id);
			else training.setQuantity(training.getQuantity() - 1);
		}	
	}

	@Override
	public ArrayList<Training> getCart() {
		return new ArrayList<Training> (cart.values());
	}

	@Override
	public int order(int idCustomer) {
		if(customerDao.read(idCustomer) != null) {
			double total = getTotal(); 
			Order order = new Order(total, new Date(), idCustomer);
			if(orderDao.create(order)) {	
				for(Training training : cart.values()) {	
					orderItemDao.create(new OrderItem(0, training.getIdTraining(), training.getQuantity(), training.getPrice(), order.getIdOrder()));
				}
				return order.getIdOrder();
			}
		}
		return 0;
	}

	@Override
	public ArrayList<Training> readTrainings() {
		return trainingDao.readAll();
	}

	@Override
	public Training readOneTraining(int id) {
		return trainingDao.read(id);
	}

	@Override
	public ArrayList<Category> readCategories() {
		return categoryDao.readAll();
	}

	@Override
	public ArrayList<Training> readTrainingsByCatId(int id) {
		return ((TrainingDao) trainingDao).readAllByCat(id);
	}
	
	/**
	 * renvoi le total de la commande en cours
	 * @return total
	 */
	public double getTotal() {
		double [] total = {0};
		cart.values().forEach((a) -> total[0] += a.getPrice() * a.getQuantity()); 	
		return total[0];
	}
	
	public boolean isCartEmpty() {
		return cart.isEmpty();
	}
	
	public void clearCart() {
		cart.clear();		
	}
	
	public Category readOneCategory(int id) {
		return categoryDao.read(id);
	}

	@Override
	public ArrayList<Training> findTrainingByKeyword(String keyword) {
		return ((TrainingDao) trainingDao).readAllByKeyword(keyword);
	}

	@Override
	public ArrayList<Training> findTrainingByFormat(String format) {
		return ((TrainingDao) trainingDao).readAllByFormat(format);
	}
}
