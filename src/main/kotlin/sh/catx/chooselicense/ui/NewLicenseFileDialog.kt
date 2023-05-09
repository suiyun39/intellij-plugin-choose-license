package sh.catx.chooselicense.ui

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.ValidationInfo
import com.intellij.ui.dsl.builder.*
import sh.catx.chooselicense.license.LICENSE_LIST
import sh.catx.chooselicense.license.License
import java.awt.Component
import java.time.Year
import javax.swing.DefaultListCellRenderer
import javax.swing.JComponent
import javax.swing.JList

class NewLicenseFileDialog : DialogWrapper(true) {
  var author = System.getProperty("user.name") ?: ""
  var year = Year.now().value.toString()
  var license = LICENSE_LIST[0]
  var fileName = "LICENSE"

  private val formPanel = panel {
    row("Author:") {
      textField()
        .bindText(::author)
        .validationOnInput { if (it.text.isNullOrBlank()) ValidationInfo("Author is required", it) else null }
        .align(Align.FILL)
    }
    row("Year:") {
      textField()
        .bindText(::year)
        .validationOnInput { if (it.text.isNullOrBlank()) ValidationInfo("Year is required", it) else null }
        .align(Align.FILL)
    }
    row("License:") {
      comboBox(LICENSE_LIST, LicenseListCellRenderer())
        .bindItem(::license.toNullableProperty())
        .align(Align.FILL)
    }
    row("FileName:") {
      textField()
        .bindText(::fileName)
        .validationOnInput { if (it.text.isNullOrBlank()) ValidationInfo("FileName is required", it) else null }
        .align(Align.FILL)
    }
  }

  init {
    title = "New License File"
    init()
  }

  override fun createCenterPanel(): JComponent {
    return formPanel
  }

  override fun doOKAction() {
    formPanel.apply()
    super.doOKAction()
  }
}

class LicenseListCellRenderer : DefaultListCellRenderer() {
  override fun getListCellRendererComponent(
    list: JList<*>?,
    value: Any?,
    index: Int,
    isSelected: Boolean,
    cellHasFocus: Boolean
  ): Component {
    val label = if (value is License) "${value.type} License" else ""
    return super.getListCellRendererComponent(list, label, index, isSelected, cellHasFocus)
  }
}
