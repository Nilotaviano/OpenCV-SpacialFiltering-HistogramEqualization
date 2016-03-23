package openCVProject.Histogram

import openCVProject.Context
import openCVProject.MainMenuState
import openCVProject.StateManager

/**
 * Created by nilot on 20/03/2016.
 */
class HistogramEqualizationController {
    fun returnToPreviousState() {
        StateManager.changeState(MainMenuState(), Context.stage!!)
    }
}