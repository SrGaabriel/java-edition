package org.hexalite.network.common.db.entity

import org.hexalite.network.common.api.RestUser
import org.hexalite.network.common.api.fromDatabaseEntity
import org.hexalite.network.common.db.table.UserRoles
import org.hexalite.network.common.db.table.Users
import org.hexalite.network.common.util.exposed.BaseRestWebserverUUIDEntity
import org.hexalite.network.common.util.exposed.BaseRestWebserverUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SizedIterable
import java.util.*

class User(id: EntityID<UUID>): BaseRestWebserverUUIDEntity(id, Users) {
    val lastUsername by Users.lastUsername
    val lastSeen by Users.lastSeen
    val hexes by Users.hexes

    val roles by UserRole referrersOn UserRoles.roleId

    companion object: BaseRestWebserverUUIDEntityClass<User>(Users) {
        fun new(uuid: UUID) = new(uuid) {}
    }
}

inline fun User.api() = RestUser.fromDatabaseEntity(this)

inline fun SizedIterable<User>.api() = map(User::api)