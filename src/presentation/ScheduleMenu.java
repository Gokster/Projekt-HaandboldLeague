package presentation;

import java.time.LocalDate;

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

public class ScheduleMenu {
	private Stage primaryStage;

	public ScheduleMenu(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void init(String typerOfUser) {

		GridPane topBarGrid = new GridPane();
		topBarGridOptions(topBarGrid);

		topBarElements(topBarGrid, typerOfUser);

		VBox col1 = new VBox(dateOfMatches(), TimeOfMatches(typerOfUser));

		VBox col2 = new VBox(dateOfMatchesTemp(), TimeOfMatchesTemp(typerOfUser));

		HBox calenderTimeline = new HBox(col1, col2);
		calenderTimeline.setAlignment(Pos.BASELINE_CENTER);

		VBox OuterBox = new VBox(topBarGrid, calenderTimeline);
		OuterBox.setBackground(background());

		Scene scene = new Scene(OuterBox, 1800, 1100);
		stageMods(scene);
	}

	private void topBarElements(GridPane grid, String typerOfUser) {
		buttonsNavigation(grid, typerOfUser);
		new HeadlineLabelTitle(grid, 3, 1, "Schedule");
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
		createTeam.setOnAction(e -> new MatchMakingMenuSchedule(primaryStage).init(typerOfUser));

		Button deleteTeam = new Button("Delete");
		new NavigationButton(grid, 5, 1, deleteTeam);
		deleteTeam.setOnAction(e -> new NewScheduleDeleteMenu(primaryStage).init(typerOfUser));
	}

	private VBox dateOfMatches() {
		LocalDate today = LocalDate.now();
		GridPane dateLabelGrid = new GridPane();
		gridRowOptions(dateLabelGrid);
		new ScheduleLabelTitle(dateLabelGrid, 1, 1, today.toString());

		VBox vbox = new VBox(dateLabelGrid);

		return vbox;
	}

	private VBox TimeOfMatches(String typerOfUser) {
		GridPane timeLabelGrid = new GridPane();
		gridRowOptions(timeLabelGrid);
		new ScheduleLabel(timeLabelGrid, 1, 1, "16:00");

		String matchName = "København vs. Herning";

		GridPane matchGrid = new GridPane();
		gridRowOptions(matchGrid);
		Button matchButton = new Button(matchName);
		new ScheduleMatchButton(matchGrid, 1, 1, matchButton);
		matchButton.setOnAction(e -> new SpecificMatchMenu(primaryStage).init(matchName, typerOfUser));

		String matchNameTemp = "Give vs. Herning";

		GridPane matchGridTemp = new GridPane();
		gridRowOptions(matchGridTemp);
		Button matchButtonTemp = new Button(matchNameTemp);
		new ScheduleMatchButton(matchGridTemp, 1, 1, matchButtonTemp);
		matchButtonTemp.setOnAction(e -> new SpecificMatchMenu(primaryStage).init(matchNameTemp, typerOfUser));

		VBox vbox = new VBox(timeLabelGrid, matchGrid, matchGridTemp);

		return vbox;
	}

	private VBox dateOfMatchesTemp() {
		LocalDate today = LocalDate.now();
		LocalDate nextWeek = today.plusDays(7);
		GridPane dateLabelGrid = new GridPane();
		gridRowOptions(dateLabelGrid);
		new ScheduleLabelTitle(dateLabelGrid, 1, 1, nextWeek.toString());

		VBox vbox = new VBox(dateLabelGrid);

		return vbox;
	}

	private VBox TimeOfMatchesTemp(String typerOfUser) {
		GridPane timeLabelGrid = new GridPane();
		gridRowOptions(timeLabelGrid);
		new ScheduleLabel(timeLabelGrid, 1, 1, "18:00");

		String matchName = "København vs. Herning";

		GridPane matchGrid = new GridPane();
		gridRowOptions(matchGrid);
		Button matchButton = new Button(matchName);
		new ScheduleMatchButton(matchGrid, 1, 1, matchButton);
		matchButton.setOnAction(e -> new SpecificMatchMenu(primaryStage).init(matchName, typerOfUser));

		GridPane timeLabelGridTemp = new GridPane();
		gridRowOptions(timeLabelGridTemp);
		new ScheduleLabel(timeLabelGridTemp, 1, 1, "20:00");

		String matchNameTemp = "Give vs. Herning";

		GridPane matchGridTemp = new GridPane();
		gridRowOptions(matchGridTemp);
		Button matchButtonTemp = new Button(matchNameTemp);
		new ScheduleMatchButton(matchGridTemp, 1, 1, matchButtonTemp);
		matchButtonTemp.setOnAction(e -> new SpecificMatchMenu(primaryStage).init(matchNameTemp, typerOfUser));

		String matchNameTemp2 = "Give vs. København";

		GridPane matchGridTemp2 = new GridPane();
		gridRowOptions(matchGridTemp2);
		Button matchButtonTemp2 = new Button(matchNameTemp2);
		new ScheduleMatchButton(matchGridTemp2, 1, 1, matchButtonTemp2);
		matchButtonTemp2.setOnAction(e -> new SpecificMatchMenu(primaryStage).init(matchNameTemp2, typerOfUser));

		VBox vbox = new VBox(timeLabelGrid, matchGrid, timeLabelGridTemp, matchGridTemp, matchGridTemp2);

		return vbox;
	}

	private void topBarGridOptions(GridPane grid) {
		grid.setHgap(40);
		grid.setVgap(40);
		grid.setAlignment(Pos.CENTER_LEFT);
	}

	private void gridRowOptions(GridPane grid) {
		grid.setHgap(40);
		grid.setVgap(15);
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
