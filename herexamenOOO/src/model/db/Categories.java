package model.db;

import java.util.ArrayList;

import model.domain.Category;

public class Categories {

	private ArrayList<Category> categories;

	public Categories() {
		this.categories = new ArrayList<Category>();
	}

	public void addCategorie(Category categorie) {

		if (categorie == null) {
			throw new DbException("The categorie can not be null!");
		} else {
			for (Category cat : this.categories) {
				if (cat.getTitle().equals(categorie.getTitle())
						&& cat.getDescription().equals(categorie.getDescription())) {
					throw new DbException("This categorie already exist!");
				}
			}
		}
		this.categories.add(categorie);
	}

	public void deleteCategorie(Category categorie){
		this.categories.remove(categorie.getTitle());
	}
	
	public Category getCategory(String name){
		
		Category result = null;
		for(Category cat:this.categories){
			if(cat.getTitle()==name){
				result = cat;
			}
		}
		if(result ==null ){
			throw new DbException("Categorie was not found!");
		}else{
			return result;
		}
	}
	
	public int getSizeCategories(){
		return this.categories.size();
	}
	
	public ArrayList<Category> getCategories(){
		return categories;
	}
}
