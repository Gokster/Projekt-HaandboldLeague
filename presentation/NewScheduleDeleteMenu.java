package presentation;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import data.DatabaseController;
import entities.Match;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class NewScheduleDeleteMenu {
	
	private Button dateButton;
	private ButtonEffect buttonEffect = new ButtonEffect();
	private DatabaseController dbController = new DatabaseController();
	private LocalDate selectedDate = LocalDate.now();
	private Stage primaryStage;
	private String hTeam;
	private String aTeam;
	private ArrayList<Match> arrMatches = dbController.getAllMatches();
	private ComboBox<String> matchCB;

	public NewScheduleDeleteMenu(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void init(String typerOfUser) {
		GridPane topBarGrid = new GridPane();
		topBarGridOptions(topBarGrid);

		topBarElements(topBarGrid, typerOfUser);

		VBox selecters = new VBox(dateOfMatch(), selectMatch(), deleteMatchAndCancelButtons(typerOfUser));
		selecters.setPadding(new Insets(100));

		VBox OuterBox = new VBox(topBarGrid, selecters);
		OuterBox.setBackground(background());

		Scene scene = new Scene(OuterBox, 1800, 1000);
		stageMods(scene);
	} 

	/***********************************
	 * COMBOBOX FUNCTIONALITY
	 ***********************************/
	
	private VBox selectMatch() {
		GridPane matchGrid = new GridPane();
		gridRowOptions(matchGrid);
		newLabel(matchGrid, 1, 1, "Match name:	");

		GridPane matcheCBGrid = new GridPane();
		gridRowOptions(matcheCBGrid);
		matchCB = new ComboBox<String>();

		loadCB();

		newComboBoxLayout(matcheCBGrid, 1, 1, matchCB);

		HBox hbox = new HBox(matchGrid, matcheCBGrid);
		hbox.setAlignment(Pos.CENTER);

		VBox vbox = new VBox(hbox);

		return vbox;
	}

	private void loadCB() {
		matchCB.getItems().clear();
		for (int i = 0; i < arrMatches.size(); i++) {
			if (arrMatches.get(i).getMatchDate().compareTo(Date.valueOf(selectedDate)) == 0) {
				matchCB.getItems().add(arrMatches.get(i).getHomeTeam().getTeamName() + " vs. "
						+ arrMatches.get(i).getAwayTeam().getTeamName());
			}
		}
		matchCB.getSelectionModel().select(0);
	}
	
	private Match splitTeamNames() {
		String check;
		String match = matchCB.getValue();

		for (int i = 0; i < match.length() - 4; i++) {
			check = match.substring(i, i + 5);
			if (check.equals(" vs. ")) {
				hTeam = match.substring(0, i);
				aTeam = match.substring(i + 5, match.length());
			}
		}

		for (Match matches : arrMatches) {
			if (matches.getMatchDate().compareTo(Date.valueOf(selectedDate)) == 0) {
				if (matches.getHomeTeam().getTeamName().equals(hTeam)
						&& matches.getAwayTeam().getTeamName().equals(aTeam)) {
					return matches;
				}
			}
		}
		return null;
	}
	
	/***********************************
	 * DATEPICKER FUNCTIONALITY
	 ***********************************/
	
	private HBox dateOfMatch() {
		GridPane dateLabelGrid = new GridPane();
		gridRowOptions(dateLabelGrid);
		newLabel(dateLabelGrid, 0, 0, "Date: ");

		GridPane dateButtonGrid = new GridPane();
		gridRowOptions(dateButtonGrid);

		datePickerButtonMethod(dateButtonGrid, LocalDate.now().toString());

		HBox hbox = new HBox(dateLabelGrid, dateButtonGrid);
		hbox.setAlignment(Pos.CENTER);

		return hbox;
	}

	private void datePickerButtonMethod(GridPane dateButtonGrid, String date) {
		dateButton = new Button(date);
		MatchMakingButtonCalendar(dateButtonGrid, 1, 1, dateButton);

		dateButtonGrid.getChildren().add(dateButton);
		dateButton.setOnAction(e -> {
			dateButtonGrid.getChildren().remove(dateButton);
			datePickerCalendarMethod(dateButtonGrid);
		});
	}

	private void datePickerCalendarMethod(GridPane dateButtonGrid) {
		DatePicker dp = new DatePicker();
		datePickerHighlights(dp);
		DatePickerSkin datePickerSkin = new DatePickerSkin(dp);
		Node popupContent = datePickerSkin.getPopupContent();

		dp.setShowWeekNumbers(false); 

		popupContent.setOnMouseClicked(e -> {
			selectedDate = dp.getValue();
			datePickerButtonMethod(dateButtonGrid, selectedDate.toString());
			dateButtonGrid.getChildren().remove(popupContent);
			loadCB();
		});

		dateButtonGrid.getChildren().add(popupContent);
	}
	
	/***********************************
	 * DELETE AND CANCEL BUTTONS
	 ***********************************/
	
	private HBox deleteMatchAndCancelButtons(String typerOfUser) {
		GridPane DeleteMatchGrid = new GridPane();
		gridRowOptions(DeleteMatchGrid);
		Button DeleteMatchButton = new Button("Delete Match");
		newButtonLayout(DeleteMatchGrid, 1, 1, DeleteMatchButton);
		DeleteMatchButton.setOnAction(e -> {

			dbController.deleteMatch(splitTeamNames());
			new ScheduleMenu(primaryStage).init(typerOfUser);
		});

		GridPane cancelGrid = new GridPane();
		gridRowOptions(cancelGrid);
		Button cancelButton = new Button("Cancel");
		newButtonLayout(cancelGrid, 1, 1, cancelButton);
		cancelButton.setOnAction(e -> new ScheduleMenu(primaryStage).init(typerOfUser));

		HBox hbox = new HBox(DeleteMatchGrid, cancelGrid);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(100));

		return hbox;
	}
	
	/***********************************
	 * LABEL
	 ***********************************/

	public void newLabel(GridPane grid, int row, int col, String text) {
		Label obj = new Label(text);

		obj.setFont(Font.font("Calibri", 60));
		obj.setTextFill(Color.web("#707070"));
		obj.setMinWidth(200);
		obj.setAlignment(Pos.CENTER);

		GridPane.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}
	
	/***********************************
	 * DATEPICKER LAYOUT
	 ***********************************/
	
	private void datePickerHighlights(DatePicker dp) {
		ArrayList<LocalDate> arrDates = new ArrayList<>();
		LocalDate date;
		for (int i = 0; i < arrMatches.size(); i++) {
			date = arrMatches.get(i).getMatchDate().toLocalDate();

			arrDates.add(date);
		}

		dp.setDayCellFactory(new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(DatePicker param) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);

						if (!empty && item != null) {
							if (arrDates.contains(item)) {
								this.setStyle("-fx-background-color: green; -fx-text-fill: white");
							}
						}
					}
				};
			}
		});
	}
	
	/***********************************
	 * COMBOBOX LAYOUT
	 ***********************************/

	public void newComboBoxLayout(GridPane grid, int row, int col, ComboBox<?> obj) {
		obj.getStylesheets().add("/presentation/MatchMakingComboBoxCss.css");

		obj.setMinWidth(400);
		obj.setMinHeight(60);
		obj.setMaxHeight(120);
		obj.setMaxWidth(120);

		obj.getSelectionModel().select(0);
		buttonEffect.defaultEffect(obj);

		obj.onMouseEnteredProperty().set(e -> buttonEffect.enterEffect(obj));
		obj.onMouseExitedProperty().set(e -> buttonEffect.defaultEffect(obj));

		GridPane.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}
	
	/***********************************
	 * BUTTON LAYOUT
	 ***********************************/

	public void newButtonLayout(GridPane grid, int row, int col, Button obj) {
		obj.setFont(Font.font("Calibri", 30));
		obj.setMinWidth(400);
		obj.setMinHeight(60);
		obj.setMaxHeight(120);
		obj.setMaxWidth(120);

		buttonEffect.defaultEffect(obj);

		obj.onMouseEnteredProperty().set(e -> buttonEffect.enterEffect(obj));
		obj.onMouseExitedProperty().set(e -> buttonEffect.defaultEffect(obj));

		GridPane.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}
	
	public void MatchMakingButtonCalendar(GridPane grid, int row, int col, Button obj) {
		obj.setFont(Font.font("Calibri", 30));
		obj.setMinWidth(400);
		obj.setMinHeight(60);
		obj.setMaxHeight(120);
		obj.setMaxWidth(120);

		buttonEffect.defaultEffect(obj);

		obj.onMouseEnteredProperty().set(e -> buttonEffect.enterEffect(obj));
		obj.onMouseExitedProperty().set(e -> buttonEffect.defaultEffect(obj));
	}

	
	/***********************************
	 * NAVIGATION BUTTONS
	 ***********************************/
	
	private void topBarElements(GridPane grid, String typerOfUser) {
		buttonsNavigation(grid, typerOfUser);
		new HeadlineLabelTitle(grid, 3, 1, "Delete Match");
	}

	private void buttonsNavigation(GridPane grid, String typerOfUser) {
		Button home = new Button("Home");
		navigationButtonLayout(grid, 1, 1, home);
		home.setOnAction(e -> new MainMenu(primaryStage).init(typerOfUser));

		Button back = new Button("Back");
		navigationButtonLayout(grid, 2, 1, back);
		back.setOnAction(e -> new ScheduleMenu(primaryStage).init(typerOfUser));
	}
	
	/***********************************
	 * NAVIGATION LAYOUT
	 ***********************************/
	
	public void navigationButtonLayout(GridPane grid, int row, int col, Button obj) {
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
	
	/***********************************
	 * GENERAL LAYOUT
	 ***********************************/

	private Background background() {

		BackgroundFill background_fill = new BackgroundFill(Color.web("#9A9A9A"), CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(background_fill);

		return background;
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


	/***********************************
	 * SCENE
	 ***********************************/

	private void stageMods(Scene scene) {

		primaryStage.setTitle("Main Menu");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
