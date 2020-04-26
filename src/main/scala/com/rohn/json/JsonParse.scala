package com.rohn.json

import io.circe._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import java.io.File
import io.circe.Decoder.Result

object JsonParse {
    def main(args: Array[String]): Unit ={

      /*
         When json has structure as below
            {
              "200" : "Success",
              "400" : "Fail"
            }
       */

      def errorMessage(key : String) = {
        val inputFile = new File(getClass.getClassLoader.getResource("codes.json").getPath)
        val jsonContent = scala.io.Source.fromFile(inputFile).mkString

        //parse json
        val doc = parse(jsonContent).getOrElse(Json.Null)
        val cursor = doc.hcursor
        val result = cursor.get[String](key)
        println(result)
        result
      }

      /*
       When json has structure as below
          {
              "500" : {
                "server" : "Server Error"
                }
          }
     */

      def errorMessages(key : String) = {
        val inputFile = new File(getClass.getClassLoader.getResource("codes.json").getPath)
        val jsonContent = scala.io.Source.fromFile(inputFile).mkString

        //parse json
         parse(jsonContent) match {
          case Left(failure) => println("Invalid JSON :(")
          case Right(json) =>  val cursor = json.hcursor
            val result = cursor.downField(key).get[String]("server") match {
              case Right(message) => println(message)
              case Left(failure) => failure
            }
        }

      }

      errorMessage("200")
      errorMessages("500")
    }
}
