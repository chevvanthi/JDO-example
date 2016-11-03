package com.jdo.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class View extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession(false);
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		if (session != null) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			javax.jdo.Query q = pm.newQuery(Contact.class);
			@SuppressWarnings("unchecked")
			List<Contact> contacts = (List<Contact>) q.execute();
			out.println("-----Contact Details----");

			for (Contact c : contacts) {
				out.println("<br>");
				out.println("Name: " + c.getFirstName() + " " + c.getLastName() + "  ");
				out.println("<br>");
				out.println("Mobile Number: " + c.getNumber());
			}
		} else {
			out.println("Please login first");
			req.getRequestDispatcher("login.html").include(req, res);
		}
		out.println("<html><body>");
		out.println("<br><a href='contact.html'>Add contact</a>");
		out.println("<br>");
		out.println("<br><a href='search.html'>Search contact</a>");
		out.println("<br>");
		out.println("<br><a href='delete.html'>Delete Contact</a>");
		out.println("<br>");
		out.println("<br><a href='/Logout'>Logout</a>");

		out.println("</body></html>");

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		String name = req.getParameter("name");
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Contact c = new Contact();
		try {
			c = pm.getObjectById(Contact.class, name);
			out.println("Number: " +c.getNumber());
			out.println("<html><body>");
			out.println("<br><a href='contact.html'>Add contact</a>");
			out.println("<br>");		
			out.println("<br><a href='delete.html'>Delete Contact</a>");
			out.println("<br>");
			out.println("<br><a href='/View'>View Cntact</a>");
			out.println("<br>");
			out.println("<br><a href='/Logout'>Logout</a>");
			out.println("<br>");			
			out.println("</body></html>");

		} catch (Exception e) {
			out.println("No name found please enter the valid name");
			req.getRequestDispatcher("search.html").include(req, res);
		}
	}
}