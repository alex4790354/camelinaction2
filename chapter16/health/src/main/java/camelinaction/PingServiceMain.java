package camelinaction;

import org.apache.camel.main.Main;

/**
 * Main class to run the ping service
 */
public class PingServiceMain {

    public static void main(String[] args) throws Exception {
        // use Camel main to run Camel easily from Java main
        Main main = new Main();

        // add the route
        main.configure().addRoutesBuilder(new PingService());

        System.out.println("Ping service running. Try sending a HTTP GET to http://localhost:8080/ping");
        System.out.println("Camel started use ctrl + c to stop.");

        // run until the JVM terminates
        main.run();
    }

}
