package dev.extframework.gradle.common

import org.gradle.api.artifacts.dsl.RepositoryHandler
import java.net.URI

interface CommonCredentialProvider {
    val user: String
    val password: String
}

enum class RepositoryType(
    val path: String
) {
    SNAPSHOTS("snapshots"),
    RELEASES("releases")
}

fun RepositoryHandler.extFramework(
    type: RepositoryType = RepositoryType.SNAPSHOTS,
    credentials: CommonCredentialProvider? = null
) {
    maven {
        it.url = URI.create("https://maven.extframework.dev/${type.path}")

        if (credentials != null) it.credentials { creds ->
            creds.username = credentials.user
            creds.password = credentials.password
        }
    }
}

fun RepositoryHandler.durganMcBroom(
    type: RepositoryType = RepositoryType.SNAPSHOTS,
    provider: CommonCredentialProvider? = null
) {
    maven {
        it.url = URI.create("https://maven.durganmcbroom.com/${type.path}")

        if (provider != null) it.credentials { creds ->
            creds.username = provider.user
            creds.password = provider.password
        }
    }
}

