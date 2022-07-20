package nix.summer.practice.coffeemachine

fun main() {
    val coffeeMachine = CoffeeMachine()
    while (coffeeMachine.isWork()) {
        println("\nWrite action (buy, fill, take, remaining, exit):")
        coffeeMachine.parseActivity(readln())
    }
}