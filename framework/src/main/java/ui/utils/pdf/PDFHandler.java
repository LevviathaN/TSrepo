package ui.utils.pdf;

import com.testautomationguru.utility.CompareMode;
//import com.testautomationguru.utility.PDFUtil;
import ui.utils.Reporter;
import ui.utils.pdf.PDFUtil;
import ui.utils.bpp.PreProcessFiles;

import java.awt.*;
import java.io.IOException;


public class PDFHandler {

    private static final String fileUploadPath = PreProcessFiles.QUARK_FILES_FOLDER_PATH;
    static PDFUtil pdfUtil = new PDFUtil();

    public static boolean checkPDF(String file1, String file2) throws IOException {
        String fileOne = fileUploadPath + "/" + file1;
        String fileTwo = fileUploadPath + "/" + file2;
        boolean comparison = true;

        //pdfUtil.enableLog();
        pdfUtil.compareAllPages(true);
        pdfUtil.setCompareMode(CompareMode.TEXT_MODE);

        comparison = pdfUtil.compare(fileOne, fileTwo);
        if (comparison != true) {
            pdfUtil.setCompareMode(CompareMode.VISUAL_MODE);
            pdfUtil.highlightPdfDifference(true);
            pdfUtil.highlightPdfDifference(Color.RED);
            pdfUtil.setImageDestinationPath(Reporter.getQuarkImagesFolder());
            pdfUtil.compare(fileOne, fileTwo);
        } else {
            Reporter.log("Text comparison for PDF files is completed successfully! No differences found!");
        }

        return comparison;
    }

    public static String getPageCount(String fileName) throws IOException {
        return String.valueOf(pdfUtil.getPageCount(fileUploadPath + "/" + fileName));
    }

    public static boolean checkPDFPAges(String file1, String file2, int beginPage, int endPage) throws IOException {
        String fileOne = fileUploadPath + "/" + file1;
        String fileTwo = fileUploadPath + "/" + file2;
        boolean comparison = true;

        //pdfUtil.enableLog();
        pdfUtil.compareAllPages(true);
        pdfUtil.setCompareMode(CompareMode.VISUAL_MODE);
        pdfUtil.highlightPdfDifference(true);
        pdfUtil.highlightPdfDifference(Color.RED);
        pdfUtil.setImageDestinationPath(Reporter.getQuarkImagesFolder());
        comparison = pdfUtil.compare(fileOne, fileTwo, beginPage, endPage);

        return comparison;
    }
}
