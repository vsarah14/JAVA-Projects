package model;

public class Person {

    /**
     *declares the fields of the Person Class
     */
    private int id;
    private int arrivalTime;
    private int serviceTime;

    /**
     * getters and setters
     * @return the parameters of the class
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    /**
     * constructor
     * initializes the object
     * @param id represents the unique id of a person
     * @param arrivalTime represents the time when the person arrives
     * @param serviceTime represents the amount of time that the people need in order to satisfy its service
     */

    public Person(int id, int arrivalTime, int serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    //the function that displays the people waiting to enter the queue
    public String display(Person c){
        String person ="";
        person = "(" + c.getId() + "," + c.getArrivalTime() + "," + c.getServiceTime() + ");";
        return person;
    }

}
