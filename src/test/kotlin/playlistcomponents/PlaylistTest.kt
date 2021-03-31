package playlistcomponents

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.io.File
import java.io.FileNotFoundException
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Stream
import kotlin.test.assertTrue


class PlaylistTest {
    companion object {
        @JvmStatic
        @Suppress("unused")
        fun validPaths(): Stream<Path> = Stream.of(
            Path.of("test.txt"),
            Path.of("dummy.txt"),
            Path.of("./foo.txt"),
            Path.of("../born.txt"),
            Files.createTempFile("", ".conf")
        )
    }


    @Test
    fun `separator holds correct character`() {
        val tab = 9
        val tabAsString = tab.toChar()
        assertThat(SEPARATOR).isEqualTo(tabAsString)
    }

    @ParameterizedTest
    @MethodSource("validPaths")
    fun `reads paths and empty file correctly`(path: Path) {
        val file = File(path.toUri()).apply { createNewFile() }
        try {
            assertThat(file.readLines()).isEmpty()
        } finally {
            Files.deleteIfExists(path)
        }
    }

    @Test
    fun `reading from invalid path does not work and playlist is empty`() {
        val testPath = Path.of("bull/shit.txt")

        assertThrows<FileNotFoundException> {
            Playlist(testPath).apply { assertTrue(isEmpty()) }
        }
    }
}
