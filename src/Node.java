import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Node<E extends Cloneable> implements Cloneable {
    private E value;
    private Node<E> next;

    public Node(E value, Node<E> next) {
        this.value = value;
        this.next = next;
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
        this.value = value;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public boolean isContained(E other) {
        if (this.value.equals(other))
            return true;
        if (this.next == null)
            return false;
        return next.isContained(other);
    }

    /**
     * This method deep copy the Node by using recursion.
     *
     * @return Node<E>
     */
    @Override
    protected Node<E> clone() {
        try {
            //creating instance
            Node<E> cloned = (Node<E>) super.clone();

            //we use Node of Nodes, so it is required to check and act differently.
            //in any case it is recursive for deep copy
            if (this.value instanceof Node)
                // no invoking because it is a Node.
                cloned.value = (E) (((Node<E>) this.value).clone());
            else
                cloned.value = (E) (this.value).getClass().getMethod("clone").invoke(value);

            // recursion stop condition
            if (cloned.next != null) {
                cloned.next = this.next.clone();
            }
            return cloned;
        } catch (Exception e) {
            // As required to return null in case of any exception.
            return null;
        }

    }


}
