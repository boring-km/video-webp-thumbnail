package webp;

import com.luciad.imageio.webp.WebPWriteParam;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GenerateWebp {
    public static void main(String[] args) throws IOException {

        BufferedImage image = ImageIO.read(new File("/Users/kangmin/dev/images/1.png"));
        // Obtain a WebP ImageWriter instance
        ImageWriter writer = ImageIO.getImageWritersByMIMEType("image/webp").next();

        // Configure encoding parameters
        WebPWriteParam writeParam = new WebPWriteParam(writer.getLocale());
        writeParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        writeParam.setCompressionType(writeParam.getCompressionTypes()[WebPWriteParam.LOSSLESS_COMPRESSION]);

        // Configure the output on the ImageWriter
        writer.setOutput(new FileImageOutputStream(new File("output.webp")));

        // Encode
        writer.write(null, new IIOImage(image, null, null), writeParam);
    }
}
