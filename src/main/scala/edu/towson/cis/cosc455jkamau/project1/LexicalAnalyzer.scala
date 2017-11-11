package edu.towson.cis.cosc455jkamau.project1


trait LexicalAnalyzer {
  def addChar(): Unit

  def getChar(): Unit

  def getNextToken(): Unit

  def lookup(): Unit = {
    println("this is lookup implementation!")
  }

}
