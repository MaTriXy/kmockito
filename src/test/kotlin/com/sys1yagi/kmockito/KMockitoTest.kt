package com.sys1yagi.kmockito

import com.sys1yagi.kmockito.fortest.Item
import com.sys1yagi.kmockito.fortest.NameHolder
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import org.mockito.Mockito.*

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
        var item = Item(NameHolder("name"), 10).spy()
        item.doReturn(11).length()
        assertThat(item.length(), `is`(11))
        item.verify(times(1)).length()
    }

    @Test
    fun doNothingTest() {
        var item = Item(NameHolder("name"), 10).spy()
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
            val (value, callback) = it.arguments2<Int, (Int) -> Unit>()
            callback.invoke(10)
        }
        item.plusAsync(10, {
            assertThat(it, `is`(10))
        });
        item.verify().plusAsync(anyInt(), any())

    }

    @Test
    fun answerArgumentsTest() {
        open class Model {
            open fun multipleArgumentsMethod(a: Int, b: String, c: Item, d: NameHolder): Int {
                return 10
            }
        }

        val model: Model = mock()
        model.multipleArgumentsMethod(anyInt(), anyString(), any(), any()).invoked
                .thenAnswer {
                    val (a, b, c, d) = it.arguments4<Int, String, Item, NameHolder>()

                    assertThat(b, `is`("test"))
                    assertThat(c.nameHolder.name, `is`("a"))
                    assertThat(c.length, `is`(12))
                    assertThat(d.name, `is`("name"))

                    return@thenAnswer a
                }
        val result = model.multipleArgumentsMethod(11, "test", Item(NameHolder("a"), 12), NameHolder("name"))
        assertThat(result, `is`(11))
    }

    @Test
    fun doAnswerTest() {
        var item: Item = mock()

        item.doAnswer {
            10
        }.length()

        assertThat(item.length(), `is`(10))
    }

}
