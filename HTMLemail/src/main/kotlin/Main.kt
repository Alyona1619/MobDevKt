import java.io.File

fun main() {
    data class Message(val address: String?, val topic: String?, val text: String?, val date: String?) {
        fun toHTML(): String {
            var template = "<table style=\"font-family: Arial, sans-serif; background-color: #f2f2f2; padding: 10px; border-collapse: collapse; border: 1px solid #000; width: 40%; margin-top: 10px;\">"
            // ниже используется интерполяция строк (подстановка переменных как в PHP/Python)
            address?.let { template += "<tr><td style=\"border-right: 1px solid #000; padding: 5px;\">address</td><td>$it</td></tr> \n" }
            topic?.let { template += "<tr><td style=\"border-right: 1px solid #000; padding: 5px;\">topic</td><td>$it</td></tr> \n" }
            text?.let { template += "<tr><td style=\"border-right: 1px solid #000; padding: 5px;\">text:</td><td>$it</td></tr>" }
            date?.let { template += "<tr><td style=\"border-right: 1px solid #000; padding: 5px;\">date:</td><td>$it</td></tr>" }
            template += "</table>"
            return template
        }
    }

    val m = Message("askbill@microsoft.com", null, "Hello, my friend!", "19.10.2023")
    val e = m.toHTML()
    File("email.html").writeText(e)
}