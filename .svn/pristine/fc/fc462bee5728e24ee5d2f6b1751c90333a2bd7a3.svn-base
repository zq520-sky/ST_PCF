package com.samton.platform.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;

import com.samton.platform.framework.util.Public;
import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;


/**
 * 文件上传工具类
 * @CopyRight:		SamTon
 * @Comments:		上传文件、下载文件
 * @author: 		lt
 * @Create Date:	2011-11-03 09:50
 * @Modified By:	zx
 * @Modified By:	2011-11-07 21:30
 * @Version:		1.0
 *
 */
public final class UpLoadFileUtil {
	/*
	 * 默认文件大小
	 */
	private static final int BUFFER_SIZE=16*1024;  

	private static Logger log = Logger.getLogger(UpLoadFileUtil.class);
	
	/**
	 * 上传文件到服务器
	 * @param file  传入的文件
	 * @param targetFlie  上传到服务器的文件
	 * @return 
	 * false 失败  上传附件路径不存在
	 * true 成功
	 * @throws  Exception 
	 * 抛出	系统找不到指定的文件
	 *      其它异常
	 */
	public static boolean upLoad(File file, File targetFlie) throws Exception{   
        InputStream in = null;   
        OutputStream out = null;   
        try {   
        	//Date begin = new Date();
        	//log.info("上传文件【"+file.getName()+"】保存为【"+targetFlie.getName()+"】开始时间【"+begin.getTime()+"】");
            in = new BufferedInputStream(new FileInputStream(file), BUFFER_SIZE);   
            out = new BufferedOutputStream(new FileOutputStream(targetFlie),   
                    BUFFER_SIZE);   
            byte[] buffer = new byte[BUFFER_SIZE];   
            int len = 0;   
            while ((len = in.read(buffer)) > 0) {   
                out.write(buffer, 0, len);   
            }   
            //Date end = new Date();
            //log.info("上传文件【"+file.getName()+"】保存为【"+targetFlie.getName()+"】完成时间【"+end.getTime()+"】,用时【"+(end.getTime()-begin.getTime())+"】");
            return true;  
        } catch (FileNotFoundException e){
        	return false;
        }catch (Exception e) {   
           throw e;   
        } finally {   
            if (null != in) {   
                try {   
                    in.close();   
                } catch (IOException e) {   
                }   
            }   
            if (null != out) {   
                try {   
                    out.close();   
                } catch (IOException e) {   
                }   
            }   
        }   
    }   
	
	/**
	 * 将文件中数据存入数组返回
	 * @param file 要导入的文件<br>
	 * 				些操作没有删除上传到服务器的文件,如上传的文件只是暂时需要,可以在调用之后加上file.delete()手动删除
	 * @return   
	 */
	public static List<String[]>  getData(File file) throws Exception{
		BufferedReader input = null;
		try {
			if (file.exists()) {
				if (file.isFile()) {
					List<String[]> arrayData = new ArrayList<String[]>();
//					input = new BufferedReader(
//							new FileReader(file));
					input = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
					String lineText;
					while ((lineText = input.readLine()) != null){
						String[] lineArray = lineText.split(",",-1);
						arrayData.add(lineArray);
					}
					return arrayData;
				}
			}
		} catch (Exception e) {
		} finally{
			if (null != input) {   
                try {   
                	input.close();   
                } catch (IOException e) {   
                    e.printStackTrace();   
                }   
            }   
		}
		return null;

    }  
	
	/**
	 * 将文件中数据存入数组返回(去除数字两边的引号)
	 * @param file 要导入的文件<br>
	 * 				些操作没有删除上传到服务器的文件,如上传的文件只是暂时需要,可以在调用之后加上file.delete()手动删除
	 * @return   
	 */
	public static List<String[]>  getData2(File file) throws Exception{
		BufferedReader input = null;
		try {
			if (file.exists()) {
				if (file.isFile()) {
					List<String[]> arrayData = new ArrayList<String[]>();
//					input = new BufferedReader(
//							new FileReader(file));
					input = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
					String lineText;
					while ((lineText = input.readLine()) != null){
						String[] lineArray = lineText.replace("\"", "").split(",");
						arrayData.add(lineArray);
					}
					return arrayData;
				}
			}
		} catch (Exception e) {
		} finally{
			if (null != input) {   
                try {   
                	input.close();   
                } catch (IOException e) {   
                    e.printStackTrace();   
                }   
            }   
		}
		return null;

    } 
	
	/**
	 * 将文件中数据存入数组返回(不识别逗号)
	 * @param file 要导入的文件<br>
	 * 				些操作没有删除上传到服务器的文件,如上传的文件只是暂时需要,可以在调用之后加上file.delete()手动删除
	 * @return   
	 */
	public static List<String>  getData3(File file) throws Exception{
		BufferedReader input = null;
		try {
			if (file.exists()) {
				if (file.isFile()) {
					List<String> arrayData = new ArrayList<String>();
//					input = new BufferedReader(
//							new FileReader(file));
					input = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
					String lineText;
					while ((lineText = input.readLine()) != null){
//						String[] lineArray = lineText.split(",",-1);
						arrayData.add(lineText);
					}
					return arrayData;
				}
			}
		} catch (Exception e) {
		} finally{
			if (null != input) {   
                try {   
                	input.close();   
                } catch (IOException e) {   
                    e.printStackTrace();   
                }   
            }   
		}
		return null;

    }

	/**
	 * 将文件中数据存入数组返回
	 * @param inputStream 要导入的文件<br>
	 * 				些操作没有删除上传到服务器的文件,如上传的文件只是暂时需要,可以在调用之后加上file.delete()手动删除
	 * @return
	 */
	public static List<String[]>  getData(InputStream inputStream) throws Exception{
		BufferedReader input = null;
		try {
			if (inputStream!=null) {
				List<String[]> arrayData = new ArrayList<String[]>();
//					input = new BufferedReader(
//							new FileReader(file));
				input = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
				String lineText;
				while ((lineText = input.readLine()) != null){
					String[] lineArray = lineText.split(",",-1);
					arrayData.add(lineArray);
				}
				return arrayData;
			}
		} catch (Exception e) {
		} finally{
			if (null != input) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;

	}

	/**
	 * 将文件进行压缩.
	 * 
	 * @param zipFileName
	 *            压缩后的ZIP文件名.
	 * @param inputFileName
	 *            要压缩的文件名或文件目录名.
	 * @return 压缩后的文件.
	 */
	public static File toZip(String zipFileName, String inputFileName)
			throws Exception {
		return toZip(zipFileName, new File(inputFileName));
	}
	/**
	 * 多个文件打包下载
	 * @param zipFileName
	 * @param path
	 * @param inputFileName
	 * @return
	 * @throws Exception
	 */
	public static File toZip(String zipFileName, String path,String[] inputFileName,String filePath)
	throws Exception {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				zipFileName));
		out.setEncoding("UTF-8");
		File file = toZip(out, inputFileName, "", zipFileName,filePath);
		out.flush();
		out.close();
		return file;
	}

	/**
	 * 将文件进行压缩.
	 * @param zipFileName
	 *            压缩后的ZIP文件名.
	 * @param inputFile
	 *            要压缩的文件或文件目录.
	 * @return 压缩后的文件.
	 * @throws Exception
	 *             一些常规的IO异常信息.
	 */
	public static File toZip(String zipFileName, File inputFile)
			throws Exception {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				zipFileName));
		out.setEncoding("UTF-8");
		File file = toZip(out, inputFile, "", zipFileName);
		out.flush();
		out.close();
		return new File(zipFileName);
	}
	/**
	 * 多个文件打包下载
	 * @param outputStream
	 * @param inputFileName
	 * @param base
	 * @param zipFileName
	 * @return
	 * @throws Exception
	 */
	public static File toZip(ZipOutputStream outputStream, String[] inputFileName,
		String base, String zipFileName,String filePath) throws Exception {
		for (int i = 0; i < inputFileName.length; i++) {
			if(!"".equals(inputFileName[i])) {
				String refile = "";
				//判断有没有指定别名
				String[] inputfiles = inputFileName[i].split("\\|");
				String inputfile =null;
				String alias_name =null;
				if(inputfiles.length > 1){
					inputfile = inputfiles[1]; 
					alias_name = inputfiles[0]+inputfile.substring(inputfile.lastIndexOf("."),inputfile.length());
				} else {
					inputfile = inputFileName[i];
				}
				if(filePath != null && !"".equals(filePath.trim())){
					refile = filePath+"/"+inputfile;
				} else {
					refile = inputfile;
				}
				File file = new File(refile);
				if(!file.exists()) {
					continue;
				}
				String fileName = "";
				if (file.isDirectory()) {
				} else {
					if(alias_name != null){
						fileName = alias_name;
					} else {
						fileName = file.getName();
					}
				}
				outputStream.putNextEntry(new org.apache.tools.zip.ZipEntry(fileName));
				FileInputStream in = new FileInputStream(refile);
				int b;
				while ((b = in.read()) != -1) {
					outputStream.write(b);
				}
				in.close();
			}
		}
		return new File(zipFileName);
	}
	/**
	 * 将文件进行压缩.
	 * 
	 * @param outputStream
	 * @param file
	 *            要压缩的文件或文件目录.
	 * @param base
	 *            文件夹中子目录名称.
	 * @param zipFileName
	 *            压缩后的ZIP文件名.
	 * @return 压缩后的文件.
	 * @throws Exception
	 *             一些常规的IO异常信息.
	 */
	public static File toZip(ZipOutputStream outputStream, File file,
			String base, String zipFileName) throws Exception {
		if (file.isDirectory()) {
			File[] fl = file.listFiles();
			outputStream.putNextEntry(new org.apache.tools.zip.ZipEntry(base
					+ "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < fl.length; i++)
				toZip(outputStream, fl[i], base + fl[i].getName(), zipFileName);
		} else {
			if ("".equals(base)) {
				base = file.getName();
			} 
			outputStream.putNextEntry(new org.apache.tools.zip.ZipEntry(base));
			FileInputStream in = new FileInputStream(file);
			int b;
			while ((b = in.read()) != -1) {
				outputStream.write(b);
			}
			in.close();
		}
		return new File(zipFileName);
	}

	/**
	 * 多个文件打包下载
	 * @param zipName  zip名称
	 * @param path    路径 
	 * @param files   文件名称数组
	 * @return
	 * @throws Exception
	 */
	public static File toZip(String zipName, String path, String files,String filePath) throws Exception {
		String[] tempfiles = files.split(":");
		if(tempfiles.length > 0) {
			return toZip(zipName, path,tempfiles,filePath);
		} else {
			return toZip(zipName, tempfiles[0]);
		}
	}
 
	/**
	 * 检查文件大小
	 * @param file  文件
	 * @param m   1M/单位
	 * @return  false文件大小不合格  true文件大小合格
	 */
	public static boolean checkFileSize(File file,int m){
		if(file.length() > m *1024){
			return false;
		}
		return true;
	}
	
	/**
	 * 默认检查文件大小不能大于5M
	 * @param file  文件
	 * @return  false文件大小不合格  true文件大小合格
	 */
	public static boolean checkFileSize(File file){
		if(file.length() > 5 * 1024 * 1024){
			return false;
		}
		return true;
	}
	


	
	/**
     * 一行一行写入文件，解决写入中文字符时出现乱码
     * 
     * 流的关闭顺序：先打开的后关，后打开的先关，
     *       否则有可能出现java.io.IOException: Stream closed异常
     * @param in 输入流
     * @param path 存储文件的路径param表中的参数     
     * @param fileName 存储文件名     
	 * @return 
     * 
     * @throws IOException 
     */
    public static String writeFile(InputStream in,String path,String fileName){
    	if(in==null || path==null || fileName==null){
    		return null;
    	}
    	FileOutputStream fos = null ;
    	OutputStreamWriter osw = null ;
    	BufferedWriter bw = null ;
    	BufferedReader br = null ;
    	String filePath = null;
		try {
			//输入流
			br = new BufferedReader(new InputStreamReader(in,"UTF-8")); 
			//获取文件存储路径
			//ServletContext servletContext = ServletActionContext.getServletContext();
			WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
			ServletContext servletContext = webApplicationContext.getServletContext();
			filePath = Public.getParamValue(path, servletContext);
			//构建文件路径和文件名
			filePath = filePath + "/" + fileName ;
			//写入中文字符时解决中文乱码问题
			fos = new FileOutputStream(new File(filePath));
	        osw = new OutputStreamWriter(fos, "UTF-8");
	        bw = new BufferedWriter(osw);
	        //简写如下：
	        //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
	        //new FileOutputStream(new File("E:/phsftp/evdokey/evdokey_201103221556.txt")), "UTF-8"));
	        String line = "" ;
	        while ((line = br.readLine()) != null) {  
	            bw.write(line+"\r\n");
	            //bw.newLine();
	        } 
	        //注意关闭的先后顺序，先打开的后关闭，后打开的先关闭
		} catch (Exception e) {
			log.error(e);
		}finally{
			try {
				if(bw!=null){
					bw.flush();
					bw.close();
				}
				if(osw!=null){
					osw.close();
				}
				if(fos!=null){
					fos.close();
				}
		        if(in!=null){
		        	in.close();
		        }
			} catch (IOException e) {
				log.error(e);
			}
		}
		return filePath ;
    }
}
