package com.mk8.domain.usecase.base

interface UseCase<out T> {
    suspend fun execute(): T
}