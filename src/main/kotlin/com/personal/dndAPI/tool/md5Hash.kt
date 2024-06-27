// package com.personal.dndAPI.tool

// import java.security.MessageDigest
// import com.personal.dndAPI.dto.SensitiveString

// class md5Hash {
//     companion object{
//     val md = MessageDigest.getInstance("MD5")
//     val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9') + listOf('!', '#', '$', '%', '&', '*', '+', '-', '/', '=', '?', '@', '^', '_', '`', '{', '|', '}', '~')
//     fun String.securlyStore(): SensitiveString{
//         val salt = List(5){ charPool.random() }.joinToString("")
//         val saltedString = this + salt
//         val hash = md.digest((saltedString).toByteArray())
//         lateinit var storedString: SensitiveString
//         storedString.hash = hash.toString()
//         storedString.salt = salt
//         storedString.persist()
//         return storedString
//     }
//     fun SensitiveString.compareTo(originalString: String): Boolean{
//         val ogString = originalString + this.salt
//         return md.digest(ogString.toByteArray()).toString() == this.hash
//     }

//     }
// }