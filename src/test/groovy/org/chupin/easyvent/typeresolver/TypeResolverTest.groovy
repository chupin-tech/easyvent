package org.chupin.easyvent.typeresolver

import org.chupin.easyvent.test.typeresolver.dummyclass.Foo
import org.chupin.easyvent.test.typeresolver.dummyclass.FooImpl
import org.chupin.easyvent.test.typeresolver.dummyclass.FooImplChild
import org.chupin.easyvent.test.typeresolver.dummyclass.NotFoo
import spock.lang.Shared
import spock.lang.Specification

class TypeResolverTest extends Specification {

    @Shared
    static def classes = [String.class, Integer.class, Double.class, Float.class, Byte.class, Boolean.class, Long.class,
                          int.class, double.class, float.class, byte.class, boolean.class, long.class,
                          String[].class, int[].class, double[].class, float[].class, byte[].class, boolean[].class, long[].class]

    def "of - throw IllegalArgumentException if clazz is null"() {
        when:
        TypeResolver.of(null)

        then:
        thrown(IllegalArgumentException.class)
    }

    def "of - create with clazz"() {
        when:
        def result = TypeResolver.of(clazz).clazz

        then:
        result == clazz

        where:
        clazz << classes
    }

    def "isAssignableFrom - throw IllegalArgumentException if clazz is null"() {
        given:
        def type = TypeResolver.of(String.class)

        when:
        type.isAssignableFrom(null)

        then:
        thrown(IllegalArgumentException.class)
    }

    def "isAssignableFrom - return true if same class"() {
        given:
        def type = TypeResolver.of(clazz)

        when:
        def result = type.isAssignableFrom(clazz)

        then:
        result

        where:
        clazz << classes
    }

    def "isAssignableFrom - return false if there is no hierarchical relationship"() {
        given:
        def type = TypeResolver.of(Foo.class)

        when:
        def result = type.isAssignableFrom(NotFoo.class)

        then:
        !result
    }

    def "isAssignableFrom - return false if there is a reversed hierarchical relationship - child class & super class"() {
        given:
        def type = TypeResolver.of(FooImpl.class)

        when:
        def result = type.isAssignableFrom(Foo.class)

        then:
        !result
    }

    def "isAssignableFrom - return true if there is a hierarchical relationship - interface & class"() {
        given:
        def type = TypeResolver.of(Foo.class)

        when:
        def result = type.isAssignableFrom(FooImpl.class)

        then:
        result
    }

    def "isAssignableFrom - return true if there is a hierarchical relationship - super class & child class"() {
        given:
        def type = TypeResolver.of(FooImpl.class)

        when:
        def result = type.isAssignableFrom(FooImplChild.class)

        then:
        result
    }
}
