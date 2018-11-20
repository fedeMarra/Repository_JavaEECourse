package ardea.events.web;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ardea.events.Event;
import ardea.events.service.EventSearchBean;

/**
 * Servlet implementation class EventSearchServlet
 */
@WebServlet("/EventSearch")
public class EventSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private EventSearchBean eventsService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String word = request.getParameter("name");
		List<Event> result = eventsService.searchByName(word);
		request.setAttribute("events", result);
		request.getRequestDispatcher("eventList.jsp").forward(request, response);
		
	}

}
