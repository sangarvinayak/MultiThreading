package printingthings.foobar.tasks;

import printingthings.foobar.NumberPrinter;

public class CustomPrintTaskBuzz implements Runnable{

    private NumberPrinter numberPrinter;
    private int start;
    private int end;
    private String TO_PRINT = "Buzz";


    public CustomPrintTaskBuzz(NumberPrinter numberPrinter, int start, int end) {
        this.numberPrinter = numberPrinter;
        this.start = start;
        this.end = end;
    }


    @Override
    public void run() {
        for(int i = this.start; i <=  this.end; i++){
            if((i%3 != 0) && (i%5 == 0)) {
                try {
                    numberPrinter.print(i, TO_PRINT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}