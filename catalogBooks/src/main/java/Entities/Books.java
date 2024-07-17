package Entities;

public class Books {
	private int id;
	private int id_usuario;
	private String name;
	private String description;
	private String cover;
	
	
	public Books() {
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getId_usuario() {
		return id_usuario;
	}


	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
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


	public String getCover() {
		return cover;
	}


	public void setCover(String cover) {
		this.cover = cover;
	}


	public Books(int id, int id_usuario, String name, String description, String cover) {
		super();
		this.id = id;
		this.id_usuario = id_usuario;
		this.name = name;
		this.description = description;
		this.cover = cover;
	}
	
	
}
