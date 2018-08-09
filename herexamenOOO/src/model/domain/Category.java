package model.domain;

public class Category {

	private String title;
	private String description;
	private boolean mainCategory;
	
	
	public Category(String title, String description,boolean mainCategorie){
		setTitle(title);
		setDescription(description);
		this.mainCategory =mainCategorie;
	}
	public Category(String title, String description){
		setTitle(title);
		setDescription(description);
		this.mainCategory =true;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Boolean getMainCategory(){
		return this.mainCategory;
	}
	
	public void setTitle(String title) {
		if(title == null || title.isEmpty()){
			throw new DomainException("Name can not be empty!");
		}
		this.title = title;
	}
	
	public void setDescription(String description) {
		if(description == null || description.isEmpty()){
			throw new DomainException("Description can not be empty!");
		}
		this.description = description;
	}
	
}
