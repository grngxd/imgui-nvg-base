import org.gradle.internal.os.OperatingSystem

plugins {
    id("fabric-loom") version "1.6-SNAPSHOT" apply false
    id("io.freefair.lombok") version "6.6.+" apply false
    id("java")
    kotlin("jvm") version "2.0.20"
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

subprojects {
    apply(plugin = "io.freefair.lombok")
    apply(plugin = "kotlin")

    java {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    repositories {
        mavenCentral()
        maven("https://jitpack.io")
    }

    val imguVersion = "78129897e0"

    dependencies {
        implementation("com.github.grngxd:imgu:$imguVersion")
    }
}

group = "cc.grng.base"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://maven.fabricmc.net/")
}

dependencies {

}