public class Node<E extends Cloneable> implements Cloneable{
    private E value;
    private Node<E> next;
    public Node(E value, Node<E> next) {
        this.value= value;this.next= next;
    }
    public Node(E value) {
        this(value, null);
    }

    public E getValue() {
        return value;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setValue(E value) {
        this.value= value;
    }

    public void setNext(Node<E> next) {
        this.next= next;
    }

    public boolean isContained(E other){
        if (this.value.equals(other))
            return true;
        if (this.next == null)
            return false;
        return next.isContained(other);
    }

    @Override
    protected Node<E> clone() throws CloneNotSupportedException {
        try {
            Node<E> clone = (Node<E>) super.clone();
            Node<E> head = new Node<>(clone.getValue());
            Node<E> tail = head;
            while (clone.getNext() != null) {
                tail.setNext(new Node<>(clone.getNext().getValue()));
                tail = tail.getNext();
                clone = clone.getNext();
            }
            return head;
        }
        catch (CloneNotSupportedException e) {
            return null;
        }
    }


}
