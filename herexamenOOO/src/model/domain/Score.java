package model.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class Score {

	private int currentQuestionId = 0;
	private ArrayList<Question> falseQuestions;
	private ArrayList<Question> questions;

	Map<String, Integer> score = new HashMap<>();
	Map<String, Integer> totalscoren = new HashMap<>();

	public Score(ArrayList<Category> categories, ArrayList<Question> questions) {
		this.questions = questions;
		falseQuestions = new ArrayList<>();
		for (int i = 0; i < categories.size(); i++) {
			score.put(categories.get(i).getTitle(), 0);
			totalscoren.put(categories.get(i).getTitle(), 0);
		}

	}

	public void controlAnwser(String answer) {
		String category = questions.get(currentQuestionId).getCategory();
		if (questions.get(currentQuestionId).getCorrectStatement().equals(answer)) {

			score.put(category, score.get(category) + 1);
			totalscoren.put(category, totalscoren.get(category) + 1);
		} else {
			totalscoren.put(category, totalscoren.get(category) + 1);
			falseQuestions.add(questions.get(currentQuestionId));
		}
		currentQuestionId++;
	}

	public Boolean isLastQuestion() {
		if (currentQuestionId == questions.size()) {
			return true;
		} else {
			return false;
		}
	}

	public int getCurrentQuestionId() {
		return currentQuestionId;
	}

	public void setCurrentQuestionId(int currentQuestionId) {
		this.currentQuestionId = currentQuestionId;
	}

	public ArrayList<Question> getFalseQuestions() {
		return falseQuestions;
	}

	public void setFalseQuestions(ArrayList<Question> falseQuestions) {
		this.falseQuestions = falseQuestions;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	public String toString() {
		String total = "";
		int totalscore = 0;

		for (String key : score.keySet()) {
			total = total + "Category " + key + ": " + score.get(key) + "/" + totalscoren.get(key) + "\n";
			totalscore = totalscore + score.get(key);
		}
		if (totalscore == questions.size()) {
			return "Beautiful! Everything is Perfect!";
		} else {
			return "Your Score: " + totalscore + "\n" + total;
		}
	}

	public String toStringFeedback() {
		String feedback = "";
		for (Question key : falseQuestions) {
			
			feedback = feedback +  key.getFeedback()  + "\n\n";
		}
		return feedback;
	}

}
