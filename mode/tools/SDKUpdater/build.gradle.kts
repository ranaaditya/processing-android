import java.nio.file.Files
import java.nio.file.StandardCopyOption.REPLACE_EXISTING
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}
dependencies {
    compile("org.processing:pde:${project.extra["processingVersion"]}")
    compile("com.android.tools:sdklib-${project.extra["toolsLibVersion"]}")
    compile("com.android.tools:repository-${project.extra["toolsLibVersion"]}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

sourceSets {
    main {
        java {
            srcDirs("src/")
        }

//        kotlin {
//            srcDirs("src/")
//        }
    }
}

    tasks.clean {
        doFirst {
            delete("tool")
        }
    }
    tasks.build {
        doLast {
            // Copy jar file to tool folder
            val toolJar = file("tool/SDKUpdater.jar");
            toolJar.mkdirs();
            Files.copy(file("$buildDir/libs/SDKUpdater.jar").toPath(),
                    toolJar.toPath(), REPLACE_EXISTING);
        }
    }
    repositories {
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

//    compileTestKotlin {
//        kotlinOptions {
//            jvmTarget = "1.8"
//        }
//    }

