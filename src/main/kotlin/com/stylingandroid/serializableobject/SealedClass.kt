package com.stylingandroid.serializableobject

import java.io.Serializable

sealed class SealedClass : Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }

    object Object : SealedClass() {
        private fun readResolve(): Any = Object
    }

    object Object2 : SealedClass() {
        private fun readResolve(): Any = Object2
    }

    data class DataClass(val data: String) : SealedClass()
}
