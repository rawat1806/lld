package Service;

import java.util.HashMap;

public class RateLimiterService {

    HashMap<Integer, RateLimiter> rateLimiterHashMap;


    public RateLimiterService(){
        this.rateLimiterHashMap = new HashMap<>();
    }

    public RateLimiter isRateLimitedUser(int clientId){
        ifNotCreateRateLimiterConfig(clientId);
        return rateLimiterHashMap.get(clientId);
    }

    private void ifNotCreateRateLimiterConfig(int clientId){
        if(!rateLimiterHashMap.containsKey(clientId)){
            rateLimiterHashMap.put(clientId, new TokenBucketAlgo(3, 1));
        }
    }
}
