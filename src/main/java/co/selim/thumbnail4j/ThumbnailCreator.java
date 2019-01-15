package co.selim.thumbnail4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Paths;
import java.util.List;

/**
 * Utility class that can be used to create thumbnail images.
 */
public class ThumbnailCreator {
    /**
     * Returns a byte array representing the thumbnail image.
     * This method calls the command line utility 'convert' to create a thumbnail image of the specified dimension.
     * The original aspect ratio will be preserved.
     *
     * @param px       number of pixels on the longest side
     * @param imageUri uri pointing to a local image
     * @return a byte array representing the thumbnail image
     * @throws IOException if an IO related error occurs
     */
    public static byte[] createThumbnail(int px, URI imageUri) throws IOException {
        ByteArrayOutputStream thumbnailBytes = new ByteArrayOutputStream();
        List<String> command = List.of("convert",
                "-thumbnail",
                String.valueOf(px),
                Paths.get(imageUri).toString(),
                "-");

        Process convertProcess = new ProcessBuilder(command)
                .redirectError(ProcessBuilder.Redirect.INHERIT)
                .start();

        copyAndClose(convertProcess.getInputStream(), thumbnailBytes);

        return thumbnailBytes.toByteArray();
    }

    /**
     * Returns a byte array representing the thumbnail image.
     * This method calls the command line utility 'convert' to create a thumbnail image of the specified dimension.
     * The original aspect ratio will be preserved.
     *
     * @param px               number of pixels on the longest side
     * @param imageInputStream input stream containing the original image
     * @return a byte array representing the thumbnail image
     * @throws IOException if an IO related error occurs
     */
    public static byte[] createThumbnail(int px, InputStream imageInputStream) throws IOException {
        ByteArrayOutputStream thumbnailBytes = new ByteArrayOutputStream();
        List<String> command = List.of("convert",
                "-thumbnail",
                String.valueOf(px),
                "-",
                "-");

        Process convertProcess = new ProcessBuilder(command)
                .redirectError(ProcessBuilder.Redirect.INHERIT)
                .start();

        copyAndClose(imageInputStream, convertProcess.getOutputStream());
        copyAndClose(convertProcess.getInputStream(), thumbnailBytes);

        return thumbnailBytes.toByteArray();
    }

    private static void copyAndClose(InputStream from, OutputStream to) throws IOException {
        try (from; to) {
            from.transferTo(to);
        }
    }
}
