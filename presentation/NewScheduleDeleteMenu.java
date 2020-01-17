package presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class NewScheduleDeleteMenu {
	private Stage primaryStage;
	private ButtonEffect buttonEffect = new ButtonEffect();

	public NewScheduleDeleteMenu(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void init(String typerOfUser) {
		GridPane topBarGrid = new GridPane();
		topBarGridOptions(topBarGrid);

		topBarElements(topBarGrid, typerOfUser);

		VBox selecters = new VBox(selectTeamAndName(), createTeamAndCancelButtons(typerOfUser));
		selecters.setAlignment(Pos.CENTER);

		VBox OuterBox = new VBox(topBarGrid, selecters);
		OuterBox.setBackground(background());

		Scene scene = new Scene(OuterBox, 1800, 1000);
		stageMods(scene);
	}

	private void topBarElements(GridPane grid, String typerOfUser) {
		buttonsNavigation(grid, typerOfUser);
		new HeadlineLabelTitle(grid, 3, 1, "Delete Match");
	}

	private void buttonsNavigation(GridPane grid, String typerOfUser) {
		Button home = new Button("Home");
		NavigationButton(grid, 1, 1, home);
		home.setOnAction(e -> new MainMenu(primaryStage).init(typerOfUser));

		Button back = new Button("Back");
		NavigationButton(grid, 2, 1, back);
		back.setOnAction(e -> new ScheduleMenu(primaryStage).init(typerOfUser));
	}

	private VBox selectTeamAndName() {
		GridPane leagueGrid = new GridPane();
		gridRowOptions(leagueGrid);
		NewLabel(leagueGrid, 1, 1, "Date:		");

		GridPane leagueCBGrid = new GridPane();
		gridRowOptions(leagueCBGrid);
		ComboBox leagueCB = new ComboBox();
		leagueCB.getItems().add("1");
		leagueCB.getItems().add("2");
		leagueCB.getItems().add("3");
		leagueCB.getItems().add("4");
		NewComboBox(leagueCBGrid, 1, 2, leagueCB);

		HBox hbox1 = new HBox(leagueGrid, leagueCBGrid);
		hbox1.setAlignment(Pos.CENTER);

		GridPane teamNameGrid = new GridPane();
		gridRowOptions(teamNameGrid);
		NewLabel(teamNameGrid, 1, 1, "Match name:	");

		GridPane teamNameCBGrid = new GridPane();
		gridRowOptions(teamNameCBGrid);
		ComboBox teamNameCB = new ComboBox();
		teamNameCB.getItems().add("1");
		teamNameCB.getItems().add("2");
		teamNameCB.getItems().add("3");
		teamNameCB.getItems().add("4");
		NewComboBox(teamNameCBGrid, 1, 2, teamNameCB);

		HBox hbox2 = new HBox(teamNameGrid, teamNameCBGrid);
		hbox2.setAlignment(Pos.CENTER);

		VBox vbox = new VBox(hbox1, hbox2);
		vbox.setPadding(new Insets(150));

		return vbox;
	}

	private HBox createTeamAndCancelButtons(String typerOfUser) {
		GridPane createTeamGrid = new GridPane();
		gridRowOptions(createTeamGrid);
		Button createTeamButton = new Button("Delete Match");
		NewButton(createTeamGrid, 1, 1, createTeamButton);
		// createTeamButton.setOnAction(e -> new
		// TeamsMenu(primaryStage).init(typerOfUser));

		GridPane cancelGrid = new GridPane();
		gridRowOptions(cancelGrid);
		Button cancelButton = new Button("Cancel");
		NewButton(cancelGrid, 1, 1, cancelButton);
		// cancelButton.setOnAction(e -> new TeamsMenu(primaryStage).init(typerOfUser));

		HBox hbox = new HBox(createTeamGrid, cancelGrid);
		hbox.setAlignment(Pos.CENTER);
//		hbox.setPadding(new Insets(100));

		return hbox;
	}

	public void NewComboBox(GridPane grid, int row, int col, ComboBox obj) {
		obj.getStylesheets().add("/presentation/MatchMakingComboBoxCss.css");

		obj.setMinWidth(400);
		obj.setMinHeight(60);
		obj.setMaxHeight(120);
		obj.setMaxWidth(120);

		obj.getSelectionModel().select(0);
		buttonEffect.defaultEffect(obj);

		obj.onMouseEnteredProperty().set(e -> buttonEffect.enterEffect(obj));
		obj.onMouseExitedProperty().set(e -> buttonEffect.defaultEffect(obj));

		grid.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}

	public void NewButton(GridPane grid, int row, int col, Button obj) {
		obj.setFont(Font.font("Calibri", 30));
		obj.setMinWidth(400);
		obj.setMinHeight(60);
		obj.setMaxHeight(120);
		obj.setMaxWidth(120);

		buttonEffect.defaultEffect(obj);

		obj.onMouseEnteredProperty().set(e -> buttonEffect.enterEffect(obj));
		obj.onMouseExitedProperty().set(e -> buttonEffect.defaultEffect(obj));

		grid.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}
	public void NewLabel(GridPane grid, int row, int col, String text) {
		Label obj = new Label(text);

		obj.setFont(Font.font("Calibri", 60));
		obj.setTextFill(Color.web("#707070"));
		obj.setMinWidth(200);
		obj.setAlignment(Pos.CENTER);
		
		grid.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
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
