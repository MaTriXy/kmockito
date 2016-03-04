package com.sys1yagi.kmockito

import com.taroid.knit.should
import org.junit.Test
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.times

class KMockitoTest {

    open class Item(private var length: Int) {
        open fun length(): Int = length

        open fun increment(): Unit {
            length++
        }

        open fun plus(value: Int) = length() + value
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

    @Test
    fun doNothingTest() {
        var item = Item(10).spy()
        item.doNothing.increment()
        item.increment()

        item.length().should be 10
    }

    @Test
    fun doCallReadMethodTest() {
        var item = Item::class.java.mock()
        item.length().invoked.thenReturn(10)
        item.doCallRealMethod.plus(anyInt())

        item.plus(10).should be 20
    }
}
