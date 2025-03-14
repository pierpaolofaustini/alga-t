import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.scene.image.Image;

public class WelcomePage extends Application {
	
	public static Stage window; 
    
	/* Metodo per ritornare l'altezza della finestra da utilizzare successivamente per creare nuove finestre con le stesse dimensioni iniziali */ 
    public static double returnHeight() {
    	return window.getHeight();
    }
    
    /* Metodo per ritornare la larghezza della finestra da utilizzare successivamente per creare nuove finestre con le stesse dimensioni iniziali */
    public static double returnWidth() {
    	return  window.getWidth();
    }
    
    public static void setResizable(boolean b) {
    	window.setResizable(b);
    }
    
    /* Viene creata la nuova scena, posizionata al centro dello schermo e non è permesso ridimensionarla */
    public static void SetScene(Scene scene) {
    	window.setScene(scene);
    	window.centerOnScreen();
        window.setResizable(false);
    }
	
    @Override
    public void start(Stage primaryStage) {
    	
    		window = primaryStage;
            
    		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon/Icon.png")));
    		primaryStage.setTitle("AlgaT");
            StackPane layout = new StackPane(); 
            
            Text text1 = new Text("Benvenuti in AlgaT!");
            Text text2 = new Text("AlgaT è un simulatore interativo di algoritmi.\n");
            Text text3 = new Text("Developed by Luca Cotugno & Pierpaolo Faustini");
            
            Button buttonStart = new Button("Clicca qui per iniziare!");
            
            layout.getChildren().addAll(text1, text2, text3, buttonStart);
            
            StackPane.setAlignment(text1, Pos.TOP_CENTER);
            StackPane.setAlignment(text2, Pos.CENTER);
            StackPane.setAlignment(buttonStart, Pos.CENTER);
            StackPane.setAlignment(text3, Pos.BOTTOM_RIGHT);
            
            StackPane.setMargin(text1, new Insets(70, 0, 0, 0));
            StackPane.setMargin(text2, new Insets(0, 0, 0, 0));
            StackPane.setMargin(buttonStart, new Insets(70, 0, 0, 0));
            StackPane.setMargin(text3, new Insets(0, 50, 20, 0));
         
            buttonStart.setOnAction(e -> {
               window.setScene(Algo.createIntroduction());
               window.setResizable(false);
            });
           
            /* La finestra prende la larghezza e l'altezza massima rispetto allo schermo in cui il programma viene eseguito */
            window.setMaximized(true);
            Scene scene = new Scene(layout,window.getHeight(),window.getWidth());
            
            	
            window.setScene(scene);
            window.show();
            window.setResizable(false);
    }
 
}
