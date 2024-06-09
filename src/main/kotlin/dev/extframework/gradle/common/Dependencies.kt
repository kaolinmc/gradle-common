package dev.extframework.gradle.common

import org.gradle.api.artifacts.dsl.DependencyHandler

const val COMMON_UTIL_VERSION = "1.1.3-SNAPSHOT"

const val EXT_LOADER_VERSION = "1.1.1-SNAPSHOT"

const val BOOT_VERSION = "2.1.1-SNAPSHOT"

const val LAUNCHER_META_HANDLER_VERSION = "1.1.2-SNAPSHOT"

const val MINECRAFT_BOOTSTRAPPER_VERSION = "1.0-SNAPSHOT"

const val ARCHIVE_MAPPER_VERSION = "1.2.1-SNAPSHOT"

const val ARCHIVES_VERSION = "1.2-SNAPSHOT"

fun DependencyHandler.commonUtil(
    version: String = COMMON_UTIL_VERSION,
    configurationName: String = DEFAULT_CONF
) = add(
    configurationName,
    "$BASE_GROUP:common-util:$version"
)

fun DependencyHandler.extLoader(
    version: String = EXT_LOADER_VERSION,
    configurationName: String = DEFAULT_CONF
) = add(
    configurationName,
    "$BASE_GROUP.components:ext-loader:$version"
)

fun DependencyHandler.boot(
    version: String = BOOT_VERSION,
    configurationName: String = DEFAULT_CONF
) = add(
    configurationName,
    "$BASE_GROUP:boot:$version"
)

fun DependencyHandler.launcherMetaHandler(
    version: String = LAUNCHER_META_HANDLER_VERSION,
    configurationName: String = DEFAULT_CONF
) = add(
    configurationName,
    "$BASE_GROUP:launchermeta-handler:$version"
)

fun DependencyHandler.minecraftBootstrapper(
    version: String = MINECRAFT_BOOTSTRAPPER_VERSION,
    configurationName: String = DEFAULT_CONF
) = add(
    configurationName,
    "$BASE_GROUP.components:minecraft-bootstrapper:$version"
)

fun DependencyHandler.archiveMapper(
    tiny: Boolean = false,
    proguard: Boolean = false,

    version: String = ARCHIVE_MAPPER_VERSION,
    configurationName: String = DEFAULT_CONF
) {
    add(configurationName,"$BASE_GROUP:archive-mapper:$version")

    if (tiny) add(configurationName,"$BASE_GROUP:archive-mapper-tiny:$version")
    if (proguard) add(configurationName,"$BASE_GROUP:archive-mapper-proguard:$version")
}

fun DependencyHandler.archives(
    mixin: Boolean = false,

    version: String = ARCHIVES_VERSION,
    configurationName: String = DEFAULT_CONF
) {
    add(configurationName, "$BASE_GROUP:archives:$version")

    if (mixin) add(configurationName, "$BASE_GROUP:archives-mixin:$version")
}