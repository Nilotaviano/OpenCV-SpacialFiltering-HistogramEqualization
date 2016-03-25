package openCVProject.MeanFilter

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.stage.Stage
import openCVProject.IState

class FMeanFilter : Application(), IState {

    override fun start(primaryStage: Stage) {
        val root = FXMLLoader.load<Parent>(javaClass.getResource("MeanFilter.fxml"))
        primaryStage.title = "OpenCV - Filtro de média"
        primaryStage.scene.root = root
        primaryStage.show()
    }

    override fun enterState(stage: Stage) {
        start(stage)
    }

    override fun leaveState() {

    }
}