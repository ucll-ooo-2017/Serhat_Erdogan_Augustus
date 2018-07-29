package model.domain;

public class Categorie {

	private String name;
	private String description;
	
	
	public Categorie(String name, String description){
		setName(name);
		setDescription(description);
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setName(String name) {
		if(name == null || name.isEmpty()){
			throw new DomainException("Name can not be empty!");
		}
		this.name = name;
	}
	
	public void setDescription(String description) {
		if(description == null || description.isEmpty()){
			throw new DomainException("Description can not be empty!");
		}
		this.description = description;
	}
}
