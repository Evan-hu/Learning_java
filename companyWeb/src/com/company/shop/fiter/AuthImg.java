package com.company.shop.fiter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证图片生成过滤�?
 * 
 * @author Administrator
 * 
 */

public class AuthImg extends HttpServlet {

	private static final long serialVersionUID = -713850703427207779L;
	public static final String AUTH_CODE = "authCode";
	public static final String AJAX_AUTH_CODE = "ajaxAuthCode";

	Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		// 在内存中创建图象
		int width = 85, height = 35;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 获取图形上下�?
		Graphics g = image.getGraphics();
		// 生成随机�?
		Random random = new Random();
		// 设定背景�?
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		// 设定字体

		// 画边�?
		// g.drawRect(0,0,width-1,height-1);
		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测�?
		g.setColor(getRandColor(120, 200));
		for (int i = 0; i < 20; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(10);
			int yl = random.nextInt(10);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 取随机产生的认证�?4位数�?
		String sRand = "";
		int fontSize = 0;
		for (int i = 0; i < 4; i++) {
			fontSize = random.nextInt(20);
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			// 将认证码显示到图象中
			g.setFont(new Font("黑体", Font.CENTER_BASELINE, fontSize < 30 ? 30
					: fontSize));
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生�?
			g.drawString(rand, 20 * i + 3, 28);
		}
		if("ajax".equals(request.getParameter("m"))){//对于ajax验证码特殊处理防治重复同�?��页面
			request.getSession().setAttribute(AJAX_AUTH_CODE, sRand);	
		}
		else{
			// 将认证码存入SESSION
			request.getSession().setAttribute(AUTH_CODE, sRand);	
		}
		// 图象生效
		g.dispose();
		// 输出图象到页�?
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}
}
