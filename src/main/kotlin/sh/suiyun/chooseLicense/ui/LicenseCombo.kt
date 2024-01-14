package sh.suiyun.chooseLicense.ui

import com.intellij.openapi.ui.ComboBox
import javax.swing.DefaultComboBoxModel
import sh.suiyun.chooseLicense.license.License
import sh.suiyun.chooseLicense.license.LICENSE_LIST

/**
 * LICENSE 选择框
 */
class LicenseCombo : ComboBox<License>() {
  init {
    model = DefaultComboBoxModel(LICENSE_LIST.toTypedArray())
    isSwingPopup = false
  }
}
