public class PriorityItem<T> {

    private T item;
    private int priority;
 

    public PriorityItem(T object,int priority){
        setItem(object);
        this.priority = priority;
    }

    public PriorityItem(T object){
        setItem(object);
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
