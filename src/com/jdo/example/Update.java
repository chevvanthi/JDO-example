package com.jdo.example;

import java.io.IOException;
import java.io.PrintWriter;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");

		PrintWriter out = res.getWriter();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String name = req.getParameter("name");
		

		try {
			Contact e = pm.getObjectById(Contact.class, name);
			pm.deletePersistent(e);
			out.println("Contact deleted succesfully");
			out.println("<br><html><body>");
			out.println("<a href='/View'>View contact</a>");
			out.println("<br>");
			out.println("<a href='contact.html'>Add contact</a>");
			out.println("<br>");
			out.println("<a href='update.html'>Update contact</a>");
			out.println("<br>");
			out.println("<br> <a href='search.html'>Search contact</a>");
			out.println("<br>");
			out.println("<a href='/Logout'>Logout</a>");
			out.println("</body></html>");
		} catch (Exception e) {
			out.println("no name found please enter valid name ");
			req.getRequestDispatcher("delete.html").include(req, res);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		String name = req.getParameter("name");
		String number = req.getParameter("number");
		Contact c = new Contact();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			c = pm.getObjectById(Contact.class, name);
			out.println("Updated succesfully");
			c.setNumber(number);
			pm.makePersistent(c);
			out.println("<html><body>");
			out.println("<br><a href='/View'>View contact</a>");
			out.println("<br>");
			out.println("<br><a href='contact.html'>Add contact</a>");
			out.println("<br>");
			out.println("<br><a href='delete.html'>Delete contact</a>");
			out.println("<br>");
			out.println("<br><a href='search.html'>Search contact</a>");
			out.println("<br>");
			out.println("<br><a href='/Logout'>Logout</a>");
			out.println("<br></body></html>");

		} catch (Exception e) {
			out.println("No name found please enter correct name");
			req.getRequestDispatcher("update.html").include(req, res);
		}
	}
}
