package model;

import controller.Controller;

import java.util.ArrayList;

public class Queue implements Runnable {

    private ArrayList<Person> people = new ArrayList<Person>();            //a list of the people from the queue
    private boolean ready;                                           //to see if we can perform operations on queue
    private int s = 0;

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public void Queue() {
        this.people = new ArrayList<Person>();
        ready = true;
    }

    public ArrayList<Person> getClients() {
        return people;
    }

    public void setClients(ArrayList<Person> people) {
        this.people = people;
    }

    //add a client
    public void addCustomer(Person newC) {
        people.add(newC);
    }

    //delete the first one
    public void removeCustomer() {
        people.remove(0);
    }

    //total waiting time from a queue
    public int getTotalTime(ArrayList<Person> people) {
        int totalTime = 0;
        for (Person c1 : people) {
            totalTime += c1.getServiceTime();
        }
        return totalTime;
    }

    //display a queue
    @Override
    public String toString() {
        String finalQueue = "";
        if (people.size() != 0) {
            for (Person c : people) {
                finalQueue += "(" + c.getId() + "," + c.getArrivalTime() + "," + c.getServiceTime() + ");";
            }
        } else
            finalQueue = "closed";
        return finalQueue;
    }

    //we display the queue, by decrementing the waiting time and removing a client
    @Override
    public void run() {
        int time = Controller.getAlgorithmTime();                    //the time should be equal to the simulation time from Algorithm class
        while (ready) {                                              //we perform operations on queue while we have clients
            if (time == Controller.getAlgorithmTime())
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            else if (people.isEmpty()) {                           //time will go up even if we have clients in the queue or not
                time++;
            } else {
                s = people.get(0).getServiceTime() - 1;            //we modify the service time by decrementing it with 1, as the time goes by
                people.get(0).setServiceTime(s);
                if (s == 0) {                                     //if the service time is zero, we need to remove the client
                    removeCustomer();
                }
                time++;
            }
        }
    }
}
