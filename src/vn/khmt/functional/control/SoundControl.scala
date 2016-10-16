package vn.khmt.functional.control

import javafx.embed.swing.JFXPanel
import java.io.File
import javafx.scene.media._

object SoundControl {

  def soundCtrl(): Unit = {
    val fxPanel = new JFXPanel
    val soundFile: String = new File("button-09.mp3") toURI () toString ()
    val mediaPlayer = new MediaPlayer(new Media(soundFile))
    mediaPlayer.play()
    }
}