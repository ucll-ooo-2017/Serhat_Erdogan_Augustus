package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.domain.Categorie;
import model.domain.Question;
import model.facade.Service;
import view.panels.CategoryDetailPane;
import view.panels.QuestionDetailPane;

public class Controller implements Observer{
	private final Service service;
	private Stage stage;
	
	public Controller() {
		service = new Service();
		service.addObserver(this);
		this.stage = new Stage();
		Categorie cat1 = new Categorie("Design principles", "The SOLID design principles.",true);
		Categorie cat2 = new Categorie("Java", "java extra's.",true);
		service.addCategorie(cat1);
		service.addCategorie(cat2);

	}
	public Service getService(){
		return this.service;
	}
	
	public void AddCategorie(String name,String description, boolean mainCategorie){
		Categorie categorie = new Categorie(name, description,mainCategorie);
		this.service.addCategorie(categorie);
		closeCatergoryDetail();
	}
	
	public ArrayList<Categorie> getCategories(){
		return service.getCategories();
	}
	public ArrayList<String> getCategoriesTitle(){
		ArrayList<String> categories = new ArrayList<String>();
		for(Categorie s : this.service.getCategories()){
			categories.add(s.getTitle());
		}
		return categories;
	}
	
	public void startCatergoryDetail(){
		this.stage = new Stage();
		GridPane view = new CategoryDetailPane(this, stage);
		Scene scene = new Scene(view);
		stage.setScene(scene);
		stage.show();
	}
	public void closeCatergoryDetail(){
		this.stage.close();
	}
	
	
	//questionController
	public void addQuestion(String question, String statement, ArrayList<String> statements, String categorie,
			String feedback) {
		Question ques = new Question(question, statement, statements, categorie, feedback);
		service.addQuestion(ques);
		closeQuestionDetail();
	}
	
	public ArrayList<Question> getQuestion(){
		return service.getQuestions();
	}
	
	public void startQuestionDetail(){
		this.stage = new Stage();
		GridPane view = new QuestionDetailPane(this,stage);
		Scene scene = new Scene(view);
		stage.setScene(scene);
		stage.show();
	}
	public void closeQuestionDetail(){
		this.stage.close();
	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
