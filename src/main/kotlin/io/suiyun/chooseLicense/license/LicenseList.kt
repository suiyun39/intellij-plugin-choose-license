package io.suiyun.chooseLicense.license

import io.suiyun.chooseLicense.utils.Resource

val LICENSE_FILE_LIST = Resource.getLicenseTemplateFiles()

val LICENSE_LIST = LICENSE_FILE_LIST.map { fileName ->
  val (spdxId, content) = Resource.parseLicenseTemplate(fileName)

  License(spdxId, content)
}
