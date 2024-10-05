import Service.RateLimiter;
import Service.RateLimiterService;

public class ClientService {
    int clientId;
    RateLimiterService rateLimiterService;

    public ClientService(int clientId){
        this.clientId = clientId;
        this.rateLimiterService = new RateLimiterService();
    }

    public String clientRequest(){
        boolean isAllowed = rateLimiterService.isRateLimitedUser(clientId).grantAccess();
        if(isAllowed) return "OK";
        else return "Too Many Requests";
    }
}
