package nix.summer.practice.coffeemachine

class CoffeeMachine() {
    private var water = 400
    private var milk = 540
    private var coffeeBeans = 120
    private var cups = 9
    private var money = 550
    private val scan = java.util.Scanner(System.`in`)
    private var status = true

    fun parseActivity(command: String) {

        when (command) {
            "buy" -> buy()
            "fill" -> fill()
            "take" -> take()
            "remaining" -> coffeeMachineRemaining()
            "exit" -> status = false
            else -> println("wrong command")
        }

    }

    fun isWork(): Boolean {
        return status;
    }

    fun coffeeMachineRemaining() {
        println(
            "\nThe coffee machine has:\n" +
                    "${this.water} of water\n" +
                    "${this.milk} of milk\n" +
                    "${this.coffeeBeans} of coffee beans\n" +
                    "${this.cups} of dispasable cups\n" +
                    "${this.money} of money"
        )
    }

    private fun makeCoffee(coffeeType: Coffee) {
        if (coffeeType.water > this.water) {
            println("Sorry, not enough water!")
            return
        } else if (coffeeType.milk > this.milk) {
            println("Sorry, not enough milk!")
            return
        } else if (coffeeType.coffeeBeans > this.coffeeBeans) {
            println("Sorry, not enough coffee beans!")
            return
        } else if (coffeeType.money < 1) {
            println("Sorry, not enough disposable cups!")
            return
        } else {
            println("I have enough resources, making you a coffee!")
        }
        this.cups -= 1
        this.water -= coffeeType.water
        this.coffeeBeans -= coffeeType.coffeeBeans
        this.milk -= coffeeType.milk
        this.money += coffeeType.money
    }

    fun buy() {
        println(
            "What do you want to buy?\n" +
                    " 1 - espresso,\n" +
                    " 2 - latte,\n" +
                    " 3 - cappuccino,\n" +
                    " 4 - raf,\n" +
                    " back - to main menu"
        )
        when (scan.nextLine()) {
            "1" -> makeCoffee(Coffee.ESPRESSO)
            "2" -> makeCoffee(Coffee.LATTE)
            "3" -> makeCoffee(Coffee.CAPPUCCINO)
            "4" -> makeCoffee(Coffee.RAF)
            "back" -> println()
            else -> println("wrong command")
        }
    }

    fun fill() {
        println("\nWrite how many ml of water do you want to add: ")
        val water = scan.nextInt()
        this.water += water
        println("Write how many ml of milk do you want to add: ")
        val milk = scan.nextInt()
        this.milk += milk
        println("Write how many grams of coffee beans do you want to add: ")
        val coffeeBeans = scan.nextInt()
        this.coffeeBeans += coffeeBeans
        println("Write how many disposable cups of coffee do you want to add: ")
        val cups = scan.nextInt()
        this.cups += cups
    }

    fun take() {
        println("\nI gave you ${this.money}")
        this.money = 0
    }
}

enum class Coffee(val water: Int, val coffeeBeans: Int, val milk: Int, val money: Int) {
    ESPRESSO(250, 16, 0, 4),
    LATTE(350, 20, 75, 7),
    CAPPUCCINO(200, 12, 100, 6),
    RAF(150, 14, 200, 12);

}