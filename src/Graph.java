import java.util.*; 

public class Graph {

    private List<Node> nodes = new ArrayList<>();
    private List<Node> settledNodes = new ArrayList<>();

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
    
    public List<Node> getSettledNodes() {
    	return this.settledNodes;
    }
    
    public void setSettledNodes(List<Node> settledNodes) {
    	this.settledNodes = settledNodes;
    }
}
