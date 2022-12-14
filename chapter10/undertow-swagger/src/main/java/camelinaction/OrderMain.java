package camelinaction;

import camelinaction.dummy.DummyOrderService;
import org.apache.camel.main.Main;

/**
 * A main class to try to run this example
 */
public class OrderMain {

    public static void main(String[] args) throws Exception {
        Main main = new Main();

        // setup a dummy order service
        main.bind("orderService", new DummyOrderService());

        // add the order route with the Rest services
        main.configure().addRoutesBuilder(new OrderRoute());

        System.out.println("***************************************************");
        System.out.println("");
        System.out.println("  Rider Auto Parts REST order service");
        System.out.println("");
        System.out.println("  You can try calling this service using:");
        System.out.println("     http://localhost:8080/orders/1");
        System.out.println("     http://localhost:8080/orders/2");
        System.out.println("");
        System.out.println("  The Swagger API:");
        System.out.println("     http://localhost:8080/api-doc/swagger.json");
        System.out.println("     http://localhost:8080/api-doc/swagger.yaml");
        System.out.println("***************************************************");

        // run the application
        main.run();

    }
}
