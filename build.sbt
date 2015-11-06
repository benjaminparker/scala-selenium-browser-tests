name := "scala-selenium-browser-tests"

version := "1.0"

scalaVersion := "2.11.7"

resolvers += "Sonatype Repo" at "http://oss.sonatype.org/content/groups/public/"

libraryDependencies ++= Seq(
  "im.mange" % "driveby_2.11" % "0.3.32" % "test",
  "org.specs2" % "specs2-core_2.11" % "3.6.5" % "test",
  "org.fluentlenium" % "fluentlenium-core" % "0.10.6" % "test"
)

parallelExecution in Test := true


