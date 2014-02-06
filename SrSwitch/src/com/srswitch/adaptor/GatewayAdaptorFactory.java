package com.srswitch.adaptor;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;



@Component
public class GatewayAdaptorFactory implements ApplicationContextAware {

	private static final String DEFAULT_GATEWAY_ADAPTOR = "defaultGatewayAdaptor";
	
	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext appContext)
			throws BeansException {

		this.applicationContext = appContext;
	}
	
	public GatewayAdaptor getAdapter(String moduleName) throws Exception
	{
        if (moduleName == null) {
        	moduleName = DEFAULT_GATEWAY_ADAPTOR;
        }
        GatewayAdaptor bean = (GatewayAdaptor) applicationContext.getBean(moduleName);

        return bean;
	}


	
	
}
