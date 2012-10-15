package kamatwitter

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(TweetService)
@Mock(Tweet)
class TweetServiceTests {

    void testPublish() {

        //init
        def tweet = new Tweet(
                message: 'Ehi There!',
                author: new User(),
        )
        assert Tweet.count() == 0
        //execute
        service.publish(tweet)
        //verify
        assert !tweet.hasErrors()
        assert Tweet.count() == 1
    }
}
