package camelinaction;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.ModelCamelContext;
import org.apache.camel.model.cloud.ServiceCallConfigurationDefinition;

public class MyStaticRouteGlobal extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // create a Service Call EIP configuration
        ServiceCallConfigurationDefinition global = new ServiceCallConfigurationDefinition();
        // use the http component
        global.component("http")
            // add the static list of servers
            .staticServiceDiscovery()
                // the syntax is name@hostname:port
                // and you can separate multiple servers by comma
                .servers("hello-service@localhost:8081,hello-service@localhost:8082");

        // set as global configuration on CamelContext
        getContext().adapt(ModelCamelContext.class).setServiceCallConfiguration(global);

        // Camel route that calls the service
        from("timer:trigger?period=2000")
            // `hello-service` = name of service
            // `/camel/hello` is used in uri templating which
            // means this is used in the context-path of the actual http uri
            .serviceCall("hello-service/camel/hello")
            .log("Response ${body}");
    }
}
