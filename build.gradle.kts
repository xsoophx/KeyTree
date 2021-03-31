import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.4.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.4.10"
}


repositories {
    mavenCentral()
}

application {
    mainClass.set("src.main.kotlin.Main.kt")
}

object Version {
    const val JUNIT = "5.7.0"
    const val KOTLINX_SERIALIZATION = "1.0.1"
    const val MOCKK = "1.10.2"
    const val SLF4J = "1.7.30"
    const val ASSERTK = "0.23"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    testImplementation(kotlin("test-junit5"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:${Version.JUNIT}")
    testImplementation("org.junit.jupiter:junit-jupiter-params:${Version.JUNIT}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${Version.JUNIT}")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Version.KOTLINX_SERIALIZATION}")

    implementation("org.slf4j:slf4j-api:${Version.SLF4J}")

    testImplementation("io.mockk:mockk:${Version.MOCKK}")

    implementation("com.willowtreeapps.assertk:assertk:${Version.ASSERTK}")
}

tasks {
    "wrapper"(Wrapper::class) {
        gradleVersion = "6.7"
    }

    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf(
                "-Xjvm-default=enable"
            )
        }
    }

    withType<Test> {
        useJUnitPlatform()
    }
}
