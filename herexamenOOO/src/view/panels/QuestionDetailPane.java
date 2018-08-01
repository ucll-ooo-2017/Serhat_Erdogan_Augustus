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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import view.panels.CategoryDetailPane.setCancelAction;
import view.panels.CategoryDetailPane.setSaveAction;

public class QuestionDetailPane extends GridPane {
	private Button btnOK, btnCancel;
	private TextArea statementsArea;
	private TextField questionField, statementField, feedbackField;
	private Button btnAdd, btnRemove;
	private ComboBox categoryField;
	private Controller controller;
	private ArrayList<String> statements;
	private int i;

	public QuestionDetailPane(Controller controller, Stage stage) {
		i = 0;
		this.controller = controller;
		statements = new ArrayList<>();
		this.setPrefHeight(300);
		this.setPrefWidth(320);

		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(5);
		this.setHgap(5);

		add(new Label("Question: "), 0, 0, 1, 1);
		questionField = new TextField();
		add(questionField, 1, 0, 2, 1);

		add(new Label("Statement: "), 0, 1, 1, 1);
		statementField = new TextField();
		add(statementField, 1, 1, 2, 1);

		add(new Label("Statements: "), 0, 2, 1, 1);
		statementsArea = new TextArea();
		statementsArea.setPrefRowCount(5);

		statementsArea.setEditable(false);
		add(statementsArea, 1, 2, 2, 5);

		Pane addRemove = new HBox();
		btnAdd = new Button("add");
		btnAdd.setOnAction(new AddStatementListener());
		addRemove.getChildren().add(btnAdd);

		btnRemove = new Button("remove");
		btnRemove.setOnAction(new RemoveStatementListener());
		addRemove.getChildren().add(btnRemove);
		add(addRemove, 1, 8, 2, 1);

		ObservableList<String> options = FXCollections.observableArrayList();

		for (String s : controller.getCategoriesTitle()) {
			options.add(s);
		}
		this.categoryField = new ComboBox<>(options);
		add(new Label("Category: "), 0, 9, 1, 1);
		add(categoryField, 1, 9, 2, 1);

		add(new Label("Feedback: "), 0, 10, 1, 1);
		feedbackField = new TextField();
		add(feedbackField, 1, 10, 2, 1);

		btnCancel = new Button("Cancel");
		btnCancel.setText("Cancel");
		btnCancel.setOnAction(new setCancelAction());
		add(btnCancel, 0, 11, 1, 1);

		btnOK = new Button("Save");
		btnOK.isDefaultButton();
		btnOK.setText("Save");
		btnOK.setOnAction(new setSaveAction());
		add(btnOK, 1, 11, 2, 1);

	}

	class setSaveAction implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			String question = questionField.getText();
			String statement = statementField.getText();
			String categorie = (String) categoryField.getValue();
			String feedback = feedbackField.getText();

			try {
				controller.addQuestion(question, statement, statements, categorie, feedback);
			} catch (Exception e) {
				Scene scene = new Scene(new GridPane());
				showAlert(Alert.AlertType.ERROR, scene.getWindow(), "Form Error!", e.getMessage());
			}
		}
	}

	class setCancelAction implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			controller.closeQuestionDetail();
		}
	}

	class AddStatementListener implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			String statement = statementField.getText();

			try {
				if (statements.size() < 5) {
					statements.add(statement);
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
