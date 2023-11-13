import net.minecraftforge.gradle.common.util.MinecraftExtension
import org.gradle.api.artifacts.DependencyResolveDetails
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
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import com.github.jengelman.gradle.plugins.shadow.tasks.ConfigureShadowRelocation
import java.util.*
import org.gradle.api.file.FileCollection
import java.util.List
import java.util.jar.JarEntry
import net.minecraftforge.gradle.common.util.RunConfig
import java.util.jar.JarInputStream

import java.util.jar.JarOutputStream
import javax.inject.Inject

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

buildscript {
    repositories {
        
        

        jcenter()

        gradlePluginPortal()

        maven(url = "https://maven.mcmoddev.com/")
        maven(url = "https://jitpack.io")
        maven(url = "https://maven.apache.org/")
        maven{
        url = uri("https://libraries.minecraft.net")
        }
        

        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
        flatDir {
		dirs(project.file("libs"))
	}
        maven(url = "https://repo.spring.io/milestone")



    }

    dependencies {
        classpath("gradle.plugin.com.github.johnrengelman:shadow:8.0.0")
    }



}


plugins {
    id("net.minecraftforge.gradle") version "6.+"
    id("com.github.johnrengelman.shadow") version "8.0.0"
    id("java")
    
    `maven-publish`

}



apply {
    plugin("java-base")

    plugin("eclipse")

    plugin("java")

    plugin("com.github.johnrengelman.shadow")
    plugin("maven-publish")

}

sourceSets.main.configure {

	//kotlin.srcDirs += project.file("src/main/kotlin")

	java.srcDirs += project.file("src/main/java")

	resources.srcDirs += project.file("src/generated/resources")

}

version = "0.1"
java { 
     toolchain { 
         languageVersion.set(JavaLanguageVersion.of(8))
     }
}
group = "com.nullptr.mod"
minecraft {
    mappings("stable", "39-1.12")

  //  copyIdeResources = true



	runs {

		configureEach {

			workingDirectory(project.file("run"))



			property("forge.logging.markers", "REGISTRIES")

			property("forge.logging.console.level", "debug")



			mods {

				create(project.name) {

					source(sourceSets.main.get())

				}

			}

		}



		create("client") {

			// NO-OP

		}



		create("server") {

			args("--nogui")

		}



		create("data") {

			workingDirectory(project.file("run-data"))



			args(

				"--mod",

				"mod",

				"--all",

				"--output",

				project.file("src/generated/resources/"),

				"--existing",

				project.file("src/main/resources/")

			)

		}

	}
}

val javaModuleAttribute = Attribute.of("javaModule", Boolean::class.javaObjectType)
configurations.compileClasspath {
    attributes.attribute(javaModuleAttribute, true)
}
configurations.runtimeClasspath {
    attributes.attribute(javaModuleAttribute, true)
}

dependencies.artifactTypes.maybeCreate("jar").attributes.attribute(javaModuleAttribute, false)

dependencies.registerTransform(JavaModuleTransform::class) {
    from.attributes.attribute(ArtifactTypeDefinition.ARTIFACT_TYPE_ATTRIBUTE, "jar").attribute(javaModuleAttribute, false)
    to.attributes.attribute(ArtifactTypeDefinition.ARTIFACT_TYPE_ATTRIBUTE, "jar").attribute(javaModuleAttribute, true)
}


dependencies {
    //implementation("net.minecraftforge:legacydev:0.2.3.1")
    implementation(gradleApi())
    minecraft(group = "net.minecraftforge", name = "forge", version = "1.12.2-14.23.5.2860")
    
    implementation("club.minnced:discord-webhooks:0.8.4")

    implementation("com.theokanning.openai-gpt3-java:service:0.12.0")

    implementation(files("JDA-4.4.1_353-withDependencies-no-opus.jar"))

    shadow("com.theokanning.openai-gpt3-java:service:0.12.0")

    shadow( files ("JDA-4.4.1_353-withDependencies-no-opus.jar"))

}


// Shadow ALL dependencies: 
tasks.create<ConfigureShadowRelocation>("relocateShadowJar") { 
      target = tasks["shadowJar"] as ShadowJar 
      prefix = "${project.group}"
}
tasks.named<ShadowJar>("shadowJar").configure { 
      dependsOn(tasks["relocateShadowJar"]) // Other config 
}

tasks {
   register<Jar>("mm") {
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



        finalizedBy("reobf")

    }

    shadowJar {
      // configurations = [project.configurations.compileClasspath]
       archiveBaseName.set("shadow") 
       archiveClassifier.set("") 
       archiveVersion.set("")
       manifest.inheritFrom(named<Jar>("mm").get().manifest) 
       minimize()

    }



    register<ReobfuscateJar>("reobf") {

        shadowJar

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
