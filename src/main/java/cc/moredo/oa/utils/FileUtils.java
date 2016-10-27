package cc.moredo.oa.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * 
 * @author guohao
 *
 */
public class FileUtils {
	/*
	 * 得到一个随机的文件名
	 * 前17位是当前时间+3位随机数
	 */
	public static String getDateName(String fileName){
		//得到文件名后缀
		String suffix = fileName.substring(fileName.lastIndexOf('.'));
		//当前时间
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		fileName = format.format(new Date());
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			fileName = fileName + random.nextInt(9);
		}
		fileName = fileName + suffix;
		return fileName;
	}
	
	
	
	 // 复制文件---参数(路径)---源文件路径---目标文件路径
    public static void copyFile(String sourceAbsolutePath, String targetAbsolutePath) throws IOException {
    	System.out.println("-从--"+sourceAbsolutePath+"--到--"+targetAbsolutePath);
    	File sourceFile = new File(sourceAbsolutePath);
    	File targetFile = new File(targetAbsolutePath);
    	/*if(!targetFile.exists()){
    		
    		targetFile.mkdirs();
    	}*/
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }
    // 复制文件----文件对象---源文件--目标文件
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }
    // 复制文件夹---把一个目录下(不包括当前目录)的所有文件,包括下层目录(文件夹)复制到另一个目录下
    public static void copyDirectiory(String sourceDir, String targetDir) throws IOException {
        // 新建目标目录
        (new File(targetDir)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDir)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // 源文件
                File sourceFile = file[i];
                // 目标文件
                File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file[i].getName());
                copyFile(sourceFile, targetFile);
            }
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹
                String dir1 = sourceDir + "/" + file[i].getName();
                // 准备复制的目标文件夹
                String dir2 = targetDir + "/" + file[i].getName();
                copyDirectiory(dir1, dir2);
            }
        }
    }

	/*
	 * 保存文件--指定一个总文件夹
	 * 此文件夹下依次存放文件名为1,2,3...的文件,每个文件夹内存一百个文件
	 * 按照此规则 ,此方法将返回  路径 url 下当前应该存放 文件的路径
	 */
	public static String saveFileUrl(String url){
		System.out.println(" saveFileUrl---url-"+url);
		File file = new File(url);
		//如果给定的目录为空,则先建一个文件夹为1的文件夹
		if(file.list().length==0){
			new File(url+"/1").mkdir();
			System.out.println("00000000000");
			return url+"/1";
		}else{
			//找出文件夹中文件名最大的
			String[] filename = file.list();
			int max = 0;
			for (String name : filename) {
				if(Integer.parseInt(name)>max){
					max = Integer.parseInt(name);
				}
			}
			//开始判断,如果这个最大的文件夹中的文件超过500个,则再创建一个比这个更大的
			if(new File(url+"/"+max).list().length>499){
				max++;
				new File(url+"/"+max).mkdir();
				return url+"/"+max;
			}else{
				return url+"/"+max;
			}
		}
		
	}
	
	/**
	 * 读取property文件方法
	 * @param path
	 * @param key
	 * @return
	 */
	public static String getProperty(String path,String key){
		
		Properties properties = new Properties();
		String classesPath = FileUtils.class.getResource("/").getPath();
		System.out.println("得到的classpath为-----------"+classesPath);
		File file = new File(classesPath+path);
		try {
			properties.load(new FileInputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String value = properties.getProperty(key);
		return value;
		
	}
		
}
