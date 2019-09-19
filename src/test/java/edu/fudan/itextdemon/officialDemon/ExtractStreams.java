/*

    This file is part of the iText (R) project.
    Copyright (c) 1998-2016 iText Group NV

*/

/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/30286601/extracting-an-embedded-object-from-a-pdf
 */
package edu.fudan.itextdemon.officialDemon;

//import com.itextpdf.kernel.PdfException;
//import com.itextpdf.kernel.pdf.PdfDocument;
//import com.itextpdf.kernel.pdf.PdfObject;
//import com.itextpdf.kernel.pdf.PdfReader;
//import com.itextpdf.kernel.pdf.PdfStream;
//import com.itextpdf.test.annotations.type.SampleTest;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * itext7 官方demon
 */

//@Category(SampleTest.class)
public class ExtractStreams {
    public static final String DEST = "/Users/bianlingfeng/IdeaProjects/itext-demon/extract_streams%s";
//    public static final String SRC = "/Users/bianlingfeng/IdeaProjects/itext-demon/image.pdf";
    public static final String SRC = "/Users/bianlingfeng/IdeaProjects/itext-demon/Whitepaper_pdf2Data_2017.pdf";

    @BeforeClass
    public static void before() {
        new File(DEST).getParentFile().mkdirs();
    }

    public static void main(String[] args) throws IOException {
        before();
        new ExtractStreams().manipulatePdf();
    }

    @Test
    public void manipulatePdf() throws IOException {
//        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC));
//        PdfObject obj;
//        List<Integer> streamLengths = new ArrayList<>();
//        for (int i = 1; i <= pdfDoc.getNumberOfPdfObjects(); i++) {
//            obj = pdfDoc.getPdfObject(i);
//            if (obj != null && obj.isStream()) {
//                System.out.println("type = " + obj.getType());
//                byte[] b;
//                try {
//                    b = ((PdfStream) obj).getBytes();
//                } catch (PdfException exc) {
//                    b = ((PdfStream) obj).getBytes(false);
//                }
//                System.out.println(b.length);
//                FileOutputStream fos = new FileOutputStream(String.format(DEST, i));
//                fos.write(b);
//
//                streamLengths.add(b.length);
//                fos.close();
//            }
//        }
//        Assert.assertArrayEquals(new Integer[]{30965, 74}, streamLengths.toArray(new Integer[streamLengths.size()]));
//        pdfDoc.close();
    }
}