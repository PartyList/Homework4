import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
    protected Node<E> clone() {
        try {


            Node<E> cloned = (Node<E>) super.clone();
            if(this.value instanceof Node<?>)
                cloned.value = (E) (((Node<?>) this.value).clone());
            else
                cloned.value = (E) (this.value).getClass().getMethod("clone").invoke(value);
            System.out.println((value.getClass().getMethod("clone")));
            if(cloned.next != null){
                cloned.next = this.next.clone();
            }
            return cloned;
//            clone.value = cloned_value;
//            Node<E> cloned_next = clone.next;
//            Node<E> temp = ;
//            while(cloned_next != null){
//                cloned_value = (E) value.getClass().getMethod("clone").invoke(cloned_next.value);
//                cloned_next.setNext(new Node<E>(cloned_value));
//                cloned_next = cloned_next.next;
//            }

        }
        catch(Exception e){
            // As required to return null in case of any exception.
            System.out.println(e);
            return null;
        }

    }


}
