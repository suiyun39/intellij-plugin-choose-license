package sh.suiyun.chooseLicense.license

class License(val spdxId: String, val content: String) {
  private fun replaceAuthor(author: String, text: String): String {
    when (spdxId) {
      "AGPL-3.0", "GPL-2.0", "GPL-3.0", "LGPL-2.1" -> {
        return text.replace("<name of author>", author)
      }

      "Apache-2.0" -> {
        return text.replace("[name of copyright owner]", author)
      }

      "BSD-2-Clause", "BSD-3-Clause", "BSD-3-Clause-Clear", "BSD-4-Clause", "MIT", "MIT-0", "ISC" -> {
        return text.replace("[fullname]", author)
      }

      "WTFPL" -> {
        return text.replace("Sam Hocevar <sam@hocevar.net>", author)
      }

      else -> {
        return text
      }
    }
  }

  private fun replaceYear(year: String, text: String): String {
    when (spdxId) {
      "AGPL-3.0", "GPL-2.0", "GPL-3.0", "LGPL-2.1" -> {
        return text.replace("<year>", year)
      }

      "Apache-2.0" -> {
        return text.replace("[yyyy]", year)
      }

      "BSD-2-Clause", "BSD-3-Clause", "BSD-3-Clause-Clear", "BSD-4-Clause", "MIT", "MIT-0", "ISC" -> {
        return text.replace("[year]", year)
      }

      "WTFPL" -> {
        return text.replace("Copyright (C) 2004", "Copyright (C) $year")
      }

      else -> {
        return text
      }
    }
  }

  /**
   * 生成 license
   */
  fun generateLicense(author: String, year: String): String {
    val text = replaceAuthor(author, content)
    return replaceYear(year, text)
  }

  override fun toString(): String {
    return spdxId
  }
}
