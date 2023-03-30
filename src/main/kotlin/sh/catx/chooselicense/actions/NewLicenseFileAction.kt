package sh.catx.chooselicense.actions

import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.application.runWriteAction
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFile
import sh.catx.chooselicense.ui.NewLicenseFileDialog

class NewLicenseFileAction : AnAction() {
  // action 触发点
  override fun actionPerformed(e: AnActionEvent) {
    val dialog = NewLicenseFileDialog()
    val view = e.getRequiredData(LangDataKeys.IDE_VIEW)
    val directory = view.orChooseDirectory ?: return

    if (dialog.showAndGet()) {
      val fileName = dialog.fileName
      val content = dialog.license.generate(dialog.author, dialog.year)
      val file = createFile(directory, fileName, content)
      view.selectElement(file)
    }
  }

  private fun createFile(directory: PsiDirectory, fileName: String, content: String): PsiFile {
    return runWriteAction {
      var file = directory.findFile(fileName)

      if (file == null) {
        file = directory.createFile(fileName)
      }

      file.virtualFile.setBinaryContent(content.toByteArray())
      file
    }
  }
}
