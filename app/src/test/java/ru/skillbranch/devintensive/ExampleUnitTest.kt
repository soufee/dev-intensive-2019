package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.*
import ru.skillbranch.devintensive.models.*
import ru.skillbranch.devintensive.utils.Utils
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
        val txtMEssage = BaseMessage.makeMessage(user, Chat("0"), payload = "any text message", type = "text")
        val imgMEssage = BaseMessage.makeMessage(user, Chat("0"), payload = "any image url", type = "image")

        println(txtMEssage.formatMessage())
        println(imgMEssage.formatMessage())
    }

    @Test
    fun parseFullName_test(){
        val user1 =  Utils.parseFullName(null)
        val user2 = Utils.parseFullName("")
        val user3 = Utils.parseFullName(" ")
        val user4 = Utils.parseFullName("Pavel")
        val user5 = Utils.parseFullName("Pavel Durov")

        println(user1)
        println(user2)
        println(user3)
        println(user4)
        println(user5)
    }

    @Test
    fun toInitials_test(){
        val jd = Utils.toInitials("john", "doe") //JD
        val j = Utils.toInitials("John", null) //J
        val null1 = Utils.toInitials(null, null) //null
        val null2 = Utils.toInitials(" ", "") //null
        val null3 = Utils.toInitials(" ", " ") //null
        val null4 = Utils.toInitials("", " ") //null
        val null5 = Utils.toInitials("", "") //null
        println(jd)
        println(j)
        println(null1)
        println(null2)
        println(null3)
        println(null4)
        println(null5)
        assertEquals("JD", jd)
        assertEquals("J", j)
        assertNull(null, null1)
        assertNull(null, null2)
        assertNull(null, null3)
        assertNull(null, null4)
        assertNull(null, null5)
    }

    @Test
    fun transliteration_test(){
        println(Utils.transliteration("Ашамаз Шомахов"))
        println(Utils.transliteration("Лалина Шомахова", "_"))
        println(Utils.transliteration("Зарина", "_"))
        println(Utils.transliteration("Ирбек"))
        println(Utils.transliteration(""))
    }

    @Test
    fun humanizeDiff_test(){
        val now = Date()
        println("${now.format()} ${now.humanizeDiff()}") //только что
        val after2Sec = Date().add(-2, TimeUnits.SECOND)
        println("${after2Sec.format()} ${after2Sec.humanizeDiff()}") //несколько секунд назад
        val after50Sec = Date().add(-50, TimeUnits.SECOND)
        println("${after50Sec.format()} ${after50Sec.humanizeDiff()}") //минуту назад
        val after73Sec = Date().add(-73, TimeUnits.SECOND)
        println("${after73Sec.format()} ${after73Sec.humanizeDiff()}") //минуту назад
        val after88Sec = Date().add(-88, TimeUnits.SECOND)
        println("${after88Sec.format()} ${after88Sec.humanizeDiff()}") // 2 минуты назад
        val after20Min = Date().add(-20, TimeUnits.MINUTE)
        println("${after20Min.format()} ${after20Min.humanizeDiff()}") // 20 минут назад
        val after50Min = Date().add(-50, TimeUnits.MINUTE)
        println("${after50Min.format()} ${after50Min.humanizeDiff()}") //час назад
        val after73Min = Date().add(-73, TimeUnits.MINUTE)
        println("${after73Min.format()} ${after73Min.humanizeDiff()}") //час назад
        val after88Min = Date().add(-88, TimeUnits.MINUTE)
        println("${after88Min.format()} ${after88Min.humanizeDiff()}") // два часа назад
        val after2Hour = Date().add(-2, TimeUnits.HOUR)
        println("${after2Hour.format()} ${after2Hour.humanizeDiff()}") // два часа назад
        val after21Hour = Date().add(-21, TimeUnits.HOUR)
        println("${after21Hour.format()} ${after21Hour.humanizeDiff()}") // 21 час назад
        val after27Hour = Date().add(-27, TimeUnits.HOUR)
        println("${after27Hour.format()} ${after27Hour.humanizeDiff()}") //день назад -
        val after2Day = Date().add(-2, TimeUnits.DAY)
        println("${after2Day.format()} ${after2Day.humanizeDiff()}") //два дня назад
        val after350Day = Date().add(-350, TimeUnits.DAY)
        println("${after350Day.format()} ${after350Day.humanizeDiff()}") //350 дней назад
        val after370Day = Date().add(-370, TimeUnits.DAY)
        println("${after370Day.format()} ${after370Day.humanizeDiff()}") // более года назад

        println(Date().add(-2, TimeUnits.HOUR).humanizeDiff()) //2 часа назад
        println(Date().add(-5, TimeUnits.DAY).humanizeDiff()) //5 дней назад
        println(Date().add(2, TimeUnits.MINUTE).humanizeDiff()) //через 2 минуты
        println(Date().add(7, TimeUnits.DAY).humanizeDiff()) //через 7 дней
        println(Date().add(-400, TimeUnits.DAY).humanizeDiff()) //более года назад
        println(Date().add(400, TimeUnits.DAY).humanizeDiff()) //более чем через год
    }

    @Test
    fun plural_test(){
        println(TimeUnits.SECOND.plural(1)) //1 секунду
        println(TimeUnits.MINUTE.plural(4)) //4 минуты
        println(TimeUnits.HOUR.plural(19)) //19 часов
        println(TimeUnits.DAY.plural(222)) //222 дня
    }


    @Test
    fun stripHtml_test(){
        println("""<p class="title">Образовательное IT-сообщество Skill Branch</p>""".stripHtml())
        println("<p>Образовательное       IT-сообщество Skill Branch</p>".stripHtml())
    }

    @Test
    fun truncate_test(){
        println("Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate()) //Bender Bending R...
        println("Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate(15)) //Bender Bending...
        println("A     ".truncate(3)) //A
    }

    @Test
    fun builder_test(){
        val buildUser = User.Builder().id("1")
            .firstName("sdsad")
            .lastName("dfgd")
            .avatar("")
            .rating(1)
            .respect(0)
            .lastVisit(Date().add(-1, TimeUnits.DAY))
            .isOnline(false)
            .build()

        println(buildUser)
    }
}
