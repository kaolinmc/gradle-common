package com.kaolinmc.gradle.common

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.publish.maven.MavenPom
import org.gradle.api.publish.maven.MavenPublication

interface CommonPublishing {
    fun publication(action: Action<CommonPublication>)

    fun repositories(action: Action<in RepositoryHandler>)
}

internal fun credentialMessage(id: String) : String = "Failed to find credentials! Please define : '$id'"

class CredentialsException(
    id: String
) : Exception(credentialMessage(id) + " or set 'creds.maven.warn' to 'true'.")

class CommonPublication(
    delegate: MavenPublication,
    private val project: Project,
    private val extension: CommonExtension
) : MavenPublication by delegate {
    fun withJava() {
        from(project.components.getByName("java"))
    }

    fun withSources() {
        artifact(extension.registerSourcesJar())
    }

    fun withDokka() {
        artifact(extension.registerJavadocJar())
    }

    fun commonPom(action: Action<CommonPomConfiguration>) {
        action.execute(CommonPomConfiguration(pom))
    }
}

class CommonPomConfiguration internal constructor(
    pom: MavenPom
) : MavenPom by pom {
    fun defaultDevelopers() {
        developers { spec ->
            spec.developer { dev ->
                dev.email.set("durganmcbroom@gmail.com")
                dev.url.set("https://durganmcbroom.com")
                dev.name.set("Durgan McBroom")
            }
        }
    }

    fun kaolinScm(project: String) {
        scm {
            it.connection.set("scm:git:git://github.com/kaolinmc/$project")
            it.developerConnection.set("scm:git:ssh://github.com:kaolinmc/$project.git")
            it.url.set("https://github.com/kaolinmc/$project")
        }
    }

    fun gnuLicense() {
        licenses { spec ->
            spec.license { license ->
                license.name.set("GNU General Public License")
                license.url.set("https://opensource.org/licenses/gpl-license")
            }
        }
    }

    fun withKaolinRepo(
        type: RepositoryType = RepositoryType.SNAPSHOTS
    ) {
        withXml {
            val repositoriesNode = it.asNode().appendNode("repositories")
            val kaolinRepoNode = repositoriesNode.appendNode("repository")
            kaolinRepoNode.appendNode("id", "kaolin")
            kaolinRepoNode.appendNode("url", "https://maven.kaolinmc.com/${type.path}")
        }
    }

    fun withDMRepo(
        type: RepositoryType = RepositoryType.SNAPSHOTS
    ) {
        withXml {
            val repositoriesNode = it.asNode().appendNode("repositories")
            val extFwkRepoNode = repositoriesNode.appendNode("repository")
            extFwkRepoNode.appendNode("id", "durganmcbroom")
            extFwkRepoNode.appendNode("url", "https://maven.durganmcbroom.com/${type.path}")
        }
    }
}