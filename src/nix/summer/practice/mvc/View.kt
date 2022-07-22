package nix.summer.practice.mvc

interface View {
    //   Connecting a class
    var controller: Controller
    //  Start Program.
    fun start()
    //   Show info.
    fun showInfo(info: String, label: Boolean): String
    //  Displaying information about resources.
    fun showInfoRes(info: String): String
    //   Output to the terminal
    fun output(text: String)
    //   Exit.
    fun exit(): Boolean
}