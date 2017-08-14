package com.devoir;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletResponse;

public class UserController extends HttpServlet {

  public UserController() {
		super();
	}

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException
  {
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/user/login.jsp");
    dispatcher.forward(req, resp);
  }
}
