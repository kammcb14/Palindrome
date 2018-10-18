
public class Stack<T> implements StackInterface<T>{
        private LinkedList<T> data;

        public Stack() {
            data = new LinkedList<T>();
        }

        public void push(T entry){
            data.add(entry,0);
        }

        public T pop(){
            if(data.size()==0){
                throw new StackException("Tried to pop from empty list");
            }
            T toRemove = data.get(0);
            data.remove(0);
            return toRemove;
        }

        public int size(){
            return data.size();
        }

        public T peek(){
            if(data.size()==0){
                throw new StackException("Tried to peek from empty list");
            }
            return data.get(0);
        }

        public String toString(){
            return data.toString();
        }
}
