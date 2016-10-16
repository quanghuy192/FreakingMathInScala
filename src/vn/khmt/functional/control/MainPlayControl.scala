package vn.khmt.functional.control

import java.awt.event._
import java.io._
import java.util._

import javax.swing.JFrame
import vn.khmt.functional._
import vn.khmt.functional.view.FinishScreen
import vn.khmt.functional.entity.Quiz
import vn.khmt.functional.view.MainPlayScreen
import scala.util.control.Breaks

class MainPlayControl extends Runnable {

  var mainPlay: MainPlayScreen = new MainPlayScreen
  var listQuiz: ArrayList[Quiz] = _
  var isPause = false
  var count = 0
  var mFrame: JFrame = new JFrame
  var bestScore: Int = _

  mFrame setSize (435, 680)
  mFrame setVisible (true)
  mFrame setResizable (false)
  mFrame setLocationRelativeTo (null)
  mFrame setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE)
  mFrame getContentPane () add (mainPlay)

  mainPlay.trueBtn addActionListener (new MyListener(event => trueBtnClick()))
  mainPlay.falseBtn addActionListener (new MyListener(event => falseBtnClick()))

  def actionPlay() {
    val threadPlay = new Thread(this).start
    mainPlay.text = listQuiz get (count) getQuestion()
    mainPlay repaint()
  }

  var number1, number2, number3: Int = _
  var newQuiz: Quiz = _
  def readData() {
    listQuiz = new ArrayList[Quiz]
    var rand = new Random

    for (i <- 0 to 100) {
      number1 = rand.nextInt(40);
      number2 = rand.nextInt(37);
      number3 = rand.nextInt(4);

      if (null != getNewQuestion(number1, number2, number3)) {
        newQuiz = new Quiz(getNewQuestion(number1, number2, number3)(1),
          getNewAnswer(getNewQuestion(number1, number2, number3),
            number1, number2))
      }
      listQuiz add(newQuiz)
    }
  }

  def getNewAnswer(string: Array[String], number1: Int, number2: Int): String = {

    (string(2).toInt) match {
      case 0 =>
        return (string(0).toInt == (number1 + number2)).toString
      case 1 =>
        return (string(0).toInt == (number1 - number2)).toString
      case 2 =>
        return (string(0).toInt == ((number1 % 10) * (number2 % 10))).toString
      case 3 =>
        return (number1 % (number2 % 10 + 1) == 0
          && string(0).toInt == (number1 / (number2 % 10 + 1))).toString
    }
    return ""
  }

  var result: StringBuilder = _
  var resultNumber: String = _
  def getNewQuestion(number1: Int, number2: Int, number3: Int): Array[String] = {

    var resultArray = new Array[String](3)
    var random = new Random
    var randomResult = number1 + number2 - number3
    result = new StringBuilder

    number3 match {
      case 0 => {
        if (randomResult % 2 == 0) {
          var resultFake = random.nextInt(50)
          result append (number1) append (" + ") append (number2) append (" = ") append (resultFake)
          resultArray(0) = resultFake toString
        } else {
          resultNumber = (number1 + number2).toString
          result append (number1) append (" + ") append (number2) append (" = ") append (resultNumber)
          resultArray(0) = resultNumber
        }
        resultArray(1) = result.toString
        resultArray(2) = "0"
        return resultArray
      }

      case 1 => {
        if (randomResult % 2 == 0) {
          var resultFake = random.nextInt(30)
          result append (number1) append (" - ") append (number2) append (" = ") append (resultFake)
          resultArray(0) = resultFake toString
        } else {
          resultNumber = (number1 - number2).toString
          result append (number1) append (" - ") append (number2) append (" = ") append (resultNumber)
          resultArray(0) = resultNumber
        }
        resultArray(1) = result.toString
        resultArray(2) = "1"
        return resultArray
      }

      case 2 =>
        if (randomResult % 2 == 0) {
          var resultFake = random.nextInt(50)
          result append (number1 % 10) append (" x ") append (number2 % 10) append (" = ") append (resultFake)
          resultArray(0) = resultFake toString
        } else {
          resultNumber = ((number1 % 10) * (number2 % 10)).toString
          result append (number1 % 10) append (" x ") append (number2 % 10) append (" = ") append (resultNumber)
          resultArray(0) = resultNumber
        }
        resultArray(1) = result.toString
        resultArray(2) = "2"
        return resultArray

      case 3 =>
        if (randomResult % 2 == 0) {
          var resultFake = random.nextInt(20)
          result append (number1) append (" : ") append (number2 % 10) append (" = ") append (resultFake)
          resultArray(0) = String.valueOf(resultFake)
        } else {
          resultNumber = (number1 / (number2 % 10 + 1)).toString
          result append (number1) append (" : ") append (number2 % 10 + 1) append (" = ") append (resultNumber)
          resultArray(0) = resultNumber
        }
        resultArray(1) = result.toString
        resultArray(2) = "3"
        return resultArray
    }
    return new Array[String](0)
  }

  override def run() {
    val loop = new Breaks
    for (i <- 0 to 29) {
      if (isPause) {
        loop.break
      } else {
        mainPlay.time setText((29 - i).toString + " s ")
        mainPlay.progressBar setValue(29 - i)
        try {
          Thread sleep(1000)
        } catch {
          case e: InterruptedException => e.printStackTrace()
        }
      }
    }
    if (isPause == false)
      finish(mainPlay.scores)
  }

  def finish(scores: String): Unit = {

    var file = new File("E:\\best.DAT")
    if (!file.exists()) {
      try {
        file.createNewFile()
        var out = new DataOutputStream(new FileOutputStream(file))
        out writeUTF (count toString)
        bestScore = count
        out close ()
      } catch {
        case e: IOException => e.printStackTrace()
      }
    } else {
      try {
        var in = new DataInputStream(new FileInputStream(file))
        var best = in readUTF()
        if (best.trim().toInt <= count) {
          bestScore = count
          file delete ()
          var newFile = new File("E:\\best.DAT")
          newFile createNewFile ()
          var out = new DataOutputStream(new FileOutputStream(newFile))
          out.writeUTF(count toString)
          out.close()
        } else {
          bestScore = best.toInt
        }
        in close ()
      } catch {
        case e: FileNotFoundException => e.printStackTrace()
        case e: IOException => e.printStackTrace()
      }
    }

    var fn = new FinishScreen(scores, bestScore.toString)
    var finishFrm = new JFrame("Freaking Math")
    finishFrm setSize (396, 328)
    finishFrm setVisible (true)
    finishFrm setResizable (false)
    finishFrm setLocationRelativeTo (null)
    finishFrm setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE)
    finishFrm getContentPane () add (fn)
    mFrame setVisible (false)
  }

  def testQuiz(e: Int): Boolean = {
    if ((e == 1 && listQuiz.get(count).getAnswer().equalsIgnoreCase("true"))
      || (e == 0 && listQuiz.get(count).getAnswer().equalsIgnoreCase("false"))) {
      return true
    }
    return false
  }

  def nextQuiz() = {
    if (count >= listQuiz.size) {
      finish(mainPlay.scores)
    } else {
      count += 1
      mainPlay.text = listQuiz get (count) getQuestion ()
      mainPlay.scores = String.valueOf(count)
      mainPlay repaint ()
    }
  }

  def stopGamePlay() = isPause = true

  class MyListener(f: ActionEvent => Unit) extends ActionListener {
    override def actionPerformed(event: ActionEvent) = { f(event) }
  }

  def trueBtnClick() {
    SoundControl soundCtrl()
    if (testQuiz(1)) {
      nextQuiz()
    } else {
      stopGamePlay()
      finish(mainPlay.scores)
      mFrame setVisible (false)
      return
    }
  }

  def falseBtnClick() {
    SoundControl soundCtrl()
    if (testQuiz(0)) {
      nextQuiz()
    } else {
      stopGamePlay()
      finish(mainPlay.scores)
      mFrame setVisible (false)
      return
    }
  }
}