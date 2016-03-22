package openCVProject.MeanFilter

import javafx.fxml.FXML
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import openCVProject.Context
import openCVProject.Utils
import org.opencv.core.Mat

/**
 * Created by nilot on 21/03/2016.
 */
class MeanFilterController {
    @FXML var beforeImg: ImageView? = null
    @FXML var afterImg: ImageView? = null

    fun initialize() {
        beforeImg?.image = Context.image
        afterImg?.image = applyMeanFilter(Context.image!!)
    }

    private fun applyMeanFilter(image: Image): Image {
        val matBefore = Utils.imageToMat(image)
        val matResult = Mat(matBefore.rows(), matBefore.cols(), matBefore.type())
        val depth: Int

        if (Context.meanFilterSelectedOption.equals("3x3")) {
            depth = 1
        } else {
            // TODO: Assuming else is 5x5
            depth = 2
        }

        // SHUT UP THIS IS BEAUTIFUL
        for (row in 0..matBefore.rows() - 2) {
            for (col in 0..matBefore.cols() - 2) {
                if (depth == 1) {
                    val c1: DoubleArray = matBefore.get(getCorrectedIndex(row + 1, matBefore.rows()), getCorrectedIndex(col - 1, matBefore.cols()))
                    val c2: DoubleArray = matBefore.get(getCorrectedIndex(row + 1, matBefore.rows()), getCorrectedIndex(col, matBefore.cols()))
                    val c3: DoubleArray = matBefore.get(getCorrectedIndex(row + 1, matBefore.rows()), getCorrectedIndex(col + 1, matBefore.cols()))
                    val c4: DoubleArray = matBefore.get(getCorrectedIndex(row, matBefore.rows()), getCorrectedIndex(col - 1, matBefore.cols()))
                    val c5: DoubleArray = matBefore.get(getCorrectedIndex(row, matBefore.rows()), getCorrectedIndex(col, matBefore.cols()))
                    val c6: DoubleArray = matBefore.get(getCorrectedIndex(row, matBefore.rows()), getCorrectedIndex(col + 1, matBefore.cols()))
                    val c7: DoubleArray = matBefore.get(getCorrectedIndex(row - 1, matBefore.rows()), getCorrectedIndex(col - 1, matBefore.cols()))
                    val c8: DoubleArray = matBefore.get(getCorrectedIndex(row - 1, matBefore.rows()), getCorrectedIndex(col, matBefore.cols()))
                    val c9: DoubleArray = matBefore.get(getCorrectedIndex(row - 1, matBefore.rows()), getCorrectedIndex(col + 1, matBefore.cols()))

                    var r = listOf(c1[0], c2[0], c3[0], c4[0], c5[0], c6[0], c7[0], c8[0], c9[0])
                    var g = listOf(c1[1], c2[1], c3[1], c4[1], c5[1], c6[1], c7[1], c8[1], c9[1])
                    var b = listOf(c1[2], c2[2], c3[2], c4[2], c5[2], c6[2], c7[2], c8[2], c9[2])
                    var a = listOf(c1[3], c2[3], c3[3], c4[3], c5[3], c6[3], c7[3], c8[3], c9[3])

                    matResult.put(getCorrectedIndex(row, matBefore.rows()), getCorrectedIndex(col, matBefore.cols()),
                            r.average(), g.average(), b.average(), a.average())
                }
            }
        }
        return Utils.mat2Image(matResult)
    }

    private fun getCorrectedIndex(index: Int, maxIndex: Int) =
            when {
                index < 0 -> 1
                index > maxIndex -> maxIndex
                else -> index
            }

}
