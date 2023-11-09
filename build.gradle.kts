import net.minecraftforge.gradle.common.util.MinecraftExtension

import net.minecraftforge.gradle.patcher.tasks.ReobfuscateJar

import org.gradle.api.artifacts.type.ArtifactTypeDefinition 

import org.gradle.api.artifacts.transform.InputArtifact

import org.gradle.api.artifacts.transform.TransformAction

import org.gradle.api.artifacts.transform.TransformOutputs

import org.gradle.api.artifacts.transform.TransformParameters

import org.gradle.api.file.ArchiveOperations

import org.gradle.api.file.FileSystemLocation

import org.gradle.api.provider.Provider

import java.text.SimpleDateFormat

import java.util.*

import java.util.jar.JarEntry
import net.minecraftforge.gradle.common.util.RunConfig
import java.util.jar.JarInputStream

import java.util.jar.JarOutputStream
import wtf.gofancy.fancygradle.patch.Patch
import javax.inject.Inject

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

buildscript {
    repositories {
        maven(

            url = "https://mvnrepository.com/artifact/"

        )

        mavenCentral()

        jcenter()

        gradlePluginPortal()

        mavenLocal()

        maven(url = "https://jitpack.io")

        maven(url = "https://libraries.minecraft.net")

        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")

        maven(url = "https://repo.spring.io/milestone")



    }

    dependencies {
       // classpath("wtf.gofancy.fancygradle:wtf.gofancy.fancygradle.gradle.plugin:1.1.+")
        //classpath "net.minecraftforge.gradle:ForgeGradle:5.1.+"
          //classpath("wtf.gofancy.fancygradle:1.1.3-0")
        classpath("net.minecraftforge.gradle:ForgeGradle:5.1.69")
        classpath("gradle.plugin.com.github.jengelman.gradle.plugins:shadow:7.0.0")
      //  classpath("com.github.jengelman.gradle.plugins:shadow:7.0.0")

    }



}




plugins {
    id("wtf.gofancy.fancygradle") version "latest.release"
    id("net.minecraftforge.gradle") version "5.1.69"
    //id("wtf.gofancy.fancygradle") version "1.1.2-0"
    id("com.github.johnrengelman.shadow") version "7.0.0"

    kotlin("jvm") version "1.5.31"

    id("java")

    `maven-publish`

}



apply {
    plugin("wtf.gofancy.fancygradle")
    plugin("java-base")
    plugin("net.minecraftforge.gradle")

    plugin("eclipse")

    plugin("java")

    plugin("com.github.johnrengelman.shadow")

    plugin("maven-publish")

}



version = "0.1"
//java.toolchain.languageVersion.set(JavaLanguageVersion.of(8))
group = "com.nullptr.mod"



sourceSets {

    main {

        java {

            srcDirs("src/main/java")

            exclude("**/com/nullptr/mod/party/**")

        }

    }

}





val javaModuleAttribute = Attribute.of("javaModule", true.javaClass)



configurations["compileClasspath"].attributes.attribute(javaModuleAttribute, true)

configurations["runtimeClasspath"].attributes.attribute(javaModuleAttribute, true)



dependencies.artifactTypes.maybeCreate("jar").attributes.attribute(javaModuleAttribute, false)



dependencies.registerTransform(JavaModuleTransform::class.java) {

    from.attributes.attribute(ArtifactTypeDefinition.ARTIFACT_TYPE_ATTRIBUTE, "jar")

        .attribute(javaModuleAttribute, false)

    to.attributes.attribute(ArtifactTypeDefinition.ARTIFACT_TYPE_ATTRIBUTE, "jar").attribute(javaModuleAttribute, true)

}



val sourceCompatibility = JavaVersion.VERSION_1_8

val targetCompatibility = JavaVersion.VERSION_1_8



val Project.minecraft: MinecraftExtension

    get() = extensions.getByType()



dependencies {

    minecraft("net.minecraftforge:forge:1.12.2-14.23.5.2860")
    implementation("club.minnced:discord-webhooks:0.8.4")

    implementation("org.ow2.asm:asm:7.1")

    implementation("com.theokanning.openai-gpt3-java:service:0.12.0")

    implementation( files ("JDA-4.4.1_353-withDependencies-no-opus.jar"))

    implementation(gradleApi())

    implementation("com.theokanning.openai-gpt3-java:service:0.12.0")

    implementation("org.ow2.asm:asm:7.1")

    implementation(files("JDA-4.4.1_353-withDependencies-no-opus.jar"))

    shadow("org.ow2.asm:asm:7.1")

    shadow("com.theokanning.openai-gpt3-java:service:0.12.0")

    shadow( files ("JDA-4.4.1_353-withDependencies-no-opus.jar"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.31")

}
minecraft {
   mappings {
    channel="snapshot"
    version="20171003-1.12"
}
   // mappings("snapshot", "20171003-1.12")
  //  mappings("stable", "39-1.12")

    runs {
        val config = Action<RunConfig> {
            properties(
                mapOf(
                    "forge.logging.markers" to "SCAN,REGISTRIES,REGISTRYDUMP,COREMODLOG",
                    "forge.logging.console.level" to "debug"
                )
            )
            workingDirectory = project.file("run").canonicalPath
            source(sourceSets["main"])
            //jvmArgs.add("-Dfml.coreMods.load=$coremodPath")
        }

        create("client", config)
        create("server", config)
    }
}


configurations {

    shadow

}



java {

    sourceCompatibility = JavaVersion.VERSION_1_8

    targetCompatibility = JavaVersion.VERSION_1_8

}



tasks {

    withType<Jar> {

        archiveBaseName.set("mod")

        manifest {

            attributes(

                hashMapOf(

                    "Specification-Title" to "examplemod",

                    "Specification-Vendor" to "examplemodsareus",

                    "Specification-Version" to "1",

                    "Implementation-Title" to project.name,

                    "Implementation-Version" to "${version}",

                    "Implementation-Vendor" to "examplemodsareus",

                    "Implementation-Timestamp" to SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(Date())

                )

            )

        }



        finalizedBy("reobfShadowJar")

    }

    shadowJar {

        configurations = mutableListOf(project.configurations.shadow.get())

        relocate("org.ow2.asm", "${project.group}.shadow.org.objectweb.asm")

        relocate("org.apache.commons.collections4", "${project.group}.shadow.org.apache.commons.collections4")

        relocate("gnu.trove", "${project.group}.shadow.gnu.trove")

        relocate("com.neovisionaries.ws.client", "${project.group}.shadow.com.neovisionaries.ws.client")

        relocate("com.iwebpp.crypto", "${project.group}.shadow.com.iwebpp.crypto")

        relocate("net.dv8tion.jda", "${project.group}.shadow.net.dv8tion.jda")

        relocate("org.json", "${project.group}.shadow.org.json")

        relocate("okio", "${project.group}.shadow.okio")

        relocate("okhttp3", "${project.group}.shadow.okhttp3")

        relocate("club.minnced.discord", "${project.group}.shadow.club.minnced.discord")

        dependencies {

            exclude(dependency("org.jetbrains:annotations"))

            exclude(dependency("com.google.code.findbugs:jsr305"))

        }

        classifier = ""

    }



    withType<ReobfuscateJar> {

        shadowJar {

            

        }

    }

}



publishing {

    publications {

        register<MavenPublication>("maven") {

            from(components["java"])

        }

    }

    repositories {

        maven(

            url = "file:///${project.projectDir}/mcmodsrepo"

        )

    }

}


// @CacheableTransform - if transform results should also be shared via remote build cache

abstract class JavaModuleTransform : TransformAction<TransformParameters.None> {



    @get:InputArtifact

    abstract val inputArtifact: Provider<FileSystemLocation>



    @get:Inject

    abstract val archiveOperations: ArchiveOperations
    fun readAllBytes(inputStream: InputStream): ByteArray {
    val bufLen = 1024
    val buf = ByteArray(bufLen)
    var readLen: Int
    var exception: IOException? = null

    try {
        val outputStream = ByteArrayOutputStream()
        // Далее ваш код для чтения из inputStream и записи в outputStream
        // ...
        return outputStream.toByteArray()
    } catch (e: IOException) {
        exception = e
    } finally {
        if (exception != null) {
            // Обработка исключения, если необходимо
            throw exception
        }
    }
    return byteArrayOf()
    }


    override fun transform(outputs: TransformOutputs) {

        val isModule = archiveOperations.zipTree(inputArtifact).any { it.name == "module-info.class" }

        if (isModule) {

            outputs.file(inputArtifact)

        } else {

            val originalJar = inputArtifact.get().asFile

            val target = outputs.file(originalJar.nameWithoutExtension + "-module.jar")



            JarInputStream(originalJar.inputStream()).use { input ->

                JarOutputStream(target.outputStream(), input.manifest).use { output ->

                    var jarEntry = input.nextJarEntry

                    while (jarEntry != null) {

                        output.putNextEntry(jarEntry)

                        output.write(readAllBytes(input))

                        output.closeEntry()

                        jarEntry = input.nextJarEntry

                    }



                    output.putNextEntry(JarEntry("module-info.class"))

                    output.write(

                        readAllBytes(this::class.java.getResourceAsStream(

                            "${originalJar.nameWithoutExtension}/module-info.class"

                        ))

                    )

                    output.closeEntry()

                }

            }

        }

    }

}


fancyGradle { 
     patches { 
        resources 
        coremods 
        codeChickenLib 
        asm 
        mergetool 
    } 
}
fun DependencyHandler.minecraft(

    dependencyNotation: Any

): Dependency = add("minecraft", dependencyNotation)!!



