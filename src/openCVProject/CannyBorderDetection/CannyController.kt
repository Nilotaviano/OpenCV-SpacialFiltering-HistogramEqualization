package openCVProject.CannyBorderDetection

import javafx.fxml.FXML
import javafx.scene.control.CheckBox
import javafx.scene.control.Slider
import javafx.scene.image.ImageView
import openCVProject.BorderDetectionState
import openCVProject.Context
import openCVProject.StateManager
import openCVProject.Utils
import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc

/**
 * Created by nilot on 20/03/2016.
 */
class CannyController {
    @FXML var imageView: ImageView? = null
    @FXML var chkBorderDetection: CheckBox? = null
    @FXML var threshholdSlider: Slider? = null

    private var grayScale: Mat? = null
    private var edges: Mat? = null

    fun initialize() {
        imageView?.image = Context.image

        grayScale = Mat()
        edges = Mat()
    }

    fun detectBordersSelectionChanged() {

        if (chkBorderDetection!!.isSelected) {
            var mat = Utils.imageToMat(Context.image!!);
            mat = this.doCanny(mat);
            imageView?.image = Utils.mat2Image(mat)
        } else {
            imageView?.image = Context.image
        }
    }

    private fun doCanny(frame: Mat): Mat {
        Imgproc.cvtColor(frame, grayScale, Imgproc.COLOR_BGR2GRAY)

        Imgproc.blur(grayScale, edges, Size(2.0, 2.0))

        Imgproc.Canny(edges, edges, this.threshholdSlider!!.value, this.threshholdSlider!!.value * 3)

        val result = Mat()
        frame.copyTo(result, edges)

        return result
    }
    fun returnToPreviousState() {
        StateManager.changeState(BorderDetectionState(), Context.stage!!)
    }
}