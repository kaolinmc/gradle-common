package com.kaolinmc.gradle.common

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.gradle.api.Project

abstract class DependencyManagement {
    private val dependencies : MutableMap<String, Map<String, String>>

    init {
        data class ArtifactMetadata(
            val group: String?,
            val artifact: String?,
            val version: String
        )

        val content = this::class.java.getResourceAsStream("/default_versions.json")!!.use {
            jacksonObjectMapper().readValue<Map<String, ArtifactMetadata>>(it)
        }

        dependencies = content.map {
            it.key to mutableMapOf<String, String>(
                "group" to (it.value.group ?: BASE_GROUP),
                "name" to (it.value.artifact ?: it.key),
                "version" to it.value.version
            )
        }.toMap().toMutableMap()
    }

    infix fun String.set(version: String) {
        dependencies[this] = dependencies[this]!!.toMutableMap().also {
            it["version"] = version
        }
    }

    operator fun get(id: String): Map<String, String> {
        return dependencies[id]!!
    }

    companion object {
        fun Project.buildNotation(
            id: String,
        ): Map<String, String> {
            return project.rootProject.extensions.getByType(DependencyManagement::class.java)[id]
        }
    }
}