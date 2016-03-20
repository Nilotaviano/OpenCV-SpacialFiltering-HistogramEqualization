package openCVProject

import javafx.stage.Stage

/**
 * Created by nilot on 20/03/2016.
 */
interface IState {
    fun enterState(stage: Stage)
    fun leaveState()
}