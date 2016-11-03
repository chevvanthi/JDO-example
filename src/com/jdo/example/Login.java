package com.jdo.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.*;
import com.google.appengine.api.datastore.Query.FilterOperator;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			User e = pm.getObjectById(User.class, name);
			if (name.equals(e.getName())) {
				if (password.equals(e.getPassword())) {
					out.println("hai " + name + " you logged in succesfully");
					HttpSession session = req.getSession();
					out.println("<html><body>");
					out.println("<br>");
					out.println("<br><a href='contact.html'>Add contact</a>");
					out.println("<br>");
					out.println("<br><a href='/View'>view contacts</a>");
					out.println("<br>");
					out.println("<br><a href='delete.html'> Delete contact<br>");
					out.println("<br>");
					out.println("<br><a href='search.html'>Search Contact<br>");
					out.println("<br>");
					out.println("<br><a href='update.html'> Update contact</a><br>");
					out.println("<br>");
					out.println("<br><a href='/Logout'>Logout</a>");
					out.println("<br>");
					
					out.println("</body></html>");
				} else {
					out.println("<br>password doesnot match please enter correct password");
					req.getRequestDispatcher("login.html").include(req, res);
				}

			} else {
				out.println("<br>please register first");
				req.getRequestDispatcher("register.html").forward(req, res);
			}

		} catch (Exception e) {
			out.println("<br>Please Register your name first");
			req.getRequestDispatcher("register.html").include(req, res);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String number = req.getParameter("number");
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Contact c = new Contact();
		try {
			Contact e = pm.getObjectById(Contact.class, firstName);
			if(number.equals(e.getNumber())){
			out.println("number already exits please add new contact ");
			req.getRequestDispatcher("contact.html").include(req, res);
			}else
			{
				out.println("<br>Contacts added succesfully");
				c.setFirstName(firstName);
				c.setLastName(lastName);
				c.setNumber(number);
				out.println("<br> your  Contact details :");
				out.println("<br>Name of the contact is " + c.getFirstName() + " " + c.getLastName());
				out.println("<br>Mobile number: " + c.getNumber());
				out.println("<br><html><body>");
				out.println("<br><a href='/View'>View contacts</a>");
				out.println("<br>");
				out.println("<br><a href='delete.html'>Delete contact</a>");
				out.println("<br>");
				out.println("<br><a href='contact.html'>Add contact</a>");
				out.println("<br>");
				out.println("<a href='search.html'>Search contact</a>");
				out.println("<br>");
				out.println("<br><a href='/Logout'>Logout</a>");
				out.println("<br>");
				out.println("<br></html></body>");

	
			}
		} catch (Exception e) {

			out.println("<br>Contacts added succesfully");
			c.setFirstName(firstName);
			c.setLastName(lastName);
			c.setNumber(number);
			out.println("<br> your  Contact details :");
			out.println("<br>Name of the contact is " + c.getFirstName() + " " + c.getLastName());
			out.println("<br>Mobile number: " + c.getNumber());
			try {
				pm.makePersistent(c);
			} finally {
				pm.close();
			}
			out.println("<br><html><body>");
			out.println("<br><a href='/View'>View contacts</a>");
			out.println("<br>");
			out.println("<br><a href='delete.html'>Delete contact</a>");
			out.println("<br>");
			out.println("<br><a href='contact.html'>Add contact</a>");
			out.println("<br>");
			out.println("<br><a href='/Logout'>Logout</a>");
			out.println("<br>");
			out.println("<br></html></body>");

		}
	
	}
}
