package controller;

import model.Person;
import model.Queue;
import view.View;

import static java.lang.Integer.parseInt;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Controller implements Runnable {

    //in order to print the content to the file
    File file1 = new File("out.txt");
    FileWriter fw = new FileWriter(file1);
    PrintWriter pw = new PrintWriter(fw);

    private View view = new View();
    private int simulationTime = 0;
    private String result = "";
    private ArrayList<Person> waitingPeople = new ArrayList<Person>();
    static private int algorithmTime = 0;
    private ArrayList<Person> people = new ArrayList<Person>();
    private ArrayList<Queue> queues = new ArrayList<Queue>();
    private ArrayList<Thread> threads = new ArrayList<Thread>();  //we need a thread for each queue

    static public int getAlgorithmTime() {
        return algorithmTime;
    }

    public void setAlgorithmTime(int algorithmTime) {
        this.algorithmTime = algorithmTime;
    }

    //this is the data that we read from the interface
    private int clientsC;
    private int queuesQ;
    private int minArrival;
    private int maxArrival;
    private int minService;
    private int maxService;

    private int max = 0;
    private int hour = 0;
    private float average = 0;

    public Controller(View view) throws IOException {
        this.view = view;
    }
    //here we initialize the controller
    public void initController() {
        this.view.setTitle("Queue Management System");
        this.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.view.pack();
        this.view.setVisible(true);
        this.view.setResizable(false);
        this.view.getSimulationButton().addActionListener(e -> executeSimulation());
    }

    //we get the text from the interface
    public void executeSimulation() {
        String noClients = this.view.getTextField1().getText();
        String noQueues = this.view.getTextField2().getText();
        String simTime = this.view.getTextField3().getText();
        String minArr = this.view.getTextField4().getText();
        String maxArr = this.view.getTextField5().getText();
        String minSer = this.view.getTextField6().getText();
        String maxSer = this.view.getTextField7().getText();

        //we convert the strings to integers
        try {
            clientsC = parseInt(noClients);
            queuesQ = parseInt(noQueues);
            simulationTime = parseInt(simTime);
            minArrival = parseInt(minArr);
            maxArrival = parseInt(maxArr);
            minService = parseInt(minSer);
            maxService = parseInt(maxSer);
            System.out.println(clientsC);
            System.out.println(queuesQ);
            System.out.println(simulationTime);
            System.out.println(minArrival);
            System.out.println(maxArrival);
            System.out.println(minService);
            System.out.println(maxService);
        } catch (Exception e) {
            System.out.println("error");
        }

        //for each person, we generate random numbers for the parameters, and we add the persons to the list
        for (int i = 1; i <= clientsC; i++) {
            Person person = new Person(i, randomGenerator(minArrival, maxArrival), randomGenerator(minService, maxService));
            people.add(person);
            waitingPeople.add(person);
        }

        //we generate the queues and add them to the list
        for (int i = 0; i < queuesQ; i++) {
            Queue queue = new Queue();
            queues.add(queue);
            queues.get(i).setReady(true);
        }

        //we generate the threads for each queue(so the number of threads is the number of the queues)
        for (int i = 0; i <= queues.size() - 1; i++) {
            Thread t = new Thread(queues.get(i));
            threads.add(t);
        }

        //we generate the main thread of this class adn we start it
        Thread tr = new Thread(this);
        tr.start();

    }

    //we verify if all queues are empty
    public boolean emptyQueues(ArrayList<Queue> q) {
        boolean empty = false;
        int nr = 0;
        for (int i = 0; i <= q.size() - 1; i++) {
            if (q.get(i).getClients().isEmpty()) {
                nr++;
            }
        }
        if (nr == q.size()) {
            empty = true;
        }
        return empty;
    }

    //we get the minimum queue
    public int minimumQueue(ArrayList<Queue> queue) {
        int index = 0;
        int min = queue.get(0).getTotalTime(queue.get(0).getClients());
        for (int i = 0; i < queuesQ; i++) {
            if (queue.get(i).getTotalTime(queue.get(i).getClients()) < min) {
                index = i;
                min = queue.get(i).getTotalTime(queue.get(i).getClients());
            }
        }
        return index;
    }

    //we calculate the total service time for all the clients
    public int totalWaiting(ArrayList<Person> all){
        int a = 0;
        for(Person c: all){
            a += c.getServiceTime();
        }
        return a;
    }

    //this function generates a random number between two other numbers
    public int randomGenerator(int min, int max) {
        int result = (int) (Math.random() * (max - min)) + min;
        return result;
    }

    public int getSimulationTime() {
        return simulationTime;
    }

    public void setSimulationTime(int simulationTime) {
        this.simulationTime = simulationTime;
    }

    //here the run method is implemented
    //we are searching for the smallest queue and add the person to that queue
    @Override
    public void run() {
        for (int i = 0; i < queues.size(); i++) {            //we start the threads for the queue
            threads.get(i).start();
        }
        this.view.setAverage((float)totalWaiting(people)/(float)clientsC);    //this calculates the average service time
        while ((!waitingPeople.isEmpty() || !emptyQueues(queues)) && algorithmTime < simulationTime) {
                try {                                                        //as long as we have people that are waiting, and we didn't pass the simulation time
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            for (int i = 0; i < clientsC; i++) {                             //we verify if the client arrived at the queue
                if (algorithmTime == people.get(i).getArrivalTime()) {
                    int minimQ = minimumQueue(queues);                       //we find the smallest queue
                    queues.get(minimQ).addCustomer(people.get(i));           //and we add the person
                    waitingPeople.remove(people.get(i));                     //we remove the person from the waiting list
                }
            }

            System.out.println("Time " + algorithmTime);                                  //here is how we display the log of events
            result += "Time " + algorithmTime + "\r\n" + "Waiting clients: ";
            for (int i = 0; i < waitingPeople.size(); i++) {
                result += waitingPeople.get(i).display(waitingPeople.get(i));
            }
            result += "\r\n";
            for (int i = 0; i < queuesQ; i++) {
                String p = "Queue " + (i + 1) + ": ";
                p += queues.get(i).toString();
                result += p + "\r\n";
                System.out.println(p);
                int nrPerson = 0;
                nrPerson = queues.get(i).getClients().size();
                if(nrPerson >= max) {
                    max = nrPerson;
                    hour = algorithmTime;
                }
            }
            this.view.setResult(result);
            algorithmTime++;
        }

        this.view.setHour(hour);
        this.view.setWaiting(average);
        pw.write(result);
        pw.close();
        for (int i = 0; i < queuesQ; i++) {                //we set ready to false for all queues, so that we don't enter the run method from the Queue class anymore
            queues.get(i).setReady(false);
        }
    }


}
