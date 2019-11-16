package com.intelligrape.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll("Executing #sno")
    void "validating user"() {
        given:
        User user = new User(email: testEmail, firstName: testFirstName, lastName: testLastName, password: "123456")
        when:
        Boolean result = user.validate()

        then:
        result == valid
        when:
        String TempUName = username
        then:
        TempUName == username
        where:
        sno | testFirstName | testLastName | testEmail          | username        | valid
        1   | ""            | "hello"      | "a@b.com"          | null            | false
        2   | "shivangi"    | "jain"       | "abc"              | "shivangi jain" | false
        3   | "aakash"      | "sharma"     | "aakash@gmail.com" | "aakash sharma" | true

    }

    void "Unique Email"() {

        setup:
        String testEmail = "shivangi@tothenew.com"
        User user = new User(email: testEmail, firstName: "testFirstName", lastName: "testLastName", password: "123456")

        when:
        user.save()

        then:
        user.count() == 1

        when:
        User user2 = new User(email: testEmail, firstName: "testFirstName", lastName: "testLastName", password: "123456")
        user2.save()

        then:
        user2.count() == 1
        user2.errors.allErrors.size() == 1
        user2.errors.getFieldErrorCount('email') == 1

    }

    void "to String test"() {

        given:
        User user = new User(firstName: "shivangi", lastName: "jain", password: "1234568")
        expect:
        user.toString() == "shivangi jain"

    }


}
