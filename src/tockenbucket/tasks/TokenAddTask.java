package tockenbucket.tasks;

import tockenbucket.TokenBucket;

public class TokenAddTask implements Runnable{

    private TokenBucket tokenBucket;

    public TokenAddTask(TokenBucket tokenBucket) {
        this.tokenBucket = tokenBucket;
    }

    @Override
    public void run() {
        try {
            this.tokenBucket.addTokens();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
