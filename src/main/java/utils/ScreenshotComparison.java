package utils;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

import static utils.ProductSync.reporter;


public class ScreenshotComparison {


    static String TARGET_FOLDER = "target";


    public static Screenshot getExpectedScreenshot(String name) throws IOException {
        Screenshot file = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(DriverProvider.instance.get());
        ImageIO.write(file.getImage(), "PNG", new File("src/main/resources/data/production/screenshot" + File.separator + name + ".png"));
        reporter.info("got expected");
        return file;
    }

    public static Screenshot getActualScreenshot() throws IOException {
        String name = "actual.png";
        Screenshot file = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(2000))
                .takeScreenshot(DriverProvider.instance.get());
        ImageIO.write(file.getImage(), "PNG", new File(TARGET_FOLDER + File.separator + name));
        reporter.info("got actual");
        return file;
    }

    public static boolean compare() throws IOException {
        reporter.info("comparing");
        String name = "result.png";
        BufferedImage actual = ImageIO.read(new File(TARGET_FOLDER + File.separator + "actual.png"));
        BufferedImage expected = ImageIO.read(new File(TARGET_FOLDER + File.separator + "expected.png"));
        ImageDiff diff = new ImageDiffer().makeDiff(actual, expected);
        return ImageIO.write(diff.getMarkedImage(), "PNG", new File(TARGET_FOLDER + File.separator + name));
    }

}
