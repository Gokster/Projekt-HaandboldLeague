package presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene; 
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LoginMenu {
	private Stage primaryStage;
	private ButtonEffect buttonEffect = new ButtonEffect();

	public LoginMenu(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void init() {

		GridPane grid = new GridPane();
		gridOptions(grid);

		MainMenuLabelTitle(grid, 0, 1, "Gruppe 7 Håndbold");

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
		MainMenuButton(grid, 1, 2, leagues);
		leagues.setOnAction(e -> new MainMenu(primaryStage).init(typeOfUser1));

		String typeOfUser2 = "Referee";
		Button matchMaking = new Button(typeOfUser2);
		MainMenuButton(grid, 1, 3, matchMaking);
		matchMaking.setOnAction(e -> new MainMenu(primaryStage).init(typeOfUser2));

		String typeOfUser3 = "Viewer";
		Button schedule = new Button(typeOfUser3);
		MainMenuButton(grid, 1, 4, schedule);
		schedule.setOnAction(e -> new MainMenu(primaryStage).init(typeOfUser3));
	}
	public void MainMenuButton(GridPane grid, int row, int col, Button obj) {

		obj.setFont(Font.font("Calibri", 60));
		obj.setMinWidth(600);
		obj.setAlignment(Pos.CENTER);

		buttonEffect.defaultEffect(obj);

		obj.onMouseEnteredProperty().set(e -> buttonEffect.enterEffect(obj));
		obj.onMouseExitedProperty().set(e -> buttonEffect.defaultEffect(obj));

		grid.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}
	public void MainMenuLabelTitle(GridPane grid, int row, int col, String text) {
		Label obj = new Label(text);

		obj.setFont(Font.font("Calibri", FontWeight.BOLD, 130)); 
		obj.setTextFill(Color.web("#707070"));
		
		grid.setColumnSpan(obj, 2);
		grid.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
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
