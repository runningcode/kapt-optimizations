
plugins {
    kotlin("jvm")
    kotlin("kapt")
    `java-library`
}

repositories {
    mavenCentral()
}

tasks.register("createJavaFile") {
    val generatedJavaDir = project.layout.buildDirectory.dir("generatedJava")
    outputs.dir(generatedJavaDir)
    doLast {
        generatedJavaDir.get().file("Foo.java").asFile.writeText("""
            import java.lang.String;
            class Foo {
            }
        """.trimIndent())
    }
}

sourceSets.main.configure {
    java.srcDir(tasks.named("createJavaFile").map { it.outputs })
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}
