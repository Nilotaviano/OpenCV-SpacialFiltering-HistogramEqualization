package openCVProject.MeanFilter

import javafx.fxml.FXML
import javafx.scene.image.ImageView
import openCVProject.Context

/**
 * Created by nilot on 21/03/2016.
 */
class MeanFilterController {
    @FXML
    internal var beforeImg: ImageView? = null

    fun initialize() {
        beforeImg?.image = Context.image
    }
}
