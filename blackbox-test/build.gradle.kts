import dev.extframework.gradle.common.durganMcBroom
import dev.extframework.gradle.common.extFramework

plugins {
    kotlin("jvm") version "1.9.21"

    id("dev.extframework.common") version "1.0.7"
}

group = "dev.extframework"
version = "1.0"

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