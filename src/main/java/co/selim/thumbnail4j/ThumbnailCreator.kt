package co.selim.thumbnail4j

import java.io.InputStream

/**
 * Creates a thumbnail image of the given size. The aspect ratio will be preserved.
 */
fun InputStream.createThumbnail(px: Int): ByteArray {
    val command = listOf("convert",
            "-thumbnail",
            px.toString(),
            "-",
            "-")

    val convertProcess = ProcessBuilder(command)
            .redirectError(ProcessBuilder.Redirect.INHERIT)
            .start()

    convertProcess.outputStream.use {
        this.copyTo(it)
    }

    return convertProcess.inputStream.use {
        it.readBytes()
    }
}