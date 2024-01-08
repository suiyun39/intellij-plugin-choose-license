package sh.suiyun.chooseLicense.ui

import com.intellij.openapi.ui.DialogWrapper
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import javax.swing.JComponent

/**
 * 新建 License 文件对话框
 */
class NewLicenseFileDialog : DialogWrapper(true) {
  init {
    title = "New License File"

    init()
    disableHeightResize()
  }

  /**
   * 创建对话框内容
   */
  override fun createCenterPanel(): JComponent? {
    return null
  }

  /**
   * 允许保存和恢复对话框的状态
   */
  override fun getDimensionServiceKey(): String {
    return "sh.suiyun.chooseLicense.ui.NewLicenseFileDialog"
  }

  /**
   * 禁止调整对话框高度
   */
  private fun disableHeightResize() {
    val listener = object : ComponentAdapter() {
      override fun componentResized(evt: ComponentEvent) {
        // 这里 height 设置为 0 不会影响组件撑起对话框的高度
        window.setSize(evt.component.width, 0)
      }
    }

    window.addComponentListener(listener)
  }

  /**
   * 点击确定按钮时执行的方法
   */
  override fun doOKAction() {
    super.doOKAction()
  }
}
