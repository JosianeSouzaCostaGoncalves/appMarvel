package com.example.appmarvel.util.base

interface GenericMapper<A, D> {

    fun mapToDomain(type: A): D

    fun mapFromDomain(type: D): A

    fun mapFromDomainNonNull(entity: List<A>): List<D> {
        return entity.map { mapToDomain(it) }
    }

    fun mapToDomainNonNull(domain: List<D>): List<A> {
        return domain.map { mapFromDomain(it)!! }
    }
}
