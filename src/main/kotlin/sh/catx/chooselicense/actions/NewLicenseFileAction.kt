package sh.catx.chooselicense.actions

import com.intellij.openapi.actionSystem.*

class NewLicenseFileAction : AnAction() {
  // action 触发点
  override fun actionPerformed(e: AnActionEvent) {
    println("actionPerformed")
  }
}
