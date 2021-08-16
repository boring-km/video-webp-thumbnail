package webp;

import java.io.File;

public class Mp4Video {

    /**
     * 썸네일을 추출하는 메소드
     *
     * @param source mp4 file.
     */
    public static void makeThumbnails(File source, double timeInterval, int threadSize, int imageCount) throws Exception {

        VideoCaptureThread[] videoCaptureThread = new VideoCaptureThread[threadSize];

        for(int num = 0; num < videoCaptureThread.length; num++) {
            videoCaptureThread[num] = new VideoCaptureThread(source, threadSize, num, timeInterval, imageCount);
            videoCaptureThread[num].start();
        }

        boolean runFlag = true;
        while(runFlag) {
            Thread.sleep(1000);

            runFlag = false;
            for(int i = 0; i < threadSize; i++) {
                if (videoCaptureThread[i].isAlive()) {
                    runFlag = true;
                    break;
                }
            }
        }
    }
}