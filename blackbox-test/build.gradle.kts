import dev.extframework.gradle.common.durganMcBroom
import dev.extframework.gradle.common.extFramework

plugins {
    kotlin("jvm") version "1.9.21"

    id("dev.extframework.common") version "1.0"
}

group = "dev.extframework"
version = "1.0"

repositories {
    mavenCentral()
    extFramework()
    durganMcBroom()
}

dependencies {
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

dependencies {

}

tasks.test {
    useJUnitPlatform()
}