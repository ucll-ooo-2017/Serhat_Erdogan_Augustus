//@author Serhat Erdogan

package model.facade;

import java.util.ArrayList;
import java.util.Observable;

import model.domain.Evaluation;
import model.db.Categories;
import model.db.Questions;
import model.domain.Category;
import model.domain.Question;
import model.domain.Score;

public class Service extends Observable{

	private Categories categories;
	private Questions questions;
	private Score score;
	private Evaluation evaluation;
	public Service(){
		this.categories = new Categories();
		this.questions = new Questions();
		this.evaluation = new Evaluation();
	}
	
	public void addCategory(Category categorie){
		this.categories.addCategorie(categorie);		
		this.setChanged();
		this.notifyObservers(this);
	}
	public void editCategory(String oldName,String oldDescription, Category categorie){
		this.categories.EditCategorie(oldName,oldDescription,categorie);
		this.setChanged();
		this.notifyObservers(this);
	}
	
	public void deleteCategorie(Category categorie){
		this.categories.deleteCategorie(categorie);
		this.setChanged();
		this.notifyObservers(this);
	}
	
	public ArrayList<Category> getCategories(){
		return categories.getCategories();
	}
	
	public void addQuestion(Question question){
		this.questions.addQuestion(question);
		this.setChanged();
		this.notifyObservers(this);
	}
	public void editQuestion(String oldQuestion, Question ques) {
		this.questions.EditQuestion(oldQuestion,ques);
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


	public void newTest() {
		score = new Score(this.getCategories(),this.getQuestions());
		
	}
	
	public Score getScore(){
		this.setChanged();
		this.notifyObservers(this);
		return score;
	}
	public Evaluation getEvaluation(){
		return evaluation;
	}

	
	
}
