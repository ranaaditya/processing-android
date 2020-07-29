import java.nio.file.Files
import org.zeroturnaround.zip.ZipUtil
import java.nio.file.StandardCopyOption.REPLACE_EXISTING
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
   kotlin("jvm")
}
// Extend compile to copy the jars from gradle-tooling and slf4j:
// https://stackoverflow.com/a/43602463

//configurations {
//    compile.extendsFrom compileAndCopy
//    compile.extendsFrom compileAndExtract
//}

val compileAndCopy: Configuration by configurations.creating {
    extendsFrom(configurations["compile"])
}
val compileAndExtract: Configuration  by configurations.creating {
    extendsFrom(configurations["compile"])
}

dependencies {
    compile("org.processing:core:${project.extra["processingVersion"]}")
    compile("org.processing:pde:${project.extra["processingVersion"]}")
    compile("org.processing:java-mode:${project.extra["processingVersion"]}")
    compileAndExtract("org.eclipse.jdt:org.eclipse.jdt.debug:${project.extra["jdtVersion"]}")
    compileAndCopy("org.gradle:gradle-tooling-api:${project.extra["toolingVersion"]}")
    compileAndCopy("org.slf4j:slf4j-api:${project.extra["slf4jVersion"]}")
    compileAndCopy("org.slf4j:slf4j-simple:${project.extra["slf4jVersion"]}")
    compile(fileTree( mapOf("include" to listOf("jdi.jar","jdimodel.jar"), "dir" to "mode" )))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${project.extra["kotlin_version"]}")
}

// This task copies the gradle tooling jar into the mode folder
val copyToLib by tasks.registering(Copy::class){
  from (compileAndCopy.files)
  into ("mode")
}

tasks.build {
    dependsOn(copyToLib)
}

    sourceSets {
        main {
            java {
                srcDirs ("src/")
            }
        }
    }

    tasks.register<Copy> ("getjdi") {
        // This task extracts the jar files inside org.eclipse.jdt.debug, which are
        // jdi.jar and jdimodel.jar and needed to build the debugger.
        from(zipTree(compileAndExtract.files.elementAt(0))) {
            include ("**/*.jar")
            exclude ("META-INF")
        }
        into ("mode")
    }

    tasks.register<Exec> ("permissions") {
        // This task retrieves the latest list of Android permissions and adds them
        // to the Permissions.java file. The python scripts requries BeautifulSoup
        workingDir ("scripts")
        commandLine ("python", "permissions.py")
    }

    tasks.register<Wrapper> ("wrapper") {
        gradleVersion = "${project.extra["gradlewVersion"]}" //version required for gradle wrapper
        doLast {
            val wrapperFolder = file ("mode/gradlew");
            wrapperFolder.mkdirs();
            file("gradle").renameTo(file("mode/gradlew/gradle"))
            file("gradlew").renameTo(file("mode/gradlew/gradlew"))
            file("gradlew.bat").renameTo(file("mode/gradlew/gradlew.bat"))
            ZipUtil.pack(file("mode/gradlew"), File ("mode/gradlew.zip"));
            delete ("mode/gradlew")
        }
    }

    tasks.clean {
        doFirst {
            delete(fileTree("mode").matching {
                include ("**/*.jar")
                exclude ("jdi.jar")
                exclude ("jdimodel.jar")
                exclude ("istack-commons-runtime.jar")
                exclude ("javax.activation-api.jar")
                exclude ("jaxb-api.jar")
                exclude ("jaxb-jxc.jar")
                exclude ("jaxb-runtime.jar")
                exclude ("jaxb-xjc.jar")
            })
        }
    }

    tasks.build {
        doLast {
            Files.copy(file("$buildDir/libs/mode.jar").toPath(),
                    file("mode/AndroidMode.jar").toPath(), REPLACE_EXISTING);
        }
    }

    repositories {
        mavenCentral()
        jcenter()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

//    compileTestKotlin {
//        kotlinOptions {
//            jvmTarget = "1.8"
//        }
//    }
