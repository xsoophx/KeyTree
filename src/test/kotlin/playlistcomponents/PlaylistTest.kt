package playlistcomponents

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test


class PlaylistTest {
    companion object{
        val playlistData = Playlist
    }

    @Test
    fun `separator holds correct character`(){
        val tab = 9
        val tabAsString = tab.toChar()
        assertThat(SEPARATOR).isEqualTo(tabAsString)
    }

    @Test
    fun `reads paths correctly`(){

    }
}