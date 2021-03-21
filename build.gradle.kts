import com.github.jengelman.gradle.plugins.shadow.tasks.ConfigureShadowRelocation

plugins {
    java
    kotlin("jvm") version "1.4.21"
    id("com.github.johnrengelman.shadow") version "6.1.0"

    kotlin("plugin.serialization") version "1.4.21"
}

val JVM_VERSION = JavaVersion.VERSION_1_8
val JVM_VERSION_STRING = "1.8"


group = "de.polylymer"
version = "0.0.1"

repositories {
    mavenCentral()
    jcenter()
    mavenLocal()
    maven("https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("de.mooziii", "BetterSpigot", "1.0")
    testCompile("junit", "junit", "4.12")
    compileOnly("org.spigotmc", "spigot-api", "1.16.4-R0.1-SNAPSHOT")
    implementation("net.axay", "KSpigot", "v1.16.4_R22")
    compileOnly("net.axay", "BlueUtils", "1.0.2")
    compileOnly("org.litote.kmongo", "kmongo-core", "4.2.3")
    compileOnly("org.litote.kmongo", "kmongo-serialization-mapping", "4.2.3")

}

java.sourceCompatibility = JVM_VERSION
java.targetCompatibility = JVM_VERSION



