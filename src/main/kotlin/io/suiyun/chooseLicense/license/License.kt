package io.suiyun.chooseLicense.license

class License(val spdxId: String, val content: String) {
  /**
   * 生成 license
   */
  fun generateLicense(author: String, year: String): String {
    when (spdxId) {
      "AGPL-3.0", "GPL-2.0", "GPL-3.0", "LGPL-2.1" -> {
        return content
          .replace("<name of author>", author)
          .replace("<year>", year)
      }

      "Apache-2.0" -> {
        return content
          .replace("[name of copyright owner]", author)
          .replace("[yyyy]", year)
      }

      "0BSD", "BSD-2-Clause", "BSD-2-Clause-Patent", "BSD-3-Clause", "BSD-3-Clause-Clear", "BSD-4-Clause",
      "MIT", "MIT-0", "ISC", "NCSA", "OFL-1.1", "PostgreSQL", "UPL-1.0", "Zlib" -> {
        return content
          .replace("[fullname]", author)
          .replace("[year]", year)
      }

      "WTFPL" -> {
        return content
          .replace("Sam Hocevar <sam@hocevar.net>", author)
          .replace("Copyright (C) 2004", "Copyright (C) $year")
      }

      else -> {
        return content
      }
    }
  }

  override fun toString(): String {
    return spdxId
  }
}
