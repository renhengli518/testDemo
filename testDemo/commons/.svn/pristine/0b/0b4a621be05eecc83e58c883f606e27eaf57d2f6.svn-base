package com.codyy.commons.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StreamUtils;

import com.codyy.commons.image.ImageUtil;



public class VideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Log logger = LogFactory.getLog(VideoServlet.class);

	public VideoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		String id = url.substring(url.lastIndexOf("/") + 1);
		if (StringUtils.isNotBlank(id)) {
			String[] resArr = id.split("\\.");
			if (resArr.length >= 1) {
				
				String file = ImageUtil.buildFile("N:\\video", resArr[0]+"."+resArr[1]);//"N:/video/85/24/3a/ccc.mp4";
				
				if (StringUtils.isNotBlank(file)) {
					getVideoFile(request, response, file);
				}
			}
		}
	}

	private void getVideoFile(HttpServletRequest request, HttpServletResponse response, String filePath) throws IOException {
		File file = new File(filePath);
		if (file.exists() && file.isFile()) {
			response.setContentType("application/x-download");
			response.setHeader("Accept-Ranges", "bytes");
			response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
			String range = request.getHeader("Range");
			if (StringUtils.isNotBlank(range)) {// 分段下载处理
				logger.debug("ranges:" + range);
				range = range.replaceAll("bytes=", "");
				if (StringUtils.isNotBlank(range)) {
					long fileLength = file.length();
					String[] x = range.split("-");
					int start = Integer.parseInt(x[0]);// 开始位置
					logger.debug("start:" + start);
					Integer end = null;// 结束位置, 可能为空
					if (x.length > 1) {
						end = Integer.valueOf(x[1]);
					}
					response.setHeader("Content-Length", String.valueOf(file.length() - start));
					logger.debug("end:" + end);
					// 检查位置是否超出范围
					if (start < fileLength && (end == null || (end != null && end.longValue() <= fileLength && end > start))) {
						logger.debug("position verify ok.");
						try {
							InputStream input = new FileInputStream(file);
							int limit = -1;// 限制读取的长度
							if (end != null)
								limit = end - start + 1;
							input.skip(start);
							logger.debug("skip " + start);
							int readLength = 10240;
							byte[] buffer = new byte[readLength];
							if (limit != -1 && limit < readLength)
								readLength = limit;
							logger.debug("set status and header");
							response.setStatus(206);
							String contentRange = new StringBuffer("bytes ").append(start).append("-").append(end == null ? "" : end).append("/").append(fileLength).toString();
							response.setHeader("Content-Range", contentRange);
							OutputStream out = response.getOutputStream();
							logger.debug("start send bytes");
							while (true) {
								int len = input.read(buffer, 0, readLength);
								if (len == -1)
									break;
								out.write(buffer, 0, len);
								if (limit != -1) {
									limit -= len;
									if (limit <= 0)
										break;
								}
							}
							input.close();
						} catch (IOException e) {
							logger.debug("client abort:" + e.getMessage());
						}
					}
				}
				response.sendError(404);
			} else {// 非分段下载
				logger.debug("no bytes tag");
				response.setHeader("Content-Length", String.valueOf(file.length()));
				response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
				response.setStatus(200);
				try {
					InputStream input = new FileInputStream(file);
					StreamUtils.copy(input, response.getOutputStream());
				} catch (IOException e) {
					logger.debug("client abort:" + e.getMessage());
				}
			}
		} else {
			logger.debug("file not found:" + filePath);
			response.sendError(404);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
