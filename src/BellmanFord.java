import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;

public class BellmanFord {
		
	public static Button getButtonGraph() {
		Button button = new Button("Nuovo Grafo");
		return button;
	}
	
	public static Button getButtonTimer() {
		Button button = new Button("Esegui algoritmo rallentato");
		return button;
	}
	
	public static Button getButtonPlay() {
		Button button = new Button("Esegui algoritmo instantaneo");
		return button; 
	}
		
		public static Graph getGraph() {
			Graph graph = new Graph();
			return graph; 
		}
    
public static Pane getDescription() {
			
			Pane newPane = new Pane();
			newPane.setStyle("-fx-background-color: lightgrey; -fx-border-color: black; -fx-border-radius: 10px; -fx-margin: 10px; -fx-font: normal 15px 'arial'");
			Label testo = new Label("Specifiche algoritmo: \n \n" 
					
					+ "- Tipo di grafo: Ciclico ed orientato \n"
					+ "- Struttura dati di supporto: Coda \n"
					+ "- Implementazione della coda: Utilizzo dell'interfaccia Queue di Java Collection, tramite LinkedList \n"
					+ "- Complessità (con archi di peso negativo o positivo): Polinomiale \n \n"
					
								+ "Descrizione grafo:\n \n"
					+" - I pesi degli archi vengono inizializzati automaticamente partendo da 1 e in maniera crescente al numero di nodi\n"
					+ "- Il numero di nodi può essere o 8 o 9 \n \n"
					
								+ "Descrizione algoritmo: \n \n"
					+ "E' stato scelto un grafo orientato per maggior chiarezza di comprensione, anche se l'algoritmo funziona "
					+ "anche su grafi non orientati.\n"
					+ "Prima di cominciare, vengono settati tutti i pesi dei nodi ad infinito.\n"
					+ "L'algoritmo comincia scegliendo il nodo 0 come nodo di partenza, a cui viene assegnata priorità 0.\n"
					+ "Mano a mano che l'algoritmo avanza dal nodo 0 verso i nodi di adiacenza di quel nodo, "
					+ "i pesi dei nodi vengono aggiornati in base al cammino più breve scelto dall'algoritmo.\n"
					+ "Lo stesso avviene quando vengono visitati gli altri nodi.\n"
					+ "Al termine dell'algoritmo, ovvero una volta visitati tutti i nodi del grafo, viene stampato in rosso il cammino minimo di ogni nodo (lo shortest path per quel nodo).");
			
			testo.setWrapText(true);
			testo.setPrefWidth((WelcomePage.returnWidth()/3)-50);
			testo.setTextAlignment(TextAlignment.JUSTIFY);
			testo.setStyle("-fx-padding: 10px;");
			newPane.getChildren().add(testo);
			return newPane;
		
		}

	public static Pane createGraph() {
		
		final int BELLMANFORD = 1;
		/* Creo le liste di nodi ed archi per il grafo */
		List<Node> dots = new ArrayList<>();
		List<Edge> edges = new ArrayList<>();
		
		Pane pane = new Pane(); 
		pane.setStyle("-fx-background-color: lightgrey; -fx-border-color: black; -fx-border-radius: 10px; -fx-margin: 10px; -fx-font: normal 15px 'arial'");
		Pane insidePane = new Pane(); 
		double startX = 400.0; 
		double startY = 200.0; 
		insidePane.setMinSize(startX, startY);

		/* Posiziona l'insidePane al centro del root */
		insidePane.layoutXProperty().bind(pane.widthProperty().subtract(insidePane.widthProperty()).divide(3));
		insidePane.layoutYProperty().bind(pane.heightProperty().subtract(insidePane.heightProperty()).divide(3));
		
		Node.createNode(dots,insidePane);
		Edge.createEdges(insidePane,dots,edges, BELLMANFORD);
		Node.printNode(dots, insidePane);
		
		Node.setLabel(dots,insidePane);
		for(Node node : dots) {
		node.setPriorityNode(dots, insidePane);
		}
		
		Graph graph = getGraph();
		for (Node node : dots) {
			graph.addNode(node);
		}
		
		/* Pulsanti per stampare nuovo grafo, eseguire algoritmo instantaneamente e rallentato */
		Button buttonGraph = getButtonGraph();
		buttonGraph.setLayoutX(0);
		buttonGraph.setLayoutY(80);
		
		Button buttonPlay = getButtonPlay();
		buttonPlay.setLayoutX(0);
		buttonPlay.setLayoutY(40);
		
		Button buttonTimer = getButtonTimer();
		buttonTimer.setLayoutX(0);
		buttonTimer.setLayoutY(0);
		
		buttonGraph.setOnAction(e -> {
			DescrizioneAlgoritmi.getTab().setContent(BellmanFord.createGraph());
		});
		
		buttonTimer.setOnAction(e -> {
			BellmanFordAlgorithm.calculateShortestPathFromSource(graph , graph.getNodes().get(0), 1000 );
		buttonPlay.setDisable(true);
		buttonTimer.setDisable(true);
		});
		
		
		buttonPlay.setOnAction(e -> {
			BellmanFordAlgorithm.calculateShortestPathFromSource(graph, graph.getNodes().get(0),0);
			buttonPlay.setDisable(true);
			buttonTimer.setDisable(true);
		});
		
		insidePane.getChildren().addAll(buttonPlay,buttonGraph,buttonTimer);
		
      	pane.getChildren().add(insidePane);
     	
     	return pane;
     
	}

}