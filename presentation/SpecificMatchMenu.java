package presentation;

import data.DatabaseController;
import entities.Match;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
	private String homeTable;
	private String timeTable;
	private String awayTable;
	private ButtonEffect buttonEffect = new ButtonEffect();
	private boolean matchStarted = false;
	private DatabaseController dbController = new DatabaseController();
	private TableView<SpecificMatchHistoryTable> table;

	ObservableList<SpecificMatchHistoryTable> data = FXCollections.observableArrayList();

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

		VBox homeBox = new VBox(homeTitle(), hGoalsButtons(), hTwoMinButtons());

		VBox middleBox = new VBox(history(), scoreAndTime(), startAndStop());
		middleBox.setPadding(new Insets(60, 40, 0, 40));

		VBox awayBox = new VBox(awayTitle(), aGoalsButtons(), aTwoMinButtons());

		HBox matchControls = new HBox(homeBox, middleBox, awayBox);
		matchControls.setAlignment(Pos.CENTER);

		VBox OuterBox = new VBox(topBarGrid, matchControls);
		OuterBox.setBackground(background());

		Scene scene = new Scene(OuterBox, 1800, 1000);
		stageMods(scene);
	}

	/***********************************
	 * MIDDLE SCOREBOARD - TABLE
	 ***********************************/

	private VBox history() {
		GridPane historyGrid = new GridPane();

		TableColumn<SpecificMatchHistoryTable, String> homeColumn = new TableColumn<SpecificMatchHistoryTable, String>(
				"");
		homeColumn.setMinWidth(250);
		homeColumn.setMaxWidth(250);
		homeColumn.setCellValueFactory(new PropertyValueFactory<SpecificMatchHistoryTable, String>("Home"));

		TableColumn<SpecificMatchHistoryTable, String> timeColumn = new TableColumn<SpecificMatchHistoryTable, String>(
				"First Half");
		timeColumn.setMinWidth(100);
		timeColumn.setMaxWidth(100);
		timeColumn.setCellValueFactory(new PropertyValueFactory<SpecificMatchHistoryTable, String>("Time"));

		TableColumn<SpecificMatchHistoryTable, String> awayColumn = new TableColumn<SpecificMatchHistoryTable, String>(
				"");
		awayColumn.setMinWidth(250);
		awayColumn.setMaxWidth(250);
		awayColumn.setCellValueFactory(new PropertyValueFactory<SpecificMatchHistoryTable, String>("Away"));

		table = new TableView<SpecificMatchHistoryTable>();
		table.getStylesheets().add("/presentation/SpecificMatchTableViewCss.css");
		GridPane.setConstraints(table, 1, 1);
		table.setItems(data);
		table.getColumns().addAll(homeColumn, timeColumn, awayColumn);
		historyGrid.getChildren().add(table);

		VBox vbox = new VBox(historyGrid);

		return vbox;
	}

	private String timeStampCreator() {
		long time = match.getMatchSeconds();
		String timeStamp;

		if (time % 60 < 10) {
			timeStamp = Long.toString(time / 60) + ":0" + Long.toString(time % 60);
		} else {
			timeStamp = Long.toString(time / 60) + ":" + Long.toString(time % 60);
		}

		return timeStamp;
	}

	/***********************************
	 * MIDDLE SCOREBOARD - SCORE AND TIME
	 ***********************************/

	private HBox scoreAndTime() {
		middleTitleConnectToHistory(homeScoreGrid);
		homeScore = new Label(Integer.toString(hScoreVal));
		SpecificMatchScoreLabelAndGridLeft(homeScoreGrid, 1, 1, homeScore);

		GridPane timerGrid = new GridPane();
		timerLabel = new Label("0:00");
		SpecificMatchScoreLabelAndGridMiddle(timerGrid, 1, 1, timerLabel);
		timer = new AnimationTimer() {
			public void handle(long now) {
				long time = match.getMatchSeconds();
				if (time <= 120) {
					timerGrid.getChildren().remove(timerLabel);
					if (time % 60 < 10) {
						timerLabel = new Label(Long.toString(time / 60) + ":0" + Long.toString(time % 60));
					} else {
						timerLabel = new Label(Long.toString(time / 60) + ":" + Long.toString(time % 60));
					}
					SpecificMatchScoreLabelAndGridMiddle(timerGrid, 1, 1, timerLabel);
				} else {
					timer.stop();
					matchStarted = false;
				}
			}
		};

		middleTitleConnectToHistory(awayScoreGrid);
		awayScore = new Label(Integer.toString(aScoreVal));
		SpecificMatchScoreLabelAndGridRight(awayScoreGrid, 1, 1, awayScore);

		HBox hbox = new HBox(homeScoreGrid, timerGrid, awayScoreGrid);

		return hbox;
	}

	private void middleTitleConnectToHistory(GridPane grid) {
		grid.setStyle(
				"-fx-border-radius: 5 5 0 0; -fx-border-color: #707070; -fx-border-width: 3 3 1 3; -fx-background-radius: 7; "
						+ "-fx-background-radius: 7; -fx-background: -fx-accent; -fx-background-color: #FFFFFF; -fx-border-radius: 5 5 0 0;");

		grid.setAlignment(Pos.CENTER);
		grid.setPrefWidth(608);
	}

	/***********************************
	 * MIDDLE SCOREBOARD - START/STOP MATCHTIME
	 ***********************************/

	private HBox startAndStop() {
		GridPane startGrid = new GridPane();
		gridRowOptions(startGrid);
		Button startButton = new Button("Start");
		SpecificMatchButtonSmallLeft(startGrid, 1, 1, startButton);
		startButton.setMinWidth(304);
		startButton.setMaxWidth(304);
		startButton.setOnAction(e -> {
			if (matchStarted == false) {
				timer.start();
				match.startMatch();
				matchStarted = true;
			}
		});

		GridPane stopGrid = new GridPane();
		gridRowOptions(stopGrid);
		Button stopButton = new Button("Stop");
		SpecificMatchButtonSmallRight(stopGrid, 1, 1, stopButton);
		stopButton.setMinWidth(304);
		stopButton.setMaxWidth(304);
		stopButton.setOnAction(e -> {
			timer.stop();
			matchStarted = false;
			dbController.createGoals(match.getGoalList());
			dbController.createSuspensions(match.getSuspensionList());
			match.calcWinningTeam();
			dbController.updateCurrentMatch(match);
		});

		HBox hbox = new HBox(startGrid, stopGrid);

		return hbox;
	}

	/***********************************
	 * HOME TEAM - BUTTONS
	 ***********************************/

	private HBox hGoalsButtons() {
		GridPane addGoalGrid = new GridPane();
		gridRowOptions(addGoalGrid);
		Button addGoalButton = new Button("Goal");
		SpecificMatchButtonSmallLeft(addGoalGrid, 1, 1, addGoalButton);
		addGoalButton.setOnAction(e -> {
			if (matchStarted == true) {
				table.scrollTo(200);
				match.addGoal(match.getHomeTeam(), match.getMatchSeconds());
				homeScoreGrid.getChildren().remove(homeScore);
				hScoreVal++;
				homeScore = new Label(Integer.toString(hScoreVal));
				SpecificMatchScoreLabelAndGridLeft(homeScoreGrid, 1, 1, homeScore);

				homeTable = "Goal";
				timeTable = timeStampCreator();
				awayTable = "";

				data.add(new SpecificMatchHistoryTable(homeTable, timeTable, awayTable));
			}
		});

		GridPane subGoalGrid = new GridPane();
		gridRowOptions(subGoalGrid);
		Button subGoalButton = new Button("-Goal");
		SpecificMatchButtonSmallRight(subGoalGrid, 1, 1, subGoalButton);
		subGoalButton.setOnAction(e -> {
			table.scrollTo(200);
			if (matchStarted == true) {
				if (hScoreVal > 0) {
					match.deleteGoal(match.getHomeTeam());
					homeScoreGrid.getChildren().remove(homeScore);
					hScoreVal--;
					homeScore = new Label(Integer.toString(hScoreVal));
					SpecificMatchScoreLabelAndGridLeft(homeScoreGrid, 1, 1, homeScore);
				}
				for (int i = data.size() - 1; i >= 0; i--) {
					System.out.println(data.get(i).getHome());
					if (data.get(i).getHome().compareTo("Goal") == 0) {
						data.remove(i);
						break;
					}
				}
			}
		});

		HBox hbox = new HBox(addGoalGrid, subGoalGrid);

		return hbox;
	}

	private HBox hTwoMinButtons() {
		GridPane twoMinGrid = new GridPane();
		gridRowOptions(twoMinGrid);
		Button twoMinButton = new Button("2 Min");
		SpecificMatchButtonBig(twoMinGrid, 1, 1, twoMinButton);
		twoMinButton.setOnAction(e -> {
			table.scrollTo(200);
			if (matchStarted == true) {
				match.addSuspension(match.getHomeTeam(), match.getMatchSeconds());
				homeTable = "2 min";
				timeTable = timeStampCreator();
				awayTable = "";

				data.add(new SpecificMatchHistoryTable(homeTable, timeTable, awayTable));
			}
		});

		HBox hbox = new HBox(twoMinGrid);

		return hbox;
	}

	/***********************************
	 * HOME TEAM - LABEL
	 ***********************************/

	private HBox homeTitle() {
		GridPane homeLabelGrid = new GridPane();
		LabelTitle(homeLabelGrid, 1, 1, "Home");

		HBox hbox = new HBox(homeLabelGrid);
		hbox.setAlignment(Pos.CENTER);

		return hbox;
	}

	/***********************************
	 * AWAY TEAM - BUTTONS
	 ***********************************/

	private HBox aGoalsButtons() {
		GridPane addGoalGrid = new GridPane();
		gridRowOptions(addGoalGrid);
		Button addGoalButton = new Button("Goal");
		SpecificMatchButtonSmallLeft(addGoalGrid, 1, 1, addGoalButton);
		addGoalButton.setOnAction(e -> {
			table.scrollTo(200);
			if (matchStarted == true) {
				match.addGoal(match.getAwayTeam(), match.getMatchSeconds());
				awayScoreGrid.getChildren().remove(awayScore);
				aScoreVal++;
				awayScore = new Label(Integer.toString(aScoreVal));
				SpecificMatchScoreLabelAndGridRight(awayScoreGrid, 1, 1, awayScore);

				homeTable = "";
				timeTable = timeStampCreator();
				awayTable = "Goal";

				data.add(new SpecificMatchHistoryTable(homeTable, timeTable, awayTable));
			}
		});
		GridPane subGoalGrid = new GridPane();
		gridRowOptions(subGoalGrid);
		Button subGoalButton = new Button("-Goal");
		SpecificMatchButtonSmallRight(subGoalGrid, 1, 1, subGoalButton);
		subGoalButton.setOnAction(e -> {
			table.scrollTo(200);
			if (matchStarted == true) {
				if (aScoreVal > 0) {
					match.deleteGoal(match.getAwayTeam());
					awayScoreGrid.getChildren().remove(awayScore);
					aScoreVal--;
					awayScore = new Label(Integer.toString(aScoreVal));
					SpecificMatchScoreLabelAndGridRight(awayScoreGrid, 1, 1, awayScore);

					for (int i = data.size() - 1; i >= 0; i--) {
						System.out.println(data.get(i).getAway());
						if (data.get(i).getAway().compareTo("Goal") == 0) {
							data.remove(i);
							break;
						}
					}
				}
			}
		});
		HBox hbox = new HBox(addGoalGrid, subGoalGrid);

		return hbox;
	}

	private HBox aTwoMinButtons() {
		GridPane twoMinGrid = new GridPane();
		gridRowOptions(twoMinGrid);
		Button twoMinButton = new Button("2 Min");
		SpecificMatchButtonBig(twoMinGrid, 1, 1, twoMinButton);
		twoMinButton.setOnAction(e -> {
			table.scrollTo(200);
			if (matchStarted == true) {
				match.addSuspension(match.getAwayTeam(), match.getMatchSeconds());

				homeTable = "";
				timeTable = timeStampCreator();
				awayTable = "2 min";

				data.add(new SpecificMatchHistoryTable(homeTable, timeTable, awayTable));
			}
		});

		HBox hbox = new HBox(twoMinGrid);

		return hbox;
	}

	/***********************************
	 * AWAY TEAM - BUTTONS
	 ***********************************/

	private HBox awayTitle() {
		GridPane homeLabelGrid = new GridPane();
		LabelTitle(homeLabelGrid, 1, 1, "Away");

		HBox hbox = new HBox(homeLabelGrid);
		hbox.setAlignment(Pos.CENTER);

		return hbox;
	}

	/***********************************
	 * TOP BAR ELEMENTS HOME/BACK
	 ***********************************/

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

	/***********************************
	 * BUTTONS LAYOUT
	 ***********************************/

	public void NavigationButton(GridPane grid, int row, int col, Button obj) {
		obj.setFont(Font.font("Calibri", 30));
		obj.setMinWidth(170);
		obj.setMinHeight(120);
		obj.setMaxHeight(120);
		obj.setMaxWidth(120);

		buttonEffect.defaultEffect(obj);

		obj.onMouseEnteredProperty().set(e -> buttonEffect.enterEffect(obj));
		obj.onMouseExitedProperty().set(e -> buttonEffect.defaultEffect(obj));

		GridPane.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}

	public void SpecificMatchButtonBig(GridPane grid, int row, int col, Button obj) {
		obj.setFont(Font.font("Calibri", 30));
		obj.setMinWidth(400);
		obj.setMinHeight(60);
		obj.setMaxHeight(120);
		obj.setMaxWidth(400);

		buttonEffect.defaultEffect(obj);

		obj.onMouseEnteredProperty().set(e -> buttonEffect.enterEffect(obj));
		obj.onMouseExitedProperty().set(e -> buttonEffect.defaultEffect(obj));

		GridPane.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}

	public void SpecificMatchButtonSmallLeft(GridPane grid, int row, int col, Button obj) {
		obj.setFont(Font.font("Calibri", 30));
		obj.setMinWidth(200);
		obj.setMinHeight(60);
		obj.setMaxHeight(120);
		obj.setMaxWidth(200);

		SmallLeftSpecificMatchButtonDefaultEffect(obj);

		obj.onMouseEnteredProperty().set(e -> SmallLeftSpecificMatchButtonEnterEffect(obj));
		obj.onMouseExitedProperty().set(e -> SmallLeftSpecificMatchButtonDefaultEffect(obj));

		GridPane.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}

	public void SpecificMatchButtonSmallRight(GridPane grid, int row, int col, Button obj) {
		obj.setFont(Font.font("Calibri", 30));
		obj.setMinWidth(200);
		obj.setMinHeight(60);
		obj.setMaxHeight(120);
		obj.setMaxWidth(200);

		SmallRightSpecificMatchButtonDefaultEffect(obj);

		obj.onMouseEnteredProperty().set(e -> SmallRightSpecificMatchButtonEnterEffect(obj));
		obj.onMouseExitedProperty().set(e -> SmallRightSpecificMatchButtonDefaultEffect(obj));

		GridPane.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}

	/***********************************
	 * COMBOBOX LAYOUT
	 ***********************************/

	public void SpecificMatchComboBoxSmall(GridPane grid, int row, int col, ComboBox<String> obj) {
		obj.getStylesheets().add("/presentation/SpecificMatchComboBoxSmallCss.css");

		obj.setMinWidth(100);
		obj.setMinHeight(60);
		obj.setMaxHeight(120);
		obj.setMaxWidth(100);

		obj.getSelectionModel().select(0);
		SmallSpecificMatchComboBoxDefaultEffect(obj);

		obj.onMouseEnteredProperty().set(e -> SmallSpecificMatchComboBoxEnterEffect(obj));
		obj.onMouseExitedProperty().set(e -> SmallSpecificMatchComboBoxDefaultEffect(obj));

		GridPane.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}

	/***********************************
	 * BUTTONS EFFECT
	 ***********************************/

	private void SpecificMatchButtonEnterEffect(Button obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#707070"), new CornerRadii(7), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setStyle("-fx-border-radius: 0 5 5 0; -fx-border-color: #707070; -fx-border-width: 3 3 3 1;");
		obj.setCursor(Cursor.HAND);

		obj.setTextFill(Color.web("#FFFFFF"));
	}

	private void SpecificMatchButtonDefaultEffect(Button obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#FFFFFF"), new CornerRadii(7), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setStyle("-fx-border-radius: 0 5 5 0; -fx-border-color: #707070; -fx-border-width: 3 3 3 1;");
		obj.setCursor(Cursor.DEFAULT);

		obj.setTextFill(Color.web("#707070"));
	}

	private void SmallLeftSpecificMatchButtonEnterEffect(Button obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#707070"), new CornerRadii(7), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setStyle("-fx-border-radius: 5 0 0 5; -fx-border-color: #707070; -fx-border-width: 3 1 3 3;");
		obj.setCursor(Cursor.HAND);

		obj.setTextFill(Color.web("#FFFFFF"));
	}

	private void SmallLeftSpecificMatchButtonDefaultEffect(Button obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#FFFFFF"), new CornerRadii(7), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setStyle("-fx-border-radius: 5 0 0 5; -fx-border-color: #707070; -fx-border-width: 3 1 3 3;");
		obj.setCursor(Cursor.DEFAULT);

		obj.setTextFill(Color.web("#707070"));
	}

	private void SmallRightSpecificMatchButtonEnterEffect(Button obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#707070"), new CornerRadii(7), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setStyle("-fx-border-radius: 0 5 5 0; -fx-border-color: #707070; -fx-border-width: 3 3 3 1;");
		obj.setCursor(Cursor.HAND);

		obj.setTextFill(Color.web("#FFFFFF"));
	}

	private void SmallRightSpecificMatchButtonDefaultEffect(Button obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#FFFFFF"), new CornerRadii(7), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setStyle("-fx-border-radius: 0 5 5 0; -fx-border-color: #707070; -fx-border-width: 3 3 3 1;");
		obj.setCursor(Cursor.DEFAULT);

		obj.setTextFill(Color.web("#707070"));
	}

	/***********************************
	 * COMBOBOX EFFECT
	 ***********************************/

	private void SmallSpecificMatchComboBoxEnterEffect(ComboBox<String> obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#707070"), new CornerRadii(7), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setStyle("-fx-border-radius: 5 0 0 5; -fx-border-color: #707070; -fx-border-width: 3 1 3 3;");
		obj.setCursor(Cursor.HAND);
	}

	private void SmallSpecificMatchComboBoxDefaultEffect(ComboBox<String> obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#FFFFFF"), new CornerRadii(7), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setStyle("-fx-border-radius: 5 0 0 5; -fx-border-color: #707070; -fx-border-width: 3 1 3 3;");
		obj.setCursor(Cursor.DEFAULT);
	}

	/***********************************
	 * LABELS
	 ***********************************/

	public void LabelTitle(GridPane grid, int row, int col, String text) {
		Label obj = new Label(text);

		obj.setFont(Font.font("Calibri", FontWeight.BOLD, 60));
		obj.setTextFill(Color.web("#707070"));
		obj.setMinWidth(200);
		obj.setAlignment(Pos.CENTER);
		obj.setUnderline(true);

		GridPane.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}

	public void SpecificMatchLabelTitleHistory(GridPane grid, int row, int col, String text) {
		Label obj = new Label(text);

		obj.setFont(Font.font("Calibri", FontWeight.BOLD, 45));
		obj.setTextFill(Color.web("#707070"));
		obj.setMinWidth(200);
		obj.setAlignment(Pos.CENTER);

		GridPane.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}

	/***********************************
	 * LABELS LAYOUT
	 ***********************************/

	public void SpecificMatchScoreLabelAndGridLeft(GridPane grid, int row, int col, Label obj) {
		obj.setFont(Font.font("Calibri", FontWeight.BOLD, 45));
		obj.setTextFill(Color.web("#707070"));
		obj.setAlignment(Pos.CENTER);

		grid.setStyle(
				"-fx-border-radius: 0 0 0 5; -fx-border-color: #707070; -fx-border-width: 1 1 3 3; -fx-background-radius: 0 0 0 7; "
						+ "-fx-background: -fx-accent; -fx-background-color: #FFFFFF;");

		grid.setAlignment(Pos.CENTER);
		grid.setPrefWidth(100);

		GridPane.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}

	public void SpecificMatchScoreLabelAndGridRight(GridPane grid, int row, int col, Label obj) {
		obj.setFont(Font.font("Calibri", FontWeight.BOLD, 45));
		obj.setTextFill(Color.web("#707070"));
		obj.setAlignment(Pos.CENTER);
		grid.setStyle(
				"-fx-border-radius: 0 0 5 0; -fx-border-color: #707070; -fx-border-width: 1 3 3 1; -fx-background-radius: 0 0 7 0; "
						+ "-fx-background: -fx-accent; -fx-background-color: #FFFFFF;");

		grid.setAlignment(Pos.CENTER);
		grid.setPrefWidth(100);

		GridPane.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}

	public void SpecificMatchScoreLabelAndGridMiddle(GridPane grid, int row, int col, Label obj) {
		obj.setFont(Font.font("Calibri", FontWeight.BOLD, 45));
		obj.setTextFill(Color.web("#707070"));
		obj.setAlignment(Pos.CENTER);
		grid.setStyle(
				"-fx-border-radius: 0 0 0 0; -fx-border-color: #707070; -fx-border-width: 1 1 3 1; -fx-background-radius: 0 0 0 0; "
						+ "-fx-background: -fx-accent; -fx-background-color: #FFFFFF;");

		grid.setAlignment(Pos.CENTER);
		grid.setPrefWidth(408);

		GridPane.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}

	/***********************************
	 * GRID LAYOUT
	 ***********************************/

	private void topBarGridOptions(GridPane grid) {
		grid.setHgap(40);
		grid.setVgap(40);
		grid.setAlignment(Pos.CENTER_LEFT);
	}

	private void gridRowOptions(GridPane grid) {
		grid.setVgap(15);
		grid.setAlignment(Pos.CENTER);
	}

	/***********************************
	 * BACKGROUND COLOR
	 ***********************************/
	
	private Background background() {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#9A9A9A"), CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(background_fill);

		return background;
	}

	/***********************************
	 * SETSCENE & TITLE
	 ***********************************/
	
	private void stageMods(Scene scene) {
		primaryStage.setTitle("Main Menu");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
