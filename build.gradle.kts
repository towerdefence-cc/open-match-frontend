plugins {
    java
    `maven-publish`
    id("io.freefair.lombok") version "6.1.0"
}

group = "cc.towerdefence.openmatch"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.google.code.gson:gson:2.9.0")
    compileOnly("org.jetbrains:annotations:23.0.0")


    testImplementation("com.github.ZakShearman:Minestom:9c98fe0f23")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "cc.towerdefence.openmatch"
            artifactId = "frontend"
            version = "1.0"

            from(components["java"])
        }
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}