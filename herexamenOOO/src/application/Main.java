package application;

import controller.Controller;
import handler.CreateCategoryHandler;
import handler.CreateQuestionHandler;
import handler.StartTestHandler;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.panels.AssesMainPane;
import view.panels.CategoryDetailPane;
import view.panels.CategoryOverviewPane;
import view.panels.MessagePane;
import view.panels.QuestionDetailPane;
import view.panels.QuestionOverviewPane;
import view.panels.TestPane;

public class Main extends Application {
	QuestionOverviewPane questionOverviewPane;
	CategoryOverviewPane categoryOverviewPanel;
	MessagePane messagePane;

	@Override
	public void start(Stage primaryStage) {

		try {
			Controller controller = new Controller(this);

			questionOverviewPane = new QuestionOverviewPane(controller);
			categoryOverviewPanel = new CategoryOverviewPane(controller);
			messagePane = new MessagePane(controller);

			Group root = new Group();
			Scene scene = new Scene(root, 750, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			BorderPane borderPane = new AssesMainPane(messagePane, categoryOverviewPanel, questionOverviewPane);
			borderPane.prefHeightProperty().bind(scene.heightProperty());
			borderPane.prefWidthProperty().bind(scene.widthProperty());

			root.getChildren().add(borderPane);
			primaryStage.setScene(scene);
			primaryStage.sizeToScene();

			primaryStage.show();

			categoryOverviewPanel.setNewAction(new CreateCategoryHandler(categoryOverviewPanel, controller));
			questionOverviewPane.setNewAction(new CreateQuestionHandler(questionOverviewPane, controller));
			messagePane.setStartAction(new StartTestHandler(controller));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public QuestionOverviewPane getQuestionOverviewPane() {
		return questionOverviewPane;
	}

	public CategoryOverviewPane getCategoryOverviewPane() {
		return categoryOverviewPanel;
	}

	public MessagePane getMessagePane() {
		return messagePane;
	}
}
