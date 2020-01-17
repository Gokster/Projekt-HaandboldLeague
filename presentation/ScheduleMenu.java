package presentation;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import data.DatabaseController;
import entities.Match;
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
	private DatabaseController dbController = new DatabaseController();
	private ArrayList<Match> arrMatches = dbController.getAllMatchesNotDone();

	public ScheduleMenu(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void init(String typerOfUser) {

		GridPane topBarGrid = new GridPane();
		topBarGridOptions(topBarGrid);

		topBarElements(topBarGrid, typerOfUser);
		readMatches();

//		VBox col1 = new VBox(dateOfMatches(), TimeOfMatches(typerOfUser));

		HBox calenderTimeline = new HBox();
		calenderTimeline.setAlignment(Pos.BASELINE_CENTER);

		VBox OuterBox = new VBox(topBarGrid, calenderTimeline);
		OuterBox.setBackground(background());

		Scene scene = new Scene(OuterBox, 1800, 1000);
		stageMods(scene);
	}

	private void readMatches() {
		sortArrayList();
		
//		for (int i = 0; i < arrMatches.size(); i++) {
//			Date tempDate =;
//			if (condition) {
//				
//			}
//		}
	}
	
	private void sortArrayList() {
		Collections.sort(arrMatches, Match.dateCompare);

		for (int i = 0; i < arrMatches.size(); i++) {
			System.out.println(arrMatches.get(i).getMatchDate()); 
		}
	}

//	private void readMatches() {
//		ArrayList<Match> arrWithTimes = new ArrayList<Match>();
//		GridPane grid = new GridPane();
//
//		for (Match match : arrMatches) {
//			if (!arrWithTimes.contains(match.getMatchDate())) {
//				arrWithTimes.add(match);
//			}
//
//		}
//
//		System.out.println(arrMatches.size());
//		for (int i = 0; i < arrWithTimes.size(); i++) {
//			System.out.println(arrWithTimes.get(i).getMatchDate());
//		}
//
////		for (int i = 0; i < arrMatches.size(); i++) {
////			for (int j = 0; j < arrWithTimes.size(); j++) {
////				if (condition) {
////					
////				}
////			}
//
////		System.out.println(dbController.readMatchById(1).getMatchDate().compareTo(matchDate));
////			if (dbController.readMatchById(i).getMatchDate().compareTo(matchDate)) {
//
////				arrWithTimes
////			}
////		}
//
//		Button btn = new Button("hrj");
//		new ScheduleMatchButton(grid, 0, i, btn);
//	}

//	private VBox dateOfMatches() {
//		LocalDate today = LocalDate.now();
//		GridPane dateLabelGrid = new GridPane();
//		gridRowOptions(dateLabelGrid);
//		new ScheduleLabelTitle(dateLabelGrid, 1, 1, today.toString());
//
//		VBox vbox = new VBox(dateLabelGrid);
//
//		return vbox;
//	}
//
//	private VBox TimeOfMatchesOld(String typerOfUser) {
//		GridPane timeLabelGrid = new GridPane();
//		gridRowOptions(timeLabelGrid);
//		new ScheduleLabel(timeLabelGrid, 1, 1, "16:00");
//
//		String matchName = "K�benhavn vs. Herning";
//
//		GridPane matchGrid = new GridPane();
//		gridRowOptions(matchGrid);
//		Button matchButton = new Button(matchName);
//		new ScheduleMatchButton(matchGrid, 1, 1, matchButton);
//		matchButton.setOnAction(e -> new SpecificMatchMenu(primaryStage).init(matchName, typerOfUser));
//
//		String matchNameTemp = "Give vs. Herning";
//
//		GridPane matchGridTemp = new GridPane();
//		gridRowOptions(matchGridTemp);
//		Button matchButtonTemp = new Button(matchNameTemp);
//		new ScheduleMatchButton(matchGridTemp, 1, 1, matchButtonTemp);
//		matchButtonTemp.setOnAction(e -> new SpecificMatchMenu(primaryStage).init(matchNameTemp, typerOfUser));
//
//		VBox vbox = new VBox(timeLabelGrid, matchGrid, matchGridTemp);
//
//		return vbox;
//	}
//
//	private VBox TimeOfMatches(String typerOfUser) {
//		GridPane timeLabelGrid = new GridPane();
//		gridRowOptions(timeLabelGrid);
//		new ScheduleLabel(timeLabelGrid, 1, 1, "16:00");
//
//		DataLayer dataLayer = new DataLayer();
//
//		Matches matches = new Matches(dataLayer.getConnection());
//
//		Match match = matches.readMatchById(1);
//
//		String matchName = match.getHomeTeam().getTeamName() + " vs. " + match.getAwayTeam().getTeamName();
//
//		GridPane matchGrid = new GridPane();
//		gridRowOptions(matchGrid);
//		Button matchButton = new Button(matchName);
//		new ScheduleMatchButton(matchGrid, 1, 1, matchButton);
//		matchButton.setOnAction(e -> new SpecificMatchMenu(primaryStage).init(matchName, typerOfUser));
//
//		VBox vbox = new VBox(timeLabelGrid, matchGrid);
//
//		return vbox;
//	}

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
