package sh.suiyun.chooseLicense.utils

import com.goterl.resourceloader.FileLoader

/**
 * 资源文件操作
 */
object Resource {
  /**
   * 获取指定目录下的所有资源文件
   */
  fun getResourceFiles(path: String): List<String> {
    val folder = FileLoader.get().load(path, this.javaClass)

    return folder.listFiles()?.sorted()?.map { it.name } ?: emptyList()
  }

  /**
   * 解析 LICENSE 文件模板, 获取 spdx-id 和正文内容
   */
  fun parseLicenseTemplate(fileName: String): Pair<String, String> {
    val file = this.javaClass.getResource("/licenses/$fileName")
    val text = file?.readText() ?: ""

    // 拆分 meta 和正文
    val matchResult = "(?s)---(.*?)---(.*)".toRegex().find(text)
    val meta = matchResult?.groupValues?.get(1) ?: ""
    val content = matchResult?.groupValues?.get(2)?.trimStart() ?: ""

    // 获取 spdx-id
    val spdxIdMatchResult = "spdx-id: (.*)".toRegex().find(meta)
    val spdxId = spdxIdMatchResult?.groupValues?.get(1) ?: ""

    return Pair(spdxId, content)
  }
}
