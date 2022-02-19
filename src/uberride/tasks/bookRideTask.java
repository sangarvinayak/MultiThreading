package uberride.tasks;

import uberride.RideGenerator;

import java.util.List;

public class bookRideTask implements Runnable {

    private RideGenerator uber;
    private String passenger;

    public bookRideTask(RideGenerator uber, String passenger) {
        this.uber = uber;
        this.passenger = passenger;
    }


    @Override
    public void run() {
        try {
            List<String> ridePassengers = uber.enqueuePassenger(passenger);
            if(ridePassengers.size() == 4)
                uber.drive(ridePassengers);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
