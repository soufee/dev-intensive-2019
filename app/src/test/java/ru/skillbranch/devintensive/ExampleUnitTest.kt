package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extentions.TimeUnits
import ru.skillbranch.devintensive.extentions.add
import ru.skillbranch.devintensive.extentions.format
import ru.skillbranch.devintensive.extentions.toUserView
import ru.skillbranch.devintensive.models.*
import java.util.*

/**
 * @author Ashamaz
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_instance() {
        val user = User("1", "Celin", "Dion")
    }

    @Test
    fun test_factory() {
        val user = User.Factory.makeUser("Robin Williams")
        val user2 = user.copy(id = "2", lastName = "ГэБыркъуэшыркъуэ", lastVisit = Date())
        println("$user \n$user2")
    }

    @Test
    fun test_decomposition() {
        val user = User.makeUser("Pavel Durov")
        fun getUserInfo() = user
        val (id, firstName, lastName) = getUserInfo()

        println("$id, $firstName, $lastName")
    }

    @Test
    fun test_copy(){
        val user = User.makeUser("Pavel Durov")
        var user1 = user.copy(lastVisit = Date())
        var user2 = user.copy(lastVisit = Date().add(-2, TimeUnits.SECOND))
        var user3 = user.copy(lastName = "Cena", lastVisit = Date().add(2, TimeUnits.HOUR))

        println("""
            ${user.lastVisit?.format()}
            ${user1.lastVisit?.format()}
            ${user2.lastVisit?.format()}
            ${user3.lastVisit?.format()}
        """.trimIndent())
    }

    @Test
    fun test_data_mapping(){
        val user = User.makeUser("Pavel Durov")
        val userView = user.toUserView()
        val newUser = user.copy(lastVisit = Date().add(-7, TimeUnits.SECOND))
        userView.printMe()
    }

    @Test
    fun test_abstract_factoryg(){
        val user = User.makeUser("Pavel Durov")
        val txtMEssage = BaseMessage.makeMessage(user, Chat("0"), payLoad = "any text message", type = "text")
        val imgMEssage = BaseMessage.makeMessage(user, Chat("0"), payLoad = "any image url", type = "image")

        println(txtMEssage.formatMessage())
        println(imgMEssage.formatMessage())
    }
}
