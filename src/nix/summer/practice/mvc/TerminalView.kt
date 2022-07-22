package nix.summer.practice.mvc


import java.util.*

class TerminalView(override var controller: Controller) : View {
    val scan = Scanner(System.`in`)

    override fun start() {
        var run = true
        while (run) {
            output("\nWrite action (buy, fill, take, remaining, back):")
            when(scan.nextLine()) {
                "buy" -> buy()
                "fill" -> fill()
                "take" -> {
                    controller.take()
                }
                "remaining" -> {
                    controller.remaining()
                }
                "back" ->{
                    run = exit()
                    begin()
                }
                else ->  println("Invalid option.")
            }
        }
        return
    }



    //  Shopping function
    private fun buy() {
        output("What do you want to buy?\n" +
                "1 - espresso, \n" +
                "2 - latte,\n" +
                "3 - cappuccino,\n" +
                "4-raf\n" +
                "back - to main menu:")
        val order = Order(scan.nextLine())
        controller.buy(order)
    }

    //  Fill function
    private fun fill() {
        val resources = Resources.ChangeRes()
        output("Write how many ml of water do you want to add:")
        resources.water = scan.nextInt()
        output( "Write how many ml of milk do you want to add:")
        resources.milk = scan.nextInt()
        output("Write how many grams of coffee beans do you want to add:")
        resources.coffeeBeans = scan.nextInt()
        output("Write how many disposable cups of coffee do you want to add:")
        resources.cups = scan.nextInt()
        controller.fill(resources)
    }

    //  HTML Convert to text
    private fun replaceText(text: String, value: String, replacing: String): String {
        return text.replace(
            value,
            replacing,
            true
        )
    }

    override fun showInfo(info: String, label: Boolean): String {
        output(info)
        return "Displayed"
    }

    override fun showInfoRes(info: String): String {
        var text = replaceText(info, "<html>", "\n")
        text = replaceText(text, "<h1>", "")
        text = replaceText(text, "</h1>", "")
        text = replaceText(text, "<h3>", "")
        text = replaceText(text, "</h3>", "")
        text = replaceText(text, "<br>", "\n")
        text = replaceText(text, "</html>", "")
        print(text)
        return "Print"
    }

    override fun output(text: String) {
        println(text)
    }

    override fun exit(): Boolean {
        return false
    }
}