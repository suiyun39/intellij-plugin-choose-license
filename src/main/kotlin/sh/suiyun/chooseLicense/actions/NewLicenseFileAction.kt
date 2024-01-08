package sh.suiyun.chooseLicense.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

/**
 * 新建 License 文件的 Action
 */
class NewLicenseFileAction : AnAction() {
  /**
   * Action 被触发时执行的方法
   */
  override fun actionPerformed(e: AnActionEvent) {
    println("NewLicenseFileAction")
  }
}
