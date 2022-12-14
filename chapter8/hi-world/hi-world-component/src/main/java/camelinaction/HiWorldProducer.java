package camelinaction;

import org.apache.camel.support.component.AbstractApiProducer;

import camelinaction.internal.HiWorldApiName;
import camelinaction.internal.HiWorldPropertiesHelper;

/**
 * The HiWorld producer.
 */
public class HiWorldProducer extends AbstractApiProducer<HiWorldApiName, HiWorldConfiguration> {

    public HiWorldProducer(HiWorldEndpoint endpoint) {
        super(endpoint, HiWorldPropertiesHelper.getHelper(endpoint.getCamelContext()));
    }
}
