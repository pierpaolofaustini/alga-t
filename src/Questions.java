import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Questions {

	static int questionIndex = 0;
	static int answerValue;
	
	static int getI() {
		return questionIndex; 
	}
	
	static void resetI() {
		questionIndex=0;
	}
	
	private static void addI(int i) {
		questionIndex = i + 1; 
	}
	
	private static int getAnswerValue() {
		return answerValue;
	}
	
	private static void setAnswerValue(int value) {
		answerValue = value; 
	}
	
	public static void setWindow(Stage window,String path) throws IOException {
		window.setScene(displayQuestions(window,path));
	}
	
	public static Scene displayQuestions(Stage window,String path) throws IOException {
		
		List<String> questions = new ArrayList<>();
		String filename1;
		String filename2;
		String filename3;
		String filename4;
		String filename5;
		filename1 = "Domanda1.txt";
		filename2 = "Domanda2.txt";
		filename3 = "Domanda3.txt";
		filename4 = "Domanda4.txt";
		filename5 = "Domanda5.txt";
		questions.add(filename1);
		questions.add(filename2);
		questions.add(filename3);
		questions.add(filename4);
		questions.add(filename5);
		
		StackPane pane = new StackPane();
       
        VBox root = new VBox(5);
        pane.getChildren().add(root);
        root.setAlignment(Pos.CENTER);
        
        List<String> lines = new ArrayList<>();
        Label title = new Label(); 
        Button answer = new Button("Clicca qui per rispondere");
        Button next = new Button("Prossima domanda");
        next.setDisable(true);
        
        Alert popup = new Alert(AlertType.INFORMATION);
        popup.setContentText("Risposta sbagliata! Riprova.");
        
        ToggleGroup layout = new ToggleGroup();
        
        Label spiegazione = new Label("");
        Label risposta = new Label("");
    	RadioButton answer1 = new RadioButton(); 
        RadioButton answer2 = new RadioButton();
        RadioButton answer3 = new RadioButton();
        
        answer1.setToggleGroup(layout);
        answer2.setToggleGroup(layout);
        answer3.setToggleGroup(layout);
  
        root.getChildren().addAll(title, answer1, answer2, answer3,answer,next); 
        
        
        //Logic 

        Path wiki_path = Paths.get(path , questions.get(Questions.getI()));
        Charset charset = Charset.forName("UTF-8");
        if(getI() < questions.size()) {
        	try {
        	 
        	lines = Files.readAllLines(wiki_path, charset);
        	title.setText(lines.get(0).toString()); //Getting the title
    		answer1.setText(lines.get(1).toString()); //Getting all the 3 possible answers
    		answer2.setText(lines.get(2).toString());
    		answer3.setText(lines.get(3).toString());
    		String stringValue = lines.get(4).toString(); //Getting the real answer
    		risposta.setText(lines.get(5).toString());
    		spiegazione.setText(lines.get(6).toString());
    		int numericAnswer = Integer.parseInt(stringValue);
    		setAnswerValue(numericAnswer);
    		
    		
    		answer.setOnAction(e -> {
    			
    			if(!answer1.isSelected() && !answer2.isSelected() && !answer3.isSelected()){
    				
    				Alert alert = new Alert(AlertType.INFORMATION);
    				alert.setTitle("Errore");
    				alert.setHeaderText(null);
    				alert.setContentText("Nessuna risposta selezionata,selezionare una risposta");

    				alert.showAndWait();
    			}
    			else if(answer1.isSelected() && getAnswerValue() != 1) {
    				
    				Alert alert = new Alert(AlertType.INFORMATION);
    				alert.setTitle("Risposta sbagliata");
    				alert.setHeaderText(null);
    				alert.setContentText("Risposta sbagliata");

    				alert.showAndWait();
    			}
    			else if (answer2.isSelected() && getAnswerValue() != 2) {
    				
    				Alert alert = new Alert(AlertType.INFORMATION);
    				alert.setTitle("Risposta sbagliata");
    				alert.setHeaderText(null);
    				alert.setContentText("Risposta sbagliata");

    				alert.showAndWait();
    			}
    			else if (answer3.isSelected() && getAnswerValue() != 3) {
    				
    				Alert alert = new Alert(AlertType.INFORMATION);
    				alert.setTitle("Risposta sbagliata");
    				alert.setHeaderText(null);
    				alert.setContentText("Risposta sbagliata");

    				alert.showAndWait();
    			}
    			else {
    						addI(questionIndex); 
							next.setDisable(false);
							answer.setDisable(true);
							root.getChildren().addAll(risposta,spiegazione);
    				}
    			});
        	} catch (IOException e1) {
        		Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Errore");
				alert.setHeaderText(null);
				alert.setContentText("Errore nel caricare le domande, chiudere e riaprire il programma");

				alert.showAndWait();
        	}   	
        }
        next.setOnAction(e -> {
        	try {
        		if(getI() < questions.size()) {
        			Questions.setWindow(window,path);

        		}
				else if (getI() == questions.size()) {
					Alert alert = new Alert(AlertType.INFORMATION);
    				alert.setTitle("Autoapprendimento completato");
    				alert.setHeaderText(null);
    				alert.setContentText("Hai completato le domande su questa lezione, continua con la prossima lezione");

    				alert.showAndWait();
					window.close();
					}
        	}
        		catch (IOException e1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Errore");
				alert.setHeaderText(null);
				alert.setContentText("Errore nel caricare le domande, chiudere e riaprire il programma");

				alert.showAndWait();
			}
        		
        		});
        Scene scene = new Scene(pane, 1000, 600);
        return scene;
	}
}
