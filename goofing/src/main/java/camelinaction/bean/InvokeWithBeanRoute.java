package camelinaction.bean;

import org.apache.camel.builder.RouteBuilder;


public class InvokeWithBeanRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:hello2")
            .bean(HelloBean.class, "helloThere");
    }

}
