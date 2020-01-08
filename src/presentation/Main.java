package presentation;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

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
//		MainMenu menu = new MainMenu(primaryStage);
//		menu.init();
//
//	}

}
