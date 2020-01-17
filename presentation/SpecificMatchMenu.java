package presentation;

import java.time.LocalTime;
import entities.Match;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SpecificMatchMenu {
	private Stage primaryStage;
	private Match match;
	private GridPane homeScoreGrid = new GridPane();
	private GridPane awayScoreGrid = new GridPane();
	private Label homeScore;
	private Label awayScore;
	private int hScoreVal = 0;
	private int aScoreVal = 0;
	private AnimationTimer timer;
	private Label timerLabel;
	private ButtonEffect buttonEffect = new ButtonEffect();
	private boolean matchStarted = false;

	public SpecificMatchMenu(Stage primaryStage, Match match) {
		this.primaryStage = primaryStage;
		this.match = match;
	}

	public SpecificMatchMenu(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void init(String matchName, String typerOfUser) {

		GridPane topBarGrid = new GridPane();
		topBarGridOptions(topBarGrid);

		topBarElements(topBarGrid, matchName, typerOfUser);

		VBox homeBox = new VBox(homeTitle(), hGoalsButtons(), hTwoMinButtons(), hYellowButtons(), hRedButtons(),
				hTimeOutButton(), hPenaltyButton());

		VBox middleBox = new VBox(historyTitle(), history(), scoreAndTime(), startAndStop());
		middleBox.setPadding(new Insets(60, 40, 0, 40));

		VBox awayBox = new VBox(awayTitle(), aGoalsButtons(), aTwoMinButtons(), aYellowButtons(), aRedButtons(),
				aTimeOutButton(), aPenaltyButton());

		HBox matchControls = new HBox(homeBox, middleBox, awayBox);
		matchControls.setAlignment(Pos.CENTER);

		VBox OuterBox = new VBox(topBarGrid, matchControls);
		OuterBox.setBackground(background());

		Scene scene = new Scene(OuterBox, 1800, 1000);
		stageMods(scene);
	}

	private void topBarElements(GridPane grid, String matchName, String typerOfUser) {
		buttonsNavigation(grid, typerOfUser);
		new HeadlineLabelTitle(grid, 3, 1, matchName);
	}

	private void buttonsNavigation(GridPane grid, String typerOfUser) {
		Button home = new Button("Home");
		NavigationButton(grid, 1, 1, home);
		home.setOnAction(e -> new MainMenu(primaryStage).init(typerOfUser));

		Button back = new Button("Back");
		NavigationButton(grid, 2, 1, back);
		back.setOnAction(e -> new ScheduleMenu(primaryStage).init(typerOfUser));
	}

	private HBox historyTitle() {

		GridPane homeLabelGrid = new GridPane();
		middleTitleConnectToHistory(homeLabelGrid);
		new SpecificMatchLabelTitleHistory(homeLabelGrid, 1, 1, "First Half");

		HBox hbox = new HBox(homeLabelGrid);

		return hbox;
	}

	private VBox history() {

		GridPane historyGrid = new GridPane();

		final ObservableList<SpecificMatchHistoryTable> data = FXCollections.observableArrayList(
				new SpecificMatchHistoryTable("", "01:32", "Goal 1"),
				new SpecificMatchHistoryTable("Goal 2", "01:56", ""),
				new SpecificMatchHistoryTable("2 Min #7", "03:30", ""));

		TableColumn homeColumn = new TableColumn("");
		homeColumn.setMinWidth(250);
		homeColumn.setMaxWidth(250);
		homeColumn.setCellValueFactory(new PropertyValueFactory<SpecificMatchHistoryTable, String>("Home"));

		TableColumn timeColumn = new TableColumn("First Half");
		timeColumn.setMinWidth(100);
		timeColumn.setMaxWidth(100);
		timeColumn.setCellValueFactory(new PropertyValueFactory<SpecificMatchHistoryTable, String>("Time"));

		TableColumn awayColumn = new TableColumn("");
		awayColumn.setMinWidth(250);
		awayColumn.setMaxWidth(250);
		awayColumn.setCellValueFactory(new PropertyValueFactory<SpecificMatchHistoryTable, String>("Away"));

		TableView<SpecificMatchHistoryTable> table = new TableView<SpecificMatchHistoryTable>();
		table.getStylesheets().add("/presentation/SpecificMatchTableViewCss.css");
		historyGrid.setConstraints(table, 1, 1);
		table.setItems(data);
		table.getColumns().addAll(homeColumn, timeColumn, awayColumn);
		historyGrid.getChildren().add(table);

		VBox vbox = new VBox(historyGrid);

		return vbox;
	}

	private HBox scoreAndTime() {

		middleTitleConnectToHistory(homeScoreGrid);
		homeScore = new Label(Integer.toString(hScoreVal));
		// homeScore.textProperty().bind(Integer.toString(hScoreVal));
		new SpecificMatchScoreLabelAndGridLeft(homeScoreGrid, 1, 1, homeScore);

//		LocalTime currentTime = LocalTime.now();
		GridPane timerGrid = new GridPane();
		timerLabel = new Label("0");
		new SpecificMatchScoreLabelAndGridMiddle(timerGrid, 1, 1, timerLabel);
		
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				long time = match.getMatchSeconds();
				if (time <= 120) {
					timerGrid.getChildren().remove(timerLabel);
					timerLabel = new Label(Long.toString(time));
					new SpecificMatchScoreLabelAndGridMiddle(timerGrid, 1, 1, timerLabel);
				} else {
					timer.stop();
				}
			}
		};

		middleTitleConnectToHistory(awayScoreGrid);
		awayScore = new Label(Integer.toString(aScoreVal));
		new SpecificMatchScoreLabelAndGridRight(awayScoreGrid, 1, 1, awayScore);

		HBox hbox = new HBox(homeScoreGrid, timerGrid, awayScoreGrid);

		return hbox;
	}

	private HBox startAndStop() {

		GridPane startGrid = new GridPane();
		gridRowOptions(startGrid);
		Button startButton = new Button("Start");
		new SpecificMatchButtonSmallLeft(startGrid, 1, 1, startButton);
		startButton.setMinWidth(304);
		startButton.setMaxWidth(304);
		startButton.setOnAction(e -> {
			if(matchStarted == false) {
				timer.start();
				match.startMatch();
				matchStarted = true;
			}
		});

		GridPane pauseGrid = new GridPane();
		gridRowOptions(pauseGrid);
		Button pauseButton = new Button("Pause");
		new SpecificMatchButtonSmallRight(pauseGrid, 1, 1, pauseButton);
		pauseButton.setMinWidth(304);
		pauseButton.setMaxWidth(304);
		pauseButton.setOnAction(e -> System.out.println("Pause"));

		HBox hbox = new HBox(startGrid, pauseGrid);

		return hbox;
	}

	private HBox homeTitle() {

		GridPane homeLabelGrid = new GridPane();
		new SpecificMatchLabelTitle(homeLabelGrid, 1, 1, "Home");

		HBox hbox = new HBox(homeLabelGrid);
		hbox.setAlignment(Pos.CENTER);

		return hbox;
	}

	private HBox hGoalsButtons() {

		GridPane addGoalGrid = new GridPane();
		gridRowOptions(addGoalGrid);
		Button addGoalButton = new Button("Goal");
		new SpecificMatchButtonSmallLeft(addGoalGrid, 1, 1, addGoalButton);
		addGoalButton.setOnAction(e -> {
//			match.addGoal(match.getHomeTeam());
			homeScoreGrid.getChildren().remove(homeScore);
			hScoreVal++;
			homeScore = new Label(Integer.toString(hScoreVal));
			new SpecificMatchScoreLabelAndGridLeft(homeScoreGrid, 1, 1, homeScore);

//			new SpecificMatchScoreLabelAndGridLeft(homeScoreGrid, 1, 1,
//					Integer.toString(match.countGoal(match.getHomeTeam())));
		});

		GridPane subGoalGrid = new GridPane();
		gridRowOptions(subGoalGrid);
		Button subGoalButton = new Button("-Goal");
		new SpecificMatchButtonSmallRight(subGoalGrid, 1, 1, subGoalButton);
		subGoalButton.setOnAction(e -> {
			if (hScoreVal > 0) {
//				match.deleteGoal(match.getAwayTeam());
				homeScoreGrid.getChildren().remove(homeScore);
				hScoreVal--;
				homeScore = new Label(Integer.toString(hScoreVal));
				new SpecificMatchScoreLabelAndGridLeft(homeScoreGrid, 1, 1, homeScore);
			}
		});

		HBox hbox = new HBox(addGoalGrid, subGoalGrid);

		return hbox;
	}

	private HBox hTwoMinButtons() {

		GridPane playerTwoMinGrid = new GridPane();
		gridRowOptions(playerTwoMinGrid);
		ComboBox playerTwoMinCB = new ComboBox();
		playerTwoMinCB.getItems().add("#3");
		playerTwoMinCB.getItems().add("#99");
		playerTwoMinCB.getItems().add("#7");
		new SpecificMatchComboBoxSmall(playerTwoMinGrid, 1, 1, playerTwoMinCB);

		GridPane twoMinGrid = new GridPane();
		gridRowOptions(twoMinGrid);
		Button twoMinButton = new Button("2 Min");
		new SpecificMatchButtonMedium(twoMinGrid, 1, 1, twoMinButton);
		twoMinButton.setOnAction(e -> match.addSuspension(match.getHomeTeam()));

		HBox hbox = new HBox(playerTwoMinGrid, twoMinGrid);

		return hbox;
	}

	private HBox hYellowButtons() {

		GridPane playerYellowGrid = new GridPane();
		gridRowOptions(playerYellowGrid);
		ComboBox playerYellowCB = new ComboBox();
		playerYellowCB.getItems().add("#3");
		playerYellowCB.getItems().add("#99");
		playerYellowCB.getItems().add("#7");
		new SpecificMatchComboBoxSmall(playerYellowGrid, 1, 1, playerYellowCB);

		GridPane yellowGrid = new GridPane();
		gridRowOptions(yellowGrid);
		Button yellowButton = new Button("Yellow");
		new SpecificMatchButtonMedium(yellowGrid, 1, 1, yellowButton);
		yellowButton.setOnAction(e -> System.out.println("Yellow"));

		HBox hbox = new HBox(playerYellowGrid, yellowGrid);

		return hbox;
	}

	private HBox hRedButtons() {

		GridPane playerRedGrid = new GridPane();
		gridRowOptions(playerRedGrid);
		ComboBox playerRedCB = new ComboBox();
		playerRedCB.getItems().add("#3");
		playerRedCB.getItems().add("#99");
		playerRedCB.getItems().add("#7");
		new SpecificMatchComboBoxSmall(playerRedGrid, 1, 1, playerRedCB);

		GridPane redGrid = new GridPane();
		gridRowOptions(redGrid);
		Button redButton = new Button("Red");
		new SpecificMatchButtonMedium(redGrid, 1, 1, redButton);
		redButton.setOnAction(e -> System.out.println("Red"));

		HBox hbox = new HBox(playerRedGrid, redGrid);

		return hbox;
	}

	private HBox hTimeOutButton() {

		GridPane timeOutGrid = new GridPane();
		gridRowOptions(timeOutGrid);
		Button timeOutButton = new Button("Time Out");
		new SpecificMatchButtonBig(timeOutGrid, 1, 1, timeOutButton);
		timeOutButton.setOnAction(e -> System.out.println("Time Out"));

		HBox hbox = new HBox(timeOutGrid);

		return hbox;
	}

	private HBox hPenaltyButton() {

		GridPane penaltyGrid = new GridPane();
		gridRowOptions(penaltyGrid);
		Button penaltyButton = new Button("Penalty");
		new SpecificMatchButtonBig(penaltyGrid, 1, 1, penaltyButton);
		penaltyButton.setOnAction(e -> System.out.println("Penalty"));

		HBox hbox = new HBox(penaltyGrid);

		return hbox;
	}

	private HBox awayTitle() {

		GridPane homeLabelGrid = new GridPane();
		new SpecificMatchLabelTitle(homeLabelGrid, 1, 1, "Away");

		HBox hbox = new HBox(homeLabelGrid);
		hbox.setAlignment(Pos.CENTER);

		return hbox;
	}

	private HBox aGoalsButtons() {

		GridPane addGoalGrid = new GridPane();
		gridRowOptions(addGoalGrid);
		Button addGoalButton = new Button("Goal");
		new SpecificMatchButtonSmallLeft(addGoalGrid, 1, 1, addGoalButton);
		addGoalButton.setOnAction(e -> {
//			match.addGoal(match.getAwayTeam());
			awayScoreGrid.getChildren().remove(awayScore);
			aScoreVal++;
			awayScore = new Label(Integer.toString(aScoreVal));
			new SpecificMatchScoreLabelAndGridRight(awayScoreGrid, 1, 1, awayScore);
		});

		GridPane subGoalGrid = new GridPane();
		gridRowOptions(subGoalGrid);
		Button subGoalButton = new Button("-Goal");
		new SpecificMatchButtonSmallRight(subGoalGrid, 1, 1, subGoalButton);
		subGoalButton.setOnAction(e -> {
			if (aScoreVal > 0) {
//				match.deleteGoal(match.getAwayTeam());
				awayScoreGrid.getChildren().remove(awayScore);
				aScoreVal--;
				awayScore = new Label(Integer.toString(aScoreVal));
				new SpecificMatchScoreLabelAndGridRight(awayScoreGrid, 1, 1, awayScore);
			}
		});

		HBox hbox = new HBox(addGoalGrid, subGoalGrid);

		return hbox;
	}

	private HBox aTwoMinButtons() {

		GridPane playerTwoMinGrid = new GridPane();
		gridRowOptions(playerTwoMinGrid);
		ComboBox playerTwoMinCB = new ComboBox();
		playerTwoMinCB.getItems().add("#3");
		playerTwoMinCB.getItems().add("#99");
		playerTwoMinCB.getItems().add("#7");
		new SpecificMatchComboBoxSmall(playerTwoMinGrid, 1, 1, playerTwoMinCB);

		GridPane twoMinGrid = new GridPane();
		gridRowOptions(twoMinGrid);
		Button twoMinButton = new Button("2 Min");
		new SpecificMatchButtonMedium(twoMinGrid, 1, 1, twoMinButton);
		twoMinButton.setOnAction(e -> match.addSuspension(match.getAwayTeam()));

		HBox hbox = new HBox(playerTwoMinGrid, twoMinGrid);

		return hbox;
	}

	private HBox aYellowButtons() {

		GridPane playerYellowGrid = new GridPane();
		gridRowOptions(playerYellowGrid);
		ComboBox playerYellowCB = new ComboBox();
		playerYellowCB.getItems().add("#3");
		playerYellowCB.getItems().add("#99");
		playerYellowCB.getItems().add("#7");
		new SpecificMatchComboBoxSmall(playerYellowGrid, 1, 1, playerYellowCB);

		GridPane yellowGrid = new GridPane();
		gridRowOptions(yellowGrid);
		Button yellowButton = new Button("Yellow");
		new SpecificMatchButtonMedium(yellowGrid, 1, 1, yellowButton);
		yellowButton.setOnAction(e -> System.out.println("Yellow"));

		HBox hbox = new HBox(playerYellowGrid, yellowGrid);

		return hbox;
	}

	private HBox aRedButtons() {

		GridPane playerRedGrid = new GridPane();
		gridRowOptions(playerRedGrid);
		ComboBox playerRedCB = new ComboBox();
		playerRedCB.getItems().add("#3");
		playerRedCB.getItems().add("#99");
		playerRedCB.getItems().add("#7");
		new SpecificMatchComboBoxSmall(playerRedGrid, 1, 1, playerRedCB);

		GridPane redGrid = new GridPane();
		gridRowOptions(redGrid);
		Button redButton = new Button("Red");
		new SpecificMatchButtonMedium(redGrid, 1, 1, redButton);
		redButton.setOnAction(e -> System.out.println("Red"));

		HBox hbox = new HBox(playerRedGrid, redGrid);

		return hbox;
	}

	private HBox aTimeOutButton() {

		GridPane timeOutGrid = new GridPane();
		gridRowOptions(timeOutGrid);
		Button timeOutButton = new Button("Time Out");
		new SpecificMatchButtonBig(timeOutGrid, 1, 1, timeOutButton);
		timeOutButton.setOnAction(e -> System.out.println("Time Out"));

		HBox hbox = new HBox(timeOutGrid);

		return hbox;
	}

	private HBox aPenaltyButton() {

		GridPane penaltyGrid = new GridPane();
		gridRowOptions(penaltyGrid);
		Button penaltyButton = new Button("Penalty");
		new SpecificMatchButtonBig(penaltyGrid, 1, 1, penaltyButton);
		penaltyButton.setOnAction(e -> System.out.println("Penalty"));

		HBox hbox = new HBox(penaltyGrid);

		return hbox;
	}
	public void NavigationButton(GridPane grid, int row, int col, Button obj) {

		obj.setFont(Font.font("Calibri", 30));
		obj.setMinWidth(170);
		obj.setMinHeight(120);
		obj.setMaxHeight(120);
		obj.setMaxWidth(120);

		buttonEffect.defaultEffect(obj);

		obj.onMouseEnteredProperty().set(e -> buttonEffect.enterEffect(obj));
		obj.onMouseExitedProperty().set(e -> buttonEffect.defaultEffect(obj));

		grid.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}

	private void topBarGridOptions(GridPane grid) {
		grid.setHgap(40);
		grid.setVgap(40);
		grid.setAlignment(Pos.CENTER_LEFT);
	}

	private void gridRowOptions(GridPane grid) {
//		grid.setHgap(40);
		grid.setVgap(15);
		grid.setAlignment(Pos.CENTER);
	}

	private void middleTitleConnectToHistory(GridPane grid) {
		grid.setStyle(
				"-fx-border-radius: 5 5 0 0; -fx-border-color: #707070; -fx-border-width: 3 3 1 3; -fx-background-radius: 7; "
						+ "-fx-background-radius: 7; -fx-background: -fx-accent; -fx-background-color: #FFFFFF; -fx-border-radius: 5 5 0 0;");

		grid.setAlignment(Pos.CENTER);
		grid.setPrefWidth(608);
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
