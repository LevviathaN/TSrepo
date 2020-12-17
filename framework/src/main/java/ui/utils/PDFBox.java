package ui.utils;

import com.testautomationguru.utility.CompareMode;
import com.testautomationguru.utility.PDFUtil;

import java.awt.*;
import java.io.IOException;


public class PDFBox {

    public static void main(String args[]) throws IOException {

        PDFUtil pdfUtil = new PDFUtil();
        String file1 = System.getProperty("user.dir").replace("\\", "/") + "/framework/src/main/resources/BPP-9458_CIMA.pdf";
        String file2 = System.getProperty("user.dir").replace("\\", "/") + "/framework/src/main/resources/CIMA_TEST.pdf";

        // compares the pdf documents and returns a boolean
        // true if both files have same content. false otherwise.
        // Default is CompareMode.TEXT_MODE
        pdfUtil.setCompareMode(CompareMode.VISUAL_MODE);
        pdfUtil.compareAllPages(true);
        pdfUtil.compare(file1, file2);

        //if you need to store the result
        pdfUtil.highlightPdfDifference(true);
        pdfUtil.highlightPdfDifference(Color.RED);
        pdfUtil.setImageDestinationPath(System.getProperty("user.dir").replace("\\", "/") + "/framework/src/main/resources/");
        pdfUtil.compare(file1, file2);
    }
}
