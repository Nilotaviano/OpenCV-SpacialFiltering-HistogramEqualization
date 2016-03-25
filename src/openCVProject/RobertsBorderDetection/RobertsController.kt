package openCVProject.RobertsBorderDetection

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ScrollPane
import javafx.scene.control.Slider
import javafx.scene.image.ImageView
import openCVProject.*
import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc

class RobertsController {
    @FXML var imageView: ImageView? = null
    @FXML var btnDetectBorder: Button? = null
    @FXML var threshholdSlider: Slider? = null
    @FXML var imgScroll: ScrollPane? = null

    private var grayScale: Mat? = null
    private var edges: Mat? = null

    private var processedImage: Mat? = null
    private var lastThreshholdValue: Double = -1.0

    fun initialize() {
        imageView?.image = Context.image
        UIUtils.setZoomScroll(imageView!!, imgScroll!!)

        grayScale = Mat()
        edges = Mat()
    }

    fun detectBorders() {
        if (lastThreshholdValue != threshholdSlider?.value) {
            processedImage = ImageUtils.imageToMat(Context.image!!)
            processedImage = this.doCanny(processedImage!!)
            lastThreshholdValue = threshholdSlider!!.value
        }
        imageView?.image = ImageUtils.mat2Image(processedImage!!)
    }

    fun restoreImage() {
        imageView?.image = Context.image
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