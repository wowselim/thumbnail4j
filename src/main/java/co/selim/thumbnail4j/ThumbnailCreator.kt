package co.selim.thumbnail4j

import java.io.InputStream

fun InputStream.createThumbnail(px: Int): ByteArray {
    val command = listOf("convert",
            "-thumbnail",
            px.toString(),
            "-",
            "-")

    val convertProcess = ProcessBuilder(command)
            .redirectError(ProcessBuilder.Redirect.INHERIT)
            .start()

    this.copyTo(convertProcess.outputStream)
    return convertProcess.inputStream.readBytes()
}