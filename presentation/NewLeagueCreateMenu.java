package presentation;

import java.util.ArrayList;
import java.util.Optional;

import data.DatabaseController;
import entities.Team;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class NewLeagueCreateMenu {
	private Stage primaryStage;
	private TextField teamNameTF;
	private ArrayList<Team> teamsList;
	private DatabaseController dbController = new DatabaseController();

	public NewLeagueCreateMenu(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void init(String typerOfUser) {

		GridPane topBarGrid = new GridPane();
		topBarGridOptions(topBarGrid);

		topBarElements(topBarGrid, typerOfUser);

		VBox selecters = new VBox(createTeamBox(), createTeamAndCancelButtons(typerOfUser));
		selecters.setAlignment(Pos.CENTER);

		VBox OuterBox = new VBox(topBarGrid, selecters);
		OuterBox.setBackground(background());

		Scene scene = new Scene(OuterBox, 1800, 1000);
		stageMods(scene);
	}

	private void topBarElements(GridPane grid, String typerOfUser) {
		buttonsNavigation(grid, typerOfUser);
		new HeadlineLabelTitle(grid, 3, 1, "Create Team");
	}

	private void buttonsNavigation(GridPane grid, String typerOfUser) {
		Button home = new Button("Home");
		new NavigationButton(grid, 1, 1, home);
		home.setOnAction(e -> new MainMenu(primaryStage).init(typerOfUser));

		Button back = new Button("Back");
		new NavigationButton(grid, 2, 1, back);
		back.setOnAction(e -> new LeaguesMenu(primaryStage).init(typerOfUser));
	}

	private VBox createTeamBox() {
		GridPane teamGrid = new GridPane();
		gridRowOptions(teamGrid);

		HBox hbox1 = new HBox(teamGrid);
		hbox1.setAlignment(Pos.CENTER);

		GridPane teamNameGrid = new GridPane();
		gridRowOptions(teamNameGrid);
		new NewLabel(teamNameGrid, 1, 1, "Team name:	");

		GridPane teamNameCBGrid = new GridPane();
		gridRowOptions(teamNameCBGrid);
		teamNameTF = new TextField();
		new NewTextField(teamNameCBGrid, 1, 2, teamNameTF);

		HBox hbox2 = new HBox(teamNameGrid, teamNameCBGrid);
		hbox2.setAlignment(Pos.CENTER);

		VBox vbox = new VBox(hbox1, hbox2);
		vbox.setPadding(new Insets(150));

		return vbox;
	}

	private HBox createTeamAndCancelButtons(String typerOfUser) {
		GridPane createTeamGrid = new GridPane();
		gridRowOptions(createTeamGrid);
		Button createTeamButton = new Button("Create Team");
		new NewButton(createTeamGrid, 1, 1, createTeamButton);
		createTeamButton.setOnAction(e -> checkIfTeamExists(typerOfUser));

		GridPane cancelGrid = new GridPane();
		gridRowOptions(cancelGrid);
		Button cancelButton = new Button("Cancel");
		new NewButton(cancelGrid, 1, 1, cancelButton);
		cancelButton.setOnAction(e -> new LeaguesMenu(primaryStage).init(typerOfUser));

		HBox hbox = new HBox(createTeamGrid, cancelGrid);
		hbox.setAlignment(Pos.CENTER);
//		hbox.setPadding(new Insets(100));

		return hbox;
	}

	private void checkIfTeamExists(String typerOfUser) {
		teamsList = dbController.getAllTeams();
		
		if (compareTeamNames(teamNameTF.getText())) {

			Alert teamExists = new Alert(AlertType.ERROR);
			teamExists.setTitle("Create Team Error");
			teamExists.setHeaderText(null);
			teamExists.setContentText("A team with the name: " + teamNameTF.getText() + " already exists. Try another name." );

			teamExists.showAndWait();
		} else {
			new LeaguesMenu(primaryStage).init(typerOfUser);
		}
	}
	
	private boolean compareTeamNames(String teamName) {
		for (int i = 0; i < teamsList.size(); i++) {
			if((teamsList.get(i).getTeamName()).compareTo(teamName) == 0) {
				return true;
			}
		}
		dbController.createTeam(teamNameTF.getText());
		return false;
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
