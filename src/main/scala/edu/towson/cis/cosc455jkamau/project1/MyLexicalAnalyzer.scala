package edu.towson.cis.cosc455jkamau.project1

class MyLexicalAnalyzer  extends LexicalAnalyzer{

  var c: Char = ' '
  var token: String = ""
  var tokenList = List()
  var tokenChars = List()

  var currentChar = 0

  override def addChar(): Unit = {
    Compiler.populsteFilerray(c) :: tokenChars
  }


  override def getChar(): Char = {
    Compiler.populsteFilerray(currentChar)
  }

  override def getNextToken(): Unit = {
    c = getChar()
  }

  override def lookup(): Boolean = {
    val c: Char = getChar()

    c match {

      case '!' =>
        if (tokenChars.toString() == CONSTANTS.IMAGEB) {
          token = CONSTANTS.IMAGEB
          return true
        }

      case '#' =>
        if (tokenChars.toString() == CONSTANTS.HEADING) {
          token = CONSTANTS.HEADING
          return true
        }

      case '+' =>
        if (tokenChars.toString() == CONSTANTS.LISTITEM) {
          token = CONSTANTS.LISTITEM
          return true
        }

      case '*' =>


        if (tokenChars.toString() == CONSTANTS.ITALICS) {
          token = CONSTANTS.ITALICS
          return true
        }
        else if (tokenChars.toString() == CONSTANTS.BOLD) {
          token = CONSTANTS.BOLD
          return true
        }

      case '\\' =>
        if(tokenChars.toString() == CONSTANTS.NEWLINE) {
          token = CONSTANTS.NEWLINE
          return true
        }

        else if(tokenChars.toString() == CONSTANTS.DOCB){
          token = CONSTANTS.DOCB
          return true
        }

        else if(tokenChars.toString() == CONSTANTS.DOCE) {
          token = CONSTANTS.DOCE
          return true
        }


        else if(tokenChars.toString() == CONSTANTS.PARAB) {
          token = CONSTANTS.PARAB
          return true
        }


        else if(tokenChars.toString() == CONSTANTS.PARAE) {
          token = CONSTANTS.PARAE
          return true
        }

        else if(tokenChars.toString() == CONSTANTS.DEFB) {
          token = CONSTANTS.DEFB
          return true
        }


        else if(tokenChars.toString() == CONSTANTS.USEB) {
          token = CONSTANTS.USEB
          return true
        }


        else if(tokenChars.toString() == CONSTANTS.TITLEB) {
          token = CONSTANTS.TITLEB
          return true
        }


      case '[' =>
        if (tokenChars.toString() == CONSTANTS.LINKB) {
          token = CONSTANTS.LINKB
          return true
        }


      case ']' =>
        if (tokenChars.toString() == CONSTANTS.ITALICS) {
          token = CONSTANTS.ITALICS
          return true
        }


      case '(' =>
        if (tokenChars.toString() == CONSTANTS.ITALICS) {
          token = CONSTANTS.ITALICS
          return true
        }


      case ')' =>
        if (tokenChars.toString() == CONSTANTS.ITALICS) {
          token = CONSTANTS.ITALICS
          return true
        }


      case '=' =>
        if (tokenChars.toString() == CONSTANTS.ITALICS) {
          token = CONSTANTS.ITALICS
          return true
        }
    }




    print("there is no match")
    false
  }
}

