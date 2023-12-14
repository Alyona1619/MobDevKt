import com.google.gson.Gson
import com.google.gson.JsonObject
import com.opencsv.CSVReaderBuilder
import java.io.FileReader
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*
import java.io.File

data class AppInfo(
    val name: String,
    val category: String,
    val rating: String,
    val reviews: String,
    val size: String,
    val installs: String,
    val type: String,
    val price: String,
    val contentRating: String,
    val genres: List<String>,
    val lastUpdated: String,
    val currentVer: String,
    val androidVer: Int
)

fun parseCsvLine(line: Array<String>): AppInfo {
    return AppInfo(
        line[0],
        line[1],
        line[2],
        line[3],
        line[4],
        line[5],
        line[6],
        line[7],
        line[8],
        line[9].split(";"),
        formatDate(line[10]),
        line[11],
        mapAndroidVersion(line[12])
    )
}

fun translateCategoryToRussian(category: String): String {
    val categoryTranslations = mapOf(
        "ART_AND_DESIGN" to "ИСКУССТВО_И_ДИЗАЙН",
        "AUTO_AND_VEHICLES" to "АВТО_И_ТРАНСПОРТНЫЕ_СРЕДСТВА",
        "BEAUTY" to "КРАСОТА",
        "BOOKS_AND_REFERENCE" to "КНИГИ_И_СПРАВОЧНИКИ",
        "BUSINESS" to "БИЗНЕС",
        "COMICS" to "КОМИКСЫ",
        "COMMUNICATION" to "ОБЩЕНИЕ",
        "DATING" to "СВИДАНИЯ",
        "EDUCATION" to "ОБРАЗОВАНИЕ",
        "PRODUCTIVITY" to "Продуктивность",
        "GAME" to "Игры",
        "TOOLS" to "Инструменты",
        // Добавьте другие темы по мере необходимости
    )

    return categoryTranslations.getOrDefault(category, category)
}

fun formatDate(date: String): String {
    val inputFormat = SimpleDateFormat("MMMM d, yyyy", Locale.US)
    val outputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
    val parsedDate = inputFormat.parse(date)
    return outputFormat.format(parsedDate)
}


fun androidVersionToApi(version: Double): Int {
    return when (version) {
        in 1.0..1.5 -> 1
        in 1.5..1.6 -> 3
        in 1.6..2.0 -> 4
        in 2.0..2.1 -> 5
        in 2.1..2.2 -> 7
        in 2.2..2.3 -> 8
        in 2.3..3.0 -> 9
        in 3.0..3.1 -> 11
        in 3.1..3.2 -> 12
        in 3.2..4.0 -> 13
        in 4.0..4.1 -> 14
        in 4.1..4.2 -> 16
        in 4.2..4.3 -> 17
        in 4.3..4.4 -> 18
        in 4.4..5.0 -> 19
        in 5.0..5.1 -> 21
        in 5.1..6.0 -> 22
        in 6.0..7.0 -> 23
        in 7.0..7.1 -> 24
        in 7.1..8.0 -> 25
        in 8.0..8.1 -> 26
        in 8.1..9.0 -> 27
        in 9.0..10.0 -> 28
        in 10.0..11.0 -> 29
        in 11.0..12.0 -> 30
        else -> -1 // Handle unsupported versions as needed
    }
}

fun mapAndroidVersion(androidVer: String): Int {
    val versionRegex = "\\d+(\\.\\d+)?".toRegex()
    val version = versionRegex.find(androidVer)?.value ?: "0"
    return when (version) {
        "Varies with device" -> 0
        else -> androidVersionToApi(version.toDouble())
    }
}

fun processCsvFile(csvFile: String): List<AppInfo> {
    val apps = mutableListOf<AppInfo>()

    val reader = CSVReaderBuilder(File(csvFile).reader()).withSkipLines(1).build()

    reader.use { csvReader ->
        csvReader.forEachIndexed { index, line ->
            try {
                val app = parseCsvLine(line)
                apps.add(app)
            } catch (e: Exception) {
                println("Error parsing line $index: ${e.message}")
            }
        }
    }

    return apps
}

fun writeJsonToFile(json: JsonObject, outputFile: String) {
    File(outputFile).bufferedWriter().use { fileWriter ->
        Gson().toJson(json, fileWriter)
    }
}


fun main() {
    val csvFile = "./src/main/resources/googleplaystore.csv"
    val outputFile = "./src/main/resources/googleplaystore.json"

    val apps = processCsvFile(csvFile)

    val jsonResult = JsonObject()
    val groupedApps = apps.groupBy { it.category }
    groupedApps.forEach { (category, appsInCategory) ->
        val translatedCategory = translateCategoryToRussian(category)
        val jsonCategory = JsonObject()
        jsonCategory.addProperty("category", translatedCategory)
        jsonCategory.add("apps", Gson().toJsonTree(appsInCategory))
        jsonResult.add(translatedCategory, jsonCategory)
    }

    writeJsonToFile(jsonResult, outputFile)

    println("Output written to $outputFile")
}
