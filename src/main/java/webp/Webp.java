package webp;

import java.io.File;

public class Webp {

    public void encodeToAnimatedWebp(File source, String targetPath, int second, double timeInterval, int threadSize) throws Exception {
        int seconds = second * 10;
        Mp4Video.makeThumbnails(source, timeInterval, threadSize, seconds);
        String[] imageFilePaths = new String[seconds];
        if (System.getProperty("os.name").contains("Windows")) {
            for (int i = 0; i < seconds; i++) {
                imageFilePaths[i] = ".\\images\\" + i + ".png";
            }
        } else {
            for (int i = 0; i < seconds; i++) {
                imageFilePaths[i] = "./images/" + i + ".png";
            }
        }
        LoadLibWebp.saveImagesToAnimatedWebp(imageFilePaths, 1200, targetPath);
    }
}