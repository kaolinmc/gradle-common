import dev.extframework.gradle.common.archiveMapper
import dev.extframework.gradle.common.durganMcBroom
import dev.extframework.gradle.common.extFramework

plugins {
    kotlin("jvm") version "1.9.21"

    id("dev.extframework.common") version "1.1"
}

group = "dev.extframework"
version = "1.0"

tasks.wrapper {
    gradleVersion = "8.14.2"
}

repositories {
    mavenCentral()
    extFramework()
    durganMcBroom()
}

common {
    defaultJavaSettings()

    publishing {
        publication {
            withJava()
            withSources()
            withDokka()

            commonPom {
                withExtFrameworkRepo()

                defaultDevelopers()
                extFrameworkScm("blackbox-test")
            }
        }

        this.repositories {

        }
    }
}

tasks.test {
    useJUnitPlatform()
}