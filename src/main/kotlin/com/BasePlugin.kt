package com

import org.codehaus.groovy.runtime.ProcessGroovyMethods
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.invoke

private val masterBranches = listOf("master", "master-1")
private val devBranches = listOf("develop", "develop-1", "HEAD")

class BasePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            plugins.apply("base")
            plugins.apply("idea")

            version = qualifyVersionIfNecessary(rootProject.version as String)

            tasks {
                /*create<GenerateGitPropertiesTask>("generateGitProperties") {
                    description = "Generates a properties file with the git metadata."
                    group = "Build"
                }

                create<PrintVersionTask>("printVersion") {
                    description = "Prints the version of the artifact."
                    group = "Help"
                }*/
            }

            configure(listOf(project)) {
                defaultTasks = listOf("build")
            }
        }
    }
}

private fun qualifyVersionIfNecessary(version: String): Any {
    val branch = getBranch()
    var qualifiedVersion = version
    if (branch != null && (branch !in devBranches || branch !in masterBranches)) {
        qualifiedVersion += "-" + branch.toUpperCase().replace(Regex("[^\\p{Alnum}]+"), "")
    }
    // add SNAPSHOT if not a master branch
    if (branch !in masterBranches) {
        qualifiedVersion += "-SNAPSHOT"
    }
    return qualifiedVersion
}

fun getBranch(): String? {
    return getCiBranch() ?: getGitBranch()
}

private fun getCiBranch(): String? {
    return System.getenv("CI_COMMIT_REF_NAME")
}

private fun getGitBranch(): String? {
    return try {
        executeCommand("git rev-parse --abbrev-ref HEAD")
    } catch (ignored: Throwable) {
        null
    }
}

fun executeCommand(command: String): String {
    return ProcessGroovyMethods.getText(ProcessGroovyMethods.execute(command)).trim()
}
