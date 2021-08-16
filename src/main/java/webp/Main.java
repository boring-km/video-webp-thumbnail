package webp;

import java.io.File;

public class Main {

    // TODO JavaFX로 Client 프로그램 하나 만들어보기
    public static void main(String[] args) throws Exception {
        String videoPath = "./src/main/resources/sample-mp4-file.mp4";
        File source = new File(videoPath);
        Mp4Video.makeThumbnails(source, 0.1, 4, 120);
        String[] imageFilePaths = new String[120];
        for (int i = 0; i < 120; i++) {
            imageFilePaths[i] = "./images/" + i + ".png";
        }
        LoadLibWebp.saveImagesToAnimatedWebp(imageFilePaths, 1200, "./result.webp");
    }
}