package vn.khmt.functional.view

import java.awt._
import java.io._
import java.net._
import javax.swing._

import vn.khmt.functional.control._
import java.awt.event._

class MainStart extends JPanel {

  class MyActionListener(f: ActionEvent => Unit) extends ActionListener {
    override def actionPerformed(event: ActionEvent) = { f(event) }
  }

  var logoImg: ImageIcon = new ImageIcon(getClass.getResource("/logo.PNG"))
  var playImg: ImageIcon = new ImageIcon(getClass.getResource("/play.PNG"))
  var rateImg: ImageIcon = new ImageIcon(getClass.getResource("/highscore.PNG"))
  var topImg: ImageIcon = new ImageIcon(getClass.getResource("/rate.PNG"))
  var registerImg: ImageIcon = new ImageIcon(getClass.getResource("/login-64.PNG"))
  var logo: JLabel = new JLabel(logoImg)
  var play: JButton = new JButton(playImg)
  var rate: JButton = new JButton(rateImg)
  var top: JButton = new JButton(topImg)
  var register: JButton = new JButton(registerImg)

  setLayout(null)
  logo setBounds (12, 100, logoImg.getIconWidth(), logoImg.getIconHeight())
  play setBounds (92, 315, playImg.getIconWidth(), playImg.getIconHeight())
  rate setBounds (66, 440, rateImg.getIconWidth(), rateImg.getIconHeight())
  top setBounds (226, 440, topImg.getIconWidth(), topImg.getIconHeight())
  register setBounds (350, 10, registerImg.getIconWidth(), registerImg.getIconHeight())

  play addActionListener (new MyActionListener(event => showMainPlayScr(event)))

  def showMainPlayScr(event: ActionEvent): Unit = { 
     var mainPlayCtrl = new MainPlayControl
     mainPlayCtrl.readData
     mainPlayCtrl.actionPlay
  }

  rate addActionListener (new MyActionListener(event => JOptionPane.showMessageDialog(this, "Coming soon !!!! =)) ")))
  top addActionListener (new MyActionListener(event => JOptionPane.showMessageDialog(this, "Coming soon !!!! =)) ")))
  register addActionListener (new MyActionListener(event => JOptionPane.showMessageDialog(this, "Coming soon !!!! =)) ")))

  add(logo)
  add(play)
  add(top)
  add(rate)
  add(register)
}