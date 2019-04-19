package co.selim.thumbnail4j;

import java.io.InputStream;

/**
 * Utility class that can be used to create thumbnail images.
 */
public class ThumbnailCreator {
    /**
     * Returns a byte array representing the thumbnail image.
     * This method calls the command line utility 'convert' to create a thumbnail image of the specified dimension.
     * The original aspect ratio will be preserved. The input stream will not be closed.
     *
     * @param px               number of pixels on the longest side
     * @param imageInputStream input stream containing the original image
     * @return a byte array representing the thumbnail image
     */
    public static byte[] createThumbnail(int px, InputStream imageInputStream) {
        return ThumbnailCreatorKt.createThumbnail(imageInputStream, px);
    }
}
