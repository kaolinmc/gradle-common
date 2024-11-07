package dev.extframework.gradle.common

import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler

const val COMMON_UTIL_VERSION = "1.2-SNAPSHOT"

const val EXT_LOADER_VERSION = "2.1.6-SNAPSHOT"

const val TOOLING_API_VERSION = "1.0.1-SNAPSHOT"

const val CORE_VERSION = "1.0.3-BETA"

const val CORE_API_VERSION = "1.0-SNAPSHOT"

const val CORE_MC_VERSION = "1.0.6-BETA"

const val BOOT_VERSION = "3.2.2-SNAPSHOT"

const val LAUNCHER_META_HANDLER_VERSION = "1.2-SNAPSHOT"

const val MINECRAFT_BOOTSTRAPPER_VERSION = "2.10-SNAPSHOT"

const val ARCHIVE_MAPPER_VERSION = "1.3.1-SNAPSHOT"

const val ARCHIVES_VERSION = "1.2-SNAPSHOT"

const val OBJECT_CONTAINER_VERSION = "1.0-SNAPSHOT"

private fun DependencyHandler.addConfigured(
    configuration: String,
    notation: String
) {
    val dependency = add(
        configuration,
        notation,
    ) as ExternalModuleDependency

    dependency.isChanging = true
}

fun DependencyHandler.commonUtil(
    version: String = COMMON_UTIL_VERSION,
    configurationName: String = DEFAULT_CONF
) = addConfigured(
    configurationName,
    "$BASE_GROUP:common-util:$version"
)

fun DependencyHandler.extLoader(
    version: String = EXT_LOADER_VERSION,
    configurationName: String = DEFAULT_CONF
) = addConfigured(
    configurationName,
    "$BASE_GROUP:ext-loader:$version"
)

fun DependencyHandler.boot(
    version: String = BOOT_VERSION,
    configurationName: String = DEFAULT_CONF,

    test: Boolean = false
) {
    addConfigured(
        configurationName,
        "$BASE_GROUP:boot:$version"
    )

    if (test) addConfigured(
        "testImplementation",
        "$BASE_GROUP:boot-test:$version"
    )
}

fun DependencyHandler.objectContainer(
    version: String = OBJECT_CONTAINER_VERSION,
    configurationName: String = DEFAULT_CONF
) = addConfigured(
    configurationName,
    "$BASE_GROUP:object-container:$version"
)

fun DependencyHandler.launcherMetaHandler(
    version: String = LAUNCHER_META_HANDLER_VERSION,
    configurationName: String = DEFAULT_CONF
) = addConfigured(
    configurationName,
    "$BASE_GROUP:launchermeta-handler:$version"
)

fun DependencyHandler.minecraftBootstrapper(
    version: String = MINECRAFT_BOOTSTRAPPER_VERSION,
    configurationName: String = DEFAULT_CONF
) = addConfigured(
    configurationName,
    "$BASE_GROUP:minecraft-bootstrapper:$version"
)

fun DependencyHandler.archiveMapper(
    tiny: Boolean = false,
    proguard: Boolean = false,
    transform: Boolean = false,

    version: String = ARCHIVE_MAPPER_VERSION,
    configurationName: String = DEFAULT_CONF
) {
    addConfigured(configurationName, "$BASE_GROUP:archive-mapper:$version")

    if (tiny) addConfigured(configurationName, "$BASE_GROUP:archive-mapper-tiny:$version")
    if (proguard) addConfigured(configurationName, "$BASE_GROUP:archive-mapper-proguard:$version")
    if (transform) addConfigured(configurationName, "$BASE_GROUP:archive-mapper-transform:$version")
}

fun DependencyHandler.toolingApi(
    version: String = TOOLING_API_VERSION,
    configurationName: String = DEFAULT_CONF
) {
    addConfigured(configurationName, "$BASE_GROUP:tooling-api:$version")
}

fun DependencyHandler.coreApi(
    version: String = CORE_API_VERSION,
    configurationName: String = DEFAULT_CONF
) {
    addConfigured(configurationName, "$BASE_GROUP:core-api:$version")
}


fun DependencyHandler.archives(
    mixin: Boolean = false,

    version: String = ARCHIVES_VERSION,
    configurationName: String = DEFAULT_CONF
) {
    addConfigured(configurationName, "$BASE_GROUP:archives:$version")

    if (mixin) addConfigured(configurationName, "$BASE_GROUP:archives-mixin:$version")
}
