import java.util.Iterator;

public class IsraeliQueue<T extends Cloneable> implements Cloneable, Iterable<T> {
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

    /**
     * adds a new person that has a friend to the IsraeliQueue based on the definition.
     *
     * @param data
     * @param friend
     * @throws InvalidInputException in case of a null data.
     */
    public void add(T data, T friend) throws InvalidInputException {
        if (data == null) {
            throw new InvalidInputException();
        }
        Node<Node<T>> temp_group = this.groups; // temp group to iterate and find the friend
        if (temp_group == null) // the group is empty so there are no friends in it.
            add(data);
        else {
            while (temp_group.getNext() != null) {
                if (temp_group.getValue().isContained(friend)) {
                    //friend has found. add the person to be the last in this group
                    Node<T> temp_node = temp_group.getValue();
                    while (temp_node.getNext() != null) {
                        temp_node = temp_node.getNext();
                    }
                    Node<T> data_node = new Node<>(data);
                    temp_node.setNext(data_node);
                    size++; // added new data.
                    updateHeadTail(); //this is required as groups and head/tail are different.
                    return;
                } else {
                    temp_group = temp_group.getNext();
                }
            }
            //didn't find the friend, normal add.
            if (temp_group.getNext() == null)
                add(data);
        }
    }


    public void add(T data) throws InvalidInputException {
        if (data == null) {
            throw new InvalidInputException();
        }
        Node<Node<T>> temp_group = this.groups;
        if (temp_group == null) {
            Node<T> new_val = new Node<>(data);
            Node<Node<T>> new_group = new Node<>(new_val);
            this.groups = new_group;

        } else {
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

    /**
     * This method updates the head and the tail after adding a new data to the queue.
     */
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


    /**
     * This method removes the first person from the Queue and returns the new head of the queue.
     *
     * @return T
     */
    public T remove() throws EmptyQueueException {
        T result = null; // to return the new head after removing
        if (groups != null) {
            T removedHead = head.getValue();
            if (groups.getValue().getNext() == null) {
                groups = groups.getNext();
            } else {
                //Taiyo I think this should be deleted and replaced with Empty exception.
                groups.setValue(groups.getValue().getNext());
            }
            this.head = this.head.getNext();
            result = removedHead;
        } else {
            throw new EmptyQueueException("An error occurred while trying to remove an element from the queue.");
        }
        return result;
    }

    /**
     * This method returns the head without removing
     *
     * @return T the head of the queue.
     */
    public T peek() {
        if (this.head == null) {
            throw new EmptyQueueException("An error occurred while trying to remove an element from the queue.");
        }
        return head.getValue();
    }

    public int size() {
        return this.size;
    }

    /**
     * Deep copy of IsraeliQueue by using the clone in node.
     *
     * @return IsraeliQueue<T>
     */
    @Override
    public IsraeliQueue<T> clone() {
        try {
            IsraeliQueue clone = (IsraeliQueue<T>) super.clone();
            clone.head = clone.head.clone(); // clones the whole linklist

            Node<T> cloned_tail = clone.head;
            //finds the tail of the linklist.
            while (cloned_tail.getNext().getNext() != null) {
                cloned_tail = cloned_tail.getNext();
            }
            clone.tail = cloned_tail;
            clone.groups = this.groups.clone();

            return clone;

        } catch (Exception e) {
            // As required to return null in case of any exception.
            return null;
        }
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> currentNode = head;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T data = currentNode.getValue();
                    currentNode = currentNode.getNext();
                    return data;
                }
                return null;
            }
        };
    }

//    private class QueueIterator implements Iterator<T> {
//        private Node<T> currentNode;
//
//        public QueueIterator() {
//            currentNode = head;
//        }
//
//        @Override
//        public boolean hasNext() {
//            return currentNode != null;
//        }
//
//        @Override
//        public T next() {
//            if (hasNext()) {
//                T data = currentNode.getValue();
//                currentNode = currentNode.getNext();
//                return data;
//            }
//            return null;
//        }
//    }


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
