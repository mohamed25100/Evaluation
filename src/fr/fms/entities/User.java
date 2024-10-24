package fr.fms.entities;

public class User {

    private int idUser;
    private String login;
    private String password;
    private String role;
    
	public User(int idUser, String login, String password, String role) {
		this.idUser = idUser;
		this.login = login;
		this.password = password;
		this.role = role;
	}

	public User(String login, String password, String role) {
		this.login = login;
		this.password = password;
		this.role = role;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", login=" + login + ", password=" + password + ", role=" + role + "]";
	}
    
}
