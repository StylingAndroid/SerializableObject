package com.stylingandroid.serializableobject

import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule

@Serializable
sealed class KotlinSerializer {

    @Serializable(with = Object::class)
    object Object :
        KotlinSerializer(), ObjectSerializer<Object> by createObjectSerializer({ Object })

    @Serializable(with = Object2::class)
    object Object2 :
        KotlinSerializer(), ObjectSerializer<Object2> by createObjectSerializer({ Object2 })

    @Serializable
    data class DataClass(val data: String) : KotlinSerializer()

    companion object {

        val serializer = PolymorphicSerializer(KotlinSerializer::class)

        val serializersModule
            get() = SerializersModule {
                polymorphic(KotlinSerializer::class) {
                    Object::class with Object.serializer()
                    Object2::class with Object2.serializer()
                    DataClass::class with DataClass.serializer()
                }
            }
    }
}
