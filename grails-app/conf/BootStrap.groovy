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

        def max = new User(
                username: 'max',
                name: "Massimo",
                surname: "D'Alema",
                password: 'password',
                enabled: true,
        ).save(failOnError: true)

        def john = new User(
                username: 'john',
                name: "John",
                surname: "Doo",
                password: 'password',
                enabled: true,
        ).save(failOnError: true)

        SecUserSecRole.create(max, roleUser)
        SecUserSecRole.create(john, roleUser)

        def now = new Date()

        def authors = [max, john]

        100.times {

            new Tweet(
                    message: "Hello World ${it}",
                    author: authors[ it % 2 ],
                    time: now - it
            ).save(failOnError: true)
        }
    }

    def destroy = {

    }
}
