package model.domain;

import java.util.ArrayList;

import model.db.Questions;

public class Score {

	private ArrayList<String> answers;
	private Questions questions;
	
	public Score(){
		answers = new ArrayList<>();
		questions = new Questions();
	}
	
	public ArrayList<String> getAnwsers(){
		return questions.getCorrectAnwsers();
	}
	
}
