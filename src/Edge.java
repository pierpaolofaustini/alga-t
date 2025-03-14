import java.util.List;
import java.util.Random;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Edge {

	private final Node n1;
	private final Node n2;
	
	public Edge(Node n1, Node n2){
		this.n1 = n1 ;
		this.n2 = n2 ;
	}
	
	public Node getNodeStart() {
		return this.n1;
	}
	public Node getNodeEnd() {
		return this.n2;
	}
	
	public static void createEdges(Pane insidePane, List<Node> dots, List<Edge> edges, int type) {
		for (Node node : dots) {
    		
			/* Creo casualmente un numero tra 1 e 2 che saranno il numero massimo di archi uscenti dal nodo */
    		Random randEdgesGenerator = new Random();
        	int nEdges = randEdgesGenerator.nextInt((2 - 1) + 1) + 1;
        	
    		for(int auxInt = 0; auxInt < nEdges; auxInt++) {
    			
    			int i = node.getNodeNumber();
    			
    			/* Creo casualmente il numero del nodo a cui connettersi */
    			Random randEdgesConnected = new Random();
            	int nodeConnected = randEdgesConnected.nextInt(dots.size());
            	
            	/* Creo casualmente la priorità dell'arco tra 10 e -10, se l'algoritmo che esamina il grafo è Dijkstra
            	 * i nodi negativi vengono moltiplicati per -1 */
            	int weightRand=0;
            	while ( weightRand == 0) {
            	weightRand = new Random().nextInt((10 - (- 10)) + 1) + (-10);
            	}
            	if (weightRand < 0 &&  type == 0) {
            		weightRand = weightRand * -1; }
            	 
            	
            	/* Controllo se il nodo d'arrivo è lo stesso della partenza e se già collegato con verso opposto */
            	if(i !=  nodeConnected && !node.getAdjacentNodes(dots.get(nodeConnected)) && !dots.get(nodeConnected).getAdjacentNodes(node)) {
            	
            	node.addDestination(dots.get(nodeConnected), weightRand);            	
           		Edge edge = new Edge(node,dots.get(nodeConnected));
           		Label text = new Label();
           		text.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(3), new Insets(-1,-3,-1,-3))));
           		text.setFont(Font.font(15));
           		text.setText(Integer.toString(weightRand));
           		text.setLayoutX(((edge.getNodeStart().getXCoord() + edge.getNodeEnd().getXCoord())/2));
           		text.setLayoutY(((edge.getNodeStart().getYCoord() + edge.getNodeEnd().getYCoord())/2));
           		text.toFront();
           		edges.add(edge);
           		
           		/* Crea la freccia graficamente e la stampa */
           		Arrow arrow = new Arrow(edge);
           		dots.get(nodeConnected).addArrow(node, arrow);
           		insidePane.getChildren().addAll(arrow,text);
            		
            	}	
    		}
		}
	}


}

