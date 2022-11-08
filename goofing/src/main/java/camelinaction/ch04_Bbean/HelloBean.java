package camelinaction.ch04_Bbean;

public class HelloBean {

    public String helloThere(String name) {

        System.out.println("HelloBean invoked.");
        return String.format("Hello %s", name);

    }
}
