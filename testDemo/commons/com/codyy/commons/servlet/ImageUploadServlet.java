package com.codyy.commons.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.codyy.commons.context.SpringContext;
import com.codyy.commons.image.ImageConfig;
import com.codyy.commons.image.ImageUtil;
import com.codyy.commons.utils.ResultJson;
import com.codyy.commons.web.CustomMultipartResolver;

/**
 * Servlet implementation class ImageUploadServlet
 */
public class ImageUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(ImageUploadServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageUploadServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomMultipartResolver resolver = new CustomMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		resolver.setMaxUploadSize(1024 * 1024 * 1024);
		resolver.setServletContext(request.getSession().getServletContext());
		resolver.setMaxInMemorySize(1024 * 1024);
		ImageConfig config = SpringContext.getBean(ImageConfig.class);
		resolver.setUploadTempDir(new FileSystemResource(config.getFolder() + File.separator + "temp"));
		MultipartHttpServletRequest multiRequest = null;
		try {
			multiRequest = resolver.resolveMultipart(request);
		} catch (MaxUploadSizeExceededException e) {
			logger.debug("Upload file is too big");
			ResultJson json = new ResultJson(false, 1, "File is too big");
			response.getWriter().write(json.toString());
			return;
		}

		Collection<MultipartFile> files = multiRequest.getFileMap().values();
		if (files == null || files.size() == 0) {
			response.getWriter().write(new ResultJson(false, 2, "File content is empty!").toString());
			logger.debug("Upload file is empty");
			return;
		}

		MultipartFile multiFile = files.iterator().next();
		String original = multiFile.getOriginalFilename();
		original = (original == null) ? "" : original;
		String suffix = "";
		if (original != null) {
			int x = original.lastIndexOf('.');
			if (x >= 0) {
				suffix = original.substring(x);
			}
		}
		File file = createFile(suffix);
		multiFile.transferTo(file);// 写入目标文件
		System.out.println(file.getAbsolutePath());
		ResultJson json = new ResultJson(true, 0, file.getName());
		ImageUtil.addUploadImage(request, file.getName());
		JSONObject jsonres = new JSONObject();
		jsonres.put("result", json.isResult());
		jsonres.put("code", json.getCode());
		jsonres.put("message", json.getMessage());
		//将文件的全路径加入json对象中
		//jsonres.put("filePath", file.getAbsolutePath());
		//将源文件名加入json对象中
		jsonres.put("realname", original);
		
		//解决中文图片传递的乱码问题
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonres.toString());
		logger.debug("File upload transferTo over");
	}

	public static File createFile(String suffix) throws IOException {
		String uuid = UUID.randomUUID().toString();
		ImageConfig config = SpringContext.getBean(ImageConfig.class);
		String filename = ImageUtil.buildFile(config.getFolder(), uuid + suffix);
		File file = new File(filename);
		//获得上传文件的绝对路径
		if (file.exists()) {
			return createFile(suffix);
		} else {
			file.getParentFile().mkdirs();
			file.createNewFile();
			return file;
		}
	}

	/**
	 * 创建目录并保存图片文件(供ueditor上传文件使用)
	 * 
	 * @param request
	 * @param original
	 * @param inputStream
	 * @return String
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static String saveFile(HttpServletRequest request, String original, InputStream inputStream) throws IOException {

		original = (original == null) ? "" : original;
		String suffix = "";
		if (original != null) {
			int x = original.lastIndexOf('.');
			if (x >= 0) {
				suffix = original.substring(x);
			}
		}
		final ImageUploadServlet servlet = new ImageUploadServlet();
		final File file = servlet.createFile(suffix);
		final FileOutputStream fileOutputStream = new FileOutputStream(file);
		final byte[] b = new byte[1024];
		int len = 0;
		while ((len = inputStream.read(b, 0, 1024)) != -1) {// 写入目标文件
			fileOutputStream.write(b, 0, len);
		}
		ImageUtil.addUploadImage(request, file.getName());// 将文件名保存在session中
		fileOutputStream.close();
		return file.getName();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
