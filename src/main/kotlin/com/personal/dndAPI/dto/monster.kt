package com.personal.dndAPI.dto

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import jakarta.persistence.Entity
import org.hibernate.type.Type

@Entity
data class Monster(    
    var name: String = "", //required
    var size: String = "medium", //required
    var type: String = "", //required
    var subtype: String? = null,
    var alignment: String? = null,
    var armorClass: Int = 0, //required
    var hitPoints: Int = 0, //required
    var speeds: String = "", //required
    var strength: Int = -1, //required
    var dexterity: Int = -1, //required
    var constitution: Int = -1, //required
    var intelligence: Int = -1, //required
    var wisdom: Int = -1, //required
    var charisma: Int = -1, //required
    var damageVulnerabilities: String? = null,
    var damageResistances: String? = null,
    var damageImmunities: String? = null,
    var conditionImmunities: String? = null,
    var senses: String? = null,
    var languages: String? = null,
    var xpReward: Int = 0, //required
    var specialAbilities: String? = null,
    var actions: Array<String> = arrayOf("default"), //required
    var legendaryActions: String? = null,
    var lore: String? = null,
    var commonLocations: String? = null,
    var imageURL: String? = null,
    var description: String? = null,
    var url: String? = null
): PanacheEntity()