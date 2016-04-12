package openCVProject.SobelEdgeDetection

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ScrollPane
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import openCVProject.*
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc

class SobelController {
    @FXML var imageView: ImageView? = null
    @FXML var btnDetectEdge: Button? = null
    @FXML var imgScroll: ScrollPane? = null

    private var grayScale: Mat? = null

    var edgesImage: Image? = null
    var horizontalEdgesImage: Image? = null
    var verticalEdgesImage: Image? = null

    fun initialize() {
        imageView?.image = Context.images.last()
        UIUtils.setZoomScroll(imageView!!, imgScroll!!)

        grayScale = Mat()
    }

    fun detectEdges() {
        if (edgesImage == null) {
            edgesImage = doDetectEdges()
        }
        imageView?.image = edgesImage
    }

    fun detectHorizontalEdges() {
        if (horizontalEdgesImage == null) {
            horizontalEdgesImage = doDetectEdges(detectHorizontal = true, detectVertical = false)
        }
        imageView?.image = horizontalEdgesImage
    }

    fun detectVerticalEdges() {
        if (verticalEdgesImage == null) {
            verticalEdgesImage = doDetectEdges(detectHorizontal = false, detectVertical = true)
        }
        imageView?.image = verticalEdgesImage
    }

    private fun doDetectEdges(detectHorizontal: Boolean = true, detectVertical: Boolean = true): Image {
        var processedImage = ImageUtils.imageToMat(Context.images.first())
        processedImage = this.doSobel(processedImage, detectHorizontal, detectVertical)
        return ImageUtils.mat2Image(processedImage)
    }

    fun restoreImage() {
        imageView?.image = Context.images.last()
    }

    private fun doSobel(frame: Mat, detectHorizontal: Boolean, detectVertical: Boolean): Mat {
        Imgproc.cvtColor(frame, grayScale, Imgproc.COLOR_BGR2GRAY)

        val edges = Mat(grayScale!!.size(), grayScale!!.type())

        val sobelX = arrayOf(
                intArrayOf(-1, 0, +1),
                intArrayOf(-2, 0, +2),
                intArrayOf(-1, 0, +1)
        )

        val sobelY = arrayOf(
                intArrayOf(+1, +2, +1),
                intArrayOf(0, 0, 0),
                intArrayOf(-1, -2, -1)
        )

        for (row in 1..grayScale!!.rows() - 2) {
            for (col in 1..grayScale!!.cols() - 2) {
                val pixelX =
                        if (detectHorizontal) {
                            (sobelX[0][0] * grayScale!!.get(row - 1, col - 1).first()) + (sobelX[0][1] * grayScale!!.get(row, col - 1).first()) + (sobelX[0][2] * grayScale!!.get(row + 1, col - 1).first()) +
                                    (sobelX[1][0] * grayScale!!.get(row - 1, col).first()) + (sobelX[1][1] * grayScale!!.get(row, col).first()) + (sobelX[1][2] * grayScale!!.get(row + 1, col).first()) +
                                    (sobelX[2][0] * grayScale!!.get(row - 1, col + 1).first()) + (sobelX[2][1] * grayScale!!.get(row, col + 1).first()) + (sobelX[2][2] * grayScale!!.get(row + 1, col + 1).first())
                        } else {
                            0.0
                        }

                val pixelY = if (detectVertical) {
                    (sobelY[0][0] * grayScale!!.get(row - 1, col - 1).first()) + (sobelY[0][1] * grayScale!!.get(row, col - 1).first()) + (sobelY[0][2] * grayScale!!.get(row + 1, col - 1).first()) +
                            (sobelY[1][0] * grayScale!!.get(row - 1, col).first()) + (sobelY[1][1] * grayScale!!.get(row, col).first()) + (sobelY[1][2] * grayScale!!.get(row + 1, col).first()) +
                            (sobelY[2][0] * grayScale!!.get(row - 1, col + 1).first()) + (sobelY[2][1] * grayScale!!.get(row, col + 1).first()) + (sobelY[2][2] * grayScale!!.get(row + 1, col + 1).first())
                } else {
                    0.0
                }

                val pixel = Math.abs(pixelX) + Math.abs(pixelY)
                edges.put(row, col, pixel)
            }
        }

        return edges
    }

    fun returnToPreviousState() {
        StateManager.changeState(EdgeDetectionState(), Context.stage!!)
    }
}