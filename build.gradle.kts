import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    kotlin("multiplatform").version(Dependencies.Versions.kotlin).apply(false)
    id("com.android.library").version(Dependencies.Versions.gradle).apply(false)
    id("org.jetbrains.compose").version(Dependencies.Versions.composeMultiplatform).apply(false)
    id("org.jetbrains.dokka").version(Dependencies.Versions.dokka).apply(false)
    id("io.github.gradle-nexus.publish-plugin").version(Dependencies.Versions.nexusSonatype)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

group = Dependencies.Versions.Czan.Maven.group
version = Dependencies.Versions.Czan.versionName

nexusPublishing {
    println(gradleLocalProperties(rootDir).getProperty("sonatype.username") ?: System.getenv("sonatypeUsername"))
    println(gradleLocalProperties(rootDir).getProperty("sonatype.password") ?: System.getenv("sonatypePassword"))
    println(gradleLocalProperties(rootDir).getProperty("sonatype.stagingProfileId") ?: System.getenv("sonatypeStagingProfileId"))

    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
            username.set(gradleLocalProperties(rootDir).getProperty("sonatype.username") ?: System.getenv("sonatypeUsername"))
            password.set(gradleLocalProperties(rootDir).getProperty("sonatype.password") ?: System.getenv("sonatypePassword"))
            stagingProfileId.set(gradleLocalProperties(rootDir).getProperty("sonatype.stagingProfileId") ?: System.getenv("sonatypeStagingProfileId"))
        }
    }
}
