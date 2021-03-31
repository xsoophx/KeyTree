package playlistcomponents

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File
import java.nio.file.Path

const val SEPARATOR = '\t'

class Playlist(path: Path) {
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(Playlist::class.java)

        private fun readPlaylistData(path: Path): List<String> {
            return File(path.toUri()).useLines(charset = Charsets.UTF_8, Sequence<String>::toList)
        }
    }

    private val categories: Set<String>

    private val songs: List<Song>

    init {
        val lines = readPlaylistData(path)

        categories = lines.first().splitToSequence(SEPARATOR).toSet()
        logger.info("Loaded ${categories.size} categories.")

        songs = lines.asSequence()
            .drop(1)
            .map(this::makeSong)
            .toList()
        logger.info("Loaded ${songs.size} songs.")
    }

    private fun makeSong(line: String): Song = Song(categories.zip(line.split(SEPARATOR)).toMap())

    fun isEmpty(): Boolean = categories.isEmpty()
}