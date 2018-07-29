package domain.DB;

import java.util.ArrayList;

import model.domain.Categorie;

public class Categories {

	private ArrayList<Categorie> categories;

	public Categories() {
		this.categories = new ArrayList<Categorie>();
	}

	public void addCategorie(Categorie categorie) {

		if (categorie == null) {
			throw new DBException("The categorie can not be empty!");
		} else {
			for (Categorie cat : this.categories) {
				if (cat.getName().equals(categorie.getName())
						&& cat.getDescription().equals(categorie.getDescription())) {
					throw new DBException("This categorie already exist!");
				}
			}
		}
		this.categories.add(categorie);
	}

	public void deleteCategorie(Categorie categorie){
		this.categories.remove(categorie.getName());
	}
	
	public Categorie getCategorie(String name){
		
		Categorie result = null;
		for(Categorie cat:this.categories){
			if(cat.getName()==name){
				result = cat;
			}
		}
		if(result ==null ){
			throw new DBException("Categorie was not found!");
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
