package com.sys1yagi.kmockito

import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing

object KMockito {
    fun <T> called(methodCall: T): OngoingStubbing<T> {
        return Mockito.`when`(methodCall)
    }

}
