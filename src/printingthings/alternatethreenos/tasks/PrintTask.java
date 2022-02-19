package printingthings.alternatethreenos.tasks;

import printingthings.alternatethreenos.NumberPrinter;

public class PrintTask implements Runnable{

    private NumberPrinter numberPrinter;
    private int start;
    private int end;
    private int increment;

    public PrintTask(NumberPrinter numberPrinter, int start, int end, int increment) {
        this.numberPrinter = numberPrinter;
        this.start = start;
        this.end = end;
        this.increment = increment;
    }

    @Override
    public void run() {
        int currentCounter = this.start;
        while(currentCounter <= this.end){
            try {
                this.numberPrinter.print(currentCounter, currentCounter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentCounter += this.increment;
        }
    }
}
