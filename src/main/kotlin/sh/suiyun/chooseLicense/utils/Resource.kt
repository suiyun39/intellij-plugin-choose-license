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
}
