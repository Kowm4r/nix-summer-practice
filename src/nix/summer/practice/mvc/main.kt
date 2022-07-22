package nix.summer.practice.mvc

import kotlin.system.exitProcess


private val model = Model()
private val controller = Controller(model)


fun main() {
    //  Default data initialization.
    controller.setData()
    begin()
}

// First Menu
fun begin() {
    val view: View
    println("Select Interface 1 - Terminal 2 - Graphical 3 - Exit:")
    when(readln()) {
        "1","Terminal","terminal" -> {
            view = TerminalView(controller)
            controller.attachView(view)
        }
        "2","Graphical","graphical" -> {
            view = SwingView(controller)
            controller.attachView(view)
        }
        "3","Exit","exit" -> exitProcess(0)
    }
    controller.start()
}