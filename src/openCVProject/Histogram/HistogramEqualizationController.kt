package openCVProject.Histogram

import javafx.fxml.FXML
import javafx.scene.image.Image
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
        val image = Context.image!!
        val equalizedImage = equalizeHistogram(image)
        histogramBefore?.image = calculateHistogram(image)
        imageBefore?.image = image
        histogramAfter?.image = calculateHistogram(equalizedImage)
        imageAfter?.image = equalizedImage
    }

    private fun calculateHistogram(image: Image): Image {
        val imageMat: Mat = Utils.imageToMat(image)
        val images = ArrayList<Mat>()
        Core.split(imageMat, images)

        val histSize = MatOfInt(256)
        val channels = MatOfInt(0)
        val histRange = MatOfFloat(0.0f, 256.0f)

        val r = Mat()
        val g = Mat()
        val b = Mat()

        Imgproc.calcHist(images.subList(0, 1), channels, Mat(), b, histSize, histRange, false)
        Imgproc.calcHist(images.subList(1, 2), channels, Mat(), g, histSize, histRange, false)
        Imgproc.calcHist(images.subList(2, 3), channels, Mat(), r, histSize, histRange, false)

        val width = 150
        val height = 150
        val bin_w = Math.round(width / histSize.get(0, 0)[0]).toInt()

        val histogramMat = Mat(height, width, CvType.CV_8UC3, Scalar(0.0, 0.0, 0.0))
        Core.normalize(b, b, 0.0, histogramMat.rows().toDouble(), Core.NORM_MINMAX, -1, Mat())
        Core.normalize(g, g, 0.0, histogramMat.rows().toDouble(), Core.NORM_MINMAX, -1, Mat())
        Core.normalize(r, r, 0.0, histogramMat.rows().toDouble(), Core.NORM_MINMAX, -1, Mat())

        var i = 1
        while (i < histSize.get(0, 0)[0]) {
            Imgproc.line(histogramMat, Point((bin_w * (i - 1)).toDouble(), (height - Math.round(r.get(i - 1, 0)[0])).toDouble()),
                    Point((bin_w * i).toDouble(), (height - Math.round(r.get(i, 0)[0])).toDouble()), Scalar(0.0, 0.0, 255.0), 2, 8,
                    0)
            Imgproc.line(histogramMat, Point((bin_w * (i - 1)).toDouble(), (height - Math.round(b.get(i - 1, 0)[0])).toDouble()),
                    Point((bin_w * i).toDouble(), (height - Math.round(b.get(i, 0)[0])).toDouble()), Scalar(255.0, 0.0, 0.0), 2, 8, 0)
            Imgproc.line(histogramMat, Point((bin_w * (i - 1)).toDouble(), (height - Math.round(g.get(i - 1, 0)[0])).toDouble()),
                    Point((bin_w * i).toDouble(), (height - Math.round(g.get(i, 0)[0])).toDouble()), Scalar(0.0, 255.0, 0.0), 2, 8,
                    0)
            i++
        }

        return Utils.mat2Image(histogramMat)
    }

    private fun equalizeHistogram(image: Image): Image {
        val imageMat: Mat = Utils.imageToMat(image)
        //        Imgproc.equalizeHist(imageMat, imageMat)
        return Utils.mat2Image(imageMat)
    }

    fun returnToPreviousState() {
        StateManager.changeState(MainMenuState(), Context.stage!!)
    }
}