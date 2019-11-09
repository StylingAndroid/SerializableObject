package com.stylingandroid.serializableobject

import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.internal.SerialClassDescImpl

interface ObjectSerializer<T> : KSerializer<T> {
    fun serializer() = this
}

inline fun <reified T : Any> createObjectSerializer(crossinline instance: () -> T) =
    object : ObjectSerializer<T> {
        override val descriptor: SerialDescriptor = SerialClassDescImpl(T::class.toString())
        override fun deserialize(decoder: Decoder) = instance()
        override fun serialize(encoder: Encoder, obj: T) {
            encoder.beginStructure(descriptor, this).endStructure(descriptor)
        }
    }
