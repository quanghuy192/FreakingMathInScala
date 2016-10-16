package vn.khmt.functional.view

import javax.swing._

object Main {
  val myFrame: JFrame = new JFrame
  var mainStart: MainStart = new MainStart

  def main(args: Array[String]) {
    try {
      UIManager setLookAndFeel ("com.jtattoo.plaf.texture.TextureLookAndFeel")
    } catch {
      case ex: Exception => {
        ex printStackTrace ()
      }
    }

    myFrame setSize (435, 680)
    myFrame setVisible (true)
    myFrame setResizable (false)
    myFrame setLocationRelativeTo (null)
    myFrame setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE)
    myFrame getContentPane () add (mainStart)
  }
}