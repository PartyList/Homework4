import java.util.Iterator;

class MyCloneable implements Cloneable{
    private int num;

    public MyCloneable(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "MyCloneable: " + num;
    }

    @Override
    public int hashCode() {
        return num;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MyCloneable)){
            return false;
        }
        MyCloneable other = (MyCloneable) obj;
        return num == other.num;
    }

    @Override
    public MyCloneable clone() {
        try {
            return (MyCloneable) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        /*try {
            System.out.println("Test 1 starts");
            test1();
        }catch (Exception e){
            System.out.println("exception " + e);
        }finally {
            System.out.println("Test 1 done");
            System.out.println("--------------------------------------------");
        }*/

        Cloneable s = new MyCloneable(1);
        IsraeliQueue<MyCloneable> queue = new IsraeliQueue<>();
        queue.add(new MyCloneable(1));
        queue.add(new MyCloneable(2));
        queue.add(new MyCloneable(3));
        queue.add(new MyCloneable(4),new MyCloneable(8));
        queue.add(new MyCloneable(5),new MyCloneable(6));
        queue.add(new MyCloneable(6),new MyCloneable(2));
        queue.add(new MyCloneable(7),new MyCloneable(1));
        queue.add(new MyCloneable(8),new MyCloneable(1));
        IsraeliQueue<MyCloneable> cloned = queue.clone();
        Cloneable [] hello = {s};
        queue.remove();
        queue.remove();
        queue.remove();
        queue.remove();
        queue.remove();
        queue.remove();
        queue.remove();
        queue.remove();
    }

//    public static void test1(){
//        IsraeliQueue<MyCloneable> queue = new IsraeliQueue<>();
//        queue.add(new MyCloneable(1));
//        queue.add(new MyCloneable(2));
//        queue.add(new MyCloneable(3));
//
//        IsraeliQueue<MyCloneable> clonedQueue = queue.clone();
//
//        Iterator<MyCloneable> iterator = queue.iterator();
//        Iterator<MyCloneable> clonedIterator = clonedQueue.iterator();
//
//        while (iterator.hasNext() && clonedIterator.hasNext()){
//            MyCloneable fromOrg = iterator.next();
//            MyCloneable fromCloned = clonedIterator.next();
//
//            System.out.println(fromOrg);
//            System.out.println(fromCloned);
//            System.out.println(fromOrg.equals(fromCloned));
//            System.out.println(fromOrg == fromCloned);
//        }
//    }
}


/*
1. super park with rides.

2. up to 5 rides to pay property tax

3. every ride has a name, queue of people and max for simultaneously use

4. a. every individual has I.D and friend.

4. b. individuals are equal if their I.D is the same.

5. individuals can enter the queue of a certain ride and will proceed until they can enter the ride.

6. we use data structure that implies queue in order to let the people in the ride and out.

 */


