package sh.suiyun.chooseLicense.license

import sh.suiyun.chooseLicense.utils.Resource

val LICENSE_FILE_LIST = Resource.getResourceFiles("/licenses")

val LICENSE_LIST = LICENSE_FILE_LIST.map { fileName ->
  val (spdxId, content) = Resource.parseLicenseTemplate(fileName)

  License(spdxId, content)
}
