package openCVProject.Histogram

import javafx.fxml.FXML
import javafx.scene.image.ImageView
import openCVProject.Context
import openCVProject.MainMenuState
import openCVProject.StateManager
import openCVProject.Utils
import org.opencv.core.*
import org.opencv.imgproc.Imgproc
import java.util.*

/**
 * Created by nilot on 20/03/2016.
 */
class HistogramEqualizationController {
    @FXML var histogramBefore: ImageView? = null
    @FXML var imageBefore: ImageView? = null
    @FXML var histogramAfter: ImageView? = null
    @FXML var imageAfter: ImageView? = null

    fun initialize() {
        val mat = Utils.convertToGrayScale(Utils.imageToMat(Context.image!!))
        val equalizedMat = equalizeHistogram(mat)
        histogramBefore?.image = Utils.mat2Image(calculateHistogram(mat))
        imageBefore?.image = Utils.mat2Image(mat)
        histogramAfter?.image = Utils.mat2Image(calculateHistogram(equalizedMat))
        imageAfter?.image = Utils.mat2Image(equalizedMat)
    }

    private fun calculateHistogram(imageMat: Mat): Mat {
        val images = ArrayList<Mat>()
        Core.split(imageMat, images)

        val histSize = MatOfInt(256)
        val channels = MatOfInt(0)
        val histRange = MatOfFloat(0.0f, 256.0f)

        val b = Mat()

        Imgproc.calcHist(images.subList(0, 1), channels, Mat(), b, histSize, histRange, false)

        val width = 400
        val height = 400
        val bin_w = Math.round(width / histSize.get(0, 0)[0]).toInt()

        val histogramMat = Mat(height, width, CvType.CV_8UC3, Scalar(0.0, 0.0, 0.0))
        Core.normalize(b, b, 0.0, histogramMat.rows().toDouble(), Core.NORM_MINMAX, -1, Mat())

        var i = 1
        while (i < histSize.get(0, 0)[0]) {
            Imgproc.line(histogramMat, Point((bin_w * (i - 1)).toDouble(), (height - Math.round(b.get(i - 1, 0)[0])).toDouble()),
                    Point((bin_w * i).toDouble(), (height - Math.round(b.get(i, 0)[0])).toDouble()), Scalar(255.0, 0.0, 0.0), 2, 8, 0)
            i++
        }

        return histogramMat
    }

    private fun equalizeHistogram(imageMat: Mat): Mat {
        var equalizedMat = Mat()
        Imgproc.equalizeHist(imageMat, equalizedMat)

        return equalizedMat
    }

    fun returnToPreviousState() {
        StateManager.changeState(MainMenuState(), Context.stage!!)
    }
}