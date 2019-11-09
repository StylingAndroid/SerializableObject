package com.stylingandroid.serializableobject

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class SealedClassTest {

    private val data = "data"

    @Nested
    @DisplayName("Given a SealedClass.Object")
    inner class Object {

        val sealedClass = SealedClass.Object

        @Nested
        @DisplayName("When we use it as-is")
        inner class CheckType {

            private val type = sealedClass

            @Test
            @DisplayName("Then its type is correctly matched")
            fun checkType() {
                assertTrue(isType(type, SealedClass.Object.javaClass))
            }

            @Test
            @DisplayName("Then it is identical")
            fun checkSame() {
                assertTrue(isSame(type, SealedClass.Object))
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
                assertTrue(isType(type, SealedClass.Object.javaClass))
            }

            @Test
            @DisplayName("Then it is identical")
            fun checkSame() {
                assertTrue(isSame(type, SealedClass.Object))
            }

            @Test
            @DisplayName("Then a when block correctly matches the type")
            fun checkWhen() {
                assertTrue(whenObject(type))
            }
        }
    }

    @Nested
    @DisplayName("Given a SealedClass.DataClass")
    inner class DataClass {

        private val sealedClass = SealedClass.DataClass(data)

        @Nested
        @DisplayName("When we use it as-is")
        inner class CheckType {

            private val type = sealedClass

            @Test
            @DisplayName("Then its type is correctly matched")
            fun checkType() {
                assertTrue(isType(type, SealedClass.DataClass(data).javaClass))
            }

            @Test
            @DisplayName("Then it is not identical")
            fun checkDifferent() {
                assertFalse(isSame(type, SealedClass.DataClass(data)))
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
                assertTrue(isType(type, SealedClass.DataClass::class.java))
            }

            @Test
            @DisplayName("Then it is identical")
            fun checkSame() {
                assertFalse(isSame(type, SealedClass.DataClass(data)))
            }

            @Test
            @DisplayName("Then a when block correctly matches the type")
            fun checkWhen() {
                assertTrue(whenDataClass(type))
            }
        }
    }

    private fun isType(sealedClass: SealedClass, expected: Class<out SealedClass>): Boolean =
        sealedClass::class.java.isAssignableFrom(expected)

    private fun isSame(sealedClass: SealedClass, expected: SealedClass): Boolean =
        sealedClass === expected

    private fun whenObject(sealedClass: Any): Boolean =
        when (sealedClass) {
            SealedClass.Object -> true
            else -> false
        }

    private fun whenDataClass(sealedClass: SealedClass): Boolean =
        when (sealedClass) {
            is SealedClass.DataClass -> true
            else -> false
        }

    private fun serialize(input: SealedClass): SealedClass {
        val baos = ByteArrayOutputStream()
        val oos = ObjectOutputStream(baos)
        oos.writeObject(input)
        val bais = ByteArrayInputStream(baos.toByteArray())
        val ois = ObjectInputStream(bais)
        return ois.readObject() as SealedClass
    }
}
