package fr.fms;

import java.util.Scanner;

import fr.fms.dao.CategoryDao;
import fr.fms.dao.TrainingDao;
import fr.fms.dao.UserDao;
import fr.fms.entities.Category;
import fr.fms.entities.Training;
import fr.fms.entities.User;

public class TestDaoComponents {
	public static void main(String[] args) {
		TrainingDao trainingDao = new TrainingDao();
		CategoryDao categoryDao = new CategoryDao();
		UserDao userDao = new UserDao();
		
		//Afficher tous les formations
		System.out.println("--------Afficher tous les formations----------");
		for(Training training : trainingDao.readAll()) {
			System.out.println(training);
		}
		
		
		//Afficher une formation à partir de son id
		System.out.println("--------Afficher une formation à partir de son id----------");
		Training training = trainingDao.read(2);
		System.out.println(training);
		
		//Mise à jour de l'article
		if(training != null) {
			System.out.println("--------Mise à jour de l'article----------");
			training.setDescription("Comprehensive guide to Angular");
			trainingDao.update(training);
			System.out.println(training);
		}
		
		//Supprimer une formation
		
		if(training != null) {	
			System.out.println("-------Supprimer une formation-----------");
			trainingDao.delete(training);
		}
		
		//Afficher tous les formations par cat
		System.out.println("--------Afficher tous les formations par cat----------");
		for(Training training1 : trainingDao.readAllByCat(1)) {
			System.out.println(training1);
		}
		
        // Afficher toutes les formations contenant un mot clé
        Scanner scan = new Scanner(System.in);
        System.out.println("-------- Recherche des formations par mot clé ----------");
        System.out.print("Entrez un mot clé : ");
        String keyword = scan.nextLine();
        for (Training t : trainingDao.readAllByKeyword(keyword)) {
            System.out.println(t);
        }
		
        // Recherche par format (présentiel ou distanciel)
        System.out.println("-------- Afficher les formations par format ----------");
        System.out.print("Entrez le format 1-présentiel ou 2-distanciel) : ");
        try {
        	int format = scan.nextInt();
        	switch(format) {
	        case 1 :  
		        for (Training training2 : trainingDao.readAllByFormat("presentiel")) {
		            System.out.println(training2);
		        }
		        break;
	        case 2 : 
	        	for (Training training2 : trainingDao.readAllByFormat("distanciel")) {
	                System.out.println(training2);
	            }
	        	break;
	        default : System.out.println("numéro non valide.");
        	}
        }catch(java.util.InputMismatchException e) {
        	System.err.println("veuillez entrer un numéro valide");
        }
        
        
        
        System.out.println("------------------");
        
        
		//Afficher une cat à partir de son id
		System.out.println("--------Afficher une cat à partir de son id----------");
		Category cat = categoryDao.read(2);
		System.out.println(cat);
		
		//Afficher tous les cat
		System.out.println("--------Afficher tous les cat----------");
		for(Category cat1 : categoryDao.readAll()) {
			System.out.println(cat1);
		}
		System.out.println("------------------");
		
		//Afficher tous les users
				System.out.println("--------Afficher tous les userss----------");
				for(User user: userDao.readAll()) {
					System.out.println(user);
				}
				System.out.println("------------------");
	}
}
