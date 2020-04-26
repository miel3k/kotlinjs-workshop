plugins {
    id("org.jetbrains.kotlin.js") version "1.3.71"
}

group = "com.miel3k.kotlinjsworkshop"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-js"))
    implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.7.1")
}

kotlin.target.browser { }