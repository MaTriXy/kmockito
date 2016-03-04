package com.sys1yagi.kmockito

import com.sys1yagi.kmockito.fortest.Item
import com.sys1yagi.kmockito.fortest.Name
import com.taroid.knit.should
import org.junit.Test
import org.mockito.Mockito.*

class KMockitoTest {
    @Test
    fun mockTest() {
        var item = Item::class.java.mock()
        item.length().invoked.thenReturn(10)

        item.length().should be 10
        item.verify().length()
    }

    @Test
    fun spyTest() {
        var item = Item(Name("name"), 10).spy()
        item.doReturn(11).length()
        item.length().should be 11
        item.verify(times(1)).length()
    }

    @Test
    fun doNothingTest() {
        var item = Item(Name("name"), 10).spy()
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
