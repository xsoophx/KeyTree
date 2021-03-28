package playlistComponents

import org.slf4j.LoggerFactory
import java.io.File
import java.lang.Exception
import java.nio.file.Path

class InputPlaylistData(private val path: Path) {
    companion object{
        val logger = LoggerFactory.getLogger(this::class.java)
    }

    fun readPlaylistData() {
        return try {
            val lineList = File(path.toUri()).useLines { lines -> lines.map { it } }
        }
        catch (e: Exception){
            logger.error("Something went wrong.")
        }

    }
}