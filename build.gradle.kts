plugins {
    kotlin("jvm") version "2.0.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin.jvmToolchain(21)

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.21")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")
    testImplementation("junit:junit:4.13.2")
    testImplementation("io.strikt:strikt-core:0.34.1")
    testImplementation("io.mockk:mockk:1.13.10")
    testImplementation("io.strikt:strikt-core:0.34.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.1")
}

tasks.test {
    useJUnit()
}
