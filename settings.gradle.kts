pluginManagement {
    repositories {
        maven {
            name = "Fabric"
            url = uri("https://maven.fabricmc.net/")
        }
        maven {
            name = "legacy-fabric"
            url = uri("https://repo.legacyfabric.net/repository/legacyfabric/")
        }
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}


rootProject.name = "Base"

include("Client")
include("Bridge")

include("1.8.9")