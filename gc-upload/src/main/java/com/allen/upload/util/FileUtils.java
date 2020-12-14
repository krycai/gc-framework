package com.allen.upload.util;

import com.allen.upload.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
public class FileUtils extends org.apache.commons.io.FileUtils {

	/**
	 * 创建目录
	 * @param descDirName 目录名,包含路径
	 * @return 如果创建成功，则返回true，否则返回false
	 */
	public static boolean createDirectory(String descDirName) {

		String descDirNames = descDirName;
		if (!descDirNames.endsWith(File.separator)) {
			descDirNames = descDirNames + File.separator;
		}
		File descDir = new File(descDirNames);
		if (descDir.exists()) {
			log.debug("目录 " + descDirNames + " 已存在!");
			return false;
		}
		// 创建目录
		if (descDir.mkdirs()) {
			log.debug("目录 " + descDirNames + " 创建成功!");
			return true;
		} else {
			log.debug("目录 " + descDirNames + " 创建失败!");
			return false;
		}

	}

	/**
	 * 传过来的目录包括了文件名
	 *
	 * @param dirName
	 * @param hasFileName
	 * @return
	 */
	public static boolean createDirectory(String dirName, boolean hasFileName) {
		if (!hasFileName) {
			return createDirectory(dirName);
		}
		if (StringUtils.isEmpty(dirName)) {
			return false;
		}
		if (dirName.lastIndexOf('/') != -1) return createDirectoryByMark(dirName, "/");
		if (dirName.lastIndexOf("//") != -1) return createDirectoryByMark(dirName, "//");
		if (dirName.lastIndexOf('\\') != -1) return createDirectoryByMark(dirName, "\\");

		return false;
	}

	private static boolean createDirectoryByMark(String dirName, String mark) {
		int lastIndexOf = dirName.lastIndexOf(mark);
		if (lastIndexOf != -1) {
			String newDirName = dirName.substring(0, lastIndexOf);
			return createDirectory(newDirName);
		}
		return false;
	}


	/**
	 * 判断目录是否存在
	 * 
	 * @param destDirName 目标目录名
	 * @return 目录存在返回true，否则返回false
	 */
	public static boolean existsDirectory(String destDirName) {
		File dir = new File(destDirName);
		return dir.exists();
	}


	/**
	 * 读取到字节数组1
	 * <p>thread-safe</p>
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray(String filePath) throws IOException {

		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException(filePath);
		}
		try (FileInputStream fileInputStream = new FileInputStream(f)) {
			return IOUtils.toByteArray(fileInputStream);
		} catch (Exception e) {
			log.error("处理文件toArray出错{}", e);
		}
		return new byte[0];
	}


	/**
	 * Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArrayUseMapped(String filePath) throws IOException {
		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException(filePath);
		}
		try (RandomAccessFile rf = new RandomAccessFile(filePath, "r");
			 FileChannel fc = rf.getChannel()) {
			MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0, fc.size()).load();
			byte[] result = new byte[(int) fc.size()];
			if (byteBuffer.remaining() > 0) {
				byteBuffer.get(result, 0, byteBuffer.remaining());
			}
			return result;
		} catch (IOException e) {
			log.error("处理文件toArray出错{}", e);
		}
		return new byte[0];
	}


	/**
	 * 压缩文件或目录
	 * 
	 * @param srcDirName   压缩的根目录
	 * @param fileName     根目录下的待压缩的文件名或文件夹名，其中*或""表示跟目录下的全部文件
	 * @param descFileName 目标zip文件
	 */
	public static void zipFiles(String srcDirName, String fileName, String descFileName) {
		// 判断目录是否存在
		if (srcDirName == null) {
			log.debug("文件压缩失败，目录 " + srcDirName + " 不存在!");
			return;
		}
		File fileDir = new File(srcDirName);
		if (!fileDir.exists() || !fileDir.isDirectory()) {
			log.debug("文件压缩失败，目录 " + srcDirName + " 不存在!");
			return;
		}
		String dirPath = fileDir.getAbsolutePath();
		File descFile = new File(descFileName);
		try (OutputStream fileOutPutStream = new FileOutputStream(descFile);
			 ZipOutputStream zouts = new ZipOutputStream(fileOutPutStream)) {
			if ("*".equals(fileName) || "".equals(fileName)) {
				FileUtils.zipDirectoryToZipFile(dirPath, fileDir, zouts);
			} else {
				File file = new File(fileDir, fileName);
				if (file.isFile()) {
					FileUtils.zipFilesToZipFile(dirPath, file, zouts);
				} else {
					FileUtils.zipDirectoryToZipFile(dirPath, file, zouts);
				}
			}
			log.debug(descFileName + " 文件压缩成功!");
		} catch (Exception e) {
			log.debug("文件压缩失败：" + e.getMessage());
		}
	}

	/**
	 * 将目录压缩到ZIP输出流
	 * 
	 * @param dirPath 目录路径
	 * @param fileDir 文件信息
	 * @param zouts   输出流
	 */
	public static void zipDirectoryToZipFile(String dirPath, File fileDir, ZipOutputStream zouts) {
		if (fileDir.isDirectory()) {
			File[] files = fileDir.listFiles();
			// 空的文件夹
			if (files.length == 0) {
				// 目录信息
				ZipEntry entry = new ZipEntry(getEntryName(dirPath, fileDir));
				try {
					zouts.putNextEntry(entry);
					zouts.closeEntry();
				} catch (Exception e) {
					log.error("将目录压缩到ZIP输出流错误{}", e);
				}
				return;
			}

			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					// 如果是文件，则调用文件压缩方法
					FileUtils.zipFilesToZipFile(dirPath, files[i], zouts);
				} else {
					// 如果是目录，则递归调用
					FileUtils.zipDirectoryToZipFile(dirPath, files[i], zouts);
				}
			}
		}
	}

	/**
	 * 将文件压缩到ZIP输出流
	 * 
	 * @param dirPath 目录路径
	 * @param file    文件
	 * @param zouts   输出流
	 */
	public static void zipFilesToZipFile(String dirPath, File file, ZipOutputStream zouts) {
		ZipEntry entry = null;
		// 创建复制缓冲区
		byte[] buf = new byte[4096];
		int readByte = 0;
		if (file.isFile()) {
			try (FileInputStream fin = new FileInputStream(file)) {
				// 创建一个ZipEntry
				entry = new ZipEntry(getEntryName(dirPath, file));
				// 存储信息到压缩文件
				zouts.putNextEntry(entry);
				// 复制字节到压缩文件
				while ((readByte = fin.read(buf)) != -1) {
					zouts.write(buf, 0, readByte);
				}
				zouts.closeEntry();
				log.info("添加文件 " + file.getAbsolutePath() + " 到zip文件中!");
			} catch (Exception e) {
				 log.error("将文件压缩到ZIP输出流错误{}", e);
			}
		}
	}

	/**
	 * 获取待压缩文件在ZIP文件中entry的名字，即相对于跟目录的相对路径名
	 * 
	 * @param dirPath 目录名
	 * @param file   entry文件名
	 * @return
	 */
	private static String getEntryName(String dirPath, File file) {
		String dirPaths = dirPath;
		if (!dirPaths.endsWith(Constants.FILE_SEPARATOR)) {
			dirPaths = dirPaths + Constants.FILE_SEPARATOR;
		}
		String filePath = file.getAbsolutePath();
		// 对于目录，必须在entry名字后面加上"/"，表示它将以目录项存储
		if (file.isDirectory()) {
			filePath += "/";
		}
		int index = filePath.indexOf(dirPaths);

		return filePath.substring(index + dirPaths.length());
	}


	/**
	 * 获取文件扩展名(返回小写)
	 * 
	 * @param fileName 文件名
	 * @return 例如：test.jpg 返回： jpg
	 */
	public static String getFileExtension(String fileName) {
		if ((fileName == null) || (fileName.lastIndexOf('.') == -1)
				|| (fileName.lastIndexOf('.') == fileName.length() - 1)) {
			return null;
		}
		return StringUtils.lowerCase(fileName.substring(fileName.lastIndexOf('.') + 1));
	}

	/**
	 * 获取文件名，不包含扩展名
	 * 
	 * @param fileName 文件名
	 * @return 例如：d:\files\test.jpg 返回：d:\files\test
	 */
	public static String getFileNameWithoutExtension(String fileName) {
		if ((fileName == null) || (fileName.lastIndexOf('.') == -1)) {
			return null;
		}
		return fileName.substring(0, fileName.lastIndexOf('.'));
	}


	/**
	 * 移除 //  \\  / 标志的后缀
	 * @param sourceString
	 * @return
	 */
	public static String removeEndMark(String sourceString) {
		if (StringUtils.isEmpty(sourceString)) return "";

		if (sourceString.endsWith("//")) {
			return StringUtils.removeEnd(sourceString, "//");
		} else if (sourceString.endsWith("/")) {
			return StringUtils.removeEnd(sourceString, "/");
		} else if (sourceString.endsWith("\\")) {
			return StringUtils.removeEnd(sourceString, "\\");
		}
		return sourceString;
	}


	/**
	 * @param sourceString
	 * @return
	 */
	public static String generateNormalPath(String sourceString){
		if (StringUtils.isEmpty(sourceString)) return "";

		if (sourceString.lastIndexOf("//") != -1){
			sourceString = removeStartAndEnd(sourceString , "//");
			sourceString = StringUtils.replace(sourceString,"//" , File.separator);
			return sourceString;
		} else if (sourceString.lastIndexOf("/") != -1){
			sourceString = removeStartAndEnd(sourceString , "/");
			sourceString = StringUtils.replace(sourceString,"/" , File.separator);
			return sourceString;
		} else if (sourceString.lastIndexOf("\\") != -1){
			sourceString = removeStartAndEnd(sourceString , "\\");
			sourceString = StringUtils.replace(sourceString,"\\" , File.separator);
			return sourceString;
		}
		return sourceString;
	}

	/**
	 * 移除字符串的前后的标志
	 * @param sourceString
	 * @param mark
	 * @return
	 */
	public static String removeStartAndEnd(String sourceString, String mark){
		if (StringUtils.isEmpty(sourceString)) return "";
		sourceString = StringUtils.removeStart(sourceString , mark);
		sourceString = StringUtils.removeEnd(sourceString , mark);
		return sourceString;
	}
	

}