package openCVProject

import javafx.application.Application
import javafx.collections.FXCollections
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.ComboBox
import javafx.scene.layout.GridPane
import javafx.scene.text.Text
import javafx.stage.Stage

/**
 * Created by nilot on 20/03/2016.
 */
class MeanAndMedianMenuState : Application(), IState {
    override fun start(stage: Stage) {
        stage.title = "OpenCV - Média e mediana"

        val grid =
                if (stage.scene.root is GridPane) {
                    stage.scene.root as GridPane
                } else {
                    val grid = GridPane()
                    grid.alignment = Pos.CENTER
                    grid.hgap = 10.0
                    grid.vgap = 10.0
                    grid.padding = Insets(25.0, 25.0, 25.0, 25.0)
                    stage.scene.root = grid
                    grid
                }
        grid.children.clear()

        val cbWindowSize = ComboBox(FXCollections.observableArrayList("3x3", "5x5"))
        cbWindowSize.value = cbWindowSize.items.first()
        grid.add(cbWindowSize, 0, 0, 2, 1)

        val actionTarget = Text()
        grid.add(actionTarget, 1, 4)

        stage.show()
    }

    override fun enterState(stage: Stage) {
        start(stage)
    }

    override fun leaveState() {

    }
}