package camelinaction.ch04_Bbean;

import org.apache.camel.builder.RouteBuilder;


public class InvokeWithBeanRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:hello")
            .bean(HelloBean.class, "helloThere");
    }

}
