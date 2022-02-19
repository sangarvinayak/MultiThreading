package uberride;

import java.util.*;
import java.util.stream.Collectors;

public class RideGenerator {

    private String firstPartySymbol;
    private String secondPartySymbol;
    private Queue<String> firstPartyPassengers;
    private Queue<String> secondPartyPassengers;

    public RideGenerator(String firstPartySymbol, String secondPartySymbol) {
        this.firstPartySymbol = firstPartySymbol;
        this.secondPartySymbol = secondPartySymbol;
        this.firstPartyPassengers = new LinkedList<String>();
        this.secondPartyPassengers = new LinkedList<String>();
    }

    public synchronized List<String> enqueuePassenger(String passenger) throws InterruptedException {
        List<String> possibleRidePassengers = new ArrayList<String>();
        if(passenger.contains(firstPartySymbol)){
            while(firstPartyPassengers.size() == 4)
                this.wait();
            firstPartyPassengers.add(passenger);
        }
        else if(passenger.contains(secondPartySymbol)){
            while(secondPartyPassengers.size() == 4)
                this.wait();
            secondPartyPassengers.add(passenger);
        }
        else {;}
        // TRY DOING RIDE ALLOCATION //
        if(firstPartyPassengers.size() == 4) {
            possibleRidePassengers = new ArrayList<>(firstPartyPassengers);
            this.firstPartyPassengers = new LinkedList<String>();
        }
        if(secondPartyPassengers.size() == 4) {
            possibleRidePassengers = new ArrayList<>(secondPartyPassengers);
            this.secondPartyPassengers = new LinkedList<String>();
        }
        if (firstPartyPassengers.size() >= 2 && secondPartyPassengers.size() >= 2){
            for(int i  = 0; i < 2; i++){
                possibleRidePassengers.add(firstPartyPassengers.remove());
                possibleRidePassengers.add(secondPartyPassengers.remove());
            }
        }
        this.notifyAll();
        return possibleRidePassengers;
    }

    public synchronized void drive(List<String> ridePassengers){
        System.out.println("Yayyy an uber ride is formed with below passengers");
        ridePassengers.forEach(passenger -> System.out.print(passenger + ", "));
        System.out.println("");
    }

}
