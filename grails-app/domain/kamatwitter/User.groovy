package kamatwitter

import security.SecUser

class User extends SecUser {

    String name
    String surname

    static constraints = {
        //surname(validator: {sn, obj-> })
    }
}
