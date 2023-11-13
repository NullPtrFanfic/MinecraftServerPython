import org.gradle.api.artifacts.type.ArtifactTypeDefinition.ARTIFACT_TYPE_ATTRIBUTE
import com.nullptr.mod.JavaModuleTransform

plugins {
    id("java")
    // id("org.gradlex.extra-java-module-info")
}
group = "com.nullptr.mod"
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

// @CacheableTransform - if transform results should also be shared via remote build cache

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(8))
}
