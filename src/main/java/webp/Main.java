package webp;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
        String videoPath = "./src/main/resources/sample-mp4-file.mp4";
        File source = new File(videoPath);
        Mp4Video.makeThumbnails(source, 0.1, 4, 120);
        LoadLibWebp.saveImagesToAnimatedWebp(new String[]{}, 40, "");
    }
}