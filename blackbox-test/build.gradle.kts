import com.kaolinmc.gradle.common.durganMcBroom
import com.kaolinmc.gradle.common.kaolin

plugins {
    kotlin("jvm") version "1.9.21"

    id("com.kaolinmc.common") version "0.1"
}

group = "com.kaolinmc"
version = "1.0"

tasks.wrapper {
    gradleVersion = "8.14.2"
}

repositories {
    mavenCentral()
    kaolin()
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
                withKaolinRepo()
                defaultDevelopers()
                kaolinScm("blackbox-test")
            }
        }
    }
}

tasks.test {
    useJUnitPlatform()
}