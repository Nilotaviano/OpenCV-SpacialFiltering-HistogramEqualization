package openCVProject

import javafx.application.Application
import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.Label
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.text.Text
import javafx.stage.Stage
import openCVProject.MeanFilter.FMeanFilter
import openCVProject.MedianFilter.FMedianFilter

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

        val lblWindowSize = Label("Tamanho da janela:")
        grid.add(lblWindowSize, 0, 0)

        val cbWindowSize = ComboBox(FXCollections.observableArrayList("3x3", "5x5"))
        cbWindowSize.value = cbWindowSize.items.first()
        grid.add(cbWindowSize, 1, 0)

        val btnMean = Button("Média")
        btnMean.onAction = EventHandler<ActionEvent> {
            StateManager.changeState(FMeanFilter(), stage)
        }
        val btnMedian = Button("Mediana")
        btnMedian.onAction = EventHandler<ActionEvent> {
            StateManager.changeState(FMedianFilter(), stage)
        }

        val hbox = HBox()
        hbox.alignment = Pos.BOTTOM_RIGHT
        hbox.children.addAll(btnMean, btnMedian)
        grid.add(hbox, 0, 1, 2, 1)
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