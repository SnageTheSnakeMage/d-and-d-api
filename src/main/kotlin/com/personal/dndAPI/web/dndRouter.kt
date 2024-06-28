package com.personal.dndAPI.web

import com.personal.dndAPI.dto.Monster
import com.personal.dndAPI.dto.User
import com.personal.dndAPI.repository.dndRepo
// import com.personal.dndAPI.repository.userRepo
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import java.net.URI
import jakarta.ws.rs.core.Response.Status
import jakarta.transaction.Transactional
import jakarta.annotation.security.RolesAllowed
import jakarta.annotation.security.PermitAll
// import com.personal.dndAPI.tool.md5Hash.Companion.securlyStore
// import com.personal.dndAPI.tool.md5Hash.Companion.compareTo

@Path("/monster")
class dndRouter(val repo: dndRepo) {

    @POST
    @RolesAllowed("admin")
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createMonster(inputtedMonster: Monster) : Response{
        var newMon = inputtedMonster
        newMon.persist()
        return Response.created(URI.create("/monsters/" + newMon.id.toString())).build()
    } 

    @PUT
    @RolesAllowed("user")
    @Path("/edit")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    fun editMonster(inputtedMonster: Monster) : Response{
        if(inputtedMonster.id != null){
            val id = inputtedMonster.id ?: return Response.status(Status.BAD_REQUEST.statusCode, "Missing ID").build()
            repo.update("name = ${inputtedMonster.name},"
            + "size = ${inputtedMonster.size}," +
            " type = ${inputtedMonster.type}," +
            " subtype = ${inputtedMonster.subtype}," + 
            " alignment = ${inputtedMonster.alignment}," +
            " armorClass = ${inputtedMonster.armorClass}," +
            " hitPoints = ${inputtedMonster.hitPoints}," +
            " speeds = ${inputtedMonster.speeds}," +
            " strength = ${inputtedMonster.strength}," +
            " dexterity = ${inputtedMonster.dexterity}," +
            " constitution = ${inputtedMonster.constitution}," +
            " intelligence = ${inputtedMonster.intelligence}," +
            " wisdom = ${inputtedMonster.wisdom}," +
            " charisma = ${inputtedMonster.charisma}," +
            " damageVulnerabilities = ${inputtedMonster.damageVulnerabilities}," +
            " damageResistances = ${inputtedMonster.damageResistances}," +
            " damageImmunities = ${inputtedMonster.damageImmunities}," +
            " conditionImmunities = ${inputtedMonster.conditionImmunities}," +
            " senses = ${inputtedMonster.senses}," +
            " languages = ${inputtedMonster.languages}," +
            " xpReward = ${inputtedMonster.xpReward}," +
            " specialAbilities = ${inputtedMonster.specialAbilities}," +
            " actions = ${inputtedMonster.actions.joinToString(",")}," +
            " legendaryActions = ${inputtedMonster.legendaryActions}," +
            " lore = ${inputtedMonster.lore}," +
            " commonLocations = ${inputtedMonster.commonLocations}," +
            " imageURL = ${inputtedMonster.imageURL}," +
            " description = ${inputtedMonster.description}," +
            " url = ${inputtedMonster.url} where id = ${id}" )            
            return Response.created(URI.create("/monsters/" + id.toString())).build() 
        }
        else {
            return Response.status(Status.BAD_REQUEST.statusCode, "Missing ID").build()
        }
    }

    @GET
    @PermitAll
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getById(@PathParam("id") id: Long) : Response{
        return repo.findById(id)?.let { Response.ok(it).build() } ?: Response.status(Status.NOT_FOUND.statusCode).build()
    }

    @GET
    @PermitAll
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    fun getAll() : Response{
        return repo.listAll().let { Response.ok(it).build() }
    }

    @DELETE
    @RolesAllowed("admin")
    @Path("/{id}")
    fun deleteById(@PathParam("id") id: Long) : Response{
        return repo.deleteById(id).let { Response.ok(it).build() }
    }

    @DELETE
    @RolesAllowed("admin")
    @Path("/")
    fun deleteAll() : Response{
        return repo.deleteAll().let { Response.ok(it).build() }
    }

}

@Path("/user")
    class userRouter{

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    fun getAll() : Response{ return User.listAll().let { Response.ok(it).build()}}

    @POST
    @Path("/register")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    fun register(@QueryParam("username") username: String, @QueryParam("password") password: String) : Response{
        try{
            User.add(username, password, mutableListOf("user"))
            return Response.created(URI.create("/users/" + username)).build()
        }
        catch(e: ExceptionInInitializerError){
            return Response.status(Status.BAD_REQUEST.statusCode, "Username already exists").build()
        }
    }

    @PUT
    @Path("/promote/{username}")
    @RolesAllowed("admin")
    @Transactional
    fun promote(@PathParam("username") username: String, ): Response{
        try{
            val user = User.findByUsername(username)
            user?.roles?.add("admin")
            return Response.status(Status.OK.statusCode, "Promoted " + username + " to Admin").build()
        }
        catch(e: Exception){
            return Response.status(Status.BAD_REQUEST.statusCode, "Could not find user: " + username).build()
        }
    }
}