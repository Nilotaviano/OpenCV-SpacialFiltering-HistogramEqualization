package openCVProject.MedianFilter

import javafx.fxml.FXML
import javafx.scene.control.ScrollPane
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import openCVProject.*
import org.opencv.core.Mat

class MedianFilterController {
    @FXML var beforeImg: ImageView? = null
    @FXML var afterImg: ImageView? = null
    @FXML var beforeImgScroll: ScrollPane? = null
    @FXML var afterImgScroll: ScrollPane? = null

    fun initialize() {
        beforeImg?.image = Context.image
        afterImg?.image = applyMedianFilter(Context.image!!)

        UIUtils.setZoomScroll(beforeImg!!, beforeImgScroll!!)
        UIUtils.setZoomScroll(afterImg!!, afterImgScroll!!)
    }

    private fun applyMedianFilter(image: Image): Image {
        val matBefore = ImageUtils.imageToMat(image)
        val matResult = Mat(matBefore.rows(), matBefore.cols(), matBefore.type())
        val depth: Int

        if (Context.meanAndMedianFilterSelectedOption.equals("3x3")) {
            depth = 1
        } else {
            // TODO: Assuming else is 5x5
            depth = 2
        }

        // SHUT UP THIS IS BEAUTIFUL
        for (row in 0..matBefore.rows() - 1) {
            for (col in 0..matBefore.cols() - 1) {
                if (depth == 1) {
                    val c1: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row + 1, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col - 1, matBefore.cols() - 1))
                    val c2: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row + 1, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col, matBefore.cols() - 1))
                    val c3: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row + 1, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col + 1, matBefore.cols() - 1))
                    val c4: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col - 1, matBefore.cols() - 1))
                    val c5: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col, matBefore.cols() - 1))
                    val c6: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col + 1, matBefore.cols() - 1))
                    val c7: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row - 1, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col - 1, matBefore.cols() - 1))
                    val c8: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row - 1, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col, matBefore.cols() - 1))
                    val c9: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row - 1, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col + 1, matBefore.cols() - 1))

                    var r = arrayListOf(c1[0], c2[0], c3[0], c4[0], c5[0], c6[0], c7[0], c8[0], c9[0])
                    var g = arrayListOf(c1[1], c2[1], c3[1], c4[1], c5[1], c6[1], c7[1], c8[1], c9[1])
                    var b = arrayListOf(c1[2], c2[2], c3[2], c4[2], c5[2], c6[2], c7[2], c8[2], c9[2])
                    var a = arrayListOf(c1[3], c2[3], c3[3], c4[3], c5[3], c6[3], c7[3], c8[3], c9[3])
                    r.sort()
                    g.sort()
                    b.sort()
                    a.sort()

                    matResult.put(row, col,
                            r[4], g[4], b[4], a[4])
                } else {
                    val c1: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row + 2, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col - 2, matBefore.cols() - 1))
                    val c2: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row + 2, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col - 1, matBefore.cols() - 1))
                    val c3: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row + 2, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col, matBefore.cols() - 1))
                    val c4: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row + 2, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col + 1, matBefore.cols() - 1))
                    val c5: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row + 2, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col + 2, matBefore.cols() - 1))
                    val c6: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row + 1, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col - 2, matBefore.cols() - 1))
                    val c7: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row + 1, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col - 1, matBefore.cols() - 1))
                    val c8: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row + 1, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col, matBefore.cols() - 1))
                    val c9: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row + 1, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col + 1, matBefore.cols() - 1))
                    val c10: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row + 1, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col + 2, matBefore.cols() - 1))
                    val c11: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col - 2, matBefore.cols() - 1))
                    val c12: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col - 1, matBefore.cols() - 1))
                    val c13: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col, matBefore.cols() - 1))
                    val c14: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col + 1, matBefore.cols() - 1))
                    val c15: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col + 2, matBefore.cols() - 1))
                    val c16: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row - 1, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col - 2, matBefore.cols() - 1))
                    val c17: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row - 1, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col - 1, matBefore.cols() - 1))
                    val c18: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row - 1, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col, matBefore.cols() - 1))
                    val c19: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row - 1, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col + 1, matBefore.cols() - 1))
                    val c20: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row - 1, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col + 2, matBefore.cols() - 1))
                    val c21: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row - 2, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col - 2, matBefore.cols() - 1))
                    val c22: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row - 2, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col - 1, matBefore.cols() - 1))
                    val c23: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row - 2, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col, matBefore.cols() - 1))
                    val c24: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row - 2, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col + 1, matBefore.cols() - 1))
                    val c25: DoubleArray = matBefore.get(ImageUtils.getCorrectedIndex(row - 2, matBefore.rows() - 1), ImageUtils.getCorrectedIndex(col + 2, matBefore.cols() - 1))

                    var r = arrayListOf(c1[0], c2[0], c3[0], c4[0], c5[0], c6[0], c7[0], c8[0], c9[0], c10[0], c11[0], c12[0],
                            c13[0], c14[0], c15[0], c16[0], c17[0], c18[0], c19[0], c20[0], c21[0], c22[0], c23[0], c24[0], c25[0])
                    var g = arrayListOf(c1[1], c2[1], c3[1], c4[1], c5[1], c6[1], c7[1], c8[1], c9[1], c10[1], c11[1], c12[1],
                            c13[1], c14[1], c15[1], c16[1], c17[1], c18[1], c19[1], c20[1], c21[1], c22[1], c23[1], c24[1], c25[1])
                    var b = arrayListOf(c1[2], c2[2], c3[2], c4[2], c5[2], c6[2], c7[2], c8[2], c9[2], c10[2], c11[2], c12[2],
                            c13[2], c14[2], c15[2], c16[2], c17[2], c18[2], c19[2], c20[2], c21[2], c22[2], c23[2], c24[2], c25[2])
                    var a = arrayListOf(c1[3], c2[3], c3[3], c4[3], c5[3], c6[3], c7[3], c8[3], c9[3], c10[3], c11[3], c12[3],
                            c13[3], c14[3], c15[3], c16[3], c17[3], c18[3], c19[3], c20[3], c21[3], c22[3], c23[3], c24[3], c25[3])

                    r.sort()
                    g.sort()
                    b.sort()
                    a.sort()

                    matResult.put(row, col,
                            r[Math.ceil(r.size.toDouble() / 2).toInt()], g[Math.ceil(g.size.toDouble() / 2).toInt()],
                            b[Math.ceil(b.size.toDouble() / 2).toInt()], a[Math.ceil(a.size.toDouble() / 2).toInt()])
                }
            }
        }
        return ImageUtils.mat2Image(matResult)
    }

    fun returnToPreviousState() {
        StateManager.changeState(MeanAndMedianMenuState(), Context.stage!!)
    }
}
