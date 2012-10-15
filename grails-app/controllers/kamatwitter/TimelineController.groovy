package kamatwitter

import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService

/**
 * User: rcigni
 */
@Secured(['ROLE_USER'])
class TimelineController {

    def springSecurityService

    def tweetService

    def index() {
        redirect(action: 'show')
    }

    def show() {
        def tweets = Tweet.list(
                sort: 'time',
                order: 'desc',
                max: 20
        )

        [tweets: tweets]
    }

    def authorTweets(String username) {
        def author = User.findByUsername(username)

        if (!author) {
            flash.message = "Autore ${username} non trovato!"
            redirect(action: 'show')
            return
        }

        def tweets = Tweet.lastTimeline(author).list()

        [tweets: tweets, author: author]
    }

    def tweet() {

        def currentUser = User.get(springSecurityService.principal.id)

        def tweetInstance = new Tweet(params)
        tweetInstance.author = currentUser

        if (!tweetService.publish(tweetInstance)) {
            chain(action: "authorTweets", model: [tweetInstance:tweetInstance], params: [username: currentUser.username])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'tweet.label', default: 'Tweet'), tweetInstance.id])
        redirect(action: "authorTweets", params: [username: currentUser.username])

    }

}
