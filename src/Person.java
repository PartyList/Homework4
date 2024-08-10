public class Person {
    String person;
    String ID;
    String friend;

    public Person(String person, String ID, String friend) {
        this.person = person;
        this.ID = ID;
        this.friend = friend;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {return this.ID.equals(((Person) obj).ID);}
        return false;

    }
}
