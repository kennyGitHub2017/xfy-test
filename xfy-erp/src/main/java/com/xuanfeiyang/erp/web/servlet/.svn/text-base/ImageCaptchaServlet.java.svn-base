package com.xuanfeiyang.erp.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.math.NumberUtils;

@WebServlet(urlPatterns = { "/captcha" })
public class ImageCaptchaServlet extends HttpServlet {

	private static final long serialVersionUID = -6229295572473500691L;

	// 默认字符个数
	private final static int COUNT = 4;
	
	// 单个字符所占的宽度
	private final static int CHAR_WIDTH = 20;
	
	// 图片的高度
	private final static int HEIGHT = 30;
	
	// 字体大小
	private final static int FONT_SIZE = 24;
	
	private final static String SESSION_KEY_CAPTCHA = "SESSION_KEY_CAPTCHA";
	
	private static Random random = new Random();
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		
		// 如果带有 captchaCode 参数，则为验证操作
		String captchaCode = request.getParameter("captchaCode");
		if (captchaCode != null) {
			HttpSession session = request.getSession();
			String sessionValue = (String) session.getAttribute(SESSION_KEY_CAPTCHA);
			PrintWriter out = response.getWriter();
			out.write(captchaCode.toUpperCase().equals(sessionValue) + "");
			out.flush();
			out.close();
			return;
		}

		// 几个字符
		int count = NumberUtils.toInt(request.getParameter("c"), COUNT);
		// 图片宽度
		int width = NumberUtils.toInt(request.getParameter("w"), count * CHAR_WIDTH);
		// 图片高度
		int height = NumberUtils.toInt(request.getParameter("h"), HEIGHT);
		// 类型：0 字符数字混合,1数字，2字符
		int type = NumberUtils.toInt(request.getParameter("t"), 0);

		String codeValue = this.createCode(type, count);

		// 简单起见，放入session
		HttpSession session = request.getSession();
		session.setAttribute(SESSION_KEY_CAPTCHA, codeValue.toUpperCase());
		
		BufferedImage image = this.createImage(codeValue, width, height);

		// 指定生成的相应图片
		response.setContentType("image/jpeg");
		ImageIO.write(image, "JPEG", response.getOutputStream());

	}

	private BufferedImage createImage(String code, int width, int height) {
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_BGR);

		Graphics2D g = image.createGraphics();
		// 定义字体样式
		Font font = new Font("Arial", Font.BOLD, FONT_SIZE);
		// 设置字体
		g.setFont(font);

		g.setColor(this.getRandomColor(200, 250));
		// 绘制背景
		g.fillRect(0, 0, width, height);

		g.setColor(this.getRandomColor(180, 200));

		this.drawRandomLines(g, 160, width, height);

		for (int i = 0; i < code.length(); i++) {
			String c = code.charAt(i) + "";
			Color color = new Color(20 + random.nextInt(120),
					20 + random.nextInt(120), 20 + random.nextInt(120));

			g.setColor(color);
			// 旋转一定的角度
//			AffineTransform trans = new AffineTransform();
//			trans.rotate(random.nextInt(45) * 3.14 / 180, 20 * i + 4, 24);
			// 缩放文字
//			float scaleSize = random.nextFloat() + 0.8f;
//			if (scaleSize > 1f)
//				scaleSize = 1f;
//			trans.scale(scaleSize, scaleSize);
//			g.setTransform(trans);
			g.drawString(c, 20 * i + 4, 24);
		}

		g.dispose();
		
		return image;
	}
	
	/**
	 * 绘制干扰线
	 * 
	 * @param g
	 *            Graphics2D对象，用来绘制图像
	 * @param nums
	 *            干扰线的条数
	 */
	public void drawRandomLines(Graphics2D g, int nums, int width, int height) {
		g.setColor(this.getRandomColor(160, 200));
		for (int i = 0; i < nums; i++) {
			int x1 = random.nextInt(width);
			int y1 = random.nextInt(height);
			int x2 = random.nextInt(12);
			int y2 = random.nextInt(12);
			g.drawLine(x1, y1, x2, y2);
		}
	}

	/**
	 * 生成随机颜色
	 * 
	 * @param fc
	 *            前景色
	 * @param bc
	 *            背景色
	 * @return Color对象，此Color对象是RGB形式的。
	 */
	private Color getRandomColor(int fc, int bc) {
		if (fc > 255)
			fc = 200;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	private String createCode(int type, int length) {
		String codeValue = null;
		switch (type) {
		case 0:
			codeValue = RandomStringUtils.randomNumeric(length);
			break;

		case 1:
			codeValue = RandomStringUtils.randomAlphabetic(length);
			break;

		case 2:
			codeValue = RandomStringUtils.randomAlphanumeric(length);
			break;

		default:
			codeValue = RandomStringUtils.randomNumeric(length);
		}

		return codeValue;
	}

}
