plugins {
    `java-gradle-plugin`
    kotlin("jvm") version "1.9.21"

    id("com.gradle.plugin-publish") version "1.2.1"
}

group = "com.kaolinmc"
version = "0.1.1"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.9.20")
    implementation("com.gradle.publish:plugin-publish-plugin:1.2.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.21")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.19.0")
    testImplementation(kotlin("test"))
}

gradlePlugin {
    website = "https://github.com/kaolinmc"
    vcsUrl = "https://github.com/kaolinmc/gradle-common"

    plugins {
        create("Gradle Common") {
            id = "com.kaolinmc.common"
            implementationClass = "com.kaolinmc.gradle.common.CommonPlugin"
            displayName = "Kaolin Gradle Common"
            description = "Common tools for Kaolin's Gradle configuration."
        }
    }
}

publishing {
    repositories {
        if (!project.hasProperty("maven-user") || !project.hasProperty("maven-secret")) return@repositories

        maven {
            url = uri("https://maven.kaolinmc.com/releases")

            credentials {
                username = project.findProperty("maven-user") as String
                password = project.findProperty("maven-secret") as String
            }

            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}