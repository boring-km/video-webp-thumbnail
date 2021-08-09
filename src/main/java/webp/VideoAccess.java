package webp;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class VideoAccess {
    public File getSampleVideo() throws URISyntaxException {
        URL url = getClass().getClassLoader().getResource("sample-mp4-file.mp4");
        return Paths.get(url.toURI()).toFile();
    }
}
