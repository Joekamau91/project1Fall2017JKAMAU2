package edu.towson.cis.cosc455jkamau.project1
//Joseph Kamau
//Project1
import scala.collection.mutable.ArrayBuffer

class MyLexicalAnalyzer extends LexicalAnalyzer {

  val lookUp = new Array[String](20)
  var token = new ArrayBuffer[Char](50)
  var fileHolder: Array[Char] = Array()
  var nextChar: Char = ' '
  var filePosition: Int = -1
  var filesize: Int = 0

  //Starting to get the tokens
  def start(file1: String): Unit = {
    InitializeLookupArray()
    fileHolder = file1.toCharArray
    filesize = fileHolder.length - 1
  }

  //Moving on to the next character
  override def getChar(): Unit = {
    if (filePosition < filesize) {
      filePosition += 1
      nextChar = fileHolder.charAt(filePosition)
    }
    else
      return
  }

  //Using the getNext to add a char
  override def addChar() {
    token += nextChar
  }

  override def getNextToken(): Unit = {

    getChar()
    getNotText()

    //The process of breaking down singular tokens
    if (nextChar.equals('+') || nextChar.equals('=') || nextChar.equals('#') || nextChar.equals('(') || nextChar.equals(')') || nextChar.equals(']') || nextChar.equals('[')) {
      addChar()
    }

    else if (nextChar.equals('\\')) {
      addChar()
      getChar()
      while (!nextChar.equals('[') && nextChar != '\r' && nextChar != '\n' && nextChar != '\\') {
        if (nextChar.equals('\r')) {
          addChar()
        }
        else {
          addChar()
          getChar()
        }
      }
      if (nextChar.equals('[')) {
        addChar()
      }
      if (nextChar.equals('\\')) {
        addChar()
      }
    }

    else if (nextChar.equals('*')) {
      addChar()
      getChar()
      if (nextChar.equals('*')) {
        addChar()
        getChar()
        pakage()
      }
      else {
        filePosition -= 1
      }
    }
    else if (nextChar.equals('!')) {
      addChar()
      getChar()
      if (nextChar.equals('[')) {
        addChar()
      }
    }
    else {
      addChar()
      getChar()
      while (!CONSTANTS.TOKENS.contains(nextChar)) {
        addChar()
        if (filePosition < filesize) {
          getChar()
        }
        else {
          return
        }
      }
      filePosition -= 1
    }
    pakage()
  }

  //this will check for any special characters
  def isText(text: String): Boolean = {
    if (text.contains("\\"))
      return false
    else
      return true
  }

  //  set token to compiler
  def pakage(): Unit = {
    val posibleToken: String = token.mkString
    if (lookUp.contains(posibleToken.toUpperCase)) {
      setCurrent(posibleToken)
      token.clear()
    }
    else if (isText(posibleToken)) {
      setCurrent(posibleToken)
      token.clear()
    }
    else {
      println("Lexical Error:  Token was incorrect")
      println(posibleToken + "Was found")
      System.exit(1)
    }
  }


  def setCurrent(currentToken: String): Unit = {
    Compiler.currentToken = currentToken
  }

  //get chars through loop
  def getNotText(): Unit = {
    while (nextChar.equals(' ') || nextChar.equals('\r') || nextChar.equals('\n') || nextChar.equals('\t')) {
      getChar()
      if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DOCE)) {
        getChar()
        return
      }
    }
  }

  //defines lookup
  def InitializeLookupArray() = {

    lookUp(0) = "\\BEGIN";
    lookUp(1) = "\\END";
    lookUp(2) = "\\TITLE[";
    lookUp(3) = "]";
    lookUp(4) = "#";
    lookUp(5) = "\\PARAB";
    lookUp(6) = "\\PARAE";
    lookUp(7) = "\\DEF[";
    lookUp(8) = "\\USE[";
    lookUp(9) = "**";
    lookUp(10) = "*";
    lookUp(11) = "+";
    lookUp(12) = "\\\\";
    lookUp(13) = "[";
    lookUp(14) = "(";
    lookUp(15) = ")";
    lookUp(16) = "=";
    lookUp(17) = "![";
    lookUp(18) = "]";
    lookUp(19) = (('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')).toString()
  }
}