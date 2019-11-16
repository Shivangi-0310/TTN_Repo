package com.intelligrape.linksharing

import com.intelligrape.linksharing.Seriousness

class Subscription {
    User user
    Topic topic
    Seriousness seriousness
    static belongsTo=[user:User]
    Date dateCreated

    static constraints = {
     topic unique: "user"

    }
    def afterInsert() {
        log.info "----------User is Subscribed------"
    }

}
