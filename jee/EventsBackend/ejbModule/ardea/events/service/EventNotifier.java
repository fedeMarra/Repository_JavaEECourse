package ardea.events.service;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import ardea.events.Event;

/**
 * Message-Driven Bean implementation class for: EventNotifier
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "jms/eventsQueue"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "jms/eventsQueue")

public class EventNotifier implements MessageListener {

    public EventNotifier() { }
	
	
    public void onMessage(Message message) {
        try {
			System.out.println(">>>>>>>> Consuming "+ message.getBody(Event.class));
		} catch (JMSException e) {
			e.printStackTrace();
		}
    }
    

}
