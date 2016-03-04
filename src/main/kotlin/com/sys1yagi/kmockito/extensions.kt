package com.sys1yagi.kmockito

import org.mockito.MockSettings
import org.mockito.Mockito
import org.mockito.stubbing.Answer
import org.mockito.stubbing.OngoingStubbing
import org.mockito.stubbing.Stubber
import org.mockito.verification.VerificationMode

//// Wrappers

// mock

fun <T> Class<T>.mock(): T = Mockito.mock(this)

fun <T> Class<T>.mock(name: String): T = Mockito.mock(this, name)
fun <T> Class<T>.mock(mockSettings: MockSettings): T = Mockito.mock(this, mockSettings)
fun <T> Class<T>.mock(defaultAnswer: Answer<Any>): T = Mockito.mock(this, defaultAnswer)
fun <T> T.verify(): T = Mockito.verify(this)
fun <T> T.verify(mode: VerificationMode): T = Mockito.verify(this, mode)
fun <T> T.invoked(): OngoingStubbing<T> {
    return Mockito.`when`(this)
}

val <T> T.invoked: OngoingStubbing<T>
    get() = invoked()

fun <T> T.doNothing(): T {
    return Mockito.doNothing().`when`(this)
}

val <T> T.doNothing: T
    get() = doNothing()

fun <T> T.doCallRealMethod(): T {
    return Mockito.doCallRealMethod().`when`(this)
}

val <T> T.doCallRealMethod: T
    get() = doCallRealMethod()

// spy

fun <T> T.spy(): T = Mockito.spy(this)
fun <T> T.doReturn(value: Any): T {
    return Mockito.doReturn(value).`when`(this)
}

//// DSL

fun <T> Class<T>.deepMock(): T = Mockito.mock(this, Mockito.RETURNS_DEEP_STUBS)