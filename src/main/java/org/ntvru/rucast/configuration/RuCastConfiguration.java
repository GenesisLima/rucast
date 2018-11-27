package org.ntvru.rucast.configuration;

import org.ntvru.rucast.rss.AudiocastRSSViewer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class RuCastConfiguration extends WebMvcConfigurerAdapter {

	
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	    configurer.favorPathExtension(true).
	    favorParameter(false).
	    ignoreAcceptHeader(true).	   
	    defaultContentType(MediaType.APPLICATION_XML)
	    .defaultContentType(MediaType.APPLICATION_JSON); 
	}
	
	     @Override
	    public void configureViewResolvers(ViewResolverRegistry registry) {
	        registry.enableContentNegotiation(new AudiocastRSSViewer());
	    }
	
}
