package vn.iostar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.services.IUserService;
import vn.iostar.services.impl.UserServiceImpl;
import vn.iostar.utils.Constant;

@WebServlet(urlPatterns = "/resetPassword")
public class resetPassword extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher(Constant.RESETPASSWORD).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		
		IUserService service = new UserServiceImpl();
		String alertMsg = "";
		
		if (!service.checkVarification(email,username)) {
			alertMsg = "Email và tên đăng nhập không khớp!";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher(Constant.RESETPASSWORD).forward(req, resp);
			return;
			}
		
		boolean isSuccess = service.resetPassword(username, password);;
		if (isSuccess) {
			alertMsg = "Đổi mật khẩu thành công!";
			req.setAttribute("alert", alertMsg);
			//resp.sendRedirect(req.getContextPath() + "/login");
			req.getRequestDispatcher(Constant.RESETPASSWORD).forward(req, resp);
			} else {
			alertMsg = "System error!";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher(Constant.RESETPASSWORD).forward(req, resp);
		}
	}
}
