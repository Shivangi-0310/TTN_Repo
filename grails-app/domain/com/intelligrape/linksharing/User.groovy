package com.intelligrape.linksharing

import java.sql.Blob


class User {
    String email
    String password
    String firstName
    String lastName
    Blob photo
    Boolean admin
    Boolean active
    Date dateCreated
    Date lastUpdated
    static transients = ['userName']

    String getUserName() {

        return [this.firstName,' ', this.lastName].findAll { it }.join('')
    }
    static hasMany = [topics: Topic, subscriptions: Subscription, readingItems: ReadingItem, resources: Resource]


    static constraints = {
        email(unique: true, blank: false, email: true)
        password(blank: false, size: 5..15)
        firstName(blank: false)
        lastName(blank: false)
        photo(nullable: true)
        admin(nullable: true)
        active(nullable: true)
    }

    void createUser() {
        Integer count = User.count()
        if (count == 0) {

            User user = new User(email: "user1@gmail.com", password: "abcd12345", firstName: "user1", lastName: "user1", admin: false)

            User admin = new User(email: "user2@gmail.com", password: "12345admin", firstName: "user2", lastName: "user2", admin: true)

            log.info("data is created")
            user.save(failOnError: true, flush: true)

            admin.save(failOnError: true, flush: true)
        } else {
            log.info("User alredy exist")
        }


    }

    @Override
    String toString() {
        return this.userName
    }

}
