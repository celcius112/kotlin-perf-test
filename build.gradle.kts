plugins {
    `kotlin-dsl`
}

group = "test"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(gradleTestKit())
    testImplementation(group = "org.junit.jupiter",  name = "junit-jupiter-engine",      version = "5.4.1")
    //testImplementation(group = "org.assertj",        name = "assertj-core",              version = "3.12.2")
}

tasks {
    "test"(Test::class) {
        useJUnitPlatform()
    }
}
