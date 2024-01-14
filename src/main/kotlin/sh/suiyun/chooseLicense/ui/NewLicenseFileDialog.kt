package sh.suiyun.chooseLicense.ui

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.ValidationInfo
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.*
import javax.swing.JComponent
import sh.suiyun.chooseLicense.utils.Storage

/**
 * 扩展 Cell<JBTextField>, 增加必填校验
 */
fun Cell<JBTextField>.validationRequired(fieldName: String): Cell<JBTextField> {
  return validationOnInput {
    if (it.text.isNullOrBlank()) {
      ValidationInfo("$fieldName is required", it)
    } else {
      null
    }
  }
}

/**
 * 新建 License 文件对话框
 */
class NewLicenseFileDialog : DialogWrapper(true) {
  var author = Storage.author
  var year = Storage.year
  var license = Storage.license
  var fileName = Storage.fileName

  init {
    title = "New License File"

    init()
  }

  /**
   * 创建对话框内容
   */
  override fun createCenterPanel(): JComponent {
    return panel {
      row("Author:") {
        textField().bindText(::author).validationRequired("Author").align(Align.FILL)
      }
      row("Year:") {
        textField().bindText(::year).validationRequired("Year").align(Align.FILL)
      }
      row("License:") {
        cell(LicenseCombo()).bindItem(::license.toNullableProperty()).align(Align.FILL)
      }
      row("FileName:") {
        textField().bindText(::fileName).validationRequired("FileName").align(Align.FILL)
      }
    }
  }

  /**
   * 允许保存和恢复对话框的状态
   */
  override fun getDimensionServiceKey(): String {
    return "sh.suiyun.chooseLicense.ui.NewLicenseFileDialog"
  }

  /**
   * 点击确定按钮时执行的方法
   */
  override fun doOKAction() {
    super.doOKAction()

    // 保存用户输入信息以供下次使用
    Storage.author = author
    Storage.year = year
    Storage.fileName = fileName
    Storage.license = license
  }
}
