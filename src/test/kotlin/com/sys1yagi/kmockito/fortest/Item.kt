package com.sys1yagi.kmockito.fortest

open class Item(open var name: Name, open var length: Int) {

    open fun length(): Int = length

    open fun increment(): Unit {
        length++
    }

    open fun plus(value: Int) = length() + value
}