package openCVProject

import javafx.application.Application
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.text.Text
import javafx.stage.Stage
import openCVProject.CannyEdgeDetection.FCanny
import openCVProject.RobertsEdgeDetection.FRoberts
import openCVProject.SobelEdgeDetection.FSobel

class EdgeDetectionState : Application(), IState {
    override fun start(stage: Stage) {
        stage.title = "OpenCV - Média e mediana"

        val grid = GridPane()
        grid.alignment = Pos.CENTER
        grid.hgap = 10.0
        grid.vgap = 10.0
        grid.padding = Insets(25.0, 25.0, 25.0, 25.0)
        stage.scene.root = grid

        val (btnRoberts, btnSobel, btnCanny) = setupButtons(stage)
        val hbBtn = HBox(10.0)
        hbBtn.alignment = Pos.BOTTOM_RIGHT
        hbBtn.children.addAll(btnRoberts, btnSobel, btnCanny)
        grid.add(hbBtn, 0, 0)

        val btnGoBack = Button("Voltar")
        btnGoBack.onAction = EventHandler<ActionEvent> {
            returnToPreviousState()
        }
        grid.add(btnGoBack, 0, 1)

        val actionTarget = Text()
        grid.add(actionTarget, 1, 4)

        stage.show()
    }

    private fun setupButtons(stage: Stage): Triple<Button, Button, Button> {
        val btnRoberts = Button("1 - Gradiente de Roberts")
        btnRoberts.onAction = EventHandler<ActionEvent> {
            StateManager.changeState(FRoberts(), stage)
        }
        val btnSobel = Button("2 - Gradiente de Sobel")
        btnSobel.onAction = EventHandler<ActionEvent> {
            StateManager.changeState(FSobel(), stage)
        }
        val btnCanny = Button("3 - Gradiente de Canny")
        btnCanny.onAction = EventHandler<ActionEvent> {
            StateManager.changeState(FCanny(), stage)
        }
        return Triple(btnRoberts, btnSobel, btnCanny)
    }

    override fun enterState(stage: Stage) {
        start(stage)
    }

    override fun leaveState() {

    }

    fun returnToPreviousState() {
        StateManager.changeState(MainMenuState(), Context.stage!!)
    }
}