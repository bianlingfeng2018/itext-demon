package edu.fudan.itextdemon.utils;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItextPdfUtil {
    private List<String> imgPaths = new ArrayList<>();
    private String textPath = "";
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTextPath() {
        return textPath;
    }

    public void setTextPath(String textPath) {
        this.textPath = textPath;
    }

    public List<String> getImgPaths() {
        return imgPaths;
    }

    public void setImgPaths(List<String> imgPaths) {
        this.imgPaths = imgPaths;
    }

    public void imageTest(String filePath) throws IOException {  // 获取PDF文本内容和图片及坐标
        String t = "";
        PdfReader reader = new PdfReader(filePath);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        // 新建一个TestRenderListener对象，该对象实现了RenderListener接口，作为处理PDF的主要类
        MyTextAndImageRenderListener listener = new MyTextAndImageRenderListener();
        // 解析PDF
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            parser.processContent(i, listener);
            TextExtractionStrategy text = new SimpleTextExtractionStrategy();  // 文本处理
            t += PdfTextExtractor.getTextFromPage(reader, i, text);
        }
        // 图片
        List<byte[]> arraysByte = listener.arraysByte;  // 图片
        for (int j = 0; j < arraysByte.size(); j++) {
            String imgPath = "/Users/bianlingfeng/VscodeProject/vue-db/src/files/after/img_" + j + ".jpg";
            File file = new File(imgPath);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream out = new FileOutputStream(imgPath);
            out.write(arraysByte.get(j));
            out.flush();
            out.close();
            this.imgPaths.add(imgPath);
        }
        // 文字
        String txtPath = "/Users/bianlingfeng/VscodeProject/vue-db/src/files/after/text_all.txt";
        File file = new File(txtPath);
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fos = new FileOutputStream(txtPath);
        fos.write(t.getBytes());
        fos.flush();
        fos.close();
        this.textPath = txtPath;
        this.content = t;
    }
}
