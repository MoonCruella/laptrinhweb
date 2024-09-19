package vn.iostar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iostar.models.UserModel;
import vn.iostar.services.IUserService;
import vn.iostar.services.impl.UserServiceImpl;
import vn.iostar.utils.Constant;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	// lay toan bo ham cua UserService
	IUserService service = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ma hoa tieng viet 
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		
		// lay tham so tu view
		String username = req.getParameter("uname");
		String password = req.getParameter("psw");
		String remember = req.getParameter("remember");
		
		// kiem tra tham so 
		boolean isRememberMe = false;
		if("on".equals(remember)){
			isRememberMe = true;
		}
		// thong bao dien ten dang nhap mat khau (neu trong)
		String alertMsg="";
		if(username.isEmpty() || password.isEmpty()){
			alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
			return;
		}
		
		// xy ly bai toan : login 
		UserModel user = service.login(username, password);
		if(user!=null){
			// session : luu thong tin dang nhap ( luu tren server, backend) >< luu tren trinh duyet : cookie
			HttpSession session = req.getSession(true);
			session.setAttribute("account", user);
			if(isRememberMe){
				saveRemeberMe(resp, username);
			}
			resp.sendRedirect(req.getContextPath()+"/waiting");
		}
		else{
			
			alertMsg = "Tài khoản hoặc mật khẩu không đúng";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
		}
	}
	
	private void saveRemeberMe(HttpServletResponse resp, String username) {
		Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER,
				username);
				cookie.setMaxAge(30*60);
				resp.addCookie(cookie);
		
	}
}
	
