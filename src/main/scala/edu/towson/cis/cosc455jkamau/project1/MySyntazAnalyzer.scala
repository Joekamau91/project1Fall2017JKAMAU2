package edu.towson.cis.cosc455jkamau.project1

class MySyntazAnalyzer extends SyntaxAnalyzer{
  override def gittex(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DOCB)){
      // add to parse tree / stack
      Compiler.Scanner.getNextToken()
    }
    else {
      println("Error")
      System.exit(1)
    }
  }

  override def paragraph(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.PARAB)){

      Compiler.Scanner.getNextToken()
    }
    else{

      println("Error")
      System.exit(1)
    }
  }

  override def innerItem(): Unit = ???

  override def innerText(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.letters.toString())) {
      Compiler.Scanner.getNextToken()
    }
    else{
      println("Error")
      System.exit(1)

    }
  }

  override def link(): Unit = {
  if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.LINKB)) {
    Compiler.Scanner.getNextToken()
  }
  else{
    println("Error")
    System.exit(1)

  }
}

  override def italics(): Unit= {
  if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.ITALICS)) {
    Compiler.Scanner.getNextToken()
  }
  else{
    println("Error")
    System.exit(1)

  }
}
  override def body(): Unit = {
  if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.validText.toString())) {
    Compiler.Scanner.getNextToken()
  }
  else{
    println("Error")
    System.exit(1)

  }
}

  override def bold(): Unit = {
  if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BOLD)) {
    Compiler.Scanner.getNextToken()
  }
  else{
    println("Error")
    System.exit(1)

  }
}
  override def newline(): Unit = {
  if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.NEWLINE)) {
    Compiler.Scanner.getNextToken()
  }
  else{
    println("Error")
    System.exit(1)

  }
}
  override def title(): Unit = {
  if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.TITLEB)) {
    Compiler.Scanner.getNextToken()
  }
  else{
    println("Error")
    System.exit(1)

  }
}
  override def variableDefine(): Unit = ???

  override def image(): Unit = {
  if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.IMAGEB)) {
    Compiler.Scanner.getNextToken()
  }
  else{
    println("Error")
    System.exit(1)

  }
}

  override def variableUse(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.numbersEtc.toString())) {
      Compiler.Scanner.getNextToken()
    }
    else {
      println("Error")
      System.exit(1)
    }
  }
    override def heading(): Unit =  {


      if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.HEADING)) {
        Compiler.Scanner.getNextToken()
      }
      else {
        println("Error")
        System.exit(1)

      }
    }

    override def listItem(): Unit = {


      if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.LISTITEM)) {
        Compiler.Scanner.getNextToken()
      }
      else {
        println("Error")
        System.exit(1)

      }
    }

  }