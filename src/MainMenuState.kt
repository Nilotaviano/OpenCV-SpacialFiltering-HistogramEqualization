import javafx.application.Application
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.text.Text
import javafx.stage.FileChooser
import javafx.stage.Stage

class MainMenuState : Application() {

    override fun start(stage: Stage) {
        stage.title = "OpenCV - Projeto"
        stage.isMaximized = true
        val grid = GridPane()
        grid.alignment = Pos.CENTER
        grid.hgap = 10.0
        grid.vgap = 10.0
        grid.padding = Insets(25.0, 25.0, 25.0, 25.0)

        val scene = Scene(grid)
        stage.scene = scene

        val imageView = ImageView()
        grid.add(imageView, 0, 0)

        val fileChooser = FileChooser()
        fileChooser.title = "Selecione uma imagem"

        val fileSelection = Label("Selecionar arquivo: ")
        val fileSelectionButton = Button("...")
        fileSelectionButton.onAction = EventHandler<ActionEvent> {
            val file = fileChooser.showOpenDialog(stage)

            if(file != null) {
                val image = Image(file.inputStream())
                imageView.image = image
                imageView.fitHeight = 300.0
                imageView.isPreserveRatio = true
            }
        }

        val hbFileSelection = HBox(10.0)
        hbFileSelection.children.add(fileSelection)
        hbFileSelection.children.add(fileSelectionButton)
        grid.add(hbFileSelection, 0, 2)

        val meanAndMedian = Button("1 - Média e mediana")
        val borderDetection = Button("2 - Detecção de bordas")
        val histogramEqualization = Button("3 - Equalização de histograma")
        val hbBtn = HBox(10.0)
        hbBtn.alignment = Pos.BOTTOM_RIGHT
        hbBtn.children.add(meanAndMedian)
        hbBtn.children.add(borderDetection)
        hbBtn.children.add(histogramEqualization)
        grid.add(hbBtn, 0, 3)

        val actionTarget = Text()
        grid.add(actionTarget, 1, 4)

        stage.show()
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            launch(MainMenuState::class.java)
        }
    }
}
