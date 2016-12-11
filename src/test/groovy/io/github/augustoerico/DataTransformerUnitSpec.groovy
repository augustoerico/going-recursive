package io.github.augustoerico

import spock.lang.Specification
import io.github.augustoerico.DataTransformer as transformer

class DataTransformerUnitSpec extends Specification {

    def 'Should transform a simple map'() {

        given:
        def map = [foo: 'foo', bar: 'bar']

        and:
        def closure = { it.toUpperCase() }

        when:
        def result = transformer.transform map, closure

        then:
        result == [foo: 'FOO', bar: 'BAR']

    }

    def 'Should transform a simple list'() {

        given:
        def list = ['one', 'two']

        and:
        def closure = { it.toUpperCase() }

        when:
        def result = transformer.transform list, closure

        then:
        result == ['ONE', 'TWO']

    }

    def 'Should transform a mix of maps and lists'() {

        given:
        def map = [
                foo: 'foo',
                bar: [
                        foo: 'foo',
                        bar: ['one', 'two', 'three'],
                        boo: [tar: [ [foo: 'foo'], [bar: 'bar'] ]]
                ],
                boo: ['four']
        ]

        and:
        def closure = { it.toUpperCase() }

        when:
        def result = transformer.transform map, closure

        then:
        result == [
                foo: 'FOO',
                bar: [
                        foo: 'FOO',
                        bar: ['ONE', 'TWO', 'THREE'],
                        boo: [
                                tar: [ [foo: 'FOO'], [bar: 'BAR'] ]
                        ]
                ],
                boo: ['FOUR']
        ]
    }

}
