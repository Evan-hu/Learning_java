package com.ga.click.services;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class CutImage {

  private String srcImagePath = "";
  private BufferedImage srcImage = null;
  private String fileSufix = "";

  /**
   * @param loadImgPath
   * @param saveName
   * @param width
   * @param height
   * @throws Exception
   */
  public void saveImageAsHLJpg(String loadImgPath,String savePath,int width,int height) throws Exception {
    this.loadImage(loadImgPath);
    this.execScale(height,false);
    this.fillBlank(width, height);
    this.saveImageNoSuffix(savePath);
    //this.deleteTempPic();
  }
  
  /**
   * 载入要处理的图片文件
   * @param loadImgPath
   * @throws Exception
   */
  public void loadImage(String loadImgPath) throws Exception {
    srcImagePath = loadImgPath;
    File loadFile = new File(loadImgPath);
    srcImage = ImageIO.read(loadFile); // construct image
    fileSufix = loadFile.getName().substring(loadFile.getName().lastIndexOf(".") + 1);
  }
  
  /**
   * 删除临时文件
   */
  public void deleteTempPic(){
    File file = new File(srcImagePath);
    if(file.exists()){
      file.delete(); 
    }
    file = new File(srcImagePath + ".tmp." + fileSufix);
    if(file.exists()){
      file.delete(); 
    }
  }
  public void fillBlank(int width,int height) {
    //放大(补白)
    BufferedImage image = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_RGB);
    Graphics2D g = image.createGraphics();
    g.setColor(Color.white);
    g.fillRect(0, 0, width, height);
    int fillx = (width - this.srcImage.getWidth()) / 2;
    int filly = (height - this.srcImage.getHeight()) / 2;
    if (fillx < 0)
      fillx = 0;
    if (filly < 0)
      filly = 0;
    g.drawImage(this.srcImage, null, fillx, filly);
    g.dispose();
    this.srcImage = image;
  }
  
  public void execScale(int zoomPx,boolean isWidth) {
    double zoomRatio = 1;
    int zoomW = 0,zoomH = 0;
    if (isWidth) {
      zoomRatio = (double) zoomPx / (double) this.srcImage.getWidth();
      zoomH = (int) (this.srcImage.getHeight() * zoomRatio);
      zoomW = zoomPx;
    } else {
      zoomRatio = (double) zoomPx / (double) this.srcImage.getHeight();
      zoomW =  (int) (this.srcImage.getWidth() * zoomRatio);
      zoomH = zoomPx;
    }
    ScaleImage scaleObj = new ScaleImage();
    this.srcImage = scaleObj.imageZoomOutNoLimit(this.srcImage,zoomW , zoomH);
  }
  /**
   * 执行图片的缩放处理
   * @param zoomRatio
   */
  public void execZoom(int zoomW) throws Exception {
    if (this.srcImage == null || this.srcImage.getWidth() == zoomW) {
      return;
    }
    double maxSize = this.srcImage.getWidth();
//    if (maxSize < this.srcImage.getHeight()) {
//      maxSize = this.srcImage.getHeight();
//    }
    double zoomRatio = (double) zoomW / (double) maxSize;
    int zoomH = (int) (this.srcImage.getHeight() * zoomRatio);
    ScaleImage scaleObj = new ScaleImage();
    if (zoomRatio < 1) {
      this.srcImage = scaleObj.imageZoomOutNoLimit(this.srcImage, zoomW, zoomH);
    }
    //放大(补白)
    BufferedImage image = new BufferedImage(zoomW, zoomW,
        BufferedImage.TYPE_INT_RGB);
    Graphics2D g = image.createGraphics();
    g.setColor(Color.white);
    g.fillRect(0, 0, zoomW, zoomW);
    int fillx = (zoomW - this.srcImage.getWidth()) / 2;
    int filly = (zoomW - this.srcImage.getHeight()) / 2;
    if (fillx < 0)
      fillx = 0;
    if (filly < 0)
      filly = 0;
    g.drawImage(this.srcImage, null, fillx, filly);
    g.dispose();
    this.srcImage = image;
    File destFile = new File(srcImagePath + ".tmp." + fileSufix);
    ImageIO.write(this.srcImage, fileSufix, destFile);
  }

  public void execZoomOldFileName(int zoomW) throws Exception {
    if (this.srcImage == null || this.srcImage.getWidth() == zoomW) {
      return;
    }
    double maxSize = this.srcImage.getWidth();
    if (maxSize < this.srcImage.getHeight()) {
      maxSize = this.srcImage.getHeight();
    }
    double zoomRatio = (double) zoomW / (double) maxSize;
    int zoomH = (int) (this.srcImage.getHeight() * zoomRatio);
    ScaleImage scaleObj = new ScaleImage();
    if (zoomRatio < 1) {
      this.srcImage = scaleObj.imageZoomOutNoLimit(this.srcImage, zoomW, zoomH);
    }
    //放大(补白)

    BufferedImage image = new BufferedImage(zoomW, zoomW,
        BufferedImage.TYPE_INT_RGB);
    Graphics2D g = image.createGraphics();
    g.setColor(Color.white);
    g.fillRect(0, 0, zoomW, zoomW);
    int fillx = (zoomW - this.srcImage.getWidth()) / 2;
    int filly = (zoomW - this.srcImage.getHeight()) / 2;
    if (fillx < 0)
      fillx = 0;
    if (filly < 0)
      filly = 0;
    g.drawImage(this.srcImage, null, fillx, filly);
    g.dispose();
    this.srcImage = image;
    File destFile = new File(srcImagePath);
    ImageIO.write(this.srcImage, fileSufix, destFile);
  }

  /**
   * 执行图片的剪裁处理
   * @param x
   * @param y
   * @param width
   * @param height
   */
  public void execCut(int x, int y, int width, int height) {
    if (this.srcImage == null || x == 0 || y == 0 || width == 0 || height == 0) {
      return;
    }
    this.srcImage = this.srcImage.getSubimage(x, y, width, height);
  }

  /**
   * 获取剪切后要保存的文件名
   * @return
   */
  public String GetSaveFullPath() {
    return "";
  }

  /**
   * 执行图片的保存
   * @param saveFile
   */
  public void saveImage(String saveFile) throws Exception {
    File destFile = new File(saveFile + "." + fileSufix);
    ImageIO.write(this.srcImage, fileSufix, destFile);
  }

  public void saveImageNoSuffix(String saveFile) throws Exception {
    File destFile = new File(saveFile);
    ImageIO.write(this.srcImage, fileSufix, destFile);
  }
}
