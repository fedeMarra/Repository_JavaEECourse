package ardea.events.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import ardea.events.service.CartBean;

@SessionScoped
public class UserSession implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private CartBean cartBean;
	
	@PostConstruct
	void init() {
		cartBean.initialize();
	}
	
	@PreDestroy
	void cleanSession() {
		cartBean.close();
	}
	
	public CartBean getBean() {
		return cartBean;
	}

}
