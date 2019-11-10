package com.stylingandroid.serializableobject

import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule

sealed class KSerializerSealedClass {

    @Serializable(with = Object::class)
    object Object :
        KSerializerSealedClass(), ObjectSerializer<Object> by createObjectSerializer({ Object })

    @Serializable(with = Object2::class)
    object Object2 :
        KSerializerSealedClass(), ObjectSerializer<Object2> by createObjectSerializer({ Object2 })

    @Serializable
    data class DataClass(val data: String) : KSerializerSealedClass()

    companion object {

        val serializer = PolymorphicSerializer(KSerializerSealedClass::class)

        val serializersModule
            get() = SerializersModule {
                polymorphic(KSerializerSealedClass::class) {
                    Object::class with Object.serializer()
                    Object2::class with Object2.serializer()
                    DataClass::class with DataClass.serializer()
                }
            }
    }
}
