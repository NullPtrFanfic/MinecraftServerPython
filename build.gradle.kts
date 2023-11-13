import net.minecraftforge.gradle.common.util.MinecraftExtension
import org.gradle.api.artifacts.DependencyResolveDetails
import net.minecraftforge.gradle.patcher.tasks.ReobfuscateJar

import org.gradle.api.artifacts.type.ArtifactTypeDefinition
//import src.main.com.nullptr.mod.JavaModuleTransform

import java.text.SimpleDateFormat
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import com.github.jengelman.gradle.plugins.shadow.tasks.ConfigureShadowRelocation
import java.util.*
import org.gradle.api.file.FileCollection
import java.util.List
import net.minecraftforge.gradle.common.util.RunConfig


buildscript {
    repositories {
        maven { url = uri("https://plugins.gradle.org/m2/") }
        mavenCentral()
        maven(url="https://mvnrepository.com/artifact/")
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
        classpath("org.gradlex:extra-java-module-info:1.5")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.10")
        classpath("gradle.plugin.com.github.johnrengelman:shadow:8.0.0")
    }



}


plugins {
    id("net.minecraftforge.gradle") version "6.+"
    id("com.github.johnrengelman.shadow") version "8.0.0"
    id("java")
    id("wtf.gofancy.fancygradle") version "1.1.+"
    kotlin("jvm") version "1.9.10"
    id("org.gradlex.extra-java-module-info") version "1.5"
    `maven-publish`
   // `kotlin-dsl`
}



apply {
    plugin("java-base")
    plugin("kotlin")
    plugin("eclipse")
    plugin("org.gradlex.extra-java-module-info")
    plugin("java")
    plugin("net.minecraftforge.gradle")
    plugin("wtf.gofancy.fancygradle")

    plugin("com.github.johnrengelman.shadow")
    plugin("maven-publish")
   // plugin("kotlin-dsl")
}

fancyGradle {
	patches {
		resources
		coremods
		asm
		mergetool
	}
}

sourceSets.main.configure {

	kotlin.srcDirs += project.file("src/main/kotlin")

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



dependencies {
    //implementation("net.minecraftforge:legacydev:0.2.3.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.10")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.10")
   // implementation(gradleApi())
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
      // minimize()

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
