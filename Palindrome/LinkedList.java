public class LinkedList<T> implements ListInterface<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedList() {
        size = 0;
    }

    public int size() {
        return size;
    }

    public void add(T data, int index) {
        if (index > size || index<0) {
            throw new ListException("Tried to add out of List");
        } else if (size == 0) {
            head = new Node<T>(data, head);
            tail = head;
        } else if (index == 0) {
            head = new Node<T>(data, head);
        } else if (index == size) {
            tail.next = new Node<T>(data, tail.next);
            tail = tail.next;
        } else {
            Node<T> current = head;
            while (index > 1) {
                current = current.next;
                index--;
            }
            current.next = new Node<T>(data, current.next);
        }
        size++;

    }

    public T get(int index) {
        Node<T> current = head;
        if (index >= size || index<0) {
            throw new ListException("Tried to get from outside of List");
        } else {
            while (index > 0) {
                current = current.next;
                index--;
            }
            return current.data;
        }
    }

    public void remove(int index) {
        Node<T> current = head;
        if(index>=size || index<0){
             throw new ListException("Tried to remove from outside of List");
        }
        else if (index == 0 && size == 1) {
            head = null;
            tail = null;
        } else if (index == 0) {
            head = head.next;
        } else if (index == size - 1) {
            while (current.next != tail) {
                current = current.next;
            }
            tail = current;
            tail.next = null;
        } else {
            while (index > 1) {
                current = current.next;
                index--;
            }
            current.next = current.next.next;
        }
        size--;
    }

    public String toString() {
        if (size <= 0) {
            return "empty";
        }
        String[] stringArray = new String[size];
        Node<T> current = head;
        int index = 0;
        while (index < size) {
            stringArray[index] = current.data.toString();
            current = current.next;
            index++;
        }
        String ans = "(" + String.join(", ", stringArray) + ")";
        return ans;
    }


    protected class Node<T> {
        public T data;
        public Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }


}
