package openCVProject.MeanFilter

import javafx.fxml.FXML
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import openCVProject.Context
import openCVProject.Utils

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
        var mat = Utils.imageToMat(image)

        // TODO
        return Utils.mat2Image(mat)
    }

}
