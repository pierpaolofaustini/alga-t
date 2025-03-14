import java.util.ArrayList;

public class PriorityQueue<T> {

    private ArrayList<PriorityItem<T>> list;

    public PriorityQueue(){
        list = new ArrayList<>();
    }

    /* Inserisco l'elemento nella Priority Queue con quella priorità */
    public void push(T object,int priority){
        PriorityItem<T> priorityItem = new PriorityItem<>(object,priority);
        this.list.add(priorityItem);
    }

    /* Restituisce l'elemento della Priority Queue con la priorità minore*/
    public PriorityItem<T> pop(){
        PriorityItem<T> min = searchMin();
        remove(min);
        return min;
    }

    /* Restituisce l'elemento cercato */
    public PriorityItem<T> read(T item){
        PriorityItem<T> priorityItem = new PriorityItem<>(item);
        return searchItem(priorityItem);
    }

    /* Controlla se l'elemento cercato è nella Priority Queue */
    public boolean existItem(T item){
        PriorityItem<T> priorityItem = new PriorityItem<>(item);
        return searchItem(priorityItem) != null;
    }

    /* Rimuove l'elemento dalla Priority Queue */
    public void remove(PriorityItem<T> priorityItem){
        if(priorityItem != null){
            this.list.remove(priorityItem);
        }
    }
    
    public boolean isEmpty(){
        return this.list.isEmpty();
    }

    public PriorityItem<T> searchMin(){
        PriorityItem<T> min = null;
        for(PriorityItem<T> item : list){
            if (min == null || item.getPriority() < min.getPriority()){
                min = item;
            }
        }
        return min;
    }

    private PriorityItem<T> searchItem(PriorityItem<T> priorityItem){
        PriorityItem<T> searched = null;
        for(PriorityItem<T> item:this.list){
            if(priorityItem.equals(item)){
                searched = item;
            }
        }
        return searched;
    }

}
