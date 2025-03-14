import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.Group;

import javafx.scene.paint.Color; 

public class Arrow extends Group
{
	private final Line lineTop;
	private final Line lineBack;
    private static final double distanceLine = 5;
    private static final double arrowLength = 10;
    private static final double arrowWidth = 7;
    private Color color;
    private Polygon arrow;
    private Edge edge;
    
    
    public Arrow(Edge edge)
    {
    	this(new Line(), new Line(), new Polygon(), edge) ;    
    }
  
  public Arrow(Line lineT, Line lineB, Polygon arrow, Edge edge) 
  {
	    super(lineB, lineT, arrow);
        this.lineBack = lineB;
        this.lineTop = lineT;
        this.arrow = arrow;
        this.edge = edge;
        this.color = Color.BLACK ;
        
        //collega la freccia al nodo indicato
        setStartX(this.edge.getNodeStart().getXCoord());     
        setStartY(this.edge.getNodeStart().getYCoord());
        setEndX(this.edge.getNodeEnd().getXCoord());
        setEndY(this.edge.getNodeEnd().getYCoord());
        
        this.lineTop.setStrokeWidth(2);
        this.lineBack.setStrokeWidth(2);
	
        double circonf = this.edge.getNodeEnd().getCircle().getRadius();

        double
              arrowsx = this.edge.getNodeStart().getXCoord(),
              arrowsy = this.edge.getNodeStart().getYCoord(),
              arrowex = this.edge.getNodeEnd().getXCoord(),
              arrowey = this.edge.getNodeEnd().getYCoord(),
              arrowdx = arrowsx - arrowex,
              arrowdy = arrowsy - arrowey;

      double dist = Math.hypot(arrowdx, arrowdy);

      arrowdx /= dist;
      arrowdy /= dist;

      arrowsx = arrowsx + distanceLine * arrowdy;
      arrowsy = arrowsy - distanceLine * arrowdx;
      arrowex = arrowex + distanceLine * arrowdy;
      arrowey = arrowey - distanceLine * arrowdx;

      setStartX(arrowsx);
      setStartY(arrowsy);
      setEndX(arrowex);
      setEndY(arrowey);

	  double distance = Math.hypot(arrowex - arrowsx, arrowey - arrowsy);
	  double t = (distance - circonf + 1) / distance;

      double arrowsEx = (1 - t) * arrowsx + t * arrowex;
      double arrowsEy = (1 - t) * arrowsy + t * arrowey;

      double parallelComponent = arrowLength / (distance - circonf);
      double orthogonalComponent = arrowWidth / (distance - circonf);

	  arrowdx = (arrowsx - arrowsEx) * parallelComponent;
	  arrowdy = (arrowsy - arrowsEy) * parallelComponent;
		
	  double ox = (arrowsx - arrowsEx) * orthogonalComponent;
	  double oy = (arrowsy - arrowsEy) * orthogonalComponent;
	  
	  arrow.getPoints().setAll(new Double[]{
	  		arrowsEx, arrowsEy,
	  		arrowsEx + arrowdx - oy, arrowsEy + arrowdy + ox,
	  		arrowsEx + arrowdx + oy, arrowsEy + arrowdy - ox
		           });
          
  	};

    public final void setStartX(double value) {
        lineTop.setStartX(value);
        lineBack.setStartX(value);
        
    }

    public final double getStartX() {
        return lineTop.getStartX();
    }

    public final void setStartY(double value) {
        lineTop.setStartY(value);
        lineBack.setStartY(value);
    }

    public final double getStartY() {
        return lineTop.getStartY();
    }

    public final void setEndX(double value) {
        lineTop.setEndX(value);
        lineBack.setEndX(value);
        
    }

    public final double getEndX() {
        return lineTop.getEndX();
    }

    public final void setEndY(double value) {
    	 lineTop.setEndY(value);
         lineBack.setEndY(value);
    }

    public final double getEndY() {
        return lineTop.getEndY();
    }
    
    public void setColor(Color color)
    {
    	this.color = color ;
    	this.highlight(this.color) ;
    }
    
    
   
    public Color getColor()
    {
    	return this.color ;
    }
    
    
    public void highlight(Color color)
    {
    	lineTop.setStroke(color) ;
        arrow.setFill(color) ;
        arrow.setStroke(Color.BLACK) ;
    }
   
}
