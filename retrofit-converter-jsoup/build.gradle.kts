plugins {
    kotlin("jvm")
    `java-library`
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
