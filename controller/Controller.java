package com.devoir;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet {

  public Controller() {
		super();
	}

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException
  {
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/home.jsp");
    dispatcher.forward(req, resp);
  }
}
