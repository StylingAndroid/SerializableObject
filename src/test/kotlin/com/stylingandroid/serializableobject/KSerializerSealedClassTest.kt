package com.stylingandroid.serializableobject

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class KSerializerSealedClassTest {
    private val data = "data"

    @Nested
    @DisplayName("Given a SealedClass.Object")
    inner class Object {

        val sealedClass = KSerializerSealedClass.Object

        @Nested
        @DisplayName("When we use it as-is")
        inner class CheckType {

            private val type = sealedClass

            @Test
            @DisplayName("Then its type is correctly matched")
            fun checkType() {
                assertTrue(isType(type, KSerializerSealedClass.Object.javaClass))
            }

            @Test
            @DisplayName("Then it is identical")
            fun checkSame() {
                assertTrue(isSame(type, KSerializerSealedClass.Object))
            }

            @Test
            @DisplayName("Then a when block correctly matches the type")
            fun checkWhen() {
                assertTrue(whenObject(type))
            }
        }

        @Nested
        @DisplayName("When we serialize it")
        inner class Serialize {

            private val type = serialize(sealedClass)

            @Test
            @DisplayName("Then its type is correctly matched")
            fun checkType() {
                assertTrue(isType(type, KSerializerSealedClass.Object.javaClass))
            }

            @Test
            @DisplayName("Then it is identical")
            fun checkSame() {
                assertTrue(isSame(type, KSerializerSealedClass.Object))
            }

            @Test
            @DisplayName("Then a when block correctly matches the type")
            fun checkWhen() {
                assertTrue(whenObject(type))
            }
        }
    }

    @Nested
    @DisplayName("Given a SealedClass.Object2")
    inner class Object2 {

        val sealedClass = KSerializerSealedClass.Object2

        @Nested
        @DisplayName("When we use it as-is")
        inner class CheckType {

            private val type = sealedClass

            @Test
            @DisplayName("Then its type is correctly matched")
            fun checkType() {
                assertTrue(isType(type, KSerializerSealedClass.Object2.javaClass))
            }

            @Test
            @DisplayName("Then it is identical")
            fun checkSame() {
                assertTrue(isSame(type, KSerializerSealedClass.Object2))
            }

            @Test
            @DisplayName("Then a when block correctly matches the type")
            fun checkWhen() {
                assertTrue(whenObject2(type))
            }
        }

        @Nested
        @DisplayName("When we serialize it")
        inner class Serialize {

            private val type = serialize(sealedClass)

            @Test
            @DisplayName("Then its type is correctly matched")
            fun checkType() {
                assertTrue(isType(type, KSerializerSealedClass.Object2.javaClass))
            }

            @Test
            @DisplayName("Then it is identical")
            fun checkSame() {
                assertTrue(isSame(type, KSerializerSealedClass.Object2))
            }

            @Test
            @DisplayName("Then a when block correctly matches the type")
            fun checkWhen() {
                assertTrue(whenObject2(type))
            }
        }
    }

    @Nested
    @DisplayName("Given a SealedClass.DataClass")
    inner class DataClass {

        private val sealedClass = KSerializerSealedClass.DataClass(data)

        @Nested
        @DisplayName("When we use it as-is")
        inner class CheckType {

            private val type = sealedClass

            @Test
            @DisplayName("Then its type is correctly matched")
            fun checkType() {
                assertTrue(isType(type, KSerializerSealedClass.DataClass(data).javaClass))
            }

            @Test
            @DisplayName("Then it is not identical")
            fun checkDifferent() {
                assertFalse(isSame(type, KSerializerSealedClass.DataClass(data)))
            }

            @Test
            @DisplayName("Then a when block correctly matches the type")
            fun checkDataClass() {
                assertTrue(whenDataClass(type))
            }
        }

        @Nested
        @DisplayName("When we serialize it")
        inner class Serialize {

            private val type = serialize(sealedClass)

            @Test
            @DisplayName("Then its type is correctly matched")
            fun checkType() {
                assertTrue(isType(type, KSerializerSealedClass.DataClass::class.java))
            }

            @Test
            @DisplayName("Then it is identical")
            fun checkSame() {
                assertFalse(isSame(type, KSerializerSealedClass.DataClass(data)))
            }

            @Test
            @DisplayName("Then a when block correctly matches the type")
            fun checkWhen() {
                assertTrue(whenDataClass(type))
            }
        }
    }

    private fun isType(
        sealedClass: KSerializerSealedClass,
        expected: Class<out KSerializerSealedClass>
    ): Boolean =
        sealedClass::class.java.isAssignableFrom(expected)

    private fun isSame(sealedClass: KSerializerSealedClass, expected: KSerializerSealedClass): Boolean =
        sealedClass === expected

    private fun whenObject(sealedClass: Any): Boolean =
        when (sealedClass) {
            KSerializerSealedClass.Object -> true
            else -> false
        }

    private fun whenObject2(sealedClass: Any): Boolean =
        when (sealedClass) {
            KSerializerSealedClass.Object2 -> true
            else -> false
        }

    private fun whenDataClass(sealedClass: KSerializerSealedClass): Boolean =
        when (sealedClass) {
            is KSerializerSealedClass.DataClass -> true
            else -> false
        }

    private fun serialize(input: KSerializerSealedClass): KSerializerSealedClass {
        val serializer = Json(JsonConfiguration.Stable, KSerializerSealedClass.serializersModule)
        val json = serializer.stringify(KSerializerSealedClass.serializer, input)
        return serializer.parse(KSerializerSealedClass.serializer, json) as KSerializerSealedClass
    }
}
