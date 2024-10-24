package fr.fms.business;

import java.util.ArrayList;

import fr.fms.entities.Category;
import fr.fms.entities.Training;

public class IBusinessImpl implements IBusiness {

	@Override
	public void addToCart(Training training) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rmFromCart(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Training> getCart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int order(int idUser) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Training> readTrainings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Training readOneTraining(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Category> readCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Training> readTrainingsByCatId(int idCat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Training> findTrainingByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Training> findTrainingByFormat(String format) {
		// TODO Auto-generated method stub
		return null;
	}

}
