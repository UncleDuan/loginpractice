package com.itheima.session;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
public class CheckServlet extends HttpServlet{
	private static int WIDTH = 60; // 宽
	private static int HEIGHT = 20; // 高

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("image/jpeg");
		ServletOutputStream sos = response.getOutputStream();
		// 设置浏览器不缓存该照片
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 创建内存图像并获取图形上下文
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		// 产生随机验证码
		char[] rands = generateCheckCode();
		// 产生图片
		drawBackground(g);
		drawRands(g, rands);
		// 完成
		g.dispose();
		// 转换格式
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(image, "JPEG", bos);
		byte[] buf = bos.toByteArray();
		response.setContentLength(buf.length);
		// 输出
		sos.write(buf);
		bos.close();
		sos.close();
		// 当前验证码存入session
		session.setAttribute("check_code", new String(rands));
	}

	// 生成验证码
	private char[] generateCheckCode() {
		
		String chars = "0123456789abcdefghijklmnopqrstuvwxyz";
		char[] rands = new char[4];
		for (int i = 0; i < 4; i++) {
			int rand = (int) (Math.random() * 36);
			rands[i] = chars.charAt(rand);
		}
		return rands;
	}

	private void drawRands(Graphics g, char[] rands) {
		g.setColor(Color.BLACK);
		g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 18));
		// 不同高度输出字符
		g.drawString("" + rands[0], 1, 17);
		g.drawString("" + rands[1], 16, 15);
		g.drawString("" + rands[2], 31, 18);
		g.drawString("" + rands[3], 46, 16);
		System.out.println(rands);
	}

	private void drawBackground(Graphics g) {
		// 画背景
		g.setColor(new Color(0xDCDCDC));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		// 干扰
		for (int i = 0; i < 120; i++) {
			int x = (int) (Math.random() * WIDTH);
			int y = (int) (Math.random() * HEIGHT);
			int red = (int) (Math.random() * 255);
			int green = (int) (Math.random() * 255);
			int blue = (int) (Math.random() * 255);
			g.setColor(new Color(red, green, blue));
			g.drawOval(x, y, 1, 0);
		}
	}
}
