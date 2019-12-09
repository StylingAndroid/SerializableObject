package com.stylingandroid.serializableobject

import kotlinx.serialization.Serializable

@Serializable
sealed class KSerializerSealedClass {

    @Serializable
    object Object : KSerializerSealedClass()

    @Serializable
    object Object2 : KSerializerSealedClass()

    @Serializable
    data class DataClass(val data: String) : KSerializerSealedClass()
}
