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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SpecificTeamMenu {

	private Stage primaryStage;

	public SpecificTeamMenu(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void init(String teamName, String typerOfUser) {

		GridPane topBarGrid = new GridPane();
		topBarGridOptions(topBarGrid);

		topBarElements(topBarGrid, teamName, typerOfUser);

		HBox row1 = new HBox(activePlayers(), staff(), substitutes());

		HBox row2 = new HBox();

		VBox OuterBox = new VBox(topBarGrid, row1, row2);
		OuterBox.setBackground(background());

		Scene scene = new Scene(OuterBox, 1800, 1100);
		stageMods(scene);
	}

	private void topBarElements(GridPane grid, String teamName, String typerOfUser) {
		buttonsNavigation(grid, typerOfUser);
		new HeadlineLabelTitle(grid, 3, 1, teamName);
		buttonsCRUD(grid);
	}

	private void buttonsNavigation(GridPane grid, String typerOfUser) {
		Button home = new Button("Home");
		new NavigationButton(grid, 1, 1, home);
		home.setOnAction(e -> new MainMenu(primaryStage).init(typerOfUser));

		Button back = new Button("Back");
		new NavigationButton(grid, 2, 1, back);
		back.setOnAction(e -> new LeaguesMenu(primaryStage).init(typerOfUser));
	}

	private void buttonsCRUD(GridPane grid) {
		Button createTeam = new Button("Create");
		new NavigationButton(grid, 4, 1, createTeam);
		createTeam.setOnAction(e -> System.out.println("Create Team Leagues Menu"));

		Button deleteTeam = new Button("Delete");
		new NavigationButton(grid, 5, 1, deleteTeam);
		deleteTeam.setOnAction(e -> System.out.println("Delete Team Leagues Menu"));
	}

	private VBox activePlayers() {
		GridPane gridLabel = new GridPane();
		gridRowOptions(gridLabel);
		new SpecificTeamLabelTitle(gridLabel, 1, 1, "Active Players");

		GridPane gridList = new GridPane();
		gridRowOptions(gridList);
		teamPlayers(gridList);

		VBox vbox = new VBox(gridLabel, gridList);

		return vbox;
	}

	private VBox staff() {
		GridPane gridLabel = new GridPane();
		gridRowOptions(gridLabel);
		new SpecificTeamLabelTitle(gridLabel, 1, 1, "Staff");

		GridPane gridList = new GridPane();
		gridRowOptions(gridList);
		teamStaff(gridList);

		VBox vbox = new VBox(gridLabel, gridList);

		return vbox;
	}

	private VBox substitutes() {
		GridPane gridLabel = new GridPane();
		gridRowOptions(gridLabel);
		new SpecificTeamLabelTitle(gridLabel, 1, 1, "Substitutes");

		GridPane gridList = new GridPane();
		gridRowOptions(gridList);
		teamSubstitutes(gridList);

		VBox vbox = new VBox(gridLabel, gridList);

		return vbox;
	}

	private void teamPlayers(GridPane grid) {
		for (int i = 1; i < 9; i++) {
			new SpecificTeamLabel(grid, 1, i, "Player", i);
		}
	}

	private void teamStaff(GridPane grid) {
		for (int i = 1; i < 3; i++) {
			new SpecificTeamLabel(grid, 1, i, "Staff", i);
		}
	}

	private void teamSubstitutes(GridPane grid) {
		for (int i = 1; i < 5; i++) {
			new SpecificTeamLabel(grid, 1, i, "Sub", i);
		}
	}

	private void topBarGridOptions(GridPane grid) {
		grid.setHgap(40);
		grid.setVgap(40);
		grid.setAlignment(Pos.CENTER_LEFT);
	}

	private void gridRowOptions(GridPane grid) {
		grid.setHgap(195);
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
