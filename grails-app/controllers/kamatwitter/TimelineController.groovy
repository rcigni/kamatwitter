package kamatwitter

import grails.plugins.springsecurity.Secured

/**
 * User: rcigni
 */
@Secured(['ROLE_USER'])
class TimelineController {

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

}
