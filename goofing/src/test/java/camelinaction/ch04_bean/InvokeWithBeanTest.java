package camelinaction.ch04_bean;

import camelinaction.ch04_Bbean.InvokeWithBeanRoute;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class InvokeWithBeanTest extends CamelTestSupport {

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new InvokeWithBeanRoute();
    }

    @Test
    public void testHelloBean() throws Exception {
        String replay = template.requestBody("direct:hello", "Alex", String.class);
        assertEquals("Hello Alex", replay);
    }


}