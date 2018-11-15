val libVersion = "18.11.0"

organization := "com.github.finagle"
version := libVersion
scalaVersion := "2.12.7"
crossScalaVersions := Seq("2.11.11", "2.12.7")
scalacOptions ++= Seq("-deprecation", "-feature", "-Xexperimental")

libraryDependencies ++= Seq(
  "com.twitter" %% "finagle-core" % libVersion,
  "com.twitter" %% "finagle-netty4" % libVersion,
  "org.scalatest" %% "scalatest" % "3.0.4" % Test,
  "junit" % "junit" % "4.12" % Test
)

// Publish
publishMavenStyle := true
publishArtifact := true
publishTo := Some(Resolver.file("localDirectory", file(Path.userHome.absolutePath + "/workspace/mvn-repo")))
licenses := Seq("Apache 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))
homepage := Some(url("https://github.com/finagle/finagle-websocket"))
pomExtra :=
  <scm>
    <url>git://github.com/finagle/finagle-websocket.git</url>
    <connection>scm:git://github.com/finagle/finagle-websocket.git</connection>
  </scm>
    <developers>
      <developer>
        <id>sprsquish</id>
        <name>Jeff Smick</name>
        <url>https://github.com/sprsquish</url>
      </developer>
    </developers>