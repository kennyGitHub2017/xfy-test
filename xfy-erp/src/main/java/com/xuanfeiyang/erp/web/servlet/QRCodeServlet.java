package com.xuanfeiyang.erp.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;


/***
 * 
 * 生成二维码Servlet
 *
 */

@WebServlet(urlPatterns={"/makeQRCode"})
public class QRCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param = request.getParameter("param");
		int width = 300;
		int height = 300;
		String format = "jpg";
		
		Map<EncodeHintType, Object> ht = new HashMap<EncodeHintType, Object>();
		ht.put(EncodeHintType.CHARACTER_SET, "UTF-8");

		BitMatrix bitMatrix = null;
		try {
			
			bitMatrix = new MultiFormatWriter().encode(param,BarcodeFormat.QR_CODE, width, height, ht);
					
		} catch (WriterException e) {
			e.printStackTrace();
		}

		MatrixToImageWriter.writeToStream(bitMatrix, format,response.getOutputStream());
				
	}

}
