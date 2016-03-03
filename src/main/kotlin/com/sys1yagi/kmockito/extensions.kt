package com.sys1yagi.kmockito

import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing
import org.mockito.verification.VerificationMode

fun <T> Class<T>.mock(): T = Mockito.mock(this)
fun <T> T.spy(): T = Mockito.spy(this)
fun <T> T.verify(): T = Mockito.verify(this)
fun <T> T.verify(mode: VerificationMode): T = Mockito.verify(this, mode)
fun <T> T.invoked(): OngoingStubbing<T> {
    return Mockito.`when`(this)
}

val <T> T.invoked: OngoingStubbing<T>
    get() = invoked()
