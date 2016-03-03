package com.sys1yagi.kmockito


import com.taroid.knit.should
import org.junit.Test
import com.sys1yagi.kmockito.KMockito.called

class KMockitoTest {

    open class Item(private val length: Int) {
        open fun length(): Int = length
    }

    @Test
    fun mockTest() {
        var item = Item::class.java.mock()

        called(item.length()).thenReturn(10)

        item.length().should be 10
    }

    @Test
    fun spyTest() {
        var item = Item(10).spy()

        called(item.length()).thenReturn(11)

        item.length().should be 11
    }
}
