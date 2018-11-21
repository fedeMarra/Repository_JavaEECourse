package ardea.events.web;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ardea.events.Cart;
import ardea.events.CartItem;
import ardea.events.service.CartBean;

/**
 * Servlet implementation class BuyEventServlet
 */
@WebServlet("/BuyEvent")
public class BuyEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	@Inject
	private UserSession session;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eventId = request.getParameter("eventId");
		session.getBean().addEvent(Long.valueOf(eventId));
		List<CartItem> result = session.getBean().getCart().getListItem();
		request.setAttribute("cartItems", result);
		request.getRequestDispatcher("cart.jsp").forward(request, response);
		
		
	}

}
