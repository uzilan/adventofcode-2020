plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.20"
    `java-library`
}

repositories {
    jcenter()
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.google.guava:guava:29.0-jre")
    testImplementation("io.kotest:kotest-runner-junit5:4.3.1")
    testImplementation("io.kotest:kotest-assertions-core:4.3.1")
    testImplementation("io.kotest:kotest-property:4.3.1")
    api("org.apache.commons:commons-math3:3.6.1")
}
