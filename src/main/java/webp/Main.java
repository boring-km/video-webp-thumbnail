package webp;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
        Mp4Test mp4Test = new Mp4Test();
        VideoAccess videoAccess = new VideoAccess();
        File source = videoAccess.getSampleVideo();

        long startTime = System.currentTimeMillis();
        mp4Test.getThumbnail(source);
        long endTime = System.currentTimeMillis();
        System.out.println((endTime-startTime) + "(ms)");

    }
}