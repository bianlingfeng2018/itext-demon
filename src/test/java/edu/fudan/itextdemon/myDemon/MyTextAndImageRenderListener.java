package edu.fudan.itextdemon.myDemon;

import com.itextpdf.text.pdf.parser.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyTextAndImageRenderListener implements RenderListener {
    // 图片坐标
    List<float[]> arrays = new ArrayList<float[]>();

    // 图片
    List<byte[]> arraysByte = new ArrayList<byte[]>();

    // 图片文件类型
    PdfImageObject.ImageBytesType imgType;

    // 图片文件扩展名
    String imgExtention;

    // 文字
//    String textStr = "";

    @Override
    public void beginTextBlock() {
        System.out.println("start processing pdf...");
    }

    @Override
    public void renderText(TextRenderInfo renderInfo) {
//        TextExtractionStrategy strategy = new SimpleTextExtractionStrategy();  // 利用已经实现好的文字处理策略
//        strategy.renderText(renderInfo);
//        textStr += strategy.getResultantText();
    }

    @Override
    public void endTextBlock() {
        System.out.println("pdf done!");
    }

    @Override
    public void renderImage(ImageRenderInfo renderInfo) {
        PdfImageObject image = null;
        try {
            image = renderInfo.getImage();
            PdfImageObject.ImageBytesType imageBytesType = image.getImageBytesType();  // 文件类型
            imgType = imageBytesType;
            String fileType = image.getFileType();  // 文件扩展名
            imgExtention = fileType;
//            BufferedImage bufferedImage = image.getBufferedImage();  // java.awt.image.BufferedImage
            byte[] imageAsBytes = image.getImageAsBytes();  // 获得图片
            arraysByte.add(imageAsBytes);
            float[] resu = new float[3];  // 获得图片坐标
            // 0=>x;1=>y;2=>z;
            // z的值始终为1
            resu[0] = renderInfo.getStartPoint().get(0);
            resu[1] = renderInfo.getStartPoint().get(1);
            resu[2] = 1;
            arrays.add(resu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
