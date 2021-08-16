package webp;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.jcodec.api.FrameGrab;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;

public class VideoCaptureThread extends Thread {
    private final int threadNo;
    private final int threadSize;
    private final double plusSize;
    private final File source;
    private final int imageCount;

    public VideoCaptureThread(File source, int threadSize, int threadNo, double plusSize, int imageCount) {
        this.source = source;
        this.threadSize = threadSize;
        this.threadNo = threadNo;
        this.plusSize = plusSize;
        this.imageCount = imageCount;
    }

    public void run() {
        try {
            FrameGrab grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(source));

            for(int m = 0; m < imageCount; m++) {
                if(m % threadSize == threadNo) {
                    double startSec = m * plusSize;
                    System.out.println(threadNo + " " + startSec);

                    int frameCount = 1;
                    grab.seekToSecondPrecise(startSec);

                    for (int i=0; i < frameCount; i++) {
                        Picture picture = grab.getNativeFrame();

                        //for JDK (jcodec-javase)
                        BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);
                        ImageIO.write(bufferedImage, "png",
                                new File("./images/" + m + ".png"));
                    }
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
