public class IsraeliQueue<T>{
    /**
     * attributes - head,tail, size
     * methods: add1,add2,remove,peek,size,clone.
     * implies Iterable, IsraeliQueueException
     *
     */

    private Node<T> head;
    private Node<T> tail;
    private Node<Node<T>> groups;
    private int size;


    public IsraeliQueue() {
        this.head = null;
        this.tail = null;
        this.groups = null;
        this.size = 0;
    }

    public void add(T data,T friend){
        Node<Node<T>> temp_group = this.groups;
        if(temp_group == null)
            add(data);
        else{
        while(temp_group.getNext() != null ){
            if (temp_group.getValue().isContained(friend)){
                Node<T> temp_next_node = temp_group.getNext().getValue();
                Node<T> temp_node = temp_group.getValue();
                while(temp_node.getNext() != null){
                    temp_node = temp_node.getNext();
                }
                Node <T> data_node = new Node<>(data, temp_next_node);
                temp_node.setNext(data_node);
                size++;
                if(temp_group.getNext() == null){
                    tail = temp_node;

                }
                break;
            }
            else{
                temp_group = temp_group.getNext();
            }
            if(temp_group.getNext() == null)
                add(data);
        }

    }
    }






    public void add(T data){
        Node<Node<T>> temp_group = this.groups;
        if(temp_group == null){
            Node<T> new_val = new Node<>(data);
            Node<Node<T>> new_group = new Node<>(new_val);
            this.groups = new_group;
            head = new_group.getValue();
            tail = head;
        }
        else {
            while (temp_group.getNext() != null) {
                temp_group = temp_group.getNext();
            }

            Node<T> new_val = new Node<>(data);
            Node<Node<T>> new_group = new Node<>(new_val);
            temp_group.setNext(new_group);
            tail = temp_group.getNext().getValue();
        }
        size++;
    }
}

/*
 Node class representing each element in the queue
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public Queue() {
        front = null;
        rear = null;
        count = 0;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(T item) {
        Node<T> newNode = new Node<>(item);
        if (rear != null) {
            rear.next = newNode;
        }
        rear = newNode;
        if (front == null) {
            front = newNode;
        }
        count++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Dequeue from an empty queue");
        }
        T item = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        count--;
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Peek from an empty queue");
        }
        return front.data;
    }

    public int size() {
        return count;
    }

    public static void main(String[] args) {
        Queue<Integer> q = new Queue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);

        System.out.println("Dequeued: " + q.dequeue());  // Outputs: Dequeued: 1
        System.out.println("Peek: " + q.peek());         // Outputs: Peek: 2
        System.out.println("Size: " + q.size());         // Outputs: Size: 2
    }
 */
