package kamatwitter

class TweetService {

    def publish(Tweet tweetInstance) {

        tweetInstance.save(flush: true)

    }
}
