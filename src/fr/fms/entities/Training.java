package fr.fms.entities;

public class Training {
	private int idTraining;
	private String name;
	private String description;
	private int durationDays;
	private String format; // presentiel ou distanciel
	private double price;
	private int idCategory; // Clé étrangère vers la catégorie
	public Training(int idTraining, String name, String description, int durationDays, String format, double price, int idCategory) {
		this.idTraining = idTraining;
		this.name = name;
		this.description = description;
		this.durationDays = durationDays;
		this.format = format;
		this.price = price;
		this.idCategory = idCategory;
	}
	
	
	
	public Training(int idTraining, String name, String description, int durationDays, String format, double price) {
		this.idTraining = idTraining;
		this.name = name;
		this.description = description;
		this.durationDays = durationDays;
		this.format = format;
		this.price = price;
	}



	public int getIdTraining() {
		return idTraining;
	}
	public void setIdTraining(int idTraining) {
		this.idTraining = idTraining;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDurationDays() {
		return durationDays;
	}
	public void setDurationDays(int durationDays) {
		this.durationDays = durationDays;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	@Override
	public String toString() {
		return "Training [idTraining=" + idTraining + ", name=" + name + ", description=" + description
				+ ", durationDays=" + durationDays + ", format=" + format + ", price=" + price + ", idCategory="
				+ idCategory + "]";
	}

	
	
}
