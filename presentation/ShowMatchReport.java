package presentation;

import entities.Match;
import entities.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.MatchReport2;

public class ShowMatchReport {
	private Stage primaryStage;
	private Match match;
	private ButtonEffect buttonEffect = new ButtonEffect();
	private MatchReport2 matchReport = new MatchReport2();
	
	public ShowMatchReport(Stage primaryStage, Match match) {
		this.primaryStage = primaryStage;
		this.match = match;
	}

	public void init(String typerOfUser) {
		GridPane topBarGrid = new GridPane();
 
		topBarGridOptions(topBarGrid);

		topBarElements(topBarGrid, typerOfUser);

		ObservableList<SpecificMatchHistoryTable> eventList = FXCollections.observableArrayList();
		eventList.addAll(matchReport.matchReport(match));
		
		TableColumn<SpecificMatchHistoryTable, String> homeTeamCol = new TableColumn<SpecificMatchHistoryTable, String>("Home Team");
		homeTeamCol.setCellValueFactory(new PropertyValueFactory<SpecificMatchHistoryTable, String>("Home"));
		
		TableColumn<SpecificMatchHistoryTable, String> timeCol = new TableColumn<SpecificMatchHistoryTable, String>("Match Time");
		timeCol.setCellValueFactory(new PropertyValueFactory<SpecificMatchHistoryTable, String>("Time"));

		TableColumn<SpecificMatchHistoryTable, String> awayTeamCol = new TableColumn<SpecificMatchHistoryTable, String>("Away Team");
		awayTeamCol.setCellValueFactory(new PropertyValueFactory<SpecificMatchHistoryTable, String>("Away"));
		
		TableView<SpecificMatchHistoryTable> table = new TableView<SpecificMatchHistoryTable>();
		//table.getStylesheets().add("/presentation/LeaguesMenuTableViewCss.css");
		table.setMinWidth(350);
		GridPane.setColumnSpan(table, 3);
		GridPane.setRowSpan(table, 14);

		table.setItems(eventList);
		table.getColumns().addAll(homeTeamCol, timeCol, awayTeamCol);
		topBarGrid.getChildren().add(table);

		HBox matchInfoHBox = new HBox(homeTeam(), showMatchScore(), awayTeam());
		matchInfoHBox.setAlignment(Pos.CENTER);
		
		HBox tableHBox = new HBox(table);
		tableHBox.setAlignment(Pos.CENTER);

		VBox OuterBox = new VBox(topBarGrid, matchInfoHBox, tableHBox);
		OuterBox.setBackground(background());
		OuterBox.setAlignment(Pos.TOP_CENTER);

		Scene scene = new Scene(OuterBox, 1800, 1000);
		stageMods(scene);
	}

	private void topBarElements(GridPane grid, String typerOfUser) {
		buttonsNavigation(grid, typerOfUser);
		new HeadlineLabelTitle(grid, 3, 1, "Match Report");	
	}
	
	private VBox homeTeam() {
		GridPane gridLabel = new GridPane();
		gridRowOptions(gridLabel);
		LabelTitle(gridLabel, 1, 1, match.getHomeTeam().getTeamName());

		VBox vbox = new VBox(gridLabel);

		return vbox;
	}
	
	private VBox showMatchScore() {
		GridPane scoreLabel = new GridPane();
		gridRowOptions(scoreLabel);
		NewLabel(scoreLabel, 1, 1, "Score:");

		GridPane matchScoreLabel = new GridPane();
		gridRowOptions(matchScoreLabel);
		NewLabel(matchScoreLabel, 1, 1, matchReport.matchScore());


		VBox matchScoreVBox = new VBox(scoreLabel, matchScoreLabel);

		VBox vbox = new VBox(matchScoreVBox);

		return vbox;
	}
	
	private VBox awayTeam() {
		GridPane gridLabel = new GridPane();
		gridRowOptions(gridLabel);
		LabelTitle(gridLabel, 1, 1, match.getAwayTeam().getTeamName());

		VBox vbox = new VBox(gridLabel);

		return vbox;
	}

	private void buttonsNavigation(GridPane grid, String typerOfUser) {
		Button home = new Button("Home");
		NavigationButton(grid, 1, 1, home);
		home.setOnAction(e -> new MainMenu(primaryStage).init(typerOfUser));

		Button back = new Button("Back");
		NavigationButton(grid, 2, 1, back);
		back.setOnAction(e -> new MainMenu(primaryStage).init(typerOfUser));
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
	
	public void LabelTitle(GridPane grid, int row, int col, String text) {
		Label obj = new Label(text);

		obj.setFont(Font.font("Calibri", FontWeight.BOLD, 60));
		obj.setTextFill(Color.web("#707070"));
		obj.setMinWidth(200);
		obj.setAlignment(Pos.CENTER);
		obj.setUnderline(true);

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
