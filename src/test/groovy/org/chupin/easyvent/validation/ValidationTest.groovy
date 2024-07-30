package org.chupin.easyvent.validation

import spock.lang.Specification

class ValidationTest extends Specification {

    private static final String TEST_NAME = "testName"

    def "checkNotNull - return value if value isn't null"() {
        when:
        def result = Validation.checkNotNull(value, null)

        then:
        result === value

        where:
        value << [1, "", " ", [], {}]
    }

    def "checkNotNull - throw IllegalArgumentException if the value is null (with name)"() {
        when:
        Validation.checkNotNull(null, TEST_NAME)

        then:
        def result = thrown(IllegalArgumentException.class)
        result.message == "testName is null."
    }

    def "checkNotNull - throw IllegalArgumentException if the value is null (without name)"() {
        when:
        Validation.checkNotNull(null, null)

        then:
        def result = thrown(IllegalArgumentException.class)
        result.message == "value is null."
    }
}
