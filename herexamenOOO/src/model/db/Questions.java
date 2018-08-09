package model.db;

import java.util.ArrayList;

import model.domain.Question;

public class Questions {

	private ArrayList<Question> questions;
	
	public Questions (){
		questions = new ArrayList<>();
	}
	
	public void addQuestion(Question question){
		if(question == null){
			throw new DbException("Question can not be null!");
		}
		else{
			for(Question ques: this.questions){
				if(ques.getQuestion().equals(question.getQuestion())){
					throw new DbException("This Question already exist!");
				}
			}
		}
		this.questions.add(question);
	}
	
	public void deleteQuestion(Question question){
		this.questions.remove(question.getQuestion());
	}
	
	public Question getQuestion(String question){
		Question result = null;
		for(Question ques:this.questions){
			if(ques.getQuestion() == question){
				result = ques;
			}
		}
		if(result == null){
			throw new DbException("Question was not found!");
		}else{
			return result;
		}
	}
	
	
	
	public int getSizeQuestion(){
		return this.questions.size();
	}
	
	public ArrayList<Question> getQuestions(){
		return questions;
	}
	

}
