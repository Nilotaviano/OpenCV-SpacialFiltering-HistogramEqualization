package openCVProject

import javafx.scene.image.Image
import javafx.scene.image.WritablePixelFormat
import org.opencv.core.Core
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.core.MatOfByte
import org.opencv.imgcodecs.Imgcodecs
import java.io.ByteArrayInputStream

/**
 * Created by nilot on 21/03/2016.
 */
object Utils {
    // http://stackoverflow.com/a/34784966
    fun imageToMat(image: Image): Mat {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        val width = image.width.toInt()
        val height = image.height.toInt()
        val buffer = ByteArray(width * height * 4)

        val reader = image.pixelReader
        val format = WritablePixelFormat.getByteBgraInstance()
        reader.getPixels(0, 0, width, height, format, buffer, 0, width * 4)

        val mat = Mat(height, width, CvType.CV_8UC4)
        mat.put(0, 0, buffer)
        return mat
    }

    // http://stackoverflow.com/a/34772385
    fun mat2Image(mat: Mat): Image {
        val buffer = MatOfByte()
        Imgcodecs.imencode(".png", mat, buffer)
        return Image(ByteArrayInputStream(buffer.toArray()))
    }
}