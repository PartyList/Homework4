import java.util.Iterator;

public class AmusementRide {
    private final String name;
    private final IsraeliQueue<Person> PeopleQueue = new IsraeliQueue<>();
    private final int actionCapacity;

    public AmusementRide(String name, int actionCapacity) {
        this.name = name;
        this.actionCapacity = actionCapacity;
    }

    /**
     * This method starts the ride. It prints the names of the people in the queue.
     */
    public void startRide() {
        int size = Math.min(actionCapacity, PeopleQueue.size());
        //checks if there are people in the queue for the ride.
        if (size <= 0)
            System.out.println("Ride is empty.");
        else {
            //goes through each turn in the queue and starts the ride.
            Iterator<Person> iterator = PeopleQueue.iterator();
            System.out.println("Currently using the ride:");
            while (size > 0 && iterator.hasNext()) {
                PeopleQueue.remove();
                Person per = iterator.next();
                System.out.println(per.toString());
                size--;
            }
        }


    }

    public void addPerson(Person person) {
        PeopleQueue.add(person,person.getFriend());
    }
}
