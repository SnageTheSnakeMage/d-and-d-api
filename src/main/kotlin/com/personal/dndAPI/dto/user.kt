package com.personal.dndAPI.dto

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToMany
// import com.personal.dndAPI.dto.SensitiveString
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import org.hibernate.annotations.ManyToAny
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType

@Entity
@Table(name = "test_user")
@UserDefinition
class User(    
// val username: SensitiveString? = null,
// val password: SensitiveString? = null,
// var loggedIn: Boolean = false

@Username
val username: String = "",
@Password 
val password: String = "",
@Roles 
val roles: MutableList<String> = mutableListOf("user"),

) : PanacheEntity() {
    companion object : PanacheCompanion<User>{
        fun findByUsername(username: String) = find("username", username).firstResult()
        fun add(username: String, password: String,roles: MutableList<String>) { 
            val user: User = User(username, BcryptUtil.bcryptHash(password), roles);
            if(User.findByUsername(username) != null){
                throw ExceptionInInitializerError("Username already exists")
            }
            user.persist();
        }
    }
}