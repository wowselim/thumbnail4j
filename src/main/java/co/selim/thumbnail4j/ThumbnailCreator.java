package co.selim.thumbnail4j;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/**
 * Utility class that can be used to create thumbnail images.
 */
public class ThumbnailCreator {
    private static final List<String> THUMBNAIL_COMMANDS = Arrays.asList("convert", "-thumbnail");
    private static final ProcessBuilder processBuilder = new ProcessBuilder();

    /**
     * Returns a {@link CompletableFuture} that, on completion, returns a byte array representing the thumbnail image.
     * The original aspect ratio will be preserved.
     *
     * @param px      number of pixels on the longest side
     * @param fileUrl url pointing to a local file
     * @return a future that, on completion, returns a byte array representing the thumbnail image.
     */
    public static CompletableFuture<byte[]> createThumbnail(int px, URL fileUrl) {
        return CompletableFuture.supplyAsync(() -> {
            ByteArrayOutputStream thumbnailBytes = new ByteArrayOutputStream();
            try {
                List<String> command = new ArrayList<>(THUMBNAIL_COMMANDS);
                command.add(String.valueOf(px));
                command.add(Paths.get(fileUrl.toURI()).toString());
                command.add("-");
                Process convertProcess = processBuilder.command(command)
                        .redirectError(ProcessBuilder.Redirect.INHERIT)
                        .start();

                try (InputStream processInputStream = convertProcess.getInputStream()) {
                    processInputStream.transferTo(thumbnailBytes);
                }

                return thumbnailBytes.toByteArray();
            } catch (Exception e) {
                throw new CompletionException(e);
            }
        });
    }
}
