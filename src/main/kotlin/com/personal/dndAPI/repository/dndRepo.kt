package com.personal.dndAPI.repository

import jakarta.enterprise.context.ApplicationScoped
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import com.personal.dndAPI.dto.Monster

@ApplicationScoped
class dndRepo : PanacheRepository<Monster> {}