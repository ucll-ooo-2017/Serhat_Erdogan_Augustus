package handler;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import view.panels.TestPane;

public class SubmitAnswerHandler implements EventHandler<ActionEvent> {

	private Controller controller = Controller.getInstance();
	private Stage stage;
	private TestPane testpane;

	public SubmitAnswerHandler(TestPane testPane, Stage stage) {
		this.testpane = testPane;
		this.stage = stage;

	}

	@Override
	public void handle(ActionEvent event) {

		try {
			System.out.println(testpane.getAnwser());
			controller.getScore().controlAnwser(testpane.getAnwser());
			if (controller.getScore().isLastQuestion()) {
				stage.close();
				controller.getEvaluation().setProperty("test", "true");
				/*Stage stage = new Stage();
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Resultaat");
				alert.setHeaderText("This is your result!");
				alert.setContentText(controller.getScore().toString());
				Image image = new  Image("ucllSmall.jpg");
				ImageView imageView = new ImageView(image);
				alert.setGraphic(imageView);
				DialogPane dialogPane = alert.getDialogPane();
				dialogPane.getStylesheets().add(getClass().getResource("TestPane.css").toExternalForm());
				stage = (Stage) alert.getDialogPane().getScene().getWindow();
				alert.getDialogPane().getScene().setFill(Color.TRANSPARENT);
				stage.initStyle(StageStyle.TRANSPARENT);
				stage.showAndWait();

				if (!alert.getContentText().equals("Beautiful! Everything is Perfect!")) {

					Alert feedback = new Alert(AlertType.INFORMATION);
					feedback.setTitle("Feedback");
					feedback.setHeaderText("Feedback");
					feedback.setContentText(controller.getScore().toStringFeedback());
					feedback.setGraphic(imageView);
					DialogPane dialogPane2 = feedback.getDialogPane();
					dialogPane2.getStylesheets().add(getClass().getResource("TestPane.css").toExternalForm());
					stage = (Stage) feedback.getDialogPane().getScene().getWindow();
					feedback.getDialogPane().getScene().setFill(Color.TRANSPARENT);
					stage.initStyle(StageStyle.TRANSPARENT);
					stage.showAndWait();
				}*/
				controller.setQuestionNumberNul();
			} else {
				stage.close();
				Stage stage = new Stage();
				BorderPane pane = new BorderPane();
				TestPane testPane = new TestPane();
				pane.setCenter(testPane);
				Scene scene = new Scene(pane);
				scene.getStylesheets().add(getClass().getResource("TestPane.css").toExternalForm());
				scene.setFill(Color.TRANSPARENT);
				stage.initStyle(StageStyle.TRANSPARENT);
				stage.setScene(scene);
				stage.show();
				testPane.setProcessAnswerAction(new SubmitAnswerHandler(testPane, stage));

			}

		} catch (Exception e) {
			Scene scene = new Scene(new GridPane());
			showAlert(Alert.AlertType.ERROR, scene.getWindow(), "Form Error!", "Please chose a answer!");
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
