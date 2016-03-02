package com.javawellgrounded.di;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

public class AgentFinderModule extends AbstractModule {

	@Override
	protected void configure() {
		/*
		 * Linked bindings: are the simplest form of binding and are the same
		 * type of binding you used when configuring the AgentFinderModule in
		 * listing 3.6. This type of binding simply indicates to the injector
		 * that it should inject the implementing or extending class (yes, you
		 * can inject direct subclasses) at runtime
		 * 
		 * bind(AgentFinder.class).to(WebServiceAgentFinder.class);
		 */
		
		 //  Annotations Binding:
		  bind(AgentFinder.class)
			.annotatedWith(Names.named("primary"))
				.to(WebServiceAgentFinder.class)
				/*
				 * Stateful objects always need to be scoped! You should think about
				 * whether you want the lifespan of that object to be for the entire
				 * application, the current session, or the current request. As a second
				 * step, you should always think about the thread safety of that object.
				 */
				.in(Singleton.class);//Defining the scope in the bind.
		  							//Some developers may be more comfortable 
		  							//with having all of their rules related to 
		  							//an  injected object in one place.
		
		 
		/*
		 * Provider Interface binding support
		 * bind(AgentFinder.class).toProvider(AgentFinderProvider.class);
		 */
		  
	}
	
	/*
	 * You can use the @Provides annotation as well as, or instead of, using
	 * binding in the configure() method if you want to return a full
	 * instantiated object. For example, you might want to inject a very
	 * specific implementation. The injector will look at the return type of all
	 * of the methods with a @Provides annotation in order to determine which
	 * object to inject.
	 * 
	 * @Provides public AgentFinder provideAgentFinder(){ return new
	 * SpreadsheetAgentFinder(); 
	 * }
	 */
	
	

}
