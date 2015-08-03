package com.ga.click.util;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class BuffImage {

  public static BufferedImage resize(BufferedImage source, int targetW, int targetH) {
    // targetW��targetH�ֱ��ʾĿ�곤�Ϳ�
    int type = source.getType();
    BufferedImage target = null;
    double sx = (double) targetW / source.getWidth();
    double sy = (double) targetH / source.getHeight();
    // ������ʵ����targetW��targetH��Χ��ʵ�ֵȱ����š��������Ҫ�ȱ�����
    // �������if else���ע�ͼ���
    /*
     * if(sx>sy) { sx = sy; targetW = (int)(sx * source.getWidth()); }else{ sy = sx; targetH = (int)(sy *
     * source.getHeight()); }
     */
    if (type == BufferedImage.TYPE_CUSTOM) { // handmade
      ColorModel cm = source.getColorModel();
      WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
      boolean alphaPremultiplied = cm.isAlphaPremultiplied();
      target = new BufferedImage(cm, raster, alphaPremultiplied, null);
    } else
      target = new BufferedImage(targetW, targetH, type);
    Graphics2D g = target.createGraphics();
    g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
    g.dispose();
    return target;
  }

  public static void saveImageAsJpg(String fromFileStr, String saveToFileStr, int width, int hight) throws Exception {
    new ScaleImage().saveImageAsJpg(fromFileStr, saveToFileStr, width, hight);
  }
}