package nix.summer.practice.mvc

//  Recipe coffee.
enum class Coffee(val water: Int, val coffeeBeans: Int, val milk: Int, val money: Int) {
    ESPRESSO(250, 16, 0, 4),
    LATTE(350, 20, 75, 7),
    CAPPUCCINO(200, 12, 100, 6),
    RAF(150, 15, 200, 12);
}

//  Order - Types of coffee.
data class Order(val choice: String)

//  Resources - Adding resources or withdrawing cash.
data class Resources(
    val water: Int = 400,
    val milk: Int = 540,
    val coffeeBeans: Int = 120,
    val cups: Int = 9,
    val money: Int = 550
) {
    data class ChangeRes(
        var water: Int = 0,
        var milk: Int = 0,
        var coffeeBeans: Int = 0,
        var cups: Int = 0,
        var money: Int = 0
    )
}

//  Response - date class text outputs.
data class Response(val notify: String)

//  The class responsible for the logic of the program.
class Model {
    //  Initialization of date classes.
    private val res = Resources.ChangeRes()

    // Setting default values.
    fun setData() {
        val resources = Resources()
        res.water = resources.water
        res.coffeeBeans = resources.coffeeBeans
        res.milk = resources.milk
        res.cups = resources.cups
        res.money = resources.money
    }

    //  Forming the output of information about the remains in the coffee machine.
    fun coffeeMachineRemaining(): String {
        return "<html><h1>The coffee machine has:</h1><br>" +
                "<h3>${res.water} of water<br>" +
                "${res.milk} of milk<br>" +
                "${res.coffeeBeans} of coffee beans<br>" +
                "${res.cups} of disposable cups<br>" +
                "${res.money} of money<br></h3></html>"
    }

    //  Buying instructions.
    fun buy(order: Order): Response {
        var response = Response("")
        when (order.choice) {
            "1" -> {
                response = makeCoffee(Coffee.ESPRESSO)
            }
            "2" -> {
                response = makeCoffee(Coffee.LATTE)
            }
            "3" -> {
                response = makeCoffee(Coffee.CAPPUCCINO)
            }
            "4" -> {
                response = makeCoffee(Coffee.RAF)
            }
            "back" -> {
                return response
            }
            else -> response = Response("Invalid option.")


        }
        return response
    }

    //  Logic for calculating resources and displaying information about them.
    private fun makeCoffee(coffeeType: Coffee): Response {
        if (coffeeType.water > res.water) {
            return Response("Sorry, not enough water!")
        } else if (coffeeType.milk > res.milk) {
            return Response("Sorry, not enough milk!")
        } else if (coffeeType.coffeeBeans > res.coffeeBeans) {
            return Response("Sorry, not enough coffee beans!")
        } else if (res.cups < 1) {
            return Response("Sorry, not enough disposable cups!")
        } else {
            res.cups -= 1
            res.water -= coffeeType.water
            res.coffeeBeans -= coffeeType.coffeeBeans
            res.milk -= coffeeType.milk
            res.money += coffeeType.money
            return Response("I have enough resources, making you a coffee!")
        }
    }

    // Filling Resources.
    fun fill(resources: Resources.ChangeRes): Response {
        res.water += resources.water
        res.milk += resources.milk
        res.coffeeBeans += resources.coffeeBeans
        res.cups += resources.cups
        return Response("Resources replenished.")
    }

    //  Issuance of money.
    fun take(): Response {
        val response = Response("I gave you ${res.money}")
        res.money = 0
        return response
    }
}