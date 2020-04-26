plugins {
    id("org.jetbrains.kotlin.js") version "1.3.71"
}

group = "com.miel3k.kotlinjsworkshop"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-js"))
}

kotlin.target.browser { }