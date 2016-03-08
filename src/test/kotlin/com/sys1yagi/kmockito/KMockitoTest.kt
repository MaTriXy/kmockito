package com.sys1yagi.kmockito

import com.sys1yagi.kmockito.fortest.Item
import com.sys1yagi.kmockito.fortest.Name
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.times

class KMockitoTest {
    @Test
    fun mockTest() {
        var item: Item = mock()
        item.length().invoked.thenReturn(10)

        assertThat(item.length(), `is`(10))
        item.verify().length()
    }

    @Test
    fun spyTest() {
        var item = Item(Name("name"), 10).spy()
        item.doReturn(11).length()
        assertThat(item.length(), `is`(11))
        item.verify(times(1)).length()
    }

    @Test
    fun doNothingTest() {
        var item = Item(Name("name"), 10).spy()
        item.doNothing.increment()
        item.increment()

        assertThat(item.length(), `is`(10))
    }

    @Test
    fun doCallReadMethodTest() {
        var item: Item = mock()
        item.length().invoked.thenReturn(10)
        item.doCallRealMethod.plus(anyInt())

        assertThat(item.plus(10), `is`(20))
    }

    @Test
    fun answerTest() {
        var item: Item = mock()
        item.plusAsync(anyInt(), any()).invoked.thenAnswer {
            (it.arguments[1] as (Int) -> Unit).invoke(10)
        }

        item.plusAsync(10, {
            assertThat(it, `is`(10))
        });
        item.verify().plusAsync(anyInt(), any())
    }
}
