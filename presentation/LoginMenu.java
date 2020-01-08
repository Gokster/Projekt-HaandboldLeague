package presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene; 
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginMenu {
	private Stage primaryStage;

	public LoginMenu(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void init() {

		GridPane grid = new GridPane();
		gridOptions(grid);

		new MainMenuLabelTitle(grid, 0, 1, "Gruppe 7 Håndbold");

		GridPane grid2 = new GridPane();
		gridOptions(grid2);
		buttons(grid2);

		VBox vbox = new VBox(grid, grid2);
		vbox.setAlignment(Pos.CENTER);
		vbox.setBackground(background());

		Scene scene = new Scene(vbox, 1800, 1000);
		stageMods(scene);

	}

	private void buttons(GridPane grid) {
		String typeOfUser1 = "Organizer";
		Button leagues = new Button(typeOfUser1);
		new MainMenuButton(grid, 1, 2, leagues);
		leagues.setOnAction(e -> new MainMenu(primaryStage).init(typeOfUser1));

		String typeOfUser2 = "Referee";
		Button matchMaking = new Button(typeOfUser2);
		new MainMenuButton(grid, 1, 3, matchMaking);
		matchMaking.setOnAction(e -> new MainMenu(primaryStage).init(typeOfUser2));

		String typeOfUser3 = "Viewer";
		Button schedule = new Button(typeOfUser3);
		new MainMenuButton(grid, 1, 4, schedule);
		schedule.setOnAction(e -> new MainMenu(primaryStage).init(typeOfUser3));
	}

	private void gridOptions(GridPane grid) {
		grid.setVgap(40);
		grid.setAlignment(Pos.CENTER);
	}

	private Background background() {

		BackgroundFill background_fill = new BackgroundFill(Color.web("#9A9A9A"), CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(background_fill);

		return background;
	}

	private void stageMods(Scene scene) {

		primaryStage.setTitle("Main Menu");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
