package openCVProject.SobelBorderDetection

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.stage.Stage
import openCVProject.IState

class FSobel : Application(), IState {

    override fun start(primaryStage: Stage) {
        val root = FXMLLoader.load<Parent>(javaClass.getResource("Sobel.fxml"))
        primaryStage.title = "OpenCV - Sobel"
        primaryStage.scene.root = root
        primaryStage.show()
    }

    override fun enterState(stage: Stage) {
        start(stage)
    }

    override fun leaveState() {

    }
}