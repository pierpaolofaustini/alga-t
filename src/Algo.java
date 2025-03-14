import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class Algo {
	
	public static Scene createIntroduction() {
	
		 final int DIJKSTRA = 0;
		 final int BELLMANFORD = 1;
		
		 StackPane layout = new StackPane();
	
		 Text introduction = new Text(
		 	 
		 		"Nella teoria dei grafi, il cammino minimo (o shortest path) tra due vertici (o nodi) \n"
	 	 	+ "di un grafo è quel percorso che collega i suddetti vertici \n" 
	  	 	+ "e che minimizza la somma dei costi associati all'attraversamento \n"
		 	+ "di ciascun arco (o lato). \n \n"
		 	 
		 	+ "In questo tutorial, la partenza dell'algoritmo avviene cliccando sul bottone apposito \n"
		 	+ "e parte automaticamente dal nodo 0. \n \n"
		 	+ "Cliccando sugli altri bottoni appositi, si può generare un nuovo grafo. \n"
		 	+ "In più, al termine dell'algoritmo, si può accedere ad un'area di autoapprendimento. \n \n \n"
		 	+ "Seleziona un argomento nella barra 'Algoritmi' in alto a sinistra per iniziare la lezione");
		 	 
		 introduction.setStyle("-fx-font: normal 15px 'arial' ");
		 
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
		 
		 StackPane.setAlignment(menuBar, Pos.TOP_LEFT);
		 StackPane.setAlignment(introduction, Pos.CENTER);
		 
		 
	     layout.getChildren().addAll(menuBar,introduction);
	     
	     Scene scene = new Scene(layout,WelcomePage.returnWidth(),WelcomePage.returnHeight());
	     
	     return scene; 
		 }
	
	 
}
