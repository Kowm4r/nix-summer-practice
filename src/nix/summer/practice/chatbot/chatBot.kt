package nix.summer.practice.chatbot
 fun main(){
     //Step1
     val botName : String="Lulla"
     val birthYear :Int =2022
     println("Hello! My name is $botName.")
     println("I was created in $birthYear.")


     //Step 2
     println("Please, remind me your name.")
     val nameUser : String=readln()
     println("What a great name you have, $nameUser!")


     //Step3
     println("Let me guess your age.")
     println("Enter remainders of dividing your age by 3, 5 and 7.")
     val remainder3:Int = readln().toInt()
     val remainder5:Int = readln().toInt()
     val remainder7:Int = readln().toInt()
     val age:Int = (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105
     println("Your age is $age; that's a good time to start programming!")


     //Step4
     println("I will prove to you that I can count to any number you want:")
     val numberForCount:Int= readln().toInt()
     for(i in 1..numberForCount){
         println("$i!")
     }

     //Step5
     println("How many primitive types are there in Kotlin?")
     println("1. five")
     println("2. seven")
     println("3. eight")
     println("4. six")
     println("5. zero")
     while(true){
         val answear:String=readln().toString()
         when (answear) {
             "1.","1" -> println("Please, try again.")
             "2.","2" -> println("Please, try again.")
             "3.","3" -> println("Please, try again.")
             "4.","4" -> println("Please, try again.")
             "5.","5" -> break
             else -> println("Unknown command")

         }
     }
     println("Great, you right!")
     println("Goodbye, have a nice day!")
 }