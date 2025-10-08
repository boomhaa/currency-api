import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
}

group = "com.example"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.config.yaml)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)

    implementation("io.ktor:ktor-client-cio-jvm:2.3.6")

    implementation("org.apache.pdfbox:pdfbox:2.0.30")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")

    implementation("ch.qos.logback:logback-classic:1.5.6")

    implementation("io.insert-koin:koin-ktor:4.0.0")
    implementation("io.insert-koin:koin-logger-slf4j:4.0.0")

    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")

    implementation("io.ktor:ktor-server-core-jvm:3.3.0")

}

tasks.withType<ShadowJar> {
    archiveFileName.set("currency-api.jar")
    manifest {
        attributes["Main-Class"] = "com.example.ApplicationKt"
    }
}

tasks.register<Copy>("copyJarToRoot"){
    dependsOn("shadowJar")
    val jarFile = layout.buildDirectory.file("libs/currency-api.jar")
    from(jarFile)
    into(project.rootDir)
}

tasks.named("build") {
    finalizedBy("copyJarToRoot")
}