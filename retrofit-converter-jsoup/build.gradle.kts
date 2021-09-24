plugins {
    kotlin("jvm")
    id("org.jetbrains.dokka") version ("1.5.30")
    `java-library`
    `maven-publish`
}

kotlin {
    explicitApi()
}

dependencies {
    api("com.squareup.retrofit2:retrofit:2.9.0")
    api("org.jsoup:jsoup:1.14.2")

    testImplementation("junit:junit:4.13.2")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.9.1")
}

java {
    withSourcesJar()
}

tasks.jar {
    manifest {
        attributes("Automatic-Module-Name" to "io.github.rsookram.retrofit.converter.jsoup")
    }
}

val dokkaJavadocJar by tasks.register<Jar>("dokkaJavadocJar") {
    dependsOn(tasks.dokkaJavadoc)
    from(tasks.dokkaJavadoc.flatMap { it.outputDirectory })
    archiveClassifier.set("javadoc")
}

val dokkaHtmlJar by tasks.register<Jar>("dokkaHtmlJar") {
    dependsOn(tasks.dokkaHtml)
    from(tasks.dokkaHtml.flatMap { it.outputDirectory })
    archiveClassifier.set("html-doc")
}

publishing {
    publications {
        register<MavenPublication>("library") {
            groupId = "io.github.rsookram"
            artifactId = "retrofit-converter-jsoup"
            version = "0.0.1"

            from(components["java"])

            artifact(dokkaJavadocJar)
            artifact(dokkaHtmlJar)
        }
    }
}
