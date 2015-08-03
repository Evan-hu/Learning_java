package com.company.shop.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.io.FileUtils;

import com.company.shop.util.SystemConfig.WatermarkPosition;

/**
 * 工具�?- 图片处理
 */

public class ImageUtil {

	/**
	 * 图片缩放(图片等比例缩放为指定大小，空白部分以白色填充)
	 * 
	 * @param srcBufferedImage
	 *            源图�?
	 * @param destFile
	 *            缩放后的图片文件
	 */
	public static void zoom(BufferedImage srcBufferedImage, File destFile, int destHeight, int destWidth) {
		try {
			int imgWidth = destWidth;
			int imgHeight = destHeight;
			int srcWidth = srcBufferedImage.getWidth();
			int srcHeight = srcBufferedImage.getHeight();
			if (srcHeight >= srcWidth) {
				imgWidth = (int) Math.round(((destHeight * 1.0 / srcHeight) * srcWidth));
			} else {
				imgHeight = (int) Math.round(((destWidth * 1.0 / srcWidth) * srcHeight));
			}
			BufferedImage destBufferedImage = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = destBufferedImage.createGraphics();
			graphics2D.setBackground(Color.WHITE);
			graphics2D.clearRect(0, 0, destWidth, destHeight);
			graphics2D.drawImage(srcBufferedImage.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH), (destWidth / 2) - (imgWidth / 2), (destHeight / 2) - (imgHeight / 2), null);
			graphics2D.dispose();
			ImageIO.write(destBufferedImage, "JPEG", destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加图片水印
	 * 
	 * @param srcBufferedImage
	 *            �?��处理的源图片
	 * @param destFile
	 *            处理后的图片文件
	 * @param watermarkFile
	 *            水印图片文件
	 * 
	 */
	public static void imageWatermark(BufferedImage srcBufferedImage, File destFile, File watermarkFile, WatermarkPosition watermarkPosition, int alpha) {
		try {
			int srcWidth = srcBufferedImage.getWidth();
			int srcHeight = srcBufferedImage.getHeight();
			BufferedImage destBufferedImage = new BufferedImage(srcWidth, srcHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = destBufferedImage.createGraphics();
			graphics2D.setBackground(Color.WHITE);
			graphics2D.clearRect(0, 0, srcWidth, srcHeight);
			graphics2D.drawImage(srcBufferedImage.getScaledInstance(srcWidth, srcHeight, Image.SCALE_SMOOTH), 0, 0, null);

			if (watermarkFile != null && watermarkFile.exists() && watermarkPosition != null && watermarkPosition != WatermarkPosition.no) {
				BufferedImage watermarkBufferedImage = ImageIO.read(watermarkFile);
				int watermarkImageWidth = watermarkBufferedImage.getWidth();
				int watermarkImageHeight = watermarkBufferedImage.getHeight();
				graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha / 100.0F));
				int x = 0;
				int y = 0;
				if (watermarkPosition == WatermarkPosition.topLeft) {
					x = 0;
					y = 0;
				} else if (watermarkPosition == WatermarkPosition.topRight) {
					x = srcWidth - watermarkImageWidth;
					y = 0;
				} else if (watermarkPosition == WatermarkPosition.center) {
					x = (srcWidth - watermarkImageWidth) / 2;
					y = (srcHeight - watermarkImageHeight) / 2;
				} else if (watermarkPosition == WatermarkPosition.bottomLeft) {
					x = 0;
					y = srcHeight - watermarkImageHeight;
				} else if (watermarkPosition == WatermarkPosition.bottomRight) {
					x = srcWidth - watermarkImageWidth;
					y = srcHeight - watermarkImageHeight;
				}
				graphics2D.drawImage(watermarkBufferedImage, x, y, watermarkImageWidth, watermarkImageHeight, null);
			}
			graphics2D.dispose();
			ImageIO.write(destBufferedImage, "JPEG", destFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 图片缩放并添加图片水�?图片等比例缩放为指定大小，空白部分以白色填充)
	 * 
	 * @param srcBufferedImage
	 *            �?��处理的图�?
	 * @param destFile
	 *            处理后的图片文件
	 * @param watermarkFile
	 *            水印图片文件
	 * 
	 */
	public static void zoomAndWatermark(BufferedImage srcBufferedImage, File destFile, int destHeight, int destWidth, File watermarkFile, WatermarkPosition watermarkPosition, int alpha) {
		try {
			int imgWidth = destWidth;
			int imgHeight = destHeight;
			int srcWidth = srcBufferedImage.getWidth();
			int srcHeight = srcBufferedImage.getHeight();
			if (srcHeight >= srcWidth) {
				imgWidth = (int) Math.round(((destHeight * 1.0 / srcHeight) * srcWidth));
			} else {
				imgHeight = (int) Math.round(((destWidth * 1.0 / srcWidth) * srcHeight));
			}
			
			BufferedImage destBufferedImage = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = destBufferedImage.createGraphics();
			graphics2D.setBackground(Color.WHITE);
			graphics2D.clearRect(0, 0, destWidth, destHeight);
			graphics2D.drawImage(srcBufferedImage.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH), (destWidth / 2) - (imgWidth / 2), (destHeight / 2) - (imgHeight / 2), null);
			if (watermarkFile != null && watermarkFile.exists() && watermarkPosition != null && watermarkPosition != WatermarkPosition.no) {
				BufferedImage watermarkBufferedImage = ImageIO.read(watermarkFile);
				int watermarkImageWidth = watermarkBufferedImage.getWidth();
				int watermarkImageHeight = watermarkBufferedImage.getHeight();
				graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha / 100.0F));
				int x = 0;
				int y = 0;
				if (watermarkPosition == WatermarkPosition.topLeft) {
					x = 0;
					y = 0;
				} else if (watermarkPosition == WatermarkPosition.topRight) {
					x = destWidth - watermarkImageWidth;
					y = 0;
				} else if (watermarkPosition == WatermarkPosition.center) {
					x = (destWidth - watermarkImageWidth) / 2;
					y = (destHeight - watermarkImageHeight) / 2;
				} else if (watermarkPosition == WatermarkPosition.bottomLeft) {
					x = 0;
					y = destHeight - watermarkImageHeight;
				} else if (watermarkPosition == WatermarkPosition.bottomRight) {
					x = destWidth - watermarkImageWidth;
					y = destHeight - watermarkImageHeight;
				}
				graphics2D.drawImage(watermarkBufferedImage, x, y, watermarkImageWidth, watermarkImageHeight, null);
			}
			graphics2D.dispose();
			ImageIO.write(destBufferedImage, "JPEG", destFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取图片文件的类�?
	 * 
	 * @param imageFile
	 *            图片文件对象.
	 * @return 图片文件类型
	 */
	public static String getImageFormatName(File imageFile) {
		try {
			ImageInputStream imageInputStream = ImageIO.createImageInputStream(imageFile);
			Iterator<ImageReader> iterator = ImageIO.getImageReaders(imageInputStream);
			if (!iterator.hasNext()) {
				return null;
			}
			ImageReader imageReader = (ImageReader) iterator.next();
			imageInputStream.close();
			return imageReader.getFormatName().toLowerCase();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 图片压缩（clipWidth、clipHeight不能同时�?�?
	 * @param source 源文�?
	 * @param savePath 压缩后图片保存路�?
	 * @param clipWidth �?��宽度（为0是按�?��高度压缩�?
	 * @param clipHeight �?��高度（为0是按�?��宽度压缩�?
	 * @return 
	 */
	public static boolean clipImage(File source,String savePath,int clipWidth,int clipHeight) {
		if(!source.exists()){
			return false;
		}
		if(savePath==null || savePath.trim().length()==0){
			return false;
		}
		try {
			return clipImage(ImageIO.read(source), savePath, clipWidth, clipHeight);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 使源图按比例压缩到指定大小的尺寸（等比压缩）（如果源图小于指定的尺寸则不放大�?
	 * @param srcImage
	 * @param destImg
	 * @param width
	 * @param height
	 * @return
	 * @throws Exception
	 */
	public static boolean clipImage(BufferedImage srcImage, String destImg, int maxWidth, int maxHeight) throws Exception {
		if (destImg == null || destImg.trim().length() == 0) {
			return false;
		}
		if (srcImage == null) {
			return false;
		}
		
		int srcWidth = srcImage.getWidth(null); // 源图�?
		int srcHeight = srcImage.getHeight(null);// 源图�?
		
		if (maxWidth <= 0 && maxHeight <= 0) {// 如果指定的区域都小于0则直接返回false
			return false;
		}
		
		int scalWidht = 0;
		int scalHeight = 0;
		
		/**  计算缩放后的高宽     */
		float scalY = (float)maxHeight/(float)srcHeight;
		float scalX = (float)maxWidth/(float)srcWidth;
		if(maxWidth == 0){/** 如果没有指定宽度，则直接按高度压�?*/
			if(srcHeight > maxHeight){
				scalHeight = maxHeight;
				scalWidht = (int)Math.ceil(srcWidth * scalY);
			}else{
				scalHeight = srcHeight;
				scalWidht = srcWidth;
			}
		}else if(maxHeight == 0){/** 如果没有指定高度，则直接按宽度压�?*/
			if(srcWidth > maxWidth){
				scalWidht = maxWidth;
				scalHeight = (int)Math.ceil(srcHeight*scalX);
			}else{
				scalHeight = srcHeight;
				scalWidht = srcWidth;
			}
		}else if(srcWidth <= maxWidth && srcHeight <= maxHeight){
			scalWidht = srcWidth;
			scalHeight = srcHeight;
		}else{
			if(srcWidth > srcHeight){
				if(srcHeight * scalX <= maxHeight){
					scalWidht = maxWidth;
					scalHeight = (int)Math.ceil(srcHeight * scalX);
				}else{
					scalHeight = maxHeight;
					scalWidht = (int)Math.ceil(srcWidth * scalY);
				}
			}else{
				if(srcWidth * scalY <= maxWidth){
					scalHeight = maxHeight;
					scalWidht = (int)Math.ceil(srcWidth * scalY);
				}else{
					scalWidht = maxWidth;
					scalHeight = (int)Math.ceil(srcHeight * scalX);
				}
			}
		}
		
		/** 缩放  */
		BufferedImage scaleImage = new BufferedImage(scalWidht, scalHeight, srcImage.getType());
		Graphics g = scaleImage.getGraphics();
		g.drawImage(srcImage, 0, 0, scalWidht, scalHeight, null);
		g.dispose();
		
        // Soften.  质量修复
        float softenFactor = 0.05f;  
        float[] softenArray = { 0, softenFactor, 0, softenFactor,  
                1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };  
        Kernel kernel = new Kernel(3, 3, softenArray);  
        ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);  
        scaleImage = cOp.filter(scaleImage, null);  
        
		/** 输出   */
		BufferedOutputStream  out = new BufferedOutputStream(new FileOutputStream(destImg));
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out); 
//		
//        JPEGEncodeParam param = encoder  
//                .getDefaultJPEGEncodeParam(scaleImage);  
//        param.setQuality(1, true);  
//        encoder.setJPEGEncodeParam(param);  
//		encoder.encode(scaleImage);
//		out.flush();
//		out.close();
//		srcImage.flush();
//		scaleImage.flush();
		
		return true;
	}
	
	/**
	 * 图片上传多个规格 并且保留原图 只支持jpg格式
	 * @param w
	 * @param h
	 * @param dirPath
	 * @param id
	 */
	public static boolean clipImage(File srcFile, int[] w, int[] h, String dirPath, String imageName, Integer id){
		if(w == null || h == null || w.length == 0 ||  w.length != h.length 
				|| dirPath == null || imageName == null || id == null){
	          throw new IllegalArgumentException( "参数不正确"); 
		}
		if(srcFile == null || !srcFile.exists()){
			return false;
		}
		
		String path = dirPath + idToPathBase8(id.toString()) + File.separator;
		String temp = path + imageName;
		try {
			FileUtils.copyFile(srcFile, new File(temp + ".jpg"));
		} catch (IOException e) {
			return false;
		}
		for (int i = 0; i < w.length; i++) {
			ImageUtil.clipImage(srcFile, temp + "_" + w[i] + "_" + h[i] + ".jpg", w[i], h[i]);
		}
		return true;
	}
	
	public static String idToPathBase8(String idStr) {
		String raw = "0000000";
		String combinePath = raw.substring(0, 8 - idStr.length()) + idStr;
		StringBuilder builder = new StringBuilder();
		builder.append(File.separatorChar).append(combinePath.substring(0, 2)).append(File.separatorChar)
				.append(combinePath.substring(2, 4)).append(File.separatorChar)
				.append(combinePath.substring(4, 6)).append(File.separatorChar)
				.append(combinePath.substring(6, 8)).append(File.separatorChar);
		return builder.toString();
	}
	
}
