# AlgaT - Algoritmi di Dijkstra e Bellman-Ford

## Introduzione

AlgaT è un programma sviluppato con l'obiettivo di fornire un tutorial interattivo sui cammini minimi nei grafi. Il programma implementa gli algoritmi di **Dijkstra** e **Bellman-Ford**, permettendo di visualizzarne l'esecuzione.

## Funzionalità

### Esecuzione del Programma

All’avvio, il programma mostra delle informazioni di contesto, e seguendo la semplice logica dei bottoni da premere, è possibile arrivare alla pagina in cui poter scegliere nel menù a tendina, se scegliere l’algoritmo di Dijkstra o quello di Bellman-Ford.
Scelto l’algoritmo, viene mostrata sulla sinistra una breve descrizione dello stesso, e sulla destra si ha la possibilità di cliccare su diversi bottoni, per generare un nuovo grafo, eseguire l’algoritmo immediatamente oppure eseguire l’algoritmo in maniera rallentata. 
Una volta terminato l’algoritmo, in basso a destra della schermata diventa cliccabile il bottone per entrare in modalità auto-apprendimento.
Questa fase termina solo se l’utente risponde correttamente a tutte le domande, e, in tal caso, viene riportato alla schermata precedente.

### Tipo di Grafo:

Il tipo di grafo generato per entrambi gli algoritmi è ciclico ed orientato.
Si è deciso di utilizzare solamente la versione orientata perché, sebbene i due algoritmi funzionino anche su grafi non orientati, è più semplice ed immediato dal punto di vista dell’utente capire l’algoritmo.

### Sezione di Test:

Le domande di auto-apprendimento risiedono su dei file di testo .txt.
L’idea è quella di mettere nella prima riga del file di testo la descrizione della domanda, ad esempio: “Qual è la complessità temporale dell’algoritmo di Dijkstra?”.
Nelle successive 3 righe, bisogna inserire le 3 risposte possibili.
Nella successiva riga finale, bisogna inserire il numero della risposta esatta.
Così facendo, se si vuole inserire una nuova domanda, basta aggiungere il nuovo file con il formato spiegato dentro la cartella **questions**. Successivamente, aggiungere tale file alla `List<String> questions` nella classe `DescrizioneAlgoritmi.java`.

## Installazione

### Prerequisiti

- **Java Development Kit (JDK) 9 o superiore**
- **JavaFX** (da scaricare separatamente)

### Download delle dipendenze

Scaricare e posizionare la libreria **JavaFX** in una cartella nota.

### Compilazione ed Esecuzione

#### Compilazione

Da terminale, posizionarsi nella cartella contenente `src/`, `Icons/`, ecc., e lanciare:

```bash
javac --module-path "path-to-javafx/lib" --add-modules javafx.controls,javafx.fxml -d bin src/*.java
```

Sostituire `path-to-javafx/lib` con il percorso assoluto della cartella `lib` di JavaFX (ad esempio, `/Users/username/Documents/javafx/lib`).

#### Esecuzione

Sempre dalla stessa cartella, lanciare:

```bash
java --module-path "path-to-javafx/lib" --add-modules javafx.controls,javafx.fxml -cp "bin:." WelcomePage
```
### Strutture Dati Utilizzate

- **Coda** per Bellman-Ford (implementata con LinkedList)
- **Coda con priorità** per Dijkstra (implementata con ArrayList e Generics)
- **Mappa di adiacenza** per rappresentare il grafo
- **HashMap** per gestire le distanze minime

### Licenza

Questo progetto è rilasciato sotto licenza **MIT**.

### Autori 

- Luca Cotugno
- Pierpaolo Faustini








