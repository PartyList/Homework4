public class Park {
    private String parkName;
    private final AmusementRide[] rides = new AmusementRide[5];

    public Park(String parkName) {
        this.parkName = parkName;
    }

    /**
     * Adds a ride to the park.
     *
     * @param ride
     */
    public void add(AmusementRide ride) {
        //looks if there is any space left for a new ride.
        for (int i = 0; i < rides.length; i++) {
            if (this.rides[i] == null) {
                this.rides[i] = ride;
                return;
            }
        }
        //add exception
    }

    /**
     * Removes the specified amusement ride from the park.
     * @param amusement
     */
    public void remove(AmusementRide amusement) {
        //goes through all the rides in the park to look which one to remove.
        for (int i = 0; i < rides.length; i++) {
            if (this.rides[i] != null && this.rides[i].equals(amusement)) {
                this.rides[i] = null;
                return;
            }
        }
        //add exception
    }

    /**
     * Activates all the amusement Rides in the park.
     *
     */
    public void startRides(){
        //goes through all the existing rides and start each of them.
        for (int i = 0; i < rides.length; i++) {
            if (this.rides[i] != null) {
                this.rides[i].startRide();
            }
        }
    }

    /**
     * Add a person to a specified ride in the park.
     *
     * @param amusement
     * @param person
     */
    public void addPerson(AmusementRide amusement, Person person){
        //goes through all the rides looks for the specified one and add the person accordingly.
        for (int i = 0; i < rides.length; i++) {
            if (this.rides[i] != null && this.rides[i].equals(amusement)) {
                this.rides[i].addPerson(person);
                return;
            }
        }
    }
}
