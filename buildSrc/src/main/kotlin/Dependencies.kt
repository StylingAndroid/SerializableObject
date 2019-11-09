const val kotlinVersion = "1.3.50"

object BuildPlugins {
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val kotlinSerializationPlugin = "org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion"

    const val detekt = "io.gitlab.arturbosch.detekt"
    const val ktlint = "org.jlleitschuh.gradle.ktlint"
    const val versions = "com.github.ben-manes.versions"
    const val kotlinxSerialization = "kotlinx-serialization"
}

object Libraries {
    private object Versions {
        const val jupiter = "5.6.0-M1"
        const val kotlinxSerializationRuntime = "0.13.0"
    }

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val kotlinxSerializationRuntime =
        "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.kotlinxSerializationRuntime}"
    const val jupiter = "org.junit.jupiter:junit-jupiter:${Versions.jupiter}"

}
