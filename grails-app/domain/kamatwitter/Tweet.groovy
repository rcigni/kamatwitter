package kamatwitter

class Tweet {

    String message
    Date time

    static belongsTo = [author: User]

    static constraints = {
        message(blank: false, maxSize: 140)
        time(nullable: true)

    }

    def beforeInsert() {
        time = time ?: new Date()
    }


}
