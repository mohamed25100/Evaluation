package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import fr.fms.entities.Training;

public class TrainingDao implements Dao<Training> {

	@Override
	public boolean create(Training obj) {
		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO T_Trainings (Name, Description, DurationDays, Format,Price,IdCategory) VALUES (?,?,?,?,?,?);")){
			ps.setString(1, obj.getName());
			ps.setString(2, obj.getDescription());
			ps.setInt(3, obj.getDurationDays());
			ps.setString(4, obj.getFormat());
			ps.setDouble(5, obj.getPrice());
			ps.setInt(6, obj.getIdCategory());
			if(ps.executeUpdate() == 1) return true;
		}catch(SQLException e) {
			logger.severe("pb sql sur la création d'une formation " + e.getMessage());
		}
		return false;
	}

	@Override
	public Training read(int id) {
		try (Statement statement = connection.createStatement()){
			ResultSet rs = statement.executeQuery("SELECT * FROM T_Trainings where IdTraining=" + id + ";");
			if(rs.next()) return new Training(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDouble(6), rs.getInt(7));
		} catch (SQLException e) {
			logger.severe("pb sql sur la lecture d'une formation " + e.getMessage());
		} 	
		return null;
	}

	@Override
	public boolean update(Training obj) {
		try (PreparedStatement ps = connection.prepareStatement("UPDATE T_Trainings set Name=? , Description=? , DurationDays=? , Format=? , Price=? , IdCategory=? where IdTraining=?;")){
				ps.setString(1, obj.getName());
				ps.setString(2, obj.getDescription());
				ps.setInt(3, obj.getDurationDays());
				ps.setString(4, obj.getFormat());
				ps.setDouble(5, obj.getPrice());
				ps.setInt(6, obj.getIdCategory());
				ps.setInt(7, obj.getIdTraining());
				if( ps.executeUpdate() == 1)	return true;
		} catch (SQLException e) {
			logger.severe("pb sql sur la mise à jour d'une formation " + e.getMessage());
		} 	
		return false;
	}

	@Override
	public boolean delete(Training obj) {
		try (Statement statement = connection.createStatement()){
			statement.executeUpdate("DELETE FROM T_Trainings where IdTraining=" + obj.getIdTraining() + ";");		
			return true;
		} catch (SQLException e) {
			logger.severe("pb sql sur la suppression d'une formation " + e.getMessage());
		} 
		return false;
	}

	@Override
	public ArrayList<Training> readAll() {
		ArrayList<Training> trainings = new ArrayList<Training>();
		try(Statement statement = connection.createStatement()){
			try(ResultSet resultSet = statement.executeQuery("SELECT * FROM T_Trainings")){ 
				while(resultSet.next()) {
					int idTraining = resultSet.getInt(1);
					String name = resultSet.getString(2);
					String description = resultSet.getString(3);
					int durationDays = resultSet.getInt(4);
					String format = resultSet.getString(5);
					double price = resultSet.getDouble(6);
					trainings.add(new Training(idTraining, name, description, durationDays, format, price));
				}
				
			}
		}catch(SQLException e) {
			logger.severe("pb sql sur revoi de toute formations " + e.getMessage());
		}
		return trainings;
	}
	
	public ArrayList<Training> readAllByCat(int id) {
		ArrayList<Training> trainings = new ArrayList<Training>();
		try(Statement statement = connection.createStatement()){
			try(ResultSet resultSet = statement.executeQuery("SELECT * FROM T_Trainings where idCategory=" + id)){
				while(resultSet.next()) {
					int idTraining = resultSet.getInt(1);
					String name = resultSet.getString(2);
					String description = resultSet.getString(3);
					int durationDays = resultSet.getInt(4);
					String format = resultSet.getString(5);
					double price = resultSet.getDouble(6);
					trainings.add(new Training(idTraining, name, description, durationDays, format, price));
				}
			}
		} catch (SQLException e) {
			logger.severe("pb sql sur renvoir des formations d'une catégorie " + e.getMessage());
		}
		return trainings;
		
	}
	
	public ArrayList<Training> readAllByKeyword(String keyword) {
	    ArrayList<Training> trainings = new ArrayList<Training>();
	    try (PreparedStatement ps = connection.prepareStatement(
	        "SELECT * FROM T_Trainings WHERE Name LIKE ? OR Description LIKE ?")) {
	        // Utilisation de % pour chercher des correspondances partielles
	        String queryKeyword = "%" + keyword + "%";
	        ps.setString(1, queryKeyword);
	        ps.setString(2, queryKeyword);
	        
	        try (ResultSet resultSet = ps.executeQuery()) {
	            while (resultSet.next()) {
	                int idTraining = resultSet.getInt(1);
	                String name = resultSet.getString(2);
	                String description = resultSet.getString(3);
	                int durationDays = resultSet.getInt(4);
	                String format = resultSet.getString(5);
	                double price = resultSet.getDouble(6);
	                trainings.add(new Training(idTraining, name, description, durationDays, format, price));
	            }
	        }
	    } catch (SQLException e) {
	        logger.severe("pb sql pendant la recherche: " + e.getMessage());
	    }
	    return trainings;
	}
    public ArrayList<Training> readAllByFormat(String format) {
        ArrayList<Training> trainings = new ArrayList<Training>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM T_Trainings WHERE Format = ?")) {
            ps.setString(1, format); // Filtre par le format fourni
            
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    int idTraining = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    String description = resultSet.getString(3);
                    int durationDays = resultSet.getInt(4);
                    String trainingFormat = resultSet.getString(5); // format de la formation
                    double price = resultSet.getDouble(6);
                    trainings.add(new Training(idTraining, name, description, durationDays, trainingFormat, price));
                }
            }
        } catch (SQLException e) {
            logger.severe("pb sql lors de la récupération des formations par format : " + e.getMessage());
        }
        return trainings;
    }

}
