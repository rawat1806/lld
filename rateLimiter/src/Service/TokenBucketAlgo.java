package Service;

public class TokenBucketAlgo implements RateLimiter{
    int maxNumberOfTokens;
    int currentNumberOfTokens;
    long refillRate; // time -- in seconds
    long lastRefillTime; //time -- in milliseconds

    public TokenBucketAlgo(int maxNumberOfTokens, long refillRate){
        this.maxNumberOfTokens = maxNumberOfTokens;
        this.refillRate = refillRate;
        this.currentNumberOfTokens = maxNumberOfTokens;
        this.lastRefillTime = System.currentTimeMillis();//in milliseconds
    }


    @Override
    public synchronized boolean grantAccess(){
        boolean result = false;
        //on demand refilling
        refill();
        if(currentNumberOfTokens>=1){
            currentNumberOfTokens--;
            return true;
        }
        return false;
    }

    private void refill(){
        long now = System.currentTimeMillis();
        int additionalTokens = (int) (((now - lastRefillTime)/1000)*refillRate);
        System.out.println("Before updating number of tokens: " + currentNumberOfTokens);
        currentNumberOfTokens = Math.min(currentNumberOfTokens+additionalTokens, maxNumberOfTokens);
        System.out.println("After updating number of tokens: " + currentNumberOfTokens);

        lastRefillTime=now;
    }
}
