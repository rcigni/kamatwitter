package kamatwitter

import grails.test.mixin.TestFor

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Tweet)
class TweetTests {

    void testConstraints() {
        mockForConstraintsTests(Tweet)

        def subject = new Tweet()

        assert !subject.validate()
        assert subject.errors['message'] == 'nullable'

        subject.message = '  '
        assert !subject.validate()
        assert subject.errors['message'] == 'blank'

        subject.message = 'x' * 141
        assert !subject.validate()
        assert subject.errors['message'] == 'maxSize'

        subject.message = 'x' * 140
        assert subject.validate()

    }
}
