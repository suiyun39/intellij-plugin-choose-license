package sh.catx.chooselicense.license

val LICENSE_LIST = listOf(
  License("MIT", "[fullname]", "[year]"),
  License("AGPL-3.0", "<name of author>", "<year>"),
  License("Apache-2.0", "[name of copyright owner]", "[yyyy]"),
  License("BSD-2-Clause", "[fullname]", "[year]"),
  License("BSD-3-Clause", "[fullname]", "[year]"),
  License("BSD-3-Clause-Clear", "[fullname]", "[year]"),
  License("BSD-4-Clause", "[fullname]", "[year]"),
  License("GPL-2.0", "<name of author>", "<year>"),
  License("GPL-3.0", "<name of author>", "<year>"),
  License("LGPL-2.1", "<name of author>", "<year>"),
  License("LGPL-3.0", null, null),
  License("MPL-2.0", null, null),
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
