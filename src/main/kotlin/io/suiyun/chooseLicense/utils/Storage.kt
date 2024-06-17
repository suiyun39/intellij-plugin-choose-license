package io.suiyun.chooseLicense.utils

import com.intellij.ide.util.PropertiesComponent
import java.time.Year
import io.suiyun.chooseLicense.license.License
import io.suiyun.chooseLicense.license.LICENSE_LIST

/**
 * 存储用户输入信息
 */
object Storage {
  private var properties: PropertiesComponent = PropertiesComponent.getInstance()

  private const val AUTHOR_KEY = "io.suiyun.chooseLicense.author"
  private const val YEAR_KEY = "io.suiyun.chooseLicense.year"
  private const val FILE_NAME_KEY = "io.suiyun.chooseLicense.fileName"
  private const val LICENSE_KEY = "io.suiyun.chooseLicense.license"

  var author: String
    get() = properties.getValue(AUTHOR_KEY) ?: System.getProperty("user.name") ?: ""
    set(value) = properties.setValue(AUTHOR_KEY, value)

  var year: String
    get() = properties.getValue(YEAR_KEY) ?: Year.now().value.toString()
    set(value) = properties.setValue(YEAR_KEY, value)

  var fileName: String
    get() = properties.getValue(FILE_NAME_KEY) ?: "LICENSE"
    set(value) = properties.setValue(FILE_NAME_KEY, value)

  var license: License
    get() {
      val spdxId = properties.getValue(LICENSE_KEY) ?: "MIT"
      return LICENSE_LIST.find { it.spdxId == spdxId } ?: LICENSE_LIST[0]
    }
    set(value) {
      properties.setValue(LICENSE_KEY, value.spdxId)
    }
}
