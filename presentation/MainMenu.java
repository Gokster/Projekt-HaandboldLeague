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

public class MainMenu {
	private Stage primaryStage;
	private ButtonEffect buttonEffect = new ButtonEffect();

	public MainMenu(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public void init(String typerOfUser) {
 
		if (typerOfUser == "Organizer") {
			GridPane grid = new GridPane();
			gridOptions(grid);

			MainMenuLabelTitle(grid, 0, 1, "Hello " + typerOfUser);

			GridPane grid2 = new GridPane();
			gridOptions(grid2);
			buttonsOrganizer(grid2, typerOfUser);

			VBox vbox = new VBox(grid, grid2);
			vbox.setAlignment(Pos.CENTER);
			vbox.setBackground(background());

			Scene scene = new Scene(vbox, 1800, 1000);
			stageMods(scene);
		} else if (typerOfUser == "Referee") {
			GridPane grid = new GridPane();
			gridOptions(grid);

			MainMenuLabelTitle(grid, 0, 1, "Hello " + typerOfUser);

			GridPane grid2 = new GridPane();
			gridOptions(grid2);
			buttonsReferee(grid2, typerOfUser);

			VBox vbox = new VBox(grid, grid2);
			vbox.setAlignment(Pos.CENTER);
			vbox.setBackground(background());

			Scene scene = new Scene(vbox, 1800, 1100);
			stageMods(scene);
		} else if (typerOfUser == "Viewer") {
			GridPane grid = new GridPane();
			gridOptions(grid);

			MainMenuLabelTitle(grid, 0, 1, "Hello " + typerOfUser);

			GridPane grid2 = new GridPane();
			gridOptions(grid2);
			buttonsViewer(grid2, typerOfUser);

			VBox vbox = new VBox(grid, grid2);
			vbox.setAlignment(Pos.CENTER);
			vbox.setBackground(background());

			Scene scene = new Scene(vbox, 1800, 1100);
			stageMods(scene);
		}
	}

	private void buttonsOrganizer(GridPane grid, String typerOfUser) {
		Button leagues = new Button("League Rankings");
		MainMenuButton(grid, 1, 2, leagues);
		leagues.setOnAction(e -> new LeaguesMenu(primaryStage).init(typerOfUser));

		Button matchMaking = new Button("Matchmaking");
		MainMenuButton(grid, 1, 3, matchMaking);
		matchMaking.setOnAction(e -> new MatchMakingMenu(primaryStage).init(typerOfUser));

		Button schedule = new Button("Schedule");
		MainMenuButton(grid, 1, 4, schedule);
		schedule.setOnAction(e -> new ScheduleMenu(primaryStage).init(typerOfUser));
		
		Button logout = new Button("Log out");
		MainMenuButton(grid, 1, 5, logout);
		logout.setOnAction(e -> new LoginMenu(primaryStage).init());
	}

	private void buttonsReferee(GridPane grid, String typerOfUser) {
		Button schedule = new Button("Schedule");
		MainMenuButton(grid, 1, 2, schedule);
		schedule.setOnAction(e -> new ScheduleMenu(primaryStage).init(typerOfUser));
		
		Button logout = new Button("Log out");
		MainMenuButton(grid, 1, 3, logout);
		logout.setOnAction(e -> new LoginMenu(primaryStage).init());
	}

	private void buttonsViewer(GridPane grid, String typerOfUser) {
		Button schedule = new Button("Schedule");
		MainMenuButton(grid, 1, 2, schedule);
		schedule.setOnAction(e -> new ScheduleMenu(primaryStage).init(typerOfUser));
		
		Button logout = new Button("Log out");
		MainMenuButton(grid, 1, 3, logout);
		logout.setOnAction(e -> new LoginMenu(primaryStage).init());
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
