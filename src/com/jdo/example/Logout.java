package com.jdo.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User u = new User();

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter t = res.getWriter();

		HttpSession session2 = req.getSession(false);
		if (session2 != null) {
			session2.invalidate();
			t.println(" <br> You are logged out succesfully");
		} else {
			t.println(" <br> please login first");
			RequestDispatcher rd = req.getRequestDispatcher("index.html");
			rd.include(req, res);
		}
		t.println("<html><body>");
		t.println("<h2> Click Login to Login</h2>");
		t.println("<br> <a href='login.html'>Login</a>");
		t.println("</body></html>");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		String name = req.getParameter("name");
		String password = req.getParameter("password");

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
		u = pm.getObjectById(User.class, name);
			out.println("already registered");
			req.getRequestDispatcher("login.html").include(req, res);
		} catch (Exception e) {
			out.println("You are registered succesfully now you can login");
			u.setName(name);
			u.setPassword(password);
			pm.makePersistent(u);
			req.getRequestDispatcher("login.html").include(req, res);
		} finally {
			pm.close();
		}
	}
}