package model.facade;

import java.util.ArrayList;
import java.util.Observable;

import model.db.Categories;
import model.db.Questions;
import model.domain.Categorie;
import model.domain.Question;

public class Service extends Observable{

	private Categories categories;
	private Questions questions;
	
	public Service(){
		this.categories = new Categories();
		this.questions = new Questions();
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
	
	public void addQuestion(Question question){
		this.questions.addQuestion(question);
		this.setChanged();
		this.notifyObservers(this);
	}
	
	public void deleteQuestion(Question question){
		this.questions.deleteQuestion(question);
		this.setChanged();
		this.notifyObservers(this);
	}
	
	public ArrayList<Question> getQuestions(){
		return questions.getQuestions();
	}

	
}
