package webp;

import java.io.File;

public class Main {

    // TODO JavaFX로 Client 프로그램 하나 만들어보기
    public static void main(String[] args) throws Exception {
        String videoPath = "./src/main/resources/sample-mp4-file.mp4";
        File source = new File(videoPath);
        int seconds = 12 * 10;
        Mp4Video.makeThumbnails(source, 0.1, 4, seconds);
        String[] imageFilePaths = new String[seconds];
        if (System.getProperty("os.name").contains("Windows")) {
            for (int i = 0; i < seconds; i++) {
                imageFilePaths[i] = ".\\images\\" + i + ".png";
            }
            LoadLibWebp.saveImagesToAnimatedWebp(imageFilePaths, 1200, ".\\result.webp");
        } else {
            for (int i = 0; i < seconds; i++) {
                imageFilePaths[i] = "./images/" + i + ".png";
            }
            LoadLibWebp.saveImagesToAnimatedWebp(imageFilePaths, 1200, "./result.webp");
        }


    }
}