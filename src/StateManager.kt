import javafx.stage.Stage

/**
 * Created by nilot on 20/03/2016.
 */
object StateManager {
    private var state: IState? = null

    fun changeState(state: IState, stage: Stage) {
        this.state?.leaveState()
        this.state = state
        this.state?.enterState(stage)
    }

    @JvmStatic fun main(args: Array<String>) {
        MainMenuState.launch()
    }

}
