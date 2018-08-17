//@author Serhat Erdogan

package view.panels;

import java.util.ArrayList;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Window;

public class QuestionDetailPane extends GridPane {
	private Button btnOK, btnCancel, btnEdit;
	private TextArea statementsArea;
	private TextField questionField, statementField, feedbackField;
	private Button btnAdd, btnRemove;
	private ComboBox categoryField;
	private Controller controller = Controller.getInstance();
	private ArrayList<String> statements;
	private String quesTitle;
	private int i;
	private Label Lstatement, LstatementsArea, LAnwser;
	// private Pane toggle;
	// private ToggleGroup group, group2;
	// private RadioButton rb1, rb2;
	// private ToggleButton tb1, tb2;

	public QuestionDetailPane(String question) {
		this.quesTitle = question;
		this.i = 0;
		statements = new ArrayList<>();
		this.screen();
	}

	public QuestionDetailPane() {
		i = 0;
		statements = new ArrayList<>();
		this.screen();

	}

	private void screen() {
		this.setPrefHeight(300);
		this.setPrefWidth(320);

		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(5);
		this.setHgap(5);

		add(new Label("Question: "), 0, 0, 1, 1);
		questionField = new TextField();
		add(questionField, 1, 0, 2, 1);

		/*
		 * add(new Label("Question type: "), 0, 1, 1, 1); Pane radio = new
		 * HBox(); group = new ToggleGroup(); rb1 = new RadioButton("Yes/No");
		 * rb1.setToggleGroup(group); rb1.setUserData("Yes/No");
		 * rb1.setOnAction(new clearStatementArea());
		 * radio.getChildren().add(rb1);
		 * 
		 * rb2 = new RadioButton("Multiple choice"); rb2.setToggleGroup(group);
		 * rb2.setUserData("multiple choice"); rb2.setOnAction(new
		 * addStatementsArea()); radio.getChildren().add(rb2); add(radio, 1, 1,
		 * 3, 1);
		 */

		Lstatement = new Label("Statement: ");
		add(Lstatement, 0, 2, 1, 1);
		statementField = new TextField();
		add(statementField, 1, 2, 2, 1);

		LstatementsArea = new Label("Statements: ");
		add(LstatementsArea, 0, 3, 1, 1);
		statementsArea = new TextArea();
		statementsArea.setPrefRowCount(5);
		statementsArea.setEditable(false);
		add(statementsArea, 1, 3, 2, 5);

		/*
		 * Lstatement.setVisible(false); statementField.setVisible(false);
		 * LstatementsArea.setVisible(false); statementsArea.setVisible(false);
		 * 
		 * LAnwser = new Label("Anwser: "); add(LAnwser, 0, 3, 1, 1); toggle =
		 * new HBox(); group2 = new ToggleGroup(); tb1 = new
		 * ToggleButton("Yes"); tb1.setToggleGroup(group);
		 * tb1.setUserData("Yes"); toggle.getChildren().add(tb1);
		 * 
		 * tb2 = new ToggleButton("No"); tb2.setToggleGroup(group);
		 * tb2.setUserData("No"); toggle.getChildren().add(tb2); add(toggle, 1,
		 * 3, 1, 1); LAnwser.setVisible(false); toggle.setVisible(false);
		 */

		Pane addRemove = new HBox();
		btnAdd = new Button("add");
		btnAdd.setOnAction(new AddStatementListener());
		addRemove.getChildren().add(btnAdd);

		btnRemove = new Button("remove");
		btnRemove.setOnAction(new RemoveStatementListener());
		addRemove.getChildren().add(btnRemove);
		add(addRemove, 1, 9, 2, 1);
		// btnAdd.setVisible(false);
		// btnRemove.setVisible(false);

		ObservableList<String> options = FXCollections.observableArrayList();

		for (String s : controller.getCategoriesTitle()) {
			options.add(s);
		}
		this.categoryField = new ComboBox<>(options);
		add(new Label("Category: "), 0, 10, 1, 1);
		add(categoryField, 1, 10, 2, 1);

		add(new Label("Feedback: "), 0, 11, 1, 1);
		feedbackField = new TextField();
		add(feedbackField, 1, 11, 2, 1);

		if (quesTitle != null && !quesTitle.isEmpty()) {
			questionField.setText(controller.getQuestion(quesTitle).getQuestion());
			this.showAllStatements();
			categoryField.setValue(controller.getQuestion(quesTitle).getCategory());
			feedbackField.setText(controller.getQuestion(quesTitle).getFeedback());
		}

		btnCancel = new Button("Cancel");
		btnCancel.setText("Cancel");
		add(btnCancel, 0, 12, 1, 1);

		if (quesTitle != null && !quesTitle.isEmpty()) {
			btnEdit = new Button("Edit");
			btnEdit.isDefaultButton();
			btnEdit.setText("Edit");
			add(btnEdit, 1, 12, 2, 1);

		} else {
			btnOK = new Button("Save");
			btnOK.isDefaultButton();
			btnOK.setText("Save");
			add(btnOK, 1, 12, 2, 1);
		}
	}

	/*
	 * class addStatementsArea implements EventHandler<ActionEvent> {
	 * 
	 * @Override public void handle(ActionEvent arg0) { statementsArea.clear();
	 * statements.clear(); i = 0; LAnwser.setVisible(false);
	 * toggle.setVisible(false); Lstatement.setVisible(true);
	 * statementField.setVisible(true); LstatementsArea.setVisible(true);
	 * statementsArea.setVisible(true); btnAdd.setVisible(true);
	 * btnRemove.setVisible(true);
	 * 
	 * } }
	 * 
	 * class clearStatementArea implements EventHandler<ActionEvent> {
	 * 
	 * @Override public void handle(ActionEvent arg0) { statementsArea.clear();
	 * statements.clear(); i = 0; LAnwser.setVisible(true);
	 * toggle.setVisible(true); Lstatement.setVisible(false);
	 * statementField.setVisible(false); LstatementsArea.setVisible(false);
	 * statementsArea.setVisible(false); btnAdd.setVisible(false);
	 * btnRemove.setVisible(false);
	 * 
	 * } }
	 */

	public void setEditAction(EventHandler<ActionEvent> editAction) {
		btnEdit.setOnAction(editAction);
	}

	public void setSaveAction(EventHandler<ActionEvent> saveAction) {
		btnOK.setOnAction(saveAction);
	}

	public void setCancelAction(EventHandler<ActionEvent> cancelAction) {
		btnCancel.setOnAction(cancelAction);
	}

	public String getQuestion() {
		if (!questionField.getText().isEmpty()) {
			return questionField.getText();
		} else {
			throw new IllegalArgumentException("Question field can not be empty!");

		}
	}

	public String getStatement() {
		if (statements.size() > 0) {
			return statements.get(0);
		} else {
			throw new IllegalArgumentException("There has to be atleast 2 statements!");
		}
	}

	public Object getCategory() {
		return categoryField.getValue();
	}

	public String getFeedback() {
		return feedbackField.getText();
	}

	public ArrayList<String> getStatements() {
		return statements;
	}

	public String getOldQuestion() {
		return quesTitle;
	}

	public void setAddStatementAction(EventHandler<ActionEvent> addAction) {
		btnAdd.setOnAction(addAction);
	}

	public void showAllStatements() {
		for (String stat : controller.getStatements(quesTitle)) {
			try {
				if (statements.size() < 5) {
					statements.add(stat);
					String s = statements.get(i);
					int j = i + 1;
					statementsArea.appendText(j + ". " + s + "." + "\n");
					i++;

				}
			} catch (Exception e) {
				Scene scene = new Scene(new GridPane());
				showAlert(Alert.AlertType.ERROR, scene.getWindow(), "Form Error!", e.getMessage());
			}
		}
	}

	class AddStatementListener implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			String statement = statementField.getText();
			try {
				if (!statement.isEmpty()) {
					if (statements.size() < 5) {
						statements.add(statement);
						String s = statements.get(i);
						int j = i + 1;
						statementsArea.appendText(j + ". " + s + "." + "\n");
						i++;

					}
				}
			} catch (Exception e) {
				Scene scene = new Scene(new GridPane());
				showAlert(Alert.AlertType.ERROR, scene.getWindow(), "Form Error!", e.getMessage());
			}
		}
	}

	class RemoveStatementListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {
			try {
				statements.remove(statements.size() - 1);
				statementsArea.clear();
				int j = 1;
				for (String s : statements) {
					statementsArea.appendText(j + ". " + s + "." + "\n");
					j++;
				}
				i = i - 1;

			} catch (Exception e) {
				Scene scene = new Scene(new GridPane());
				showAlert(Alert.AlertType.ERROR, scene.getWindow(), "Form Error!", "There is no statement to delete!");
			}
		}
	}

	private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}
}
