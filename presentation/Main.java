package presentation;

import data.DatabaseController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private DatabaseController dbController = new DatabaseController();

	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		LoginMenu menu = new LoginMenu(primaryStage);
		menu.init();
	}

//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		SpecificMatchMenu menu = new SpecificMatchMenu(primaryStage, dbController.readMatchByIdNotPlayed(1));
//		menu.init("Hej Dean Title of Match", "Organizer");
//
//	}

}
