public class IsraeliQueue<T extends Cloneable> implements Cloneable{
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
                Node<T> temp_node = temp_group.getValue();
                while(temp_node.getNext() != null){
                    temp_node = temp_node.getNext();
                }
                Node <T> data_node = new Node<>(data);
                temp_node.setNext(data_node);
                size++;
                break;
            }
            else{
                temp_group = temp_group.getNext();
            }

        }
            if(temp_group.getNext() == null)
                add(data);
            else{
                updateHeadTail();
            }
    }
    }






    public void add(T data){
        Node<Node<T>> temp_group = this.groups;
        if(temp_group == null){
            Node<T> new_val = new Node<>(data);
            Node<Node<T>> new_group = new Node<>(new_val);
            this.groups = new_group;

        }
        else {
            while (temp_group.getNext() != null) {
                temp_group = temp_group.getNext();
            }

            Node<T> new_val = new Node<>(data);
            Node<Node<T>> new_group = new Node<>(new_val);
            temp_group.setNext(new_group);
        }
        updateHeadTail();
        size++;
    }

    private void updateHeadTail(){
        Node<Node<T>> temp_groups = this.groups;
        this.head = null;
        this.tail = null;
        if(temp_groups != null) {
            while(temp_groups != null){
                Node<T> temp_node = temp_groups.getValue();

                while(temp_node != null){
                    if(this.head == null){
                        this.head = new Node<>(temp_groups.getValue().getValue());
                        this.tail = this.head;
                    }
                    else{
                    Node<T> next_node = new Node<>(temp_node.getValue());
                    tail.setNext(next_node);
                    tail = tail.getNext();
                    }
                    temp_node = temp_node.getNext();
                }
                temp_groups = temp_groups.getNext();
            }
        }

        }

        public T remove(){
            T result = null;
            if(groups != null){
                T removedHead = head.getValue();
                if(groups.getValue().getNext() == null){
                    groups = groups.getNext();
                }
                else{
                    groups.setValue(groups.getValue().getNext());
                }
                updateHeadTail();
                result = removedHead;
            }
            return result;
        }

        public T peek(){
            if(this.head == null){
                //EmptyQueueException
            }
            return head.getValue();
        }

        public int size(){ return this.size; }


    @Override
    public IsraeliQueue<T> clone() {
        IsraeliQueue<T> result;
        try {
            IsraeliQueue clone = (IsraeliQueue<T>) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            clone.updateHeadTail();
            Node<Node<T>> groups_head = clone.groups.clone();
            Node<Node<T>> groups_tail = groups_head;
            while(groups_tail != null){
                groups_tail.setValue(groups_tail.getValue().clone());
                groups_tail = groups_tail.getNext();
            }

            clone.groups = groups_head;

//            Node<Node<T>> temp_groups = this.groups;
//            this.head = null;
//            this.tail = null;
//            if(temp_groups != null) {
//                while(temp_groups != null){
//                    Node<T> temp_node = temp_groups.getValue();
//
//                    while(temp_node != null){
//                        if(this.head == null){
//                            this.head = new Node<>(temp_groups.getValue().getValue());
//                            this.tail = this.head;
//                        }
//                        else{
//                            Node<T> next_node = new Node<>(temp_node.getValue());
//                            tail.setNext(next_node);
//                            tail = tail.getNext();
//                        }
//                        temp_node = temp_node.getNext();
//                    }
//                    temp_groups = temp_groups.getNext();
//                }
//            }



            return clone;
        } catch (CloneNotSupportedException e) {
            //throw new AssertionError();
            return null;
        }
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
