import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class BellmanFordAlgorithm {
	
	public static Graph calculateShortestPathFromSource(Graph graph, Node source, int timer) {
		/* Booleano true se ci sono cicli negativi all'interno del grafo */
		boolean negativeCycle = false;
		/* Creo la lista di nodi già visitati e la coda dei nodi ancora da visitare  */
        List<Node> settledNodes = new ArrayList<>();
        Queue<Node> unsettledNodes = new LinkedList<>(); 
        
        
        unsettledNodes.add(source);
        source.setPriority(0);
        source.setPriorityLabel();
        int t = 1;
        
		while (!unsettledNodes.isEmpty() && negativeCycle == false) {
        	
            Node currentNode = unsettledNodes.remove();
            
            /* b[u] = false */
            currentNode.setIsContained(false);
            
            /* Creo il task da eseguire per colorare il nodo nel momento in cui viene schedulato */
            class RemaindTaskColorNode extends TimerTask {
         	   public void run() {
         		   currentNode.highlight(Color.RED);
         		   this.cancel();
         	   }
            }
             
            new Timer().schedule(new RemaindTaskColorNode(), t*timer);
            
            t++;
            
            /* Cicla su tutti i nodi di adiacenza di quelli del currentNode  */
            for (Entry<Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
            	
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                
                /* Creo il task da eseguire per cambiare colore all'arco nel momento in cui viene schedulato */
                class RemindTaskColorEdge extends TimerTask {
         	      public void run(){
         	       	for (Entry<Node, Arrow> arrow : adjacentNode.getArrow().entrySet()) {
                       	if (arrow.getKey() == currentNode ) {
                       		if( arrow.getValue().getColor() == Color.BLACK) {
                       			arrow.getValue().setColor(Color.RED); 
                       			}
                       		else {
                       			arrow.getValue().setColor(Color.BLACK); 
                       			}
                         	}
         	        	}
         	        	this.cancel();
         	      		}
                 	}
         		
         			/* Viene eseguito due volte per alternarne i colori*/
         			new Timer().schedule(new RemindTaskColorEdge(), t*timer);
               
         			t++;
                 
         			new Timer().schedule(new RemindTaskColorEdge(), t*timer);
                 
         			t++;
                
                
                if(currentNode.getPriority() + edgeWeight < adjacentNode.getPriority()) {
                	
                	/* Creo il task da eseguire per stampare la priorità aggiornata del nodo nel momento in cui viene schedulato */
                	class RemindTaskPriorityLabel extends TimerTask {
             	        public void run(){
             	        	String label = Integer.toString(adjacentNode.getPriority());
             	        	adjacentNode.getLabel().setLayoutX(adjacentNode.getXLabel() + 5);
             	        	adjacentNode.getLabel().setLayoutY(adjacentNode.getYLabel());
             	        	adjacentNode.getLabel().setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(2), new Insets(-3,-8,-3,-8))));
             	        	adjacentNode.setText(label);
             	        	
             	        	this.cancel();
             	        }
                     }
                	
                	
                	 if(!adjacentNode.getIsContained()) {
                     	unsettledNodes.add(adjacentNode);
                     	adjacentNode.setPriority(currentNode.getPriority() + edgeWeight);
                     	
                     	new Timer().schedule(new RemindTaskPriorityLabel(), t*timer);
               		 
                        t++;
                     	
                     	adjacentNode.setIsContained(true);
                     }	
                	 
                	 List<Node> shortestPath = new ArrayList<>(currentNode.getShortestPath());
                     shortestPath.add(currentNode);
                     adjacentNode.setShortestPath(shortestPath);
                     adjacentNode.setPriority(currentNode.getPriority() + edgeWeight);
                     
                     new Timer().schedule(new RemindTaskPriorityLabel(), t*timer);
            		 
                     t++;
                     
                     
                }    
                
                for (Node node : settledNodes)
                {
                	for (Entry<Node, Integer> adjacencyNode : node.getAdjacentNodes().entrySet()) {
                		
                		Node adjacentNode1 = adjacencyNode.getKey();
                        Integer edgeWeight1 = adjacencyNode.getValue();
                        	
                        	/* Se la priorità del nodo adiacente è maggiore della priorità del nodo di partenza più la lunghezza dell'arco vuol dire 
                        	 * che nel grafo sono presenti cicli negativi      */
                            if (adjacentNode1.getPriority() > node.getPriority() + edgeWeight1 && negativeCycle == false) {
                            	
                            	Alert alert = new Alert(AlertType.INFORMATION);
            					alert.setTitle("Ciclo negativo");
            					alert.setHeaderText(null);
            					alert.setContentText("Nel grafo è presente un ciclo negativo e quindi non è possibile completare l'algoritmo. Verrà creato un nuovo grafo");

            					alert.showAndWait();
            					negativeCycle= true;
            					t=0;
                        }
                    }
                }
                
            }
            
       settledNodes.add(currentNode);
       }
        
    graph.setSettledNodes(settledNodes);
    
    /* Creo il task da eseguire per stampare lo shortest path di ogni nodo nel momento in cui l'algoritmo è finito */
    class RemindTaskColorShortestPath extends TimerTask {
    	public void run(){
	      for (Node node : settledNodes) {
	       	if (node.getShortestPath().size()>0) {
	       		for (Entry<Node, Arrow> arrow : node.getArrow().entrySet()){
	       			if( arrow.getKey() == node.getShortestPath().get(node.getShortestPath().size()-1)) {
      					arrow.getValue().setColor(Color.RED);
      					}	
	        		}
	       		}
			}
	      		DescrizioneAlgoritmi.setButton();
	      		this.cancel();
      	}
      }
     
      new Timer().schedule(new RemindTaskColorShortestPath(), t*timer);
      t++;
      
      /* Posiziono le label con le priorità dei nodi in sovraimpressione */
      for (Node node : graph.getNodes()) {
			node.getLabel().toFront();
      }
    
      /* Se nel ciclo sono presenti cicli negativi verrà stampano un nuovo grafo*/
      if (negativeCycle==true) {
    	  DescrizioneAlgoritmi.getTab().setContent(Dijkstra.createGraph());
      }
      
    return graph;
          
  }
}

