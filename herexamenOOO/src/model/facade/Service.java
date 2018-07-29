package model.facade;

import java.util.ArrayList;
import java.util.Observable;

import domain.DB.Categories;
import model.domain.Categorie;

public class Service extends Observable{

	private Categories categories;
	
	public Service(){
		this.categories = new Categories();
	}
	
	public void addCategorie(Categorie categorie){
		this.categories.addCategorie(categorie);
		this.setChanged();
		this.notifyObservers(this);
	}
	
	public void deleteCategorie(Categorie categorie){
		this.categories.deleteCategorie(categorie);
		this.setChanged();
		this.notifyObservers(this);
	}
	
	public ArrayList<Categorie> getCategories(){
		return categories.getCategories();
	}
}
