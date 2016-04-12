package openCVProject.MeanFilter

import javafx.fxml.FXML
import javafx.scene.control.ScrollPane
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import openCVProject.*
import org.opencv.core.Mat
import java.util.*

class MeanFilterController {
    @FXML var beforeImg: ImageView? = null
    @FXML var afterImg: ImageView? = null
    @FXML var beforeImgScroll: ScrollPane? = null
    @FXML var afterImgScroll: ScrollPane? = null

    fun initialize() {
        beforeImg?.image = Context.images.last()
        var processedImage = applyMeanFilter(Context.images.first())
        afterImg?.image = processedImage
        Context.images.add(processedImage)
        UIUtils.setZoomScroll(beforeImg!!, beforeImgScroll!!)
        UIUtils.setZoomScroll(afterImg!!, afterImgScroll!!)
    }

    private fun applyMeanFilter(image: Image): Image {
        val matBefore = ImageUtils.imageToMat(image)
        val matResult = Mat(matBefore.rows(), matBefore.cols(), matBefore.type())

        val offsetValues = if (Context.meanAndMedianFilterSelectedOption.equals("3x3")) {
            intArrayOf(-1, 0, +1)
        } else {
            // TODO: Assuming else is 5x5
            intArrayOf(-2, -1, 0, +1, +2)
        }

        // O(LOL)
        for (row in 0..matBefore.rows() - 1) {
            for (col in 0..matBefore.cols() - 1) {
                var pixelsChannels = ArrayList<DoubleArray>()

                for (xOffset in offsetValues) {
                    for (yOffset in offsetValues) {
                        pixelsChannels.add(matBefore.get(ImageUtils.getCorrectedIndex(row + xOffset, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col + yOffset, matBefore.cols() - 1)))
                    }
                }

                var r = pixelsChannels.map { c -> c[0] }
                var g = pixelsChannels.map { c -> c[1] }
                var b = pixelsChannels.map { c -> c[2] }
                var a = pixelsChannels.map { c -> c[3] }

                matResult.put(row, col,
                        r.average(), g.average(), b.average(), a.average())
            }
        }
        return ImageUtils.mat2Image(matResult)
    }

    fun returnToPreviousState() {
        StateManager.changeState(MeanAndMedianMenuState(), Context.stage!!)
    }
}
