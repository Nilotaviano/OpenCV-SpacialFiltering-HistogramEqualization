package openCVProject

import javafx.beans.InvalidationListener
import javafx.beans.property.SimpleDoubleProperty
import javafx.scene.control.ScrollPane
import javafx.scene.image.ImageView
import javafx.scene.input.ScrollEvent

object UIUtils {
    fun setZoomScroll(image: ImageView, scrollPane: ScrollPane) {
        val zoomProperty = SimpleDoubleProperty(200.0)

        zoomProperty.addListener(InvalidationListener {
            image.fitWidth = zoomProperty.get() * 4
            image.fitHeight = zoomProperty.get() * 3
        })

        scrollPane.addEventFilter(ScrollEvent.ANY, { event ->
            if (event.deltaY > 0) {
                zoomProperty.set(zoomProperty.get() * 1.1)
            } else if (event.deltaY < 0) {
                zoomProperty.set(zoomProperty.get() / 1.1)
            }
        })
    }
}