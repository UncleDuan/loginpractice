package com.itheima.session;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
public class IndexServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		if (user==null) {
			response.getWriter().print("还没有登录，请<a href='/loginpractice/login.jsp'>登录</a>");			
		}else {
			response.getWriter().print("已登录，欢迎"+user.getUsername()+"!");
			response.getWriter().print("<a href='/loginpractice/LogoutServlet'>退出</a>");
			Cookie cookie=new Cookie("JSESSIONID",session.getId());
			cookie.setMaxAge(1800);
			cookie.setPath("/loginpractice");
			response.addCookie(cookie);
		}
		}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		this.doGet(request, response);
	}
}
