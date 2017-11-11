 package edu.towson.cis.cosc455jkamau.project1

import scala.io.Source;

 object Compiler {
   //inisalise classes and vars
   val lex = new MyLexicalAnalyzer
   val sin = new MySyntazAnalyzer
   val sam = new MySemanticAnalyzer

   var currentToken: String = ""
   var fileContents: String = ""
   var endCase: Boolean = false
   var filename: String = ""


   def main(args: Array[String]) = {
     //initializing file name
     filename = args(0)


     checkFile(args)
     readFile(args(0))

     //package contents into a string
     lex.start(fileContents)

     //loops till file empty
     while (lex.filePosition < lex.filesize && !endCase) {
       //gets current token
       lex.getNextToken()

       //checks current token for syntax
       sin.gittex()
       if (currentToken.equalsIgnoreCase(CONSTANTS.DOCE)) {
         endCase = true
       }
     }

     //calling semantic analyzer
     sam.semantics()
   }
  // Let's create a string

   def readFile(file: String) = {
     val source = scala.io.Source.fromFile(file)
     fileContents = try source.mkString finally source.close()
   }

   //Checking the file type boss
   def checkFile(args: Array[String]) = {
     if (args.length != 1) {
       println("USAGE ERROR: Wrong number of args")
       System.exit(1)
     }
     else if (!args(0).endsWith(".gtx")) {
       println("USAGE ERROR: Wrong extension Fool")
       System.exit(1)
     }
   }
 }