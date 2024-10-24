package fr.fms.business;

import java.util.ArrayList;

import fr.fms.entities.Category;
import fr.fms.entities.Training;

public interface IBusiness {
	/**
	 * méthode qui ajoute une formation au panier
	 * @param article à ajouter
	 */
	public void addToCart(Training training);
	
	/**
	 * méthode qui retire une formation au panier s'il est dedans
	 * @param id de la formation à retirer
	 */
	public void rmFromCart(int id);	
	
	/**
	 * méthode qui renvoi sous la forme d'une liste tous les éléments du panier (gestion en mémoire)
	 * @return Liste des formations du panier
	 */
	public ArrayList<Training> getCart();	
	
	/**
	 * méthode qui réalise la commande en base avec l'idUser + total de la commande en cours + date du jour + contenu du panier :
	 * - la méthode va céer une commande en base -> idOrder + montant + date + idUser
	 * - puis va ajouter autant de commandes minifiées associées : orderItem -> idOrderItem + idArticle + Quantity + Price + idOrder
	 * @param idUser est l'identifiant du client qui est passé commande
	 * @return 1 si tout est ok 0 si pb 
	 */
	public int order(int idUser);
	
	/**
	 * méthode qui renvoi tous les formations de la table t_trainings en bdd
	 * @return Liste des formations en base
	 */
	public ArrayList<Training> readTrainings();	
	
	/**
	 * méthode renvoie la formation correspondant à l'id
	 * @param id de la formation à renvoyer
	 * @return formation correspondant si trouvé, null sinon
	 */
	public Training readOneTraining(int id);	
	
	/**
	 * méthode qui renvoi toutes les catégories de la table t_catégories en bdd
	 * @return Liste de catégories en base
	 */
	public ArrayList<Category> readCategories();
	
	/**
	 * méthode qui renvoi tous les formations d'une catégorie
	 * @param id de la catégorie
	 * @return Liste des formations
	 */
	public ArrayList<Training> readTrainingsByCatId(int idCat);
	
	public ArrayList<Training> findTrainingByKeyword(String keyword);
	
	public ArrayList<Training> findTrainingByFormat(String format);
}
