package dev.extframework.gradle.common.dm

import dev.extframework.gradle.common.DEFAULT_CONF
import org.gradle.api.artifacts.dsl.DependencyHandler

const val RESOURCE_API_VERSION = "1.2-SNAPSHOT"

const val JOBS_VERSION = "1.3.2-SNAPSHOT"

const val ARTIFACT_RESOLVER_VERSION = "1.3-SNAPSHOT"
const val ARTIFACT_RESOLVER_VERSION_MAVEN = "1.3-SNAPSHOT"

fun DependencyHandler.resourceApi(
    version: String = RESOURCE_API_VERSION,
    configurationName: String = DEFAULT_CONF
) = add(
    configurationName,
    "com.durganmcbroom:resource-api:$version",
)

fun DependencyHandler.jobs(
    logging: Boolean = true,
    progressSimple: Boolean = true,

    version: String = JOBS_VERSION,
    configurationName: String = DEFAULT_CONF
)  {
    add(configurationName, "com.durganmcbroom:jobs:$version")

    if (logging) add(configurationName, "com.durganmcbroom:jobs-logging:$version")
    if (progressSimple) add(configurationName, "com.durganmcbroom:jobs-progress-simple:$version")
}

fun DependencyHandler.artifactResolver(
    maven: Boolean = true,

    version: String = ARTIFACT_RESOLVER_VERSION,
    mavenVersion: String = version,
    configurationName: String = DEFAULT_CONF
)  {
    add(configurationName, "com.durganmcbroom:artifact-resolver:$version")

    if (maven) add(configurationName, "com.durganmcbroom:artifact-resolver-simple-maven:$mavenVersion")
}