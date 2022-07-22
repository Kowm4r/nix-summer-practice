package nix.summer.practice.mvc

//  The Controller class helps the Model class work with the View interface.
class Controller(private val model: Model) {
    private lateinit var view: View

    //  AttachView - public function to initialize view.
    fun attachView(_view: View) {
        view = _view
    }

    //  The start function is responsible for starting
    fun start() {
        view.start()
    }

    // Setting default values.
    fun setData() {
        model.setData()
    }

    // Choosing and buying coffee.
    fun buy(order: Order) {
        view.showInfo(model.buy(order).notify, false)
    }

    // Filling: Water, milk, cofBeans, cups.
    fun fill(resources: Resources.ChangeRes) {
        view.showInfo(model.fill(resources).notify, true)
    }

    //  Withdrawal Processor.
    fun take() {
        view.output(model.take().notify)
    }

    //  Information about resources and exhale them to the screen
    fun remaining() {
        view.showInfoRes(model.coffeeMachineRemaining())
    }
}