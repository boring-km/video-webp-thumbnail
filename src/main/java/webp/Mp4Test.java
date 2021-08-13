package webp;

import java.io.File;

public class Mp4Test {

    /**
     * 썸네일을 추출하는 메소드
     *
     * @param source mp4 file.
     */
    public void getThumbnail(File source) throws Exception {
        double plusSize = 0.1;
        int threadSize = 32;

        VideoThread[] videoThread = new VideoThread[threadSize];

        for(int i = 0; i < videoThread.length; i++) {
            videoThread[i] = new VideoThread(source, threadSize, i, plusSize);
            videoThread[i].start();
        }

        boolean runFlag = true;
        while(runFlag) {
            Thread.sleep(1000);

            runFlag = false;
            for(int i = 0; i < threadSize; i++) {
                if(videoThread[i].isAlive())
                    runFlag = true;
            }
        }
    }
}