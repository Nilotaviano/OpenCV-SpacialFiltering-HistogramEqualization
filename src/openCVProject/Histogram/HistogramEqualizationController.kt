package openCVProject.Histogram

import javafx.fxml.FXML
import javafx.scene.control.ScrollPane
import javafx.scene.image.ImageView
import openCVProject.*
import org.opencv.core.*
import org.opencv.imgproc.Imgproc
import java.util.*

class HistogramEqualizationController {
    @FXML var histogramBefore: ImageView? = null
    @FXML var beforeImage: ImageView? = null
    @FXML var histogramAfter: ImageView? = null
    @FXML var afterImage: ImageView? = null
    @FXML var beforeImgScroll: ScrollPane? = null
    @FXML var afterImgScroll: ScrollPane? = null

    fun initialize() {
        val mat = ImageUtils.convertToGrayScale(ImageUtils.imageToMat(Context.images.last()))
        val equalizedMat = equalizeHistogram(mat)
        histogramBefore?.image = ImageUtils.mat2Image(calculateHistogram(mat))
        beforeImage?.image = ImageUtils.mat2Image(mat)
        histogramAfter?.image = ImageUtils.mat2Image(calculateHistogram(equalizedMat))
        var processedImage = ImageUtils.mat2Image(equalizedMat)
        afterImage?.image = processedImage
        Context.images.add(processedImage)
        UIUtils.setZoomScroll(beforeImage!!, beforeImgScroll!!)
        UIUtils.setZoomScroll(afterImage!!, afterImgScroll!!)
    }

    private fun calculateHistogram(imageMat: Mat): Mat {
        val images = ArrayList<Mat>()
        Core.split(imageMat, images)

        val histSize = MatOfInt(256)
        val channels = MatOfInt(0)
        val histRange = MatOfFloat(0.0f, 256.0f)

        val b = Mat()

        Imgproc.calcHist(images.subList(0, 1), channels, Mat(), b, histSize, histRange, false)

        val width = 600
        val height = 300
        val bin_w = Math.round(width / histSize.get(0, 0)[0]).toInt()

        val histogramMat = Mat(height, width, CvType.CV_8UC3, Scalar(0.0, 0.0, 0.0))
        Core.normalize(b, b, 0.0, histogramMat.rows().toDouble(), Core.NORM_MINMAX, -1, Mat())

        var i = 1
        while (i < histSize.get(0, 0)[0]) {
            Imgproc.line(histogramMat, Point((bin_w * (i - 1)).toDouble(), (height - Math.round(b.get(i - 1, 0)[0])).toDouble()),
                    Point((bin_w * i).toDouble(), (height - Math.round(b.get(i, 0)[0])).toDouble()), Scalar(255.0, 0.0, 0.0), 1, 8, 0)
            i++
        }

        return histogramMat
    }

    // This is ugly as f, do not look
    private fun equalizeHistogram(imageMat: Mat): Mat {
        var equalizedMat = Mat(imageMat.rows(), imageMat.cols(), imageMat.type())

        //map pixel intensity to occurrence
        val intensityOccurrence = TreeMap<Double, Int>()
        var numberOfPixels = 0.0

        for (row in 0..imageMat.rows() - 1) {
            for (col in 0..imageMat.cols() - 1) {

                intensityOccurrence[imageMat.get(row, col).first()] =
                        if (intensityOccurrence[imageMat.get(row, col).first()] == null) {
                            1
                        } else {
                            intensityOccurrence[imageMat.get(row, col).first()]!! + 1
                        }

                numberOfPixels++
            }
        }

        val maxPossibleValue = 255   //It's already sorted
        val pn = intensityOccurrence.map { kv -> Pair(kv.key, kv.value / numberOfPixels) }
        val sn = TreeMap<Double, Double>()

        sn[pn.first().first] = pn.first().second

        for (i in 1..pn.size - 1) {
            sn[pn[i].first] = pn[i].second + sn[pn[i - 1].first]!!
        }

        for (row in 0..imageMat.rows() - 1) {
            for (col in 0..imageMat.cols() - 1) {
                equalizedMat.put(row, col,
                        *doubleArrayOf(((sn[imageMat.get(row, col).first()]!! * maxPossibleValue) + 0.5).toInt().toDouble()))
            }
        }
        return equalizedMat
    }

    fun returnToPreviousState() {
        StateManager.changeState(MainMenuState(), Context.stage!!)
    }
}