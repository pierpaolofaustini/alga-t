import java.util.Map.Entry;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.*; 


public class DijkstraAlgorithm {
	
	public static Graph calculateShortestPathFromSource(Graph graph, Node source, int timer) {
		
		/* Creo la lista di nodi già visitati e la coda dei nodi ancora da visitare  */
        List<Node> settledNodes = new ArrayList<>();
        PriorityQueue<Node> unsettledNodes = new PriorityQueue<>(); 
        
        unsettledNodes.push(source, 0);
        source.setPriority(0);
        source.setPriorityLabel();
        int t = 1; 
        
		while (!unsettledNodes.isEmpty()) {

            Node currentNode = unsettledNodes.pop().getItem();
            
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
                
                adjacentNode.getLabel().setVisible(true);
                
                /* Creo il task da eseguire per cambiare colore all'arco nel momento in cui viene schedulato */
                class RemindTaskColorEdge extends TimerTask {
         	        public void run(){
         	        	for (Entry<Node, Arrow> arrow : adjacentNode.getArrow().entrySet()) {
                         	if (arrow.getKey() == currentNode ) {
                         		if( arrow.getValue().getColor() == Color.BLACK) {
                         			arrow.getValue().setColor(Color.RED); }
                         		else {
                         			arrow.getValue().setColor(Color.BLACK); }
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
                     	unsettledNodes.push(adjacentNode, currentNode.getPriority() + edgeWeight);
                     	adjacentNode.setIsContained(true);
                     	
                     }
                	 
                	 else {
                		 adjacentNode.setPriority(currentNode.getPriority() + edgeWeight);
                		 
                		 new Timer().schedule(new RemindTaskPriorityLabel(), t*timer);
                		 
                         t++;
                	 }
                	 
                	 List<Node> shortestPath = new ArrayList<>(currentNode.getShortestPath());
                     shortestPath.add(currentNode);
                     adjacentNode.setShortestPath(shortestPath);
                     adjacentNode.setPriority(currentNode.getPriority() + edgeWeight);

                     new Timer().schedule(new RemindTaskPriorityLabel(), t*timer);
                     
                     t++;
                     
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
         
        return graph;
	}  
  }





 