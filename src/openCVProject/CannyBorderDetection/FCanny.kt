package openCVProject.CannyBorderDetection

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.stage.Stage
import openCVProject.IState

class FCanny : Application(), IState {

    override fun start(primaryStage: Stage) {
        val root = FXMLLoader.load<Parent>(javaClass.getResource("Canny.fxml"))
        primaryStage.title = "OpenCV - Canny"
        primaryStage.scene.root = root
        primaryStage.show()
    }

    override fun enterState(stage: Stage) {
        start(stage)
    }

    override fun leaveState() {

    }
}