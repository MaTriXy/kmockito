package com.sys1yagi.kmockito

import com.taroid.knit.should
import org.junit.Test
import org.mockito.Mockito.times

class KMockitoTest {

    open class Item(private val length: Int) {
        open fun length(): Int = length
    }

    @Test
    fun mockTest() {
        var item = Item::class.java.mock()
        item.length().invoked.thenReturn(10)

        item.length().should be 10
        item.verify().length()
    }

    @Test
    fun spyTest() {
        var item = Item(10).spy()
        item.length().invoked.thenReturn(11)

        item.length().should be 11
        item.verify(times(1)).length()
    }
}
