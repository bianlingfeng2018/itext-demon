package edu.fudan.itextdemon.myDemon;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTextAndImageTest {
    @Test
    public void imageTest() throws IOException {  // 获取PDF文本内容和图片及坐标
        String SRC = "/Users/bianlingfeng/IdeaProjects/itext-demon/Whitepaper_pdf2Data_2017.pdf";

        PdfReader reader = new PdfReader(SRC);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        // 解析PDF，并处理里面的文字
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            // 新建一个TestRenderListener对象，该对象实现了RenderListener接口，作为处理PDF的主要类
            MyTextAndImageRenderListener listener = new MyTextAndImageRenderListener();
            System.out.println("page " + i + " ------>");
            parser.processContent(i, listener);
            // 获取文字的矩形边框
//            List<Rectangle2D.Float> rectText = listener.rectText;
//            List<String> textList = listener.textList;
//            List<Float> listY = listener.listY;
//            List<Map<String, Rectangle2D.Float>> list_text = listener.row_text_rect;
//            List<byte[]> arraysByte = listener.arraysByte;
            // 图片
            List<float[]> arrays = listener.arrays; // 图片坐标
//            System.out.println("this page's images startPoint coordinate vector = " + arrays);
            List<byte[]> arraysByte = listener.arraysByte;  // 图片

            for (int j = 0; j < arraysByte.size(); j++) {
                FileOutputStream out = new FileOutputStream("/Users/bianlingfeng/IdeaProjects/itext-demon/img_" + i + "-" + j + ".jpg");
                out.write(arraysByte.get(j));
                out.flush();
                out.close();
            }
            // 文本
//            for (int j = 0; j < list_text.size(); j++) {
//                Map<String, Rectangle2D.Float> map = list_text.get(j);
//                System.out.println("list_text:" + list_text.size());
//                System.out.println("map:" + map.size());
//                for (Map.Entry<String, Rectangle2D.Float> entry : map.entrySet()) {
//                    System.out.println(i + ":" + entry.getKey() + "::" + entry.getValue().y);
//                }
//            }
//            String str = listener.textStr;
//            FileOutputStream fos = new FileOutputStream(new File("/Users/bianlingfeng/IdeaProjects/itext-demon/text_" + i + ".txt"));
//            fos.write(str.getBytes());
//            fos.flush();
//            fos.close();
            TextExtractionStrategy text = new SimpleTextExtractionStrategy();  // 文本处理
            String t = PdfTextExtractor.getTextFromPage(reader, i, text);
            FileOutputStream fos = new FileOutputStream(new File("/Users/bianlingfeng/IdeaProjects/itext-demon/text_" + i + ".txt"));
            fos.write(t.getBytes());
            fos.flush();
            fos.close();
        }
        Rectangle rec = reader.getBoxSize(1, "trim");  // 第一页矩形大小
        System.out.println("page 1, rectangle = " + rec);
    }
}
