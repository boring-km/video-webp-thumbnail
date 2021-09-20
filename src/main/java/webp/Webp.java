package webp;

import java.io.File;

public class Webp {

    public void encodeToAnimatedWebp(File source, String targetPath, int second, int threadSize) throws Exception {
        int seconds = second * 10;
        System.out.println("초: " + second);
        Mp4Video.makeThumbnails(source, threadSize, seconds);
        int fileCount = (int) (second / 0.4);
        String[] imageFilePaths = new String[fileCount];
        if (System.getProperty("os.name").contains("Windows")) {
            for (int i = 0; i < fileCount; i++) {
                imageFilePaths[i] = ".\\images\\" + (i+1) + ".png";
            }
        } else {
            for (int i = 0; i < fileCount; i++) {
                imageFilePaths[i] = "./images/" + (i+1) + ".png";
            }
        }
        String[] webpImages = LoadLibWebp.saveImagesToWebpImages(imageFilePaths);
        LoadLibWebp.saveImagesToAnimatedWebp(webpImages, 400, targetPath);
    }
}