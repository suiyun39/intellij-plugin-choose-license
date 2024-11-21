package io.suiyun.chooseLicense.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.application.runWriteAction
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFile
import io.suiyun.chooseLicense.ui.NewLicenseFileDialog

/**
 * 新建 License 文件的 Action
 */
class NewLicenseFileAction : AnAction() {
  /**
   * Action 被触发时执行的方法
   */
  override fun actionPerformed(e: AnActionEvent) {
    val dialog = NewLicenseFileDialog()
    val view = e.getData(LangDataKeys.IDE_VIEW) ?: return
    val directory = view.orChooseDirectory ?: return

    if (dialog.showAndGet()) {
      val fileName = dialog.fileName
      val content = dialog.license.generateLicense(dialog.author, dialog.year)
      val file = createFile(directory, fileName, content)
      view.selectElement(file)
    }
  }

  private fun createFile(directory: PsiDirectory, fileName: String, content: String): PsiFile {
    return runWriteAction {
      val file = directory.findFile(fileName) ?: directory.createFile(fileName)

      file.virtualFile.setBinaryContent(content.toByteArray())
      file
    }
  }
}
