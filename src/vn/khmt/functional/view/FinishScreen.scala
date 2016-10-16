package vn.khmt.functional.view

import java.awt.CardLayout
import javax.swing._
import java.awt._
import java.awt.event._

class FinishScreen(scores: String, best: String) extends JPanel {

  val img: ImageIcon = new ImageIcon(getClass getResource ("/gameover.PNG"))
  setLayout(new CardLayout)

  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)
    
    g drawImage(img.getImage(), -10, -13, null)
    g setColor(Color.RED)
    g setFont(new Font("VNI-times", Font.BOLD, 29))
    g drawString(scores, 290, 140)
    g drawString(best, 290, 190)
  }
}