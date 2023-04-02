package sh.catx.chooselicense.license

val LICENSE_LIST = listOf(
  License("MIT", "[fullname]", "[year]"),
  License("AGPL-3.0", "<name of author>", "<year>"),
)

class License(
  val type: String,
  private val authorTag: String?,
  private val yearTag: String?,
) {
  fun generate(author: String, year: String): String {
    val templateFile = this.javaClass.getResource("/license/$type.txt")
    var template = templateFile?.readText() ?: ""

    if (authorTag != null) {
      template = template.replace(authorTag, author)
    }

    if (yearTag != null) {
      template = template.replace(yearTag, year)
    }

    return template
  }
}
