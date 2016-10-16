package vn.khmt.functional.entity

class Quiz(q: String, a: String) {

  private var question: String = q
  private var answer: String = a
  
  def getQuestion():String = question
  def getAnswer():String = answer

}