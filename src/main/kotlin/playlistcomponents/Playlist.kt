package playlistcomponents

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File
import java.nio.file.Path

const val SEPARATOR = '\t'

class Playlist(private val path: Path) {

    private lateinit var categories: Set<String>

    companion object {
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

    fun checkIfEmpty() {
        val playlistData = readPlaylistData()
        when (playlistData.isNotEmpty()) {
            true -> createPlaylist()
            false -> logger.error("Playlist is empty!")
        }
    }

    private fun createPlaylist() {
        val playlistData = readPlaylistData()
        categories = extractSongCategories(playlistData.first())
        val songs = playlistData.toList().mapIndexed { index, song ->
            song.toSong().takeIf { index != 0 }
        }
    }

    private fun String.toSong(): Song = Song(categories.zip(split(SEPARATOR)).toMap())

    private fun extractSongCategories(categoriesInFile: String) = categoriesInFile.split(SEPARATOR).toSet()


    private fun readPlaylistData(): Set<String> {
        return try {
            File(path.toUri()).useLines { lines -> lines.map { it } }.toSet()
        } catch (e: Exception) {
            logger.error("Could not read file.", e)
            emptySet()
        }

    }
}