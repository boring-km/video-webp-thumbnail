package webp;

import java.awt.*;
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
    private final File source;
    private final int imageCount;

    public VideoCaptureThread(File source, int threadSize, int threadNo, int imageCount) {
        this.source = source;
        this.threadSize = threadSize;
        this.threadNo = threadNo;
        this.imageCount = imageCount;
    }

    public void run() {
        try {
            FrameGrab grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(source));

            for(int m = 1; m < imageCount+1; m++) {
                if(m % threadSize == threadNo) {
                    double plusSize = 0.1;
                    double startSec = m * plusSize;

                    int frameCount = 1;
                    grab.seekToSecondPrecise(startSec);

                    for (int i=0; i < frameCount; i++) {
                        Picture picture = grab.getNativeFrame();
                        if (m % 4 == 0) {
                            //for JDK (jcodec-javase)
                            BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);
                            BufferedImage resizedImage = resize(bufferedImage, (int) (bufferedImage.getWidth() / 4.4), (int) (bufferedImage.getHeight() / 4.4));
                            ImageIO.write(resizedImage, "png",
                                    new File("./images/" + (int)(m/4) + ".png"));
                        }

                    }
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private BufferedImage resize(BufferedImage inputImage, int width, int height) {
        BufferedImage outputImage =
                new BufferedImage(width, height, inputImage.getType());

        Graphics2D graphics2D = outputImage.createGraphics();
        graphics2D.drawImage(inputImage, 0, 0, width, height, null);
        graphics2D.dispose();

        return outputImage;
    }
}
