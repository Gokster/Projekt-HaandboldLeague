package presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LeaguesMenu {
	private Stage primaryStage;
	

	public LeaguesMenu(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
 
	public void init(String typerOfUser) {

		GridPane topBarGrid = new GridPane();
		topBarGridOptions(topBarGrid);

		topBarElements(topBarGrid, typerOfUser);

		HBox row1 = new HBox(rowOneLabel1(typerOfUser), rowOneLabel2(typerOfUser), rowOneLabel3(typerOfUser), rowOneLabel4(typerOfUser));
		
		HBox row2 = new HBox();

		VBox OuterBox = new VBox(topBarGrid, row1, row2);
		OuterBox.setBackground(background());

		Scene scene = new Scene(OuterBox, 1800, 1000);
		stageMods(scene);
	}

	private void topBarElements(GridPane grid, String typerOfUser) {
		buttonsNavigation(grid, typerOfUser);
		new HeadlineLabelTitle(grid, 3, 1, "League Rankings");
		buttonsCRUD(grid, typerOfUser);
	}

	private void buttonsNavigation(GridPane grid, String typerOfUser) {
		Button home = new Button("Home");
		new NavigationButton(grid, 1, 1, home);
		home.setOnAction(e -> new MainMenu(primaryStage).init(typerOfUser));

		Button back = new Button("Back");
		new NavigationButton(grid, 2, 1, back);
		back.setOnAction(e -> new MainMenu(primaryStage).init(typerOfUser));
	}

	private void buttonsCRUD(GridPane grid, String typerOfUser) {
		Button createTeam = new Button("Create");
		new NavigationButton(grid, 4, 1, createTeam);
		createTeam.setOnAction(e -> new NewLeagueCreateMenu(primaryStage).init(typerOfUser));

		Button deleteTeam = new Button("Delete");
		new NavigationButton(grid, 5, 1, deleteTeam);
		deleteTeam.setOnAction(e -> new NewLeagueDeleteMenu(primaryStage).init(typerOfUser));
	}

	private VBox rowOneLabel1(String typerOfUser) {
		GridPane gridLabel = new GridPane();
		gridRowOptions(gridLabel);
		new LeaguesLabelTitle(gridLabel, 1, 1, "League 1");

		GridPane gridButtons = new GridPane();
		gridRowOptions(gridButtons);
		team1(gridButtons, typerOfUser);
		team2(gridButtons, typerOfUser);

		VBox vbox = new VBox(gridLabel, gridButtons);

		return vbox;
	}

	private VBox rowOneLabel2(String typerOfUser) {
		GridPane gridLabel = new GridPane();
		gridRowOptions(gridLabel);
		new LeaguesLabelTitle(gridLabel, 1, 1, "League 2");

		GridPane gridButtons = new GridPane();
		gridRowOptions(gridButtons);
		team1(gridButtons, typerOfUser);
		team2(gridButtons, typerOfUser);

		VBox vbox = new VBox(gridLabel, gridButtons);

		return vbox;
	}

	private VBox rowOneLabel3(String typerOfUser) {
		GridPane gridLabel = new GridPane();
		gridRowOptions(gridLabel);
		new LeaguesLabelTitle(gridLabel, 1, 1, "League 3");

		GridPane gridButtons = new GridPane();
		gridRowOptions(gridButtons);
		team1(gridButtons, typerOfUser);
		team2(gridButtons, typerOfUser);

		VBox vbox = new VBox(gridLabel, gridButtons);

		return vbox;
	}

	private VBox rowOneLabel4(String typerOfUser) {
		GridPane gridLabel = new GridPane();
		gridRowOptions(gridLabel);
		new LeaguesLabelTitle(gridLabel, 1, 1, "League 4");

		GridPane gridButtons = new GridPane();
		gridRowOptions(gridButtons);
		team1(gridButtons, typerOfUser);
		team2(gridButtons, typerOfUser);

		VBox vbox = new VBox(gridLabel, gridButtons);

		return vbox;
	}

	private void team1(GridPane grid, String typerOfUser) {
		int team1Score = 3;
		String teamName = "Herning IF";
		
		Button team = new Button(teamName + "			   |   " + team1Score);
		new LeaguesTeamButton(grid, 1, 2, team);
		team.setOnAction(e -> new SpecificTeamMenu(primaryStage).init(teamName, typerOfUser));
	}
	
	private void team2(GridPane grid, String typerOfUser) {
		int team1Score = 5;
		String teamName = "København IF";
		
		Button team = new Button(teamName + "		   |   " + team1Score);
		new LeaguesTeamButton(grid, 1, 3, team);
		team.setOnAction(e -> new SpecificTeamMenu(primaryStage).init(teamName, typerOfUser));
	}

	private void topBarGridOptions(GridPane grid) {
		grid.setHgap(40);
		grid.setVgap(40);
		grid.setAlignment(Pos.CENTER_LEFT);
	}

	private void gridRowOptions(GridPane grid) {
		grid.setHgap(44);
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
