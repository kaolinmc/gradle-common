package dev.extframework.gradle.common

import org.gradle.api.Action
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.logging.LogLevel
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.api.tasks.testing.Test
import org.gradle.jvm.tasks.Jar
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.jvm.toolchain.JavaToolchainService
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class CommonPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.extensions.create("common", CommonExtension::class.java, target)
        if (target.rootProject.extensions.findByType(DependencyManagement::class.java) == null)
            target.rootProject.extensions.create("dependencyManagement", DependencyManagement::class.java)

        target.project.pluginManager.apply("maven-publish")
        target.project.pluginManager.apply("org.jetbrains.dokka")
    }
}

abstract class CommonExtension(
    val project: Project
) {
    val propertyCredentialProvider = object : CommonCredentialProvider {
        val shouldWarn = (project.findProperty("creds.maven.warn") as? String)?.toBoolean() ?: false

        override val user: String
            get() = (project.findProperty("creds.maven.user") as? String)
                ?: if (shouldWarn) {
                    project.logger.log(LogLevel.WARN, credentialMessage("creds.maven.user")); ""
                } else throw CredentialsException("creds.maven.user")
        override val password: String
            get() = (project.findProperty("creds.maven.key") as? String)
                ?: if (shouldWarn) {
                    project.logger.log(LogLevel.WARN, credentialMessage("creds.maven.key")); ""
                } else throw CredentialsException("creds.maven.key")
    }

    fun publishing(action: Action<CommonPublishing>) {
        val publishingExt = project.extensions.getByType(PublishingExtension::class.java)

        action.execute(object : CommonPublishing {
            override fun publication(action: Action<CommonPublication>) {

                val publication = CommonPublication(
                    publishingExt.publications.maybeCreate("${project.name}-release", MavenPublication::class.java),
                    project,
                    this@CommonExtension
                )

                action.execute(publication)
            }

            override fun repositories(action: Action<in RepositoryHandler>) {
                action.execute(publishingExt.repositories)
            }
        })
    }

    fun registerSourcesJar(): Jar {
        val jar = project.tasks.maybeCreate("sourcesJar", Jar::class.java)

        project.tasks.withType(Jar::class.java).named("sourcesJar").configure {
            it.archiveClassifier.set("sources")
            it.from(project.extensions.getByType(SourceSetContainer::class.java).getByName("main").allSource)
        }

        return jar
    }

    fun registerJavadocJar(): Jar {
        val jar = project.tasks.maybeCreate("javadocJar", Jar::class.java)

        project.tasks.withType(Jar::class.java).named("javadocJar").configure {
            it.archiveClassifier.set("javadoc")
            it.from(project.tasks.getByName("dokkaJavadoc"))
        }

        return jar
    }

    fun defaultJavaSettings() {
        val compileJava = project.tasks
            .withType(JavaCompile::class.java)
            .named("compileJava")

        project.tasks.withType(KotlinCompile::class.java).named("compileKotlin") { comp ->
            comp.destinationDirectory.set(
                compileJava.get().destinationDirectory.asFile.get()
            )

            comp.kotlinOptions {
                jvmTarget = "1.8"
            }
        }

        project.tasks.withType(KotlinCompile::class.java).named("compileTestKotlin") { comp ->
            comp.kotlinOptions {
                jvmTarget = "1.8"
            }
        }

        project.tasks.withType(Test::class.java).named("test") {
            it.useJUnitPlatform()
        }

        compileJava.configure {
            it.sourceCompatibility = "1.8"
            it.targetCompatibility = "1.8"
        }

        val javaToolchainService = project.extensions.getByType(JavaToolchainService::class.java)
        val javaToolchainSpec = javaToolchainService.compilerFor {
            it.languageVersion.set(JavaLanguageVersion.of(8))
        }
        project.tasks.withType(JavaCompile::class.java).configureEach {
            it.javaCompiler.set(javaToolchainSpec)
        }
    }
}