package ardea.events.client;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ardea.events.Event;

public class MessageBrowser {
	
	private Queue queue ;
	private ConnectionFactory connectionFactory;
	
	
	public MessageBrowser() throws NamingException{
		Hashtable<String, String> jndiProperties = new Hashtable<>();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http://localhost:8080/wildfly-services");
		jndiProperties.put("jboss.naming.client.ejb.context", Boolean.TRUE.toString());
		jndiProperties.put(Context.SECURITY_PRINCIPAL, "fedeM");
		jndiProperties.put(Context.SECURITY_CREDENTIALS, "1234Marra");
		
		Context context = new InitialContext(jndiProperties);
		
		connectionFactory = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
		queue = (Queue)context.lookup("jms/eventsQueue");
		
	}
	
	public void run() throws JMSException {
		JMSContext context = connectionFactory.createContext("fedeM", "1234Marra");
		context.start();
//		QueueBrowser browser = context.createBrowser(queue);
//		Enumeration e = browser.getEnumeration();
//		while(e.hasMoreElements()) {
//			Message msg = (Message)e.nextElement();	
//			System.out.println(msg.getBody(Event.class));
//		}
		// try with resources
		try ( JMSConsumer consumer = context.createConsumer(queue)){
			consumer.setMessageListener(new MessageListener() {
	
				@Override
				public void onMessage(Message event) {
					try {
						System.out.println(event.getBody(Event.class));
					}catch(JMSException e) {
						e.printStackTrace();
					}
				}	
			});	
			try {
				Thread.currentThread().sleep(10000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws NamingException, JMSException   {
		MessageBrowser msgBrowser = new MessageBrowser();
		msgBrowser.run();

	}

}
