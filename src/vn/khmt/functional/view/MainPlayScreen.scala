package vn.khmt.functional.view

import javax.swing._
import java.awt._

class MainPlayScreen extends JPanel {

  var trueImg: ImageIcon = new ImageIcon(getClass.getResource("/true.PNG"))
  var falseImg: ImageIcon = new ImageIcon(getClass.getResource("/false.PNG"))
  var trueBtn: JButton = new JButton(trueImg)
  var falseBtn: JButton = new JButton(falseImg)
  var progressBar: JProgressBar = new JProgressBar
  var time: JLabel = new JLabel

  val MAX: Int = 30
  val MIN: Int = 0
  var text: String = ""
  var scores: String = "0"

  setLayout(null);
  setFocusable(true);
  progressBar setMaximum (MAX)
  progressBar setMinimum (MIN)
  progressBar setValue (MAX)
  progressBar setBackground (Color.RED)
  progressBar setBounds (67, 100, 300, 10)

  time setForeground (Color.RED)
  time setBounds (200, 70, 300, 10)

  trueBtn setBounds (30, 430, trueImg.getIconWidth(), trueImg.getIconHeight())
  falseBtn setBounds (230, 430, falseImg.getIconWidth(), falseImg.getIconHeight())

  add(trueBtn)
  add(falseBtn)
  add(time)
  add(progressBar)

  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)
    g setColor (Color.RED)
    g setFont (new Font("VNI-times", Font.BOLD, 71))
    g drawString (text, 31, 300)
    g setFont (new Font("VNI-times", Font.BOLD, 41))
    g drawString (scores, 170, 170)
    g setFont (new Font("VNI-times", Font.BOLD, 23))
    g drawString ("Scores :", 20, 170)
  }

}