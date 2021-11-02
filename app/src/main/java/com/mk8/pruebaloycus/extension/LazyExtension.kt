package com.mk8.pruebaloycus.extension

fun <T> lazyUnSynchronized(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)