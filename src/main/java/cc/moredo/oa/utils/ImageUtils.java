package cc.moredo.oa.utils;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * 处理图片的工具类,改大小,贴水印
 * @author guohao
 * 
 */
public class ImageUtils {
	/*
	 * 为图片贴上水印
	 */
	/*
	 * 改变图片大小 通过指定修改后的宽度,按原图的长宽比例去重定义图片大小
	 */
	public static void modifySize(String srcImageFile, String result,int resultWidth) {
		try { // 获取图片类型,以赋给 绽放 的图片后的类型
			File file = new File(srcImageFile);
			String imgType = getFormatNames(file);

			BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
			int width = src.getWidth(); // 得到源图宽
			int height = src.getHeight(); // 得到源图长
			// 获取源图片高/宽 比例--缩放比例
			double scale = (double) height / (double) width;
			height = (int) (resultWidth * scale);
			Image image = src.getScaledInstance(resultWidth, height,
					Image.SCALE_DEFAULT);
			BufferedImage tag = new BufferedImage(resultWidth, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			ImageIO.write(tag, imgType, new File(result));// 输出到文件流
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 以水印的图宽度 占  要贴图的宽度的比例贴图
	 * 图的位置位置右下角对齐
	 * 按原图片类型输出
	 */
	public final static void addWatermark(String targetImg, String waterImg, double widthScale, float alpha) {
        try {
            File file = new File(targetImg);
            String imgType = getFormatNames(file);
            Image image = ImageIO.read(file);
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, width, height, null);
        
            Image waterImage = ImageIO.read(new File(waterImg));    // 水印文件
            //水印文件要缩放到的尺寸-自己原有的长宽比不变
            double waterWidth = waterImage.getWidth(null);
            double waterHeight = waterImage.getHeight(null);
            int targetWidth = (int) (width*widthScale);
            int targetHeight = (int) (targetWidth*(waterHeight/waterWidth));
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            int targetX = width - targetWidth ;
            int targetY = height - targetHeight;
            //g.drawImage(waterImage, x, y, width_1, height_1, null); // 水印文件结束
            g.drawImage(waterImage, targetX, targetY, targetWidth, targetHeight, null); // 水印文件结束
            g.dispose();
            ImageIO.write(bufferedImage, imgType, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
}

	
	
	
	// 获取图片类型的方法,暂时用于本类内部
	private static String getFormatNames(Object o) {
		try {
			ImageInputStream iis = ImageIO.createImageInputStream(o);
			Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
			if (!iter.hasNext()) {
				return null;
			}
			ImageReader reader = (ImageReader) iter.next();
			iis.close();
			return reader.getFormatName();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
//		modifySize("i:/website/3.png", "i:/website/4.png", 1000);
//		addWatermark("i:/website/b1.png","i:/website/watermark.png",0.25,1f);
	}
	/*
	 * 修改过的图片,如果图片宽大于1200像素,则设为1200像素,再贴水印
	 * 如果小于1200像素,则按原图比例
	 * imgFilePath----带有中间路径的文件
	 */
	public static String saveMaxImg(String imgFilePath) throws IOException {
		//获取应该存入的目录
		String basePath = FileUtils.getProperty("md.properties", "filesite");
		//源图片路径---全路径
		String srcImgPath = basePath + "/" + imgFilePath;
		// 获取本次应该存储进的文件夹
		String dir = FileUtils.saveFileUrl(basePath + "/" + "model-image");
		// 获取以时间名为文件名的文件名称--目标文件路径
		String fileName = FileUtils.getDateName(imgFilePath);
		String targetImgPath = dir +"/"+ fileName;
		// 获取水印图片--全路径
		String waterImgPath = basePath + "/back-important/watermark.png";
		// 开始处理图--修改大小--先判断原图的大小
		File file = new File(srcImgPath);
		System.out.println("targetImgPath为--------"+targetImgPath);
        Image image;
        int width = 0;
		try {
			image = ImageIO.read(file);
			width = image.getWidth(null);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(width>1200){
			modifySize(srcImgPath,targetImgPath,1200);
		}else{
			//直接复制
			FileUtils.copyFile(srcImgPath, targetImgPath);
		}
		addWatermark(targetImgPath,waterImgPath,0.25,1f);
		//加水印
		String relativePath = "model-image"+targetImgPath.split("model-image")[1];
		System.out.println("存入水印图片地址为----1200或更小---"+relativePath);
		return relativePath;
	}
	/*
	 * 修改过的图片 宽300像素再贴水印
	 */
	public static String saveLittleImg(String imgFilePath) {
		//获取应该存入的目录
		String basePath = FileUtils.getProperty("md.properties", "filesite");
		//源图片路径
		String srcImgPath = basePath + "/" + imgFilePath;
		// 获取本次应该存储进的文件夹
		String dir = FileUtils.saveFileUrl(basePath + "/" + "model-image");
		// 获取以时间名为文件名的文件名称
		String fileName = FileUtils.getDateName(imgFilePath);
		String targetImgPath = dir + "/" + fileName;
		// 获取水印图片
		String waterImg = basePath + "/back-important/watermark.png";
		// 开始处理图--修改大小--300px
		modifySize(srcImgPath,targetImgPath,300);
		//加水印
		addWatermark(targetImgPath,waterImg,0.25,1f);
		String relativePath = "model-image"+targetImgPath.split("model-image")[1];
		System.out.println("存入水印图片地址为----300---"+relativePath);
		return relativePath;
	}

}
