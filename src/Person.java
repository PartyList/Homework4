public class Person implements Cloneable {
    private final String name;
    private final int ID;
    private final Person friend;

    public Person(String name, int ID, Person friend) {
        this.name = name;
        this.ID = ID;
        this.friend = friend;
    }

    /**
     * This method returns the name of the person.
     *
     * @return String
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * This method checks whether 2 persons are the same.
     *
     * @param obj
     * @return boolean, true for same ID. otherwise false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            return this.ID == (((Person) obj).ID);
        }
        return false;

    }

    /**
     * This method shallow copy the Person.
     * (Strings are immutable so shallow copy are the same as deep copy but friend is person so this part is shallow)
     *
     * @return Person
     */
    @Override
    public Person clone() {
        return new Person(name, ID, friend);
    }

    public Person getFriend() {
        return friend;
    }
}
