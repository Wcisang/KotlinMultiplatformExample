package com.wcisang.generate

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import kotlinx.serialization.modules.SerializersModuleBuilder
import java.io.File
import javax.lang.model.element.Element


internal abstract class BaseAnnotationGenerator<T: Annotation>() {

    abstract val fileName: String
    abstract val packageName: String
    abstract val annotationType: Class<T>
    abstract val baseClassName: String

    fun generateFile(rows: Set<Element>, kaptKotlinGeneratedDir: String) {
        val fileBuilder = FileSpec.builder(packageName, fileName)
        val funcBuilder = FunSpec.builder("buildPolymorphic${annotationType.simpleName}").receiver(SerializersModuleBuilder::class)
        val codeBlock = CodeBlock.builder()
        rows.map {
            codeBlock.addStatement(
                "polymorphic($baseClassName::class, ${it.simpleName}::class, ${it.simpleName}.serializer())"
            )
        }
        funcBuilder.addCode(codeBlock.build())
        fileBuilder.addFunction(funcBuilder.build())
        fileBuilder.build().writeTo(File(kaptKotlinGeneratedDir))
    }
}