package com.xuanfeiyang.erp.web.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xuanfeiyang.erp.util.BarCodeUtil;
@WebServlet(urlPatterns={"/barcode"})
public class BarCodeServlet extends HttpServlet{
	
	private final static Logger logger = LoggerFactory.getLogger(BarCodeServlet.class);
	
	private static final long serialVersionUID = -6703344493738422103L;
	private static final String KEY = "keycode";
	private static final String WIDTH = "mwidth";
	private static final String HEIGHT = "mheight";
	private final String CONTENT_TYPE = "image/png"; 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType(CONTENT_TYPE); 
		String keycode = req.getParameter(KEY);
		 int width=135;
         int height=50;
        if (keycode != null && !"".equals(keycode)) {
        	OutputStream  stream = null;
            try {
                String mwidth = req.getParameter(WIDTH);
                if (mwidth != null && !"".equals(mwidth.trim())) {
                        width=Integer.valueOf(mwidth);
                }
                String mheight = req.getParameter(HEIGHT);
                if (mheight != null && !"".equals(mheight.trim())) {
                        height = Integer.valueOf(mheight);
                }
                stream = resp.getOutputStream();
                BufferedImage img = BarCodeUtil.getBarcode(keycode, width, height);
                ImageIO.write(img, "PNG", stream);
            } catch (Exception e) {
                logger.error("生成条形码图片出错", e);
            } finally {
                if (stream != null) {
                    stream.flush();
                    stream.close();
                }
            }
        }
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req,resp);
	}
	
}
