package co.selim.thumbnail4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility class that can be used to create thumbnail images.
 */
public class ThumbnailCreator {
    private static final List<String> THUMBNAIL_COMMANDS = List.of("convert", "-thumbnail");

    /**
     * Returns a byte array representing the thumbnail image.
     * This method calls the command line utility 'convert' to create a thumbnail image of the specified dimension.
     * The original aspect ratio will be preserved.
     *
     * @param px      number of pixels on the longest side
     * @param fileUri uri pointing to a local file
     * @throws IOException if an IO related error occurs
     */
    public byte[] createThumbnail(int px, URI fileUri) throws IOException {
        ByteArrayOutputStream thumbnailBytes = new ByteArrayOutputStream();
        List<String> command = new ArrayList<>(THUMBNAIL_COMMANDS);
        Collections.addAll(command, String.valueOf(px), Paths.get(fileUri).toString(), "-");

        Process convertProcess = new ProcessBuilder(command)
                .redirectError(ProcessBuilder.Redirect.INHERIT)
                .start();

        try (InputStream processInputStream = convertProcess.getInputStream()) {
            processInputStream.transferTo(thumbnailBytes);
        }

        return thumbnailBytes.toByteArray();
    }
}
