package kamatwitter

class Tweet {

    String message
    Date time

    static belongsTo = [author: User]

    static constraints = {
        message(blank: false, maxSize: 140)
        time(nullable: true)

    }

    transient springSecurityService

    def publish() {
        author = User.get(springSecurityService.principal.id)
        save(flush: true)
    }

    def beforeInsert() {
        time = time ?: new Date()
    }

    static namedQueries = {

        lastTimeline {User author ->
            eq 'author', author
            lastTweets()
        }

        lastTweets {
            maxResults(20)
            order("time", "desc")
        }
    }


}
