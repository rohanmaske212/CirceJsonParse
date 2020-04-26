Parse Json using Circe Scala library 

**Build.sbt** 

Add below plugins to build.sbt

val circeVersion = "0.12.3"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)

**Json File**
Create a Json file & put it inside resources folder.
Read this file using Scala.io relative path 