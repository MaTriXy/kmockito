package com.sys1yagi.kmockito.fortest

open class Item(open var nameHolder: NameHolder, open var length: Int) {

    open fun length(): Int = length

    open fun increment(): Unit {
        length++
    }

    open fun plus(value: Int) = length() + value

    open fun plusAsync(value: Int, callback: (Int) -> Unit) {
        callback.invoke(plus(value))
    }
}