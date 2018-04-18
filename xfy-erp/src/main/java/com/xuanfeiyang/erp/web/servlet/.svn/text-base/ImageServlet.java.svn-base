package com.xuanfeiyang.erp.web.servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xuanfeiyang.erp.App;



@WebServlet(urlPatterns = {"/images/*", "/files/*"})
public class ImageServlet  extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LoggerFactory.getLogger(ImageServlet.class);
	
	// 请求路径前缀模式
	private String urlPrefixPattern = null;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageServlet() {
		super();
	}


	@Override
	public void init(ServletConfig config) throws ServletException {		
		this.urlPrefixPattern = "^" + config.getServletContext().getContextPath();
		super.init(config);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String basePath = App.getConfig("upload.base.dir");
		String uri = request.getRequestURI();
		
		if (uri == null) {
			return;
		}
		
		String baseUrlPattern = this.getBaseUrlPattern(request);
		uri = uri.replaceFirst(baseUrlPattern, "");
		uri = java.net.URLDecoder.decode(uri, "UTF-8");
		
		String filename = basePath + uri;
		filename = filename.replace("//", "/");
		
		File file = new File(filename);
		
		// 文件不存在
		if (! file.exists()) {
			if (logger.isDebugEnabled()) {
				logger.debug("{}({})不存在", uri, filename);
			}
			
			response.setStatus(404);
			return;
		}
		
		response.setHeader("Content-Length", "" + file.length());
		
		this.responseFile(response, file);

	}
	
	
	
	private void responseFile(HttpServletResponse response, File file) {
		FileInputStream fis = null; 
		BufferedInputStream bis = null;
		

		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			
			OutputStream os = response.getOutputStream(); 			
			byte[] buffer = new byte[4096];
			
			while (bis.read(buffer) != -1) {
				os.write(buffer);
			}
			
			os.flush();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			response.setStatus(500);
			
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
			
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
		 
	}
	/**
	 * 基础URL路径，直到 servletPath 
	 * 
	 * @param request
	 * @return
	 */
	private String getBaseUrlPattern(HttpServletRequest request) {
		return this.urlPrefixPattern + request.getServletPath();
	}	
	

}
