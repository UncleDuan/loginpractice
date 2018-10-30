# loginpractice
# 关于Cookie和Session的使用
Tomcat7<br>
web2.5<br>
1.Web容器启动<br>
2.添加Servlet<br>
3.首页localhost/loginpractice/IndexServlet<br>
4.登录->LoginServlet：<br>
        成功：LogoutServlet<br>
        失败：Redirect to IndexServlet<br>
5.里面提供了一个CheckServlet来实现验证码功能<br>
<br>
Question 1：哪里用到了Session？<br>
在IndexServlet中就有：<br>
    HttpSession session=request.getSession();<br>
		User user=(User) session.getAttribute("user");<br>
    其中，User是自定义的对象。<br>
    
    返回一个Cookie<br>
    Cookie cookie=new Cookie("JSESSIONID",session.getId());<br>
		cookie.setMaxAge(1800);<br>
		cookie.setPath("/loginpractice");<br>
		response.addCookie(cookie);<br>
在这个Servlet中一开始就判断是否登录<br>
登录状态下，输出界面不一样<br>

在LogoutServlet中，退出时会删除session中的User属性<br>
    request.getSession().removeAttribute("user");<br>
    


Question 2：CheckServlet如何工作的？<br>
第一步：在login.jsp中：<br>
    <img src="/loginpractice/CheckServlet"><br />		<br>
    这里会自动的访问CheckServlet获取验证码<br>
第二步：CheckServlet随机生成4个验证字符，并加干扰点，在不同高度输出字符<br>
第三部：在session对象中加属性<br>
    session.setAttribute("check_code", new String(rands));<br>
