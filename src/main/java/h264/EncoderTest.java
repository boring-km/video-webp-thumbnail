package h264;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.codecs.h264.H264Encoder;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;

import java.io.File;
import java.io.IOException;

// https://github.com/jcodec/jcodec/blob/master/src/test/java/org/jcodec/codecs/h264/H264EncoderTest.java
public class EncoderTest {
    public static void main(String[] args) {
         H264Encoder encoder = H264Encoder.createH264Encoder();
        getAllFrames(new File("./videos/1000000037047_DRM_CLEAR.MP4"));

    }

    private static void getAllFrames(File file) {
        FrameGrab grab = null;
        try {
            grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(file));
            Picture picture;
            while (null != (picture = grab.getNativeFrame())) {
                System.out.println(picture.getWidth() + "x" + picture.getHeight() + " " + picture.getColor());
            }
        } catch (IOException | JCodecException e) {
            e.printStackTrace();
        }
    }
}
