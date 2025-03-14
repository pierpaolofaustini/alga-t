import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.scene.control.TabPane.TabClosingPolicy;

	public class DescrizioneAlgoritmi {
		static Button button2;
		static Tab tab2;
		
		public static Tab getTab() {
				return tab2;
			}
	
	public static Scene display(Pane pane, Pane pane2,int algorithm) {
			
			final int DIJKSTRA = 0;
			final int BELLMANFORD = 1;
		
			/* Viene creata la griglia formata da 3 righe di dimensione diversa e 1 colonna */
			GridPane gridpane = new GridPane();	
		 	ColumnConstraints colConst = new ColumnConstraints();
			RowConstraints rowConst1 = new RowConstraints(WelcomePage.returnHeight()/30);
			RowConstraints rowConst2 = new RowConstraints((WelcomePage.returnHeight()/30)*23);
			RowConstraints rowConst3 = new RowConstraints((WelcomePage.returnHeight()/30)*6);
			gridpane.getRowConstraints().addAll(rowConst1,rowConst2,rowConst3);
			gridpane.getColumnConstraints().add(colConst);
			 
			/* Viene creato il menù che sarà posizionato nella prima riga della griglia */
			Menu menu = new Menu("Algoritmi");
			
			MenuItem subMenu1 = new MenuItem("Dijkstra");
			subMenu1.setOnAction(e -> {
					WelcomePage.SetScene(DescrizioneAlgoritmi.display(Dijkstra.getDescription(), Dijkstra.createGraph(),DIJKSTRA));
				});
				 
			MenuItem subMenu2 = new MenuItem("Bellman-Ford");
		    subMenu2.setOnAction(e -> {
					WelcomePage.SetScene(DescrizioneAlgoritmi.display(BellmanFord.getDescription(), BellmanFord.createGraph(),BELLMANFORD));
				});
				 
			menu.getItems().addAll(subMenu1, subMenu2);
				 
			MenuBar menuBar = new MenuBar();
			menuBar.getMenus().addAll(menu);
			
			gridpane.add(menuBar,0,0 );
			
			/* Viene creata l'HBox che conterrà la spiegazione dell'algoritmo e il grafo e sarò posizionata nella seconda riga della griglia  */
			HBox root1 = new HBox();
			TabPane tabpane1 = new TabPane();
			TabPane tabpane2 = new TabPane();
			tabpane1.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
			tabpane2.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
			
			tabpane1.setMaxWidth((WelcomePage.returnWidth())/3);
			tabpane1.setMinWidth((WelcomePage.returnWidth())/3);
			tabpane2.setMaxWidth(((WelcomePage.returnWidth())/3)*2);
			tabpane2.setMinWidth(((WelcomePage.returnWidth())/3)*2);
			
			
			Tab tab1 = new Tab("Spiegazione", pane);
			tab2 = new Tab("Algoritmo"  , pane2);
			
			tabpane1.getTabs().add(tab1);
			tabpane2.getTabs().add(tab2);
			
			root1.getChildren().addAll(tabpane1,tabpane2);
			gridpane.add(root1,0,1 );
			
			/* Creo il Pane per l'ultima riga della griglia dove sarà posizionato il pulsante per l'autoapprendimento. 
			 * Ciò è sevito per poter utilizzare il CSS. 	*/
			Pane paneQuest = new Pane();
			
			button2 = new Button("Domande");
			button2.setDisable(true);
			button2.setLayoutX( WelcomePage.returnWidth() - 130);
			button2.setLayoutY(60);
			
			button2.setOnAction(e -> { 
				try {
					
					Stage window = new Stage();
					window.initModality(Modality.APPLICATION_MODAL);
				    window.setTitle("Domande");
				    Questions.resetI();
				    /* currentDir ottiene il path assoluto in cui viene eseguito il programma, nell'if successivo gli viene concatenato il path   
				     *  per andare a cercare le domande  per l'autoapprendimento */
				    Path currentDir = Paths.get("").toAbsolutePath();
				    
				    if (algorithm == DIJKSTRA ) {
				    	String path = currentDir.resolve("Questions/Dijkstra").toString();
				    	Questions.setWindow(window,path); 
				    	} else { 
				    	String path = currentDir.resolve("Questions/Bellman Ford").toString();
				    	Questions.setWindow(window,path); 
				    	}
				    	window.showAndWait();
					} catch (IOException e1) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Errore");
						alert.setHeaderText(null);
						alert.setContentText("Errore nel caricare le domande, chiudere e riaprire il programma");
			
						alert.showAndWait();
					}
				});
			
			paneQuest.getChildren().add(button2);
			paneQuest.setStyle("-fx-background-color: lightgrey; -fx-border-color: black; -fx-border-radius: 10px; -fx-margin: 10px; -fx-font: normal 15px 'arial'");
			gridpane.add(paneQuest, 0, 2);
			
			Scene scene = new Scene(gridpane,WelcomePage.returnWidth(),WelcomePage.returnHeight());
			return scene;
			
			}
				
		public static void setButton() {
				button2.setDisable(false);
			}
		
}
