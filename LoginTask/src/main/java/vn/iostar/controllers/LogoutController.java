package vn.iostar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.utils.Constant;

@WebServlet(urlPatterns = "/logout")
public class LogoutController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 req.getSession().invalidate();
		//resp.sendRedirect(req.getContextPath() + "/login"); 
		 req.getRequestDispatcher(Constant.LOGOUT).forward(req, resp);
	}
	
}
