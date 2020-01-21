package presentation;

import java.util.ArrayList;
import java.util.Optional;

import data.DatabaseController;
import entities.Team;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

public class TeamDeleteMenu {

	private ButtonEffect buttonEffect = new ButtonEffect();
	private DatabaseController dbController = new DatabaseController();
	private Stage primaryStage;
	private ArrayList<Team> teamsList;
	private ComboBox<String> teamNameCB;

	public TeamDeleteMenu(Stage primaryStage) {
		this.primaryStage = primaryStage; 
	}

	public void init(String typerOfUser) {
		GridPane topBarGrid = new GridPane();
		topBarGridOptions(topBarGrid);

		topBarElements(topBarGrid, typerOfUser);

		VBox selecters = new VBox(deleteTeamBox(), deleteTeamAndCancelButtons(typerOfUser));
		selecters.setAlignment(Pos.CENTER);

		VBox OuterBox = new VBox(topBarGrid, selecters);
		OuterBox.setBackground(background());

		Scene scene = new Scene(OuterBox, 1800, 1000);
		stageMods(scene);
	}
	
	/***********************************
	 * DELETE TEAM FUNCTIONALITY
	 ***********************************/

	private void deleteTeamFromLeague(String typerOfUser) {
		System.out.println((String) teamNameCB.getValue());
		Alert deleteAlert = new Alert(AlertType.NONE,
				("Are you sure you would like to delete the team:  " + teamNameCB.getValue() + "?"), ButtonType.YES,
				ButtonType.NO);
		deleteAlert.getDialogPane().setPrefHeight(280);
		deleteAlert.getDialogPane().setPrefWidth(1000);
		deleteAlert.getDialogPane().getStylesheets()
				.add(getClass().getResource("AlertBoxPopUpCss.css").toExternalForm());

		Optional<ButtonType> result = deleteAlert.showAndWait();

		if (result.get() == ButtonType.YES) {
			dbController.deleteTeam((String) teamNameCB.getValue());
			new TeamMenu(primaryStage).init(typerOfUser);
		}
	}
	
	/***********************************
	 * COMBOBOX
	 ***********************************/
	
	private VBox deleteTeamBox() {
		teamsList = dbController.getAllTeams();

		GridPane teamGrid = new GridPane();
		gridRowOptions(teamGrid);

		HBox hbox1 = new HBox(teamGrid);
		hbox1.setAlignment(Pos.CENTER);

		GridPane teamNameGrid = new GridPane();
		gridRowOptions(teamNameGrid);
		newLabel(teamNameGrid, 1, 1, "Team name:	");

		GridPane teamNameCBGrid = new GridPane();
		gridRowOptions(teamNameCBGrid);
		teamNameCB = new ComboBox<String>();
		for (int i = 0; i < teamsList.size(); i++) {
			teamNameCB.getItems().add(teamsList.get(i).getTeamName());
		}

		newComboBoxLayout(teamNameCBGrid, 1, 2, teamNameCB);

		HBox hbox2 = new HBox(teamNameGrid, teamNameCBGrid);
		hbox2.setAlignment(Pos.CENTER);

		VBox vbox = new VBox(hbox1, hbox2);
		vbox.setPadding(new Insets(150));

		return vbox;
	}	

	/***********************************
	 * DELETE AND CANCEL BUTTONS
	 ***********************************/

	private HBox deleteTeamAndCancelButtons(String typerOfUser) {
		GridPane createTeamGrid = new GridPane();
		gridRowOptions(createTeamGrid);
		Button createTeamButton = new Button("Delete Team");
		newButtonLayout(createTeamGrid, 1, 1, createTeamButton);
		createTeamButton.setOnAction(e -> deleteTeamFromLeague(typerOfUser));

		GridPane cancelGrid = new GridPane();
		gridRowOptions(cancelGrid);
		Button cancelButton = new Button("Cancel");
		newButtonLayout(cancelGrid, 1, 1, cancelButton);
		cancelButton.setOnAction(e -> new TeamMenu(primaryStage).init(typerOfUser));

		HBox hbox = new HBox(createTeamGrid, cancelGrid);
		hbox.setAlignment(Pos.CENTER);
		
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
	 * COMBOBOX LAYOUT
	 ***********************************/

	public void newComboBoxLayout(GridPane grid, int row, int col, ComboBox<String> obj) {
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
	
	/***********************************
	 * NAVIGATION BUTTONS
	 ***********************************/
	
	private void topBarElements(GridPane grid, String typerOfUser) {
		buttonsNavigation(grid, typerOfUser);
		new HeadlineLabelTitle(grid, 3, 1, "Delete Team");
	}

	private void buttonsNavigation(GridPane grid, String typerOfUser) {
		Button home = new Button("Home");
		navigationButtonLayout(grid, 1, 1, home);
		home.setOnAction(e -> new MainMenu(primaryStage).init(typerOfUser));

		Button back = new Button("Back");
		navigationButtonLayout(grid, 2, 1, back);
		back.setOnAction(e -> new TeamMenu(primaryStage).init(typerOfUser));
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
