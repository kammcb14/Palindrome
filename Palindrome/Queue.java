
public class Queue<T> {
    private LinkedList<T> list;
    public Queue(){
        list = new LinkedList<T>();
    }

    public void enqueue(T data){
        list.add(data, list.size());
    }

    public T dequeue(){
        if(list.size()==0){
            throw new QueueException("Tried to dequeue from empty List");
        }
        T temp = list.get(0);
        list.remove(0);
        return temp;
    }

    public int size(){
        return list.size();
    }
    public T peek(){
        if(list.size()==0){
            throw new QueueException("Tried to peek from empty list");
        }
        return list.get(0);
    }
    public String toString(){
        return list.toString();
    }
}
