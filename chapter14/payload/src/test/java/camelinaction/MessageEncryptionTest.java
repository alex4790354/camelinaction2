package camelinaction;

import java.security.Key;
import java.security.KeyStore;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.crypto.CryptoDataFormat;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.support.jsse.KeyStoreParameters;
import org.junit.Test;

public class MessageEncryptionTest extends CamelTestSupport {

    private Key secretKey;

    @Override
    protected CamelContext createCamelContext() throws Exception {
        CamelContext context = super.createCamelContext();

        KeyStoreParameters keystore = new KeyStoreParameters();
        keystore.setPassword("supersecret");
        keystore.setResource("./cia_secrets.jceks");
        keystore.setType("JCEKS");
        
        KeyStore store = keystore.createKeyStore();
        secretKey = store.getKey("ciasecrets", "secret".toCharArray());
        context.getRegistry().bind("secretKey", secretKey);

        return context;
    }

    @Test
    public void testEncryptAndDecryptMessage() throws Exception {
        getMockEndpoint("mock:unencrypted").expectedBodiesReceived("Hello World");

        template.sendBody("direct:start", "Hello World");

        assertMockEndpointsSatisfied();
        
        Exchange exchange = getMockEndpoint("mock:encrypted").getReceivedExchanges().get(0);
        assertNotEquals("Hello World", exchange.getIn().getBody());
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                CryptoDataFormat crypto = new CryptoDataFormat("DES", secretKey);
                
                from("direct:start")
                .marshal(crypto)
                .to("mock:encrypted")
                .unmarshal(crypto)
                .to("mock:unencrypted");
            }
        };
    }
}
