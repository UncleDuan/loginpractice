package com.itheima.session;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class LogoutServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 移除session中的User对象
		request.getSession().removeAttribute("user");
		response.sendRedirect("/loginpractice/IndexServlet");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
