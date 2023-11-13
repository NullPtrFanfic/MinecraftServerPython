package com.nullptr.mod

import org.gradle.api.artifacts.transform.InputArtifact

import org.gradle.api.artifacts.transform.TransformAction

import org.gradle.api.artifacts.transform.TransformOutputs

import org.gradle.api.artifacts.transform.TransformParameters

import org.gradle.api.file.ArchiveOperations

import org.gradle.api.file.FileSystemLocation

import org.gradle.api.provider.Provider
import java.util.List
import java.util.jar.JarEntry
import java.util.jar.JarInputStream

import java.util.jar.JarOutputStream
import javax.inject.Inject

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

abstract class JavaModuleTransform : TransformAction<TransformParameters.None> {



    @get:InputArtifact

    abstract val inputArtifact: Provider<FileSystemLocation>



    @get:Inject

    abstract val archiveOperations: ArchiveOperations
    @Throws(IOException::class)
    fun readAllBytes(inputStream: InputStream): ByteArray {
    val bufLen = 1024
    val buf = ByteArray(bufLen)
    var readLen: Int
    var exception: IOException? = null

    try {
        val outputStream = ByteArrayOutputStream()

        // Continue with your code for reading from inputStream and writing to outputStream
        var bytesRead: Int
        while (inputStream.read(buf).also { bytesRead = it } != -1) {
            outputStream.write(buf, 0, bytesRead)
        }

        return outputStream.toByteArray()
    } catch (e: IOException) {
        exception = e
    } finally {
        if (exception != null) {
            // Handle the exception if necessary
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
