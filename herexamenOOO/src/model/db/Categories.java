package model.db;

import java.util.ArrayList;

import model.domain.Categorie;

public class Categories {

	private ArrayList<Categorie> categories;

	public Categories() {
		this.categories = new ArrayList<Categorie>();
	}

	public void addCategorie(Categorie categorie) {

		if (categorie == null) {
			throw new DbException("The categorie can not be null!");
		} else {
			for (Categorie cat : this.categories) {
				if (cat.getTitle().equals(categorie.getTitle())
						&& cat.getDescription().equals(categorie.getDescription())) {
					throw new DbException("This categorie already exist!");
				}
			}
		}
		this.categories.add(categorie);
	}

	public void deleteCategorie(Categorie categorie){
		this.categories.remove(categorie.getTitle());
	}
	
	public Categorie getCategorie(String name){
		
		Categorie result = null;
		for(Categorie cat:this.categories){
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
	
	public ArrayList<Categorie> getCategories(){
		return categories;
	}
}
