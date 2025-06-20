package dev.extframework.gradle.common

import dev.extframework.gradle.common.DependencyManagement.Companion.buildNotation
import org.gradle.api.Project

// dev.extframework

const val COMMON_UTIL = "common-util"
fun Project.commonUtil() = buildNotation(COMMON_UTIL)
fun DependencyManagement.commonUtil(version: String) = COMMON_UTIL.set(version)

const val EXT_LOADER = "ext-loader"
fun Project.extLoader() = buildNotation(EXT_LOADER)
fun DependencyManagement.extLoader(version: String) = EXT_LOADER.set(version)

const val BOOT = "boot"
fun Project.boot() = buildNotation(BOOT)
fun DependencyManagement.boot(version: String) = BOOT.set(version)

const val BOOT_TEST = "boot-test"
fun Project.bootTest() = buildNotation(BOOT_TEST)
fun DependencyManagement.bootTest(version: String) = BOOT_TEST.set(version)

const val OBJECT_CONTAINER = "object-container"
fun Project.objectContainer() = buildNotation(OBJECT_CONTAINER)
fun DependencyManagement.objectContainer(version: String) = OBJECT_CONTAINER.set(version)

const val LAUNCHERMETA_HANDLER = "launchermeta-handler"
fun Project.launcherMetaHandler() = buildNotation(LAUNCHERMETA_HANDLER)
fun DependencyManagement.launcherMetaHandler(version: String) = LAUNCHERMETA_HANDLER.set(version)

const val ARCHIVE_MAPPER = "archive-mapper"
fun Project.archiveMapper() = buildNotation(ARCHIVE_MAPPER)
fun DependencyManagement.archiveMapper(version: String) = ARCHIVE_MAPPER.set(version)

const val ARCHIVE_MAPPER_TINY = "archive-mapper-tiny"
fun Project.archiveMapperTiny() = buildNotation(ARCHIVE_MAPPER_TINY)
fun DependencyManagement.archiveMapperTiny(version: String) = ARCHIVE_MAPPER_TINY.set(version)

const val ARCHIVE_MAPPER_PROGUARD = "archive-mapper-proguard"
fun Project.archiveMapperProguard() = buildNotation(ARCHIVE_MAPPER_PROGUARD)
fun DependencyManagement.archiveMapperProguard(version: String) = ARCHIVE_MAPPER_PROGUARD.set(version)

const val ARCHIVE_MAPPER_TRANSFORM = "archive-mapper-transform"
fun Project.archiveMapperTransform() = buildNotation(ARCHIVE_MAPPER_TRANSFORM)
fun DependencyManagement.archiveMapperTransform(version: String) = ARCHIVE_MAPPER_TRANSFORM.set(version)

const val ARCHIVE_MAPPER_MCP_LEGACY = "archive-mapper-mcp-legacy"
fun Project.archiveMapperMcpLegacy() = buildNotation(ARCHIVE_MAPPER_MCP_LEGACY)
fun DependencyManagement.archiveMapperMcpLegacy(version: String) = ARCHIVE_MAPPER_MCP_LEGACY.set(version)

const val TOOLING_API = "tooling-api"
fun Project.toolingApi() = buildNotation(TOOLING_API)
fun DependencyManagement.toolingApi(version: String) = TOOLING_API.set(version)

const val ARCHIVES = "archives"
fun Project.archives() = buildNotation(ARCHIVES)
fun DependencyManagement.archives(version: String) = ARCHIVES.set(version)

const val MIXIN = "mixin"
fun Project.mixin() = buildNotation(MIXIN)
fun DependencyManagement.mixin(version: String) = MIXIN.set(version)

// com.durganmcbroom

const val RESOURCE_API = "resource-api"
fun Project.resourceApi() = buildNotation(RESOURCE_API)
fun DependencyManagement.resourceApi(version: String) = RESOURCE_API.set(version)

const val ARTIFACT_RESOLVER = "artifact-resolver"
fun Project.artifactResolver() = buildNotation(ARTIFACT_RESOLVER)
fun DependencyManagement.artifactResolver(version: String) = ARTIFACT_RESOLVER.set(version)

const val ARTIFACT_RESOLVER_SIMPLE_MAVEN = "artifact-resolver-simple-maven"
fun Project.artifactResolverMaven() = buildNotation(ARTIFACT_RESOLVER_SIMPLE_MAVEN)
fun DependencyManagement.artifactResolverMaven(version: String) = ARTIFACT_RESOLVER_SIMPLE_MAVEN.set(version)