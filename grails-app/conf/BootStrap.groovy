import kamatwitter.Tweet
import kamatwitter.User
import security.SecRole
import security.SecUserSecRole

class BootStrap {

    def init = { servletContext ->
        if (!User.count()) {
            initDatabase()
        }
    }

    def initDatabase() {

        def roleUser = SecRole.findByAuthority("ROLE_USER") ?: new SecRole(authority: "ROLE_USER").save(failOnError: true)

        def user = new User(
                username: 'max',
                name: "Massimo",
                surname: "D'Alema",
                password: 'password',
                enabled: true,
        ).save(failOnError: true)

        SecUserSecRole.create(user, roleUser)

        def now = new Date()

        100.times {

            new Tweet(
                    message: "Hello World ${it}",
                    author: user,
                    time: now - it
            ).save(failOnError: true)
        }
    }

    def destroy = {

    }
}
