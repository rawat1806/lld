import java.util.concurrent.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");
        ClientService clientService = new ClientService(1);

//        for (int i = 1; i <= 10; i++) {
//            String response = clientService.clientRequest();
//            System.out.println("For i: " + i + " Thread: " + Thread.currentThread().getName() + " " + response);
//
//            try {
//                Thread.sleep(1000);//1sec
//            }catch(Exception e){
//                System.out.println("Error");
//            }
//        }


        Runnable r = () -> {
            System.out.println("For Thread: " + Thread.currentThread().getName() + " " + clientService.clientRequest());
        };

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);
        scheduler.scheduleAtFixedRate(r, 0, 1000, TimeUnit.MILLISECONDS);


        try {
            Thread.sleep(10000);//10sec
        }catch(Exception e){
            System.out.println("Error");
        }
        scheduler.shutdown();

        //we want to make rate limiter
        //Algorithm
        // for who do we want to make rate limiter ?
        // b2b or b2c -- lets assumed b2b
        // what all things do we want configurable ?
        //

        // what is a rate limiter and why is it implemented?
         /*
         rate limiter is an egineering tool
          -- to prevent DDOS attack.
          - to restrict one user/client to eat up all the resrouces of the server.
          - a malicious intent user doesn't hamper the regular flow of system .
          - cost optimization, available
          */

        //on what all things can rate limiting happen on:
        /*
        - API based rate limiting
        - User based rate limiting
        - IP based rate limiting
        - Mac address based rate limiting.
         */


        //rate limiting algorithims:
        /*
        1. token bucket (tokens in a bucket -- n tokens, this bucket gets filled at regular intervals)
        2. sliding window (slides )
        3. fixed window (0-10, 11-20, 21-30, 41-50)
        4. leaky bucket ( bucket, and request are processed at a constant rate)
         */


    }
}