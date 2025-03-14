import java.util.LinkedList;
import java.util.*; 
import java.util.Map;
import java.util.Random;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;


public class Node {

	
	private Circle circle;
	private double xCoord, yCoord;
	private int nodeNumber;
	private String label = "";
	private Label label1;
	private double LabelXCoord, LabelYCoord;
	private Map<Node,Integer> adjacentNodes = new HashMap<>();
	private Map<Node, Arrow> listArrow = new HashMap<>(); 
	
	private Integer priorityLabel = Integer.MAX_VALUE;
	private List<Node> shortestPath = new LinkedList<>();
	
	/* Equivalente a b[u] nelle slides del professore */
	private boolean isContained = false; 
	
	
	public Map<Node, Arrow> getArrow() {
		return this.listArrow;
	}
	
	public Label getLabel() {
		return label1;
	}
	
	public String getString() {
		return label;
	}
	
	public double getXLabel() {
		return LabelXCoord;
	}
	
	public double getYLabel() {
		return LabelYCoord;
	}
	
	public void setText(String s) {
		this.label1.setText(s);
	}
	
	public boolean getIsContained() {
		return this.isContained;
	}
	
	public void setIsContained(boolean contained) {
		this.isContained = contained;
	}
	
	public boolean getAdjacentNodes(Node node) {
		return this.adjacentNodes.containsKey(node);
	}

	public Map<Node, Integer> getAdjacentNodes() {
		return adjacentNodes;
	}

	public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
	    this.adjacentNodes = adjacentNodes;
	}
	
	public void setNodeNumber(int n) {
		this.nodeNumber = n;
	}
	
	public int getNodeNumber() {
		return this.nodeNumber;
	}
	
	public String getNodeNumberString() {
		return Integer.toString(this.nodeNumber);
	}
	
	public double getRadiusCircle() {
		return circle.getRadius();
	}
	
	public Circle getCircle() {
		return circle;
	}
	
	public void setXCoord(double x) {
		this.xCoord = x;
	}
	
	public void setYCoord(double y) {
		this.yCoord = y; 
	}
	
	public double getXCoord() {
		return xCoord;
	}
	
	public double getYCoord() {
		return yCoord;
	}
	
	public void highlight(Color color) {
		this.circle.setFill(color) ;
	}
	
	public void setPriority(int priority) {
		this.priorityLabel = priority; 
	}
	
	public int getPriority() {
		return this.priorityLabel; 
	}
	
	public List<Node> getShortestPath() {
	    return shortestPath;
	}
	
	public void setShortestPath(List<Node> shortestPath) {
	    this.shortestPath = shortestPath;
	}
	
	public void addArrow(Node node, Arrow arrow) {
		this.listArrow.put(node, arrow);
	}
	
	public void addDestination(Node destination,int distance){
		adjacentNodes.put(destination, distance);	
	}
	
	public Node(int n) {
		this.nodeNumber = n; 
		this.circle = new Circle (0, 0, 27);
	}
	
	public Node(int n, double x, double y) {
		this.setNodeNumber(n);
		this.setXCoord(x);
		this.setYCoord(y); 
		this.circle = new Circle (x, y, 27);
	}
	
	public static void createNode(List<Node> dots,Pane insidePane){
		int k = 0; 
    	int nNodes = getMaxNodes(); 
    	Node firstNode = new Node(k, 0, 0); 
    	/* Serve ad ottenere il punto più alto della circonferenza da cui partirà a posizionare i nodi */
		double radiusTop = Math.min(insidePane.getMinWidth(), insidePane.getMinHeight()) - firstNode.getRadiusCircle();
		
    	for (int i = 0; i < nNodes; i++) {
         	
            Node dot = new Node(k);
            dots.add(dot);
            k++; 
     	}
    		/* Funzione matematica per posizionare i nodi geometricamente */
    	for (Node node : dots) {
         	double x = insidePane.getMinWidth() + radiusTop * Math.cos(k * 2 * Math.PI / dots.size() - Math.PI / 2);
         	double y = insidePane.getMinHeight() + radiusTop * Math.sin(k * 2 * Math.PI / dots.size() - Math.PI / 2);
         	
         	k++; 
         	
            node.setXCoord(x);
            node.setYCoord(y);
            node.getCircle().setCenterX(x);
            node.getCircle().setCenterY(y);
    	}
    	
    	
	}
	
	public static void printNode(List<Node> dots, Pane insidePane){
		
    	for (Node node : dots) {
         	insidePane.getChildren().addAll(node.getCircle());
     	}	
	}
	
	/* Imposta le label dei nodi contenenti la priorità e li posiziona esternamente rispetto al grafo */
	public void setPriorityNode(List<Node> dots , Pane insidePane) {
		int xCoordPane = (int)insidePane.getMinWidth() / 2;
		int yCoordPane = (int)insidePane.getMinHeight() / 2;
		if( this.priorityLabel == Integer.MAX_VALUE)
		for (Node node : dots) {
			if ((int)node.getYCoord() < xCoordPane-1 ) {
				if( this.priorityLabel == Integer.MAX_VALUE) {
					this.label = "+∞"; 
					this.label1 = new Label(label);}
				else {
					this.label = Integer.toString(this.priorityLabel); }
				this.label1 = new Label(label);
				this.label1.setLayoutX(node.getXCoord() - 10);
				this.label1.setLayoutY(node.getYCoord()-50);
					node.LabelXCoord = node.getXCoord() - 10;
					node.LabelYCoord = node.getYCoord() - 50;

			}
			else if ((int)node.getYCoord() > xCoordPane ) { 
				if( this.priorityLabel == Integer.MAX_VALUE) {
					this.label = "+∞"; 
					this.label1 = new Label(label);}
				else {
					this.label = Integer.toString(this.priorityLabel); }
					this.label1 = new Label(label);
					this.label1.setLayoutX(node.getXCoord() - 10);
					this.label1.setLayoutY(node.getYCoord() + 50 );
						node.LabelXCoord = node.getXCoord() - 10;
						node.LabelYCoord = node.getYCoord() + 50;
			}
			else {  
				 	if ((int)node.getXCoord()-200 < yCoordPane) {
				 		if( this.priorityLabel == Integer.MAX_VALUE) {
				 			label = "+∞"; 
							label1 = new Label(label);}
						else {
							this.label = Integer.toString(this.priorityLabel); }
				 		this.label1 = new Label(label);
				 		this.label1.setLayoutX(node.getXCoord() - 70);
				 		this.label1.setLayoutY(node.getYCoord());
				 			node.LabelXCoord = node.getXCoord() - 70;
							node.LabelYCoord = node.getYCoord();
						}
				 	else {
				 		if(this.priorityLabel == Integer.MAX_VALUE) {
				 			this.label = "+∞"; 
				 			this.label1 = new Label(label);}
						else {
							this.label = Integer.toString(this.priorityLabel); }
				 			this.label1 = new Label(label);
				 			this.label1.setLayoutX(node.getXCoord() + 50);
				 			this.label1.setLayoutY(node.getYCoord());
				 			node.LabelXCoord = node.getXCoord() + 50;
							node.LabelYCoord = node.getYCoord();
				 			}
					}
			insidePane.getChildren().addAll(this.label1);
				}
			}
	
	/* Imposta il numero all'interno del nodo */
	public static void setLabel(List<Node> dots, Pane insidePane) {
		for (Node node : dots) {
		
			Text text = new Text();
			text.setText(node.getNodeNumberString());
			text.setX(node.getXCoord());
        	text.setY(node.getYCoord());
        	text.setX(text.getX() - text.getLayoutBounds().getWidth() / 2);
        	text.setY(text.getY() + text.getLayoutBounds().getHeight() / 4);
        	text.setFill(Color.WHITE);
        	insidePane.getChildren().addAll(text); 
		}
	}
	
	/* Aggiorna la label contenente la priorità del nodo */
	public void setPriorityLabel(){
		this.label = Integer.toString(priorityLabel);
		this.label1.setLayoutX(this.LabelXCoord + 5);
		this.label1.setLayoutY(this.LabelYCoord);
		this.label1.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(2), new Insets(-3,-8,-3,-8))));
		this.label1.toFront();
		setText(this.label);
		
	}
	
	public static int getMaxNodes() {
		int minNodes = 8;
		int maxNodes = 9; 
		Random randNodesGenerator = new Random();
    	int nNodes = randNodesGenerator.nextInt((maxNodes - minNodes) + 1) + minNodes;
    	return nNodes; 
	}
	
}