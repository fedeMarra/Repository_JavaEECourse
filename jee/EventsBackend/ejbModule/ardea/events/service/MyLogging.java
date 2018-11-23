package ardea.events.service;

import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@MyLoggingBinding
@Interceptor
public class MyLogging {
	
	@AroundInvoke
	public Object aroundInvoke(InvocationContext ic) throws Exception {
		String name = ic.getTarget().getClass().getPackage().getName();
		String m = ic.getMethod().getName();
		
		String parameters = Stream.of(ic.getParameters()).
			map(p-> p.toString()).
			collect(Collectors.joining(", ", "(", ")"));
		
		Logger.getLogger(name).info("[Log]Calling " + ic.getTarget().getClass().getName() + " "+ m + parameters);
		Object result = ic.proceed();
		Logger.getLogger(name).info("[Log]Returning " + ic.getTarget().getClass().getName()+" " + result);
		return result;
	}
	
	@PostConstruct
	public void postConstruct(InvocationContext ic) {
		String name = ic.getTarget().getClass().getPackage().getName();
		Logger.getLogger(name).info("[Log]Constructed " + ic.getTarget());
	}

}
