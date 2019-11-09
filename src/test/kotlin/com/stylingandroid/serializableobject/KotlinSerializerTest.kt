package com.stylingandroid.serializableobject

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class KotlinSerializerTest {
    private val data = "data"

    @Nested
    @DisplayName("Given a SealedClass.Object")
    inner class Object {

        val sealedClass = KotlinSerializer.Object

        @Nested
        @DisplayName("When we use it as-is")
        inner class CheckType {

            private val type = sealedClass

            @Test
            @DisplayName("Then its type is correctly matched")
            fun checkType() {
                assertTrue(isType(type, KotlinSerializer.Object.javaClass))
            }

            @Test
            @DisplayName("Then it is identical")
            fun checkSame() {
                assertTrue(isSame(type, KotlinSerializer.Object))
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
                assertTrue(isType(type, KotlinSerializer.Object.javaClass))
            }

            @Test
            @DisplayName("Then it is identical")
            fun checkSame() {
                assertTrue(isSame(type, KotlinSerializer.Object))
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

        val sealedClass = KotlinSerializer.Object2

        @Nested
        @DisplayName("When we use it as-is")
        inner class CheckType {

            private val type = sealedClass

            @Test
            @DisplayName("Then its type is correctly matched")
            fun checkType() {
                assertTrue(isType(type, KotlinSerializer.Object2.javaClass))
            }

            @Test
            @DisplayName("Then it is identical")
            fun checkSame() {
                assertTrue(isSame(type, KotlinSerializer.Object2))
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
                assertTrue(isType(type, KotlinSerializer.Object2.javaClass))
            }

            @Test
            @DisplayName("Then it is identical")
            fun checkSame() {
                assertTrue(isSame(type, KotlinSerializer.Object2))
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

        private val sealedClass = KotlinSerializer.DataClass(data)

        @Nested
        @DisplayName("When we use it as-is")
        inner class CheckType {

            private val type = sealedClass

            @Test
            @DisplayName("Then its type is correctly matched")
            fun checkType() {
                assertTrue(isType(type, KotlinSerializer.DataClass(data).javaClass))
            }

            @Test
            @DisplayName("Then it is not identical")
            fun checkDifferent() {
                assertFalse(isSame(type, KotlinSerializer.DataClass(data)))
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
                assertTrue(isType(type, KotlinSerializer.DataClass::class.java))
            }

            @Test
            @DisplayName("Then it is identical")
            fun checkSame() {
                assertFalse(isSame(type, KotlinSerializer.DataClass(data)))
            }

            @Test
            @DisplayName("Then a when block correctly matches the type")
            fun checkWhen() {
                assertTrue(whenDataClass(type))
            }
        }
    }

    private fun isType(
        sealedClass: KotlinSerializer,
        expected: Class<out KotlinSerializer>
    ): Boolean =
        sealedClass::class.java.isAssignableFrom(expected)

    private fun isSame(sealedClass: KotlinSerializer, expected: KotlinSerializer): Boolean =
        sealedClass === expected

    private fun whenObject(sealedClass: Any): Boolean =
        when (sealedClass) {
            KotlinSerializer.Object -> true
            else -> false
        }

    private fun whenObject2(sealedClass: Any): Boolean =
        when (sealedClass) {
            KotlinSerializer.Object2 -> true
            else -> false
        }

    private fun whenDataClass(sealedClass: KotlinSerializer): Boolean =
        when (sealedClass) {
            is KotlinSerializer.DataClass -> true
            else -> false
        }

    private fun serialize(input: KotlinSerializer): KotlinSerializer {
        val serializer = Json(JsonConfiguration.Stable, KotlinSerializer.serializersModule)
        val json = serializer.stringify(KotlinSerializer.serializer, input)
        return serializer.parse(KotlinSerializer.serializer, json) as KotlinSerializer
    }
}
