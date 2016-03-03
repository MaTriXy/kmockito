package com.sys1yagi.kmockito

import org.mockito.Mockito

fun <T> Class<T>.mock(): T {
    return Mockito.mock(this)
}

fun <T> T.spy(): T {
    return Mockito.spy(this)
}
