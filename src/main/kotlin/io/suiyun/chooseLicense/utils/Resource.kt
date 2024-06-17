package io.suiyun.chooseLicense.utils

import java.io.File
import java.util.jar.JarFile
import java.net.URLDecoder

/**
 * 资源文件操作
 */
object Resource {
  /**
   * 获取 LICENSE 模板文件列表
   */
  fun getLicenseTemplateFiles(): List<String> {
    val folderURL = this.javaClass.getResource("/licenses")!!

    // 常规路径, 可以通过 File API 直接读取
    if (folderURL.protocol == "file") {
      val folder = File(folderURL.file)
      return folder.listFiles()?.sorted()?.map { it.name } ?: emptyList()
    }

    // jar 包内路径, 通过 JarFile API 读取
    if (folderURL.protocol == "jar") {
      val jarFilePath = URLDecoder.decode(folderURL.file.substring(5, folderURL.file.indexOf("!")), "UTF-8")

      JarFile(File(jarFilePath)).use { jarFile ->
        return jarFile.entries().asSequence()
          .filter { it.name.startsWith("licenses/") && it.name.endsWith(".txt") }
          .map { it.name.substringAfterLast("/") }
          .sorted()
          .toList()
      }
    }

    return emptyList()
  }

  /**
   * 解析 LICENSE 模板文件, 获取 spdx-id 和正文内容
   */
  fun parseLicenseTemplate(fileName: String): Pair<String, String> {
    val file = this.javaClass.getResource("/licenses/$fileName")
    val text = file?.readText() ?: ""
    val contents = "(?s)---\n(.*?)---\n(.*)".toRegex().find(text)

    // 提取 meta 信息
    val meta = contents?.groupValues?.get(1) ?: ""
    val spdxIdMatchResult = "spdx-id: (.*)".toRegex().find(meta)
    val spdxId = spdxIdMatchResult?.groupValues?.get(1) ?: ""

    // 提取正文
    var content = contents?.groupValues?.get(2) ?: ""

    while (content.startsWith("\n")) {
      content = content.replaceFirst("\n", "")
    }

    return Pair(spdxId, content)
  }
}
