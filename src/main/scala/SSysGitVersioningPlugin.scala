package ru.simplesys.plugins

import com.typesafe.sbt.{SbtGit, GitVersioning}
import com.typesafe.sbt.SbtGit.git
import sbt._
import Keys._

object SSysGitVersioningPlugin extends AutoPlugin {
  override def requires = GitVersioning
  override def trigger = allRequirements


  override def buildSettings: Seq[Def.Setting[_]] = Seq(
   git.formattedShaVersion := {
     val base = git.baseVersion.?.value
     val suffix =
       git.makeUncommittedSignifierSuffix(/*git.gitUncommittedChanges.value*/true, git.uncommittedSignifier.value)
     base.map(b => b + suffix)
   },

   version := {
     val base = git.baseVersion.?.value
     val commitVersion = git.formattedShaVersion.value
     val datedVersion = git.formattedDateVersion.value

     if (git.gitUncommittedChanges.value) commitVersion getOrElse datedVersion
     else {
       val uncommittiedSuffix =
         git.makeUncommittedSignifierSuffix(git.gitUncommittedChanges.value, git.uncommittedSignifier.value)

       val releaseVersion =
         git.releaseVersion(git.gitCurrentTags.value, git.gitTagToVersionNumber.value, uncommittiedSuffix)

       (releaseVersion orElse commitVersion) getOrElse datedVersion
     }
   },

   shellPrompt := {
     val prompt: State => String = { state =>
       val currPrompt = SbtGit.GitCommand.prompt(state)
       val currVersion = version.value
       val branchPattern = """(.*)?\((.*)?\)>\s*""".r
       val simplePattern = """(.*)?>\s*""".r
       currPrompt match {
         case branchPattern(proj, br) => s"$proj@$currVersion($br)>"
         case simplePattern(proj) => s"$proj@$currVersion>"
         case _ => currPrompt
       }
     }
     prompt
   }
 )
}
