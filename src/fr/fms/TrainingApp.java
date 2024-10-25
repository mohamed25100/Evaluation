package fr.fms;

import java.util.ArrayList;
import java.util.Scanner;

import fr.fms.authentication.Authenticate;
import fr.fms.business.IBusinessImpl;
import fr.fms.entities.Category;
import fr.fms.entities.Customer;
import fr.fms.entities.Training;

public class TrainingApp {
	private static Scanner scan = new Scanner(System.in); 
	private static IBusinessImpl business = new IBusinessImpl();
	private static Authenticate authenticate = new Authenticate();
	
	private static final String COLUMN_ID = "IDENTIFIANT";
	private static final String COLUMN_NAME = "NOM";
	private static final String COLUMN_DESCRIPTION = "DESCRIPTION";
	private static final String COLUMN_DURATIONDAYS = "DURÉE EN JOURS";
	private static final String COLUMN_FORMAT = "PRÉSENTIEL OU DIST";
	private static final String COLUMN_DURATIONPRICE = "PRIX";
	private static final String COLUMN_QUANTITE = "QUANTITE";
	
	private static int idUser = 0;
	private static String login = null; 
	
	public static void main(String[] args) {
		System.out.println("Bonjour et bienvenu dans ma boutique, voici la liste des formations en stock\n");
		
		displayTrainings();
		int choice = 0;
		while(choice != 10) {
			displayMenu();
			choice = scanInt();
			switch(choice) {
			case 1:
				addTraining();
				break;
			case 2:
				removeTraining();
				break;
			case 3:
				displayCart(true);
				break;
			case 4:
				displayTrainings();
				break;
			case 5:
				displayCategories();
				break;
			case 6:
				displayTrainingsByCategoryId();
				break;
			case 7:
				displaykeyword();
				break;
			case 8:
				displayFormat();
				break;
			case 9:
				connection();
				break;
			case 10:
				System.out.println("à bientôt dans notre boutique :)");
				break;
			default:
				System.out.println("Veuillez saisir une valeur entre 1 et 10");
			
			}
		}
	}
	
	public static void displayFormat() {
	    System.out.println("Saisissez le format (presentiel/distanciel) :");
	    String format = scan.next();
	    
	 // Vérifiez si l'entrée est valide
	    if(!format.equalsIgnoreCase("presentiel") && !format.equalsIgnoreCase("distanciel")) {
	        System.out.println("Format invalide, veuillez saisir 'presentiel' ou 'distanciel'.");
	        return;
	    }

	    // Récupère les formations en fonction du format
	    ArrayList<Training> trainings = business.findTrainingByFormat(format);
	    
	    // Si aucune formation n'est trouvée, afficher le message
	    if(trainings.isEmpty()) {
	        System.out.println("Aucune formation trouvée pour le format " + format + ".");
	    } else {
	        // Afficher les formations dans le format donné
	        System.out.printf("FORMATION DISPONIBLES EN %s%n", format.toUpperCase());
	        System.out.printf("-----------------------------------------------------------------------------------------------------------%n");
	        System.out.printf("%-15s | %-50s | %-70s | %-15s | %-20s | %-10s%n", COLUMN_ID, COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_DURATIONDAYS, COLUMN_FORMAT, COLUMN_DURATIONPRICE);
	        System.out.printf("-----------------------------------------------------------------------------------------------------------%n");
	        
	        // afficher chaque formation en respectant le format
	        trainings.forEach(t -> System.out.printf("%-15s | %-50s | %-70s | %-15s | %-20s | %-10s%n", t.getIdTraining(), t.getName(), t.getDescription(), t.getDurationDays(), t.getFormat(), t.getPrice()));
	    }
	}


	public static void displaykeyword() {
		System.out.println("Saisissez un mot-clé:");
		String weyword = scan.next();
		ArrayList<Training> training = business.findTrainingByKeyword(weyword);
		if (training.isEmpty() != true) {
			System.out.printf("              AFFICHAGE PAR CATEGORIE    %n");
			System.out.printf("%-15s | %-35s | %-25s | %-15s %n", COLUMN_ID, COLUMN_DESCRIPTION, COLUMN_DURATIONDAYS,COLUMN_DURATIONPRICE);
			business.findTrainingByKeyword(weyword).forEach(t -> System.out.printf("%-15s | %-35s | %-25s | %-15s%n",
					t.getIdTraining(), t.getDescription(), t.getDurationDays(), t.getPrice()));
			;
		} else
			System.out.println("Aucune formation trouvé avec ce mot clé !");
	}

	public static void displayTrainingsByCategoryId() {
		System.out.println("saisissez l'id de la catégorie ");
		int id = scanInt();
		Category category = business.readOneCategory(id);
		if(category != null) {
			System.out.printf("AFFICHAGE PAR CATEGORIE : %s   %n", category.getName());
			System.out.printf("------------------------------------------------------------%n");
			System.out.printf("%-15s | %-50s | %-70s | %-15s | %-20s | %-10s%n",COLUMN_ID,COLUMN_NAME,COLUMN_DESCRIPTION,COLUMN_DURATIONDAYS,COLUMN_FORMAT,COLUMN_DURATIONPRICE);
			System.out.printf("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");
			business.readTrainingsByCatId(id).forEach( t -> System.out.printf("%-15s | %-50s | %-70s | %-15s | %-20s | %-10s%n",t.getIdTraining(),t.getName(),t.getDescription(),t.getDurationDays(),t.getFormat(),t.getPrice()));
		}
		else System.out.println("cette catégorie n'existe pas !");
	
	}

	/**
	 * Méthode qui affiche toutes les catégories
	 */
	public static void displayCategories() {
		System.out.printf("---------------------------------------------------------------------------------------------------%n");
		System.out.printf("%-25s | %-25s | %-25s %n",COLUMN_ID,COLUMN_NAME,COLUMN_DESCRIPTION);
		System.out.printf("---------------------------------------------------------------------------------------------------%n");
		business.readCategories().forEach( c -> System.out.printf("%-25s | %-25s | %-25s %n",c.getId(),c.getName(),c.getDescription()));		
	}

	public static void displayCart(boolean flag) {
		if(business.isCartEmpty()) 	System.out.println("PANIER VIDE");
		else {
			System.out.println("CONTENU DU PANIER :");
			System.out.printf("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");
			System.out.printf("%-15s | %-50s | %-70s | %-15s | %-20s | %-10s | %-10s%n", COLUMN_ID, COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_DURATIONDAYS, COLUMN_FORMAT, COLUMN_DURATIONPRICE, COLUMN_QUANTITE);
			System.out.printf("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");
			business.getCart().forEach(t -> System.out.printf("%-15s | %-50s | %-70s | %-15s | %-20s | %-10s | %-10s%n",t.getIdTraining(),t.getName(),t.getDescription(),t.getDurationDays(),t.getFormat(),t.getPrice(),t.getQuantity()));
			if(flag) {
				System.out.println("MONTANT TOTAL : " + business.getTotal());
				System.out.println("Souhaitez vous passer commande ? Oui/Non :");
				if(scan.next().equalsIgnoreCase("Oui")) {
					nextStep();
				}
			}
		}
	}

	/**
	 * Méthode qui passe la commande, l'utilisateur doit être connecté
	 * si c'est le cas, l'utilisateur sera invité à associé un client à sa commande
	 * si le client n'existe pas, il devra le créer
	 * puis une commande associée au client sera ajoutée en base
	 * De même, des commandes minifiées seront associées à la commande
	 * une fois toutes les opérations terminées correctement, le panier sera vidé et un numéro de commande attribué
	 */
	private static void nextStep() {
		if(login == null)	{ 
			System.out.println("Vous devez être connecté pour continuer");
			connection();
		}
		if(login != null) {
			int idCustomer = newCustomer(idUser);	
			if(idCustomer != 0) {
				int idOrder = business.order(idCustomer);	
				if(idOrder == 0)	System.out.println("pb lors du passage de commande");
				else {
					System.out.println("Votre commande a bien été validé, voici son numéro : " + idOrder);
					business.clearCart();
				}
			}
		}
	}

	/**
	 * Méthode qui ajoute un client en base s'il n'existe pas déjà 
	 * @return id du client afin de l'associer à la commande en cours
	 */
	private static int newCustomer(int idUser) {
		System.out.println("Avez vous déjà un compte client ? Saisissez une adresse email valide :");
		String email = scan.next();		
		if(isValidEmail(email)) {	
			Customer customer = authenticate.existCustomerByEmail(email);
			if(customer == null) {
				System.out.println("Nous n'avons pas de compte client associé, nous allons le créer ");
				scan.nextLine();	
				System.out.println("saisissez votre nom :");
				String name = scan.nextLine();
				System.out.println("saisissez votre prénom :");
				String fName = scan.next();					
				System.out.println("saisissez votre tel :");
				String tel = scan.next();		
				scan.nextLine(); 
				System.out.println("saisissez votre adresse :");
				String address = scan.nextLine();
				Customer cust = new Customer(name, fName, email, tel, address, idUser); 
				if(authenticate.addCustomer(cust))	
					return authenticate.existCustomerByEmail(email).getIdCustomer();
			}
			else {
				System.out.println("Nous allons associer la commande en cours avec le compte client : " + customer);
				return customer.getIdCustomer();
			}
		}
		else System.out.println("vous n'avez pas saisi un email valide");	
		return 0;
	}

	public static boolean isValidEmail(String email) {
		String regExp = "^[A-Za-z0-9._-]+@[A-Za-z0-9._-]+\\.[a-z][a-z]+$";	
		return email.matches(regExp);
	}

	/**
	 * Méthode qui réalise la connexion/deconnexion d'un utilisateur
	 * si l'utilisateur n'existe pas, il lui est proposé d'en créer un
	 */
	private static void connection() {
		if(login != null) {
			System.out.println("Souhaitez vous vous déconnecter ? Oui/Non");
			String response = scan.next();
			if(response.equalsIgnoreCase("Oui")) {
				System.out.println("A bientôt " + login);
				login = null;
				idUser = 0;
			}				
		}
		else {
			System.out.println("saisissez votre login : ");
			String log = scan.next();
			System.out.println("saisissez votre password : ");
			String pwd = scan.next();
			
			int id = authenticate.existUser(log,pwd);
			if(id > 0)	{
				login = log;
				idUser = id;
			}
			else {
				System.out.println("login ou password incorrect");
				System.out.println("Nouvel utilisateur, pour créer un compte, tapez ok");
				String ok = scan.next();
				if(ok.equalsIgnoreCase("ok")) {
					newUser();
				}
			}
		}
	}

	/**
	 * Méthode qui ajoute un nouvel utilisateur en base
	 */
	public static void newUser() {
		System.out.println("saisissez un login :");
		String login = scan.next();			
		int id = authenticate.existUser(login);	
		if(id == 0) { 
			System.out.println("saisissez votre password :");
			String password = scan.next();
			authenticate.addUser(login,password);		
			System.out.println("Ne perdez pas ces infos de connexion...");
			stop(2);
			System.out.println("création de l'utilisateur terminé, merci de vous connecter");
		}
		else	System.out.println("Login déjà existant en base, veuillez vous connecter");
	}

	public static void stop(int time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void removeTraining() {
		System.out.println("Selectionner l'id de la formation à supprimer du panier");
		int id = scanInt();
		business.rmFromCart(id);
		displayCart(false);
	}

	public static void addTraining() {
		System.out.println("Selectionner l'id de la formation à ajouter au panier");
		int id = scanInt();
		Training training = business.readOneTraining(id);
		if(training != null) {
			business.addToCart(training);
			displayCart(false);
		}
		else System.out.println("La formation que vous souhaitez ajouter n'existe pas, pb de saisi id");
	
	}

	/**
	 * Méthode qui affiche le menu principale
	 */
	public static void displayMenu() {
		if(login != null)	System.out.print("Compte : " + login);
		System.out.println("\n" + "Pour réaliser une action, tapez le code correspondant");
		System.out.println("1 : Ajouter une formation au panier");
		System.out.println("2 : Retirer une formation du panier");
		System.out.println("3 : Afficher mon panier + total pour passer commande");
		System.out.println("4 : Afficher tous les formations en stock");
		System.out.println("5 : Afficher toutes les catégories en base");
		System.out.println("6 : Afficher tous les formations d'une catégorie");
		System.out.println("7 : Afficher les formations par mot clé");
		System.out.println("8 : Afficher les formations en présentiel ou à dist");
		System.out.println("9 : Connexion(Deconnexion) à votre compte");
		System.out.println("10 : sortir de l'application");
	}

	public static void displayTrainings() {
		System.out.printf("%-15s | %-50s | %-70s | %-15s | %-20s | %-10s%n",COLUMN_ID,COLUMN_NAME,COLUMN_DESCRIPTION,COLUMN_DURATIONDAYS,COLUMN_FORMAT,COLUMN_DURATIONPRICE);
		System.out.printf("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");
		business.readTrainings().forEach(t -> System.out.printf("%-15s | %-50s | %-70s | %-15s | %-20s | %-10s%n",t.getIdTraining(),t.getName(),t.getDescription(),t.getDurationDays(),t.getFormat(),t.getPrice()));
	}
	
	public static int scanInt() {
		while(!scan.hasNextInt()) {
			System.out.println("saisissez une valeur entière svp");
			scan.next();
		}
		return scan.nextInt();
	}
}
