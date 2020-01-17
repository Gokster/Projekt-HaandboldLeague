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

public class MainMenu {
	private Stage primaryStage;

	public MainMenu(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public void init(String typerOfUser) {

		if (typerOfUser == "Organizer") {
			GridPane grid = new GridPane();
			gridOptions(grid);

			new MainMenuLabelTitle(grid, 0, 1, "Hello " + typerOfUser);

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

			new MainMenuLabelTitle(grid, 0, 1, "Hello " + typerOfUser);

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

			new MainMenuLabelTitle(grid, 0, 1, "Hello " + typerOfUser);

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
		new MainMenuButton(grid, 1, 2, leagues);
		leagues.setOnAction(e -> new LeaguesMenu(primaryStage).init(typerOfUser));

		Button matchMaking = new Button("Matchmaking");
		new MainMenuButton(grid, 1, 3, matchMaking);
		matchMaking.setOnAction(e -> new MatchMakingMenu(primaryStage).init(typerOfUser));

		Button schedule = new Button("Schedule");
		new MainMenuButton(grid, 1, 4, schedule);
		schedule.setOnAction(e -> new ScheduleMenu(primaryStage).init(typerOfUser));
		
		Button logout = new Button("Log out");
		new MainMenuButton(grid, 1, 5, logout);
		logout.setOnAction(e -> new LoginMenu(primaryStage).init());
	}

	private void buttonsReferee(GridPane grid, String typerOfUser) {
		Button schedule = new Button("Schedule");
		new MainMenuButton(grid, 1, 2, schedule);
		schedule.setOnAction(e -> new ScheduleMenu(primaryStage).init(typerOfUser));
		
		Button logout = new Button("Log out");
		new MainMenuButton(grid, 1, 3, logout);
		logout.setOnAction(e -> new LoginMenu(primaryStage).init());
	}

	private void buttonsViewer(GridPane grid, String typerOfUser) {
		Button schedule = new Button("Schedule");
		new MainMenuButton(grid, 1, 2, schedule);
		schedule.setOnAction(e -> new ScheduleMenu(primaryStage).init(typerOfUser));
		
		Button logout = new Button("Log out");
		new MainMenuButton(grid, 1, 3, logout);
		logout.setOnAction(e -> new LoginMenu(primaryStage).init());
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
