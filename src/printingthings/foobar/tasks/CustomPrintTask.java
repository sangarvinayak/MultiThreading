package printingthings.foobar.tasks;

import printingthings.foobar.NumberPrinter;

public class CustomPrintTask implements Runnable{

    private NumberPrinter numberPrinter;
    private int start;
    private int end;

    public CustomPrintTask(NumberPrinter numberPrinter, int start, int end) {
        this.numberPrinter = numberPrinter;
        this.start = start;
        this.end = end;
    }


    @Override
    public void run() {
        for(int i = this.start; i <=  this.end; i++){
            if((i%3 != 0) && (i%5 != 0)) {
                try {
                    numberPrinter.print(i, Integer.toString(i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
