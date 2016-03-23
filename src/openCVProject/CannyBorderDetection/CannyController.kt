package openCVProject.CannyBorderDetection

import javafx.fxml.FXML
import javafx.scene.control.CheckBox
import javafx.scene.control.Slider
import javafx.scene.image.ImageView
import openCVProject.BorderDetectionState
import openCVProject.Context
import openCVProject.StateManager

/**
 * Created by nilot on 20/03/2016.
 */
class CannyController {
    @FXML var imageView: ImageView? = null
    @FXML var chkBorderDetection: CheckBox? = null
    @FXML var threshholdSlider: Slider? = null

    fun initialize() {
        threshholdSlider!!.disableProperty().bind(chkBorderDetection?.selectedProperty())
        imageView?.image = Context.image
    }

    fun detectBordersSelectionChanged() {
    }

    fun returnToPreviousState() {
        StateManager.changeState(BorderDetectionState(), Context.stage!!)
    }
}