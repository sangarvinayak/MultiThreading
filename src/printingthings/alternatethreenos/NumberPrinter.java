package printingthings.alternatethreenos;

public class NumberPrinter {

    private int currentNumber;

    public NumberPrinter(int currentNumber) {
        this.currentNumber = currentNumber;
    }

    public synchronized void print(int currentTurn, int valToPrint) throws InterruptedException {
        while(currentTurn != currentNumber)
            this.wait();
        System.out.println(Thread.currentThread().getName() + " - " + valToPrint);
        currentNumber++;
        this.notifyAll();
    }
}
