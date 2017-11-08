package edu.towson.cis.cosc455jkamau.project1

import scala.io.Source;

object Compiler {
var populsteFilerray:Array[Char]=Array();
  var currentToken : String = ""
  var fileContents : String = ""

  val Scanner = new MyLexicalAnalyzer
  val Parser = new MySyntazAnalyzer
  val SemanticAnalyzer = new MySyntazAnalyzer

  def main(args: Array[String]): Unit = {
    checkFile(args)
    readFile(args(0))

    Scanner.getNextToken()
}
  def readFile(file : String) = {
    val source = scala.io.Source.fromFile(file)
    for(line <- Source.fromFile(file).getLines()){
      println("This " + line)
    }

    fileContents = try source.mkString finally source.close()
  }

def checkFile(args : Array[String]) = {
  if (args.length != 1) {
  println("USAGE ERROR: wrong number of args fool!")
  System.exit(1)
}
  else if (! args(0).endsWith(".gtx")) {
  println("USAGE ERROR: wrong extension fool!")
  System.exit(1)
}
}
}