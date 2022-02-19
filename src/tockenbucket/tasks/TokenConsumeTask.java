package tockenbucket.tasks;

import tockenbucket.TokenBucket;

public class TokenConsumeTask implements Runnable{

    private TokenBucket tokenBucket;

    public TokenConsumeTask(TokenBucket tokenBucket) {
        this.tokenBucket = tokenBucket;
    }

    @Override
    public void run() {
        try {
            int requiredToken = this.tokenBucket.getToken();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
