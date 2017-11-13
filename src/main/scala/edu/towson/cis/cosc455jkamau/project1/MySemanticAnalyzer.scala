package edu.towson.cis.cosc455jkamau.project1

// Joseph Kamau
//10/12/2017

import scala.collection.mutable.Stack
import java.io._
import java.awt.Desktop
import java.io.{File, IOException}

class MySemanticAnalyzer {

  var outputStack = Stack[String]()

  var parse = Stack[String]()
  var varName = new Array[String](10)
  var varMean = new Array[String](10)
  var nextToken: String = ""
  var output: String = ""
  var varCount: Int = 0
  var hasPrinted: Boolean = false

  def semantics(): Unit = {

    parse = Compiler.kam.parse.reverse
    nextToken = parse.pop()

    //Starting the file reading
    joe()
  }

  //File translation
  def joe() {

    while (!parse.isEmpty) {
      if (nextToken.equalsIgnoreCase(CONSTANTS.DOCB)) {
        outputStack.push("<html>")
        nextToken = parse.pop()



      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.TITLEB)) {

        outputStack.push("<head>")

        outputStack.push("<title>")

        outputStack.push(parse.pop())

        outputStack.push("</title>")

        outputStack.push("</head>")

        parse.pop()
        nextToken = parse.pop()
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.HEADING)) {

        outputStack.push("<h1>")

        outputStack.push(parse.pop())

        outputStack.push("</h1>")

        nextToken = parse.pop()
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.PARAB)) {

        outputStack.push("<p>")

        nextToken = parse.pop()
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.PARAE)) {
        outputStack.push("</p>")
        nextToken = parse.pop()
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.BOLD)) {
        outputStack.push("<b>")
        outputStack.push(parse.pop())
        outputStack.push("</b>")
        parse.pop()
        nextToken = parse.pop()
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.ITALICS)) {
        outputStack.push("<i>")
        outputStack.push(parse.pop())
        outputStack.push("</i>")
        parse.pop()
        nextToken = parse.pop()
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.LISTITEM)) {
        outputStack.push("<li>")
        nextToken = parse.pop()
        if (nextToken.contains("\n") && !parse.isEmpty && !nextToken.equalsIgnoreCase(CONSTANTS.DOCE)) {
          outputStack.push(nextToken)
        }
        else {
          if (!nextToken.equalsIgnoreCase(CONSTANTS.DOCE))
            joe()
        }
        outputStack.push("</li>")
        if (!parse.isEmpty)
          nextToken = parse.pop()
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.NEWLINE)) {
        outputStack.push("<br>")
        nextToken = parse.pop()
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.LINKB)) {
        val temp = parse.pop()
        parse.pop()
        parse.pop()
        nextToken = parse.pop()
        parse.pop()

        outputStack.push("<a href = \"")
        outputStack.push(nextToken)
        outputStack.push("\">")
        outputStack.push(temp)
        outputStack.push("</a> ")
        nextToken = parse.pop()
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.IMAGEB)) {
        val temp = parse.pop()
        parse.pop()
        parse.pop()
        nextToken = parse.pop()
        parse.pop()

        outputStack.push("<img src =\"")
        outputStack.push(nextToken)
        outputStack.push("\" alt=\"")
        outputStack.push(temp)
        outputStack.push("\">")
        nextToken = parse.pop()
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.DEFB)) {
        var name = parse.pop()
        parse.pop()
        val mean = parse.pop()
        parse.pop()
        name = name.filter(!" ".contains(_))

        val defed = varName.indexOf(name)
        if (defed != -1) {
          varName(defed) = name
          varMean(defed) = mean
        }
        else {
          varName(varCount) = name
          varMean(varCount) = mean
          varCount += 1
        }

        //Nothing to push,so pop
        nextToken = parse.pop()
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.USEB)) {
        var name: String = parse.pop()
        parse.pop()
        name = name.filter(!" ".contains(_))
        //Testing for variables
        if (varName.contains(name))
          outputStack.push(varMean(varName.indexOf(name)))
        else {
          println("Static Semantic Error: Varibale by that name has not been defined")
          System.exit(1)
        }

        nextToken = parse.pop()
      }
       // Let's move to the next variable
      else if (nextToken.equalsIgnoreCase(CONSTANTS.DOCE)) {
        outputStack.push("</html>")
      }
      else {
        outputStack.push(nextToken)
        nextToken = parse.pop()
      }
    }

    //print output stack to file
    val output = outputStack.reverse.mkString
    val print = new PrintWriter(new File(Compiler.filename + ".html"))
    print.write(output)
    print.close

    //starting html page
    if (!hasPrinted) {
      openHTMLFileInBrowser(Compiler.filename + ".html")
      hasPrinted = true
    }
  }


  /* * Hack Scala/Java function to take a String filename and open in default web browswer. */
  def openHTMLFileInBrowser(htmlFileStr: String) = {
    val file: File = new File(htmlFileStr.trim)
    println(file.getAbsolutePath)
    if (!file.exists())
      sys.error("File " + htmlFileStr + " does not exist.")

    try {
      Desktop.getDesktop.browse(file.toURI)
    }
    catch {
      case ioe: IOException => sys.error("Sorry file could not open boy:  " + htmlFileStr)
      case e: Exception => sys.error("we are getting there Kamau")
    }
  }

}