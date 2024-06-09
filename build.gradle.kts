plugins {
    `java-gradle-plugin`
    kotlin("jvm") version "1.9.21"

    id("com.gradle.plugin-publish") version "1.2.1"
}

group = "dev.extframework"
version = "1.0"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.9.20")
    implementation("com.gradle.publish:plugin-publish-plugin:1.2.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.21")
    testImplementation(kotlin("test"))
}

gradlePlugin {
    website = "https://github.com/extframework"
    vcsUrl = "https://github.com/extframework/gradle-common"

    plugins {
        create("Gradle Common") {
            id = "dev.extframework.common"
            implementationClass = "dev.extframework.gradle.common.CommonPlugin"
            displayName = "Extension Framework Gradle Common"
            description = "Common tools for Extension Framework's Gradle configuration."
        }
    }
}

publishing {
    repositories {
        if (!project.hasProperty("maven-user") || !project.hasProperty("maven-secret")) return@repositories

        maven {
            url = uri("https://maven.extframework.dev/releases")

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