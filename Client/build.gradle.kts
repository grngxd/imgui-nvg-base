import org.gradle.internal.os.OperatingSystem

plugins {
    id("java")
    kotlin("jvm")
}

group = "cc.grng.base"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

val lwjglVersion = "3.3.4"
var lwjglNatives = "natives-windows"

lwjglNatives = when (OperatingSystem.current()) {
    OperatingSystem.LINUX -> "natives-linux"
    OperatingSystem.WINDOWS -> "natives-windows"
    OperatingSystem.MAC_OS -> "natives-macos"
    else -> lwjglNatives
}

lwjglNatives += when (System.getProperty("os.arch")) {
    "aarch64", "armv8" -> "-arm64"
    "arm" -> "-arm32"
    "ppc64" -> "-ppc64le"
    "riscv64" -> "-riscv64"
    else -> ""
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":Bridge"))

    implementation("com.github.surge541:NVGU:1.2")

    implementation("net.java.jinput:jinput:2.0.5")

    implementation("org.lwjgl:lwjgl:$lwjglVersion")
    implementation("org.lwjgl:lwjgl-opengl:$lwjglVersion")
    implementation("org.lwjgl:lwjgl-glfw:$lwjglVersion")
    implementation("org.lwjgl:lwjgl-openal:$lwjglVersion")
    // org.lwjgl.input
    implementation("org.lwjgl:lwjgl-stb:$lwjglVersion")

    runtimeOnly("org.lwjgl:lwjgl::$lwjglNatives")
    runtimeOnly("org.lwjgl:lwjgl-opengl::$lwjglNatives")
    runtimeOnly("org.lwjgl:lwjgl-glfw::$lwjglNatives")
    runtimeOnly("org.lwjgl:lwjgl-openal::$lwjglNatives")

    implementation("com.github.SpaiR.imgui-java:imgui-java-app:v1.86.11")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}