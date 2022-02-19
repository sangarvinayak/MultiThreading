package tockenbucket;

import static java.lang.Math.min;

public class TokenBucket {

    private int tokenIngressRate;
    private int tokensInBucket;
    private int bucketSize;
    private int WAIT_TIME = 1000;

    public TokenBucket(int tokenIngressRate, int bucketSize) {
        this.tokenIngressRate = tokenIngressRate;
        this.tokensInBucket = 0;
        this.bucketSize = bucketSize;
    }

    public void addTokens() throws InterruptedException {
        while(true){
            synchronized(this) {
                int tokensToAdd = min((bucketSize - tokensInBucket), tokenIngressRate);
                tokensInBucket += tokensToAdd;
                System.out.println(Thread.currentThread().getName() + " - Added tokens to bucket " + tokensInBucket);
                this.notify();
            }
            Thread.sleep(WAIT_TIME);
        }
    }

    public synchronized int getToken() throws InterruptedException {
        while(tokensInBucket == 0)
            this.wait();
        int tokenToReturn  = tokensInBucket;
        tokensInBucket--;
        System.out.println(Thread.currentThread().getName() + " - Got tokens from bucket " +  tokensInBucket);
        this.notify();
        return tokenToReturn;
    }

}
