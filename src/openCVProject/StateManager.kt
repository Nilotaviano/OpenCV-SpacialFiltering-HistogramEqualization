package openCVProject

import javafx.stage.Stage
import org.opencv.core.Core

object StateManager {
    private var state: IState? = null

    fun changeState(state: IState, stage: Stage) {
        this.state?.leaveState()
        this.state = state
        this.state?.enterState(stage)
    }

    @JvmStatic fun main(args: Array<String>) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        MainMenuState.launch()
    }

}
