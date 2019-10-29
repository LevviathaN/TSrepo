package utils;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


public class ScreenshotComparison {

    static ReporterManager reporter = ReporterManager.Instance;


    static String TARGET_FOLDER = "target";
    static String REPORT_FOLDER = "target/Report";
    static String STAGING_SCREEN = "src/main/resources/data/staging/screenshot";
    static String PROD_SCREEN = "src/main/resources/data/production/screenshot";


    public static Screenshot getExpectedScreenshot(String name) throws IOException {
        Screenshot file = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(DriverProvider.instance.get());
        ImageIO.write(file.getImage(), "PNG", new File("src/main/resources/data/production/screenshot" + File.separator + name + ".png"));
        reporter.info("got expected");
        return file;
    }

    public static Screenshot getActualScreenshot(String name) throws IOException {
        Screenshot file = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(2000))
                .takeScreenshot(DriverProvider.instance.get());
        ImageIO.write(file.getImage(), "PNG", new File(TARGET_FOLDER + File.separator + name + ".png"));
        reporter.info("got actual");
        return file;
    }

    public static boolean compare(String page, String actualscreenshot, String name) throws IOException {
        BufferedImage expected;
        reporter.info("comparing");

        if (FileIO.getConfigProperty("EnvType").equals("PROD")) {
            expected = ImageIO.read(new File(PROD_SCREEN + File.separator + page + ".png"));
        }else {
            expected = ImageIO.read(new File(STAGING_SCREEN + File.separator + page + ".png"));
        }

        BufferedImage actual = ImageIO.read(new File(TARGET_FOLDER + File.separator + actualscreenshot + ".png"));
        ImageDiff diff = new ImageDiffer().makeDiff(actual, expected);
        return diff.hasDiff();
    }

    public static void overwriteScreenshot(String page, String actualscreenshot, String name) throws IOException {
        reporter.info("Overwriting");
        BufferedImage expected;
        if (FileIO.getConfigProperty("EnvType").equals("PROD")) {
            expected = ImageIO.read(new File(PROD_SCREEN + File.separator + page + ".png"));
        }else {
            expected = ImageIO.read(new File(STAGING_SCREEN + File.separator + page + ".png"));
        }

        BufferedImage actual = ImageIO.read(new File(TARGET_FOLDER + File.separator + actualscreenshot + ".png"));
        ImageDiff diff = new ImageDiffer().makeDiff(actual, expected);
        ImageIO.write(diff.getMarkedImage(), "PNG", new File(REPORT_FOLDER + File.separator + name));
        ImageIO.write(diff.getMarkedImage(), "PNG", new File(TARGET_FOLDER + File.separator + name));

    }

}
