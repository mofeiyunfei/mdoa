package cc.moredo.oa.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 网站用于操作文件跟FileUtils中功能区别在于这个主要是用于上传下载文件的处理
 * 
 * @author guohao
 *
 */
public class BFileUtils {

	/*
	 * 公用的上传方法 返回需要传入数据库的路径即 如 website-image/1/2014888.jpg 注:返回文件加路径 包含中间路径
	 */
	public static String upload(MultipartFile cfile, String middleFileName)
			throws IOException {
		System.out.println("调用公共 upload ---middleFileName--" + middleFileName
				+ "原始文件名" + cfile.getOriginalFilename());
		
		String oriFileName = cfile.getOriginalFilename();

		// 获取存储半成品的总文件夹
		String basePath = FileUtils.getProperty("md.properties", "filesite");
		// 获取本次应该存储进的文件夹
		String dir = FileUtils.saveFileUrl(basePath + "/" + middleFileName);
		// 获取以时间名为文件名的文件名称
		String fileName = FileUtils.getDateName(oriFileName);
		// 文件全名
		String fullFileName = dir + "/" + fileName;
		// 获得字节数组
		byte[] fbyte = cfile.getBytes();
		File file = new File(fullFileName);
		OutputStream out = new FileOutputStream(file);
		out.write(fbyte);
		out.flush();
		out.close();
		/*
		 * 使用中间路径名切开,返回路径是website之下的路径 即中间路径和之后路径
		 */
		String relativePath = middleFileName
				+ fullFileName.split(middleFileName)[1];
		System.out.println("upload中最后返回数据 return --" + relativePath);
		return relativePath;

	}

	/*
	 * 公用的普通上传方法 返回需要传入数据库的路径即 如 app-file/2014888.jpg
	 */
	public static String simpleUpload(MultipartFile cfile, String middleFileName)
			throws IOException {
		System.out.println("调用公共 upload ---middleFileName--" + middleFileName
				+ "原始文件名" + cfile.getOriginalFilename());

		// 获得原始文件名
		String oriFileName = cfile.getOriginalFilename();

		// 获取存储半成品的总文件夹
		String basePath = FileUtils.getProperty("path.properties", "website");
		// 获取以时间名为文件名的文件名称
		String fileName = FileUtils.getDateName(oriFileName);
		// 文件全名
		String fullFileName = basePath + "/" + middleFileName + "/" + fileName;

		// System.out.println("请求收到");
		// 获得字节数组
		byte[] fbyte = cfile.getBytes();
		File file = new File(fullFileName);
		// File file = new File("I:/temp");
		OutputStream out = new FileOutputStream(file);
		System.out.println(basePath + "/" + middleFileName + "/" + fileName
				+ fileName);
		out.write(fbyte);
		out.flush();
		out.close();
		// -------------------
		/*
		 * 使用中间路径名切开,返回路径是website之下的路径 即中间路径和之后路径
		 */
		String relativePath = middleFileName
				+ fullFileName.split(middleFileName)[1];
		System.out.println("upload中最后返回数据 return --" + relativePath);
		return relativePath;

	}

	/**
	 * 主要用在app下载 和存档文件的下载
	 * 同原来的/file/download.do
	 * @param request
	 * @param response
	 * @param filePath
	 */
	public static String download(HttpServletRequest request,
			HttpServletResponse response, String filePath) {

		if (filePath == null || filePath.trim().equals("")) {
			System.out.println("路径为空，下载失败=" + filePath);
			return "路径为空";
		}
		System.out.println("收到请求------文件下载请求---下载文件为-----" + filePath);
		try {
			// 乱码问题
			filePath = new String(request.getParameter("filePath").getBytes(
					"ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try {
			// path是指欲下载的文件的路径。
			String DownloadPath = FileUtils.getProperty("md.properties",
					"filesite");
			File file = new File(DownloadPath + "/" + filePath);
			System.out.println("文件存在否" + file.exists());
			if (!file.exists())
				return "文件不存在";
			// 取得文件名。
			String filename = file.getName();
			System.out.println(file.getName());
			// 取得文件的后缀名。
			// String ext = filename.substring(filename.lastIndexOf(".") +
			// 1).toUpperCase();
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(filename.getBytes("utf-8"), "ISO-8859-1"));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
			System.out.println(file.getName() + "--------下载完毕");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return "success";

	}
	/**
	 * 用于上传 同原来的/file/upload.do
	 * @param request
	 * @throws IOException
	 */
	public static void upload(HttpServletRequest request) throws IOException {
		/**
		 * 以下为上传图片时的操作
		 */
		// 转换request
	
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) request;
		// 获得到文件
		CommonsMultipartFile cfile = (CommonsMultipartFile) mr.getFile("file");
		/**
		 * 判断文件是否为空,如果为空则不执行操作 上传图片为空,则证明只想改说明文字不想改其中图片
		 */
		System.out.println("请求收到");
		if (!cfile.isEmpty()) {
			System.out.println("上传文件不为空");
			// 获得字节数组
			byte[] fbyte = cfile.getBytes();
			String fileName = "";
			// 获得当前的时间的单位毫秒最小精度
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			fileName = format.format(new Date());
			Random random = new Random();
			for (int i = 0; i < 3; i++) {
				fileName = fileName + random.nextInt(9);
			}
			// 获得原始文件名
			String oriFileName = cfile.getOriginalFilename();
			// 获得文件的后缀
			String suffix = oriFileName.substring(oriFileName.lastIndexOf("."));
			// 判断目录是否存在,不存在则创建
			// 之后这一步可能不用,因为每一个页面都有对应文件夹,而且每创建一个类别,也会创建文件夹
			File nfile = new File("I:/temp");
			if (!nfile.exists()) {
				nfile.mkdirs();
			}
			// 获取虚拟路径
			String virtualPath = "I:/temp/";
			// 虚拟路径以下部分的路径
			String localPath = fileName + suffix;
			OutputStream out = new FileOutputStream(new File(virtualPath
					+ localPath));
			System.out
					.println("I:/temp" + "/" + fileName + suffix);
			out.write(fbyte);
			out.flush();
			out.close();
		}

	}
}
