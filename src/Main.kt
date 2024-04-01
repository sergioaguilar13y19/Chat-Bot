//texts
val name = "Heladeria Tuti-Fruti"
val welcome = "Bienvenido a la $name, te presentamos nuestras opciones:"
val textCalcelOrder = "Lamentamos que en esta ocasion no podras disfrutar de nuestros explosivos sabores"
val textModifiedOrder = "Claro!, que te gutarias modificar?"

//mutableLists
val order: MutableList<String> = mutableListOf("", "", "Sin Complemento")

//lists
val menu = listOf(
    "1. Menu y Pedidos",
    "2. Recomendaciones de Sabores",
    "3. Horario y Ubicacion",
    "4. Curiosidades",
    "5. Salir"
)

//option 1
val iceCreams =
    listOf("Chocolate", "Vainilla", "Fresa", "Menta", "Cookies and Cream", "Rocky Road", "Mango", "Napolitano")
val sizes = listOf("Pequeño", "Mediano", "Grande")
val complements = listOf(
    "Topping de chocolate",
    "Topping de caramelo",
    "Nueces",
    "Chispas de colores",
    "Galletas trituradas",
    "Frutas frescas",
    "Crema batida"
)
val iceCreamElements = listOf("Helado", "Tamaño", "Complemento")

//option 2
val optionsRecommendation = listOf("1. Helados Populares", "2. Recomendaciones por Frutas")
val fruits = listOf("fresa", "platano", "durazno","mango","cereza","papaya")

//option 3
val ubications = listOf(
    "Av. Central Poniente y 8a Poniente Sur, Tuxtla Gutiérrez, Chiapas",
    "Número 4679, Tuxtla Gutiérrez, Chiapas",
    "calle 5 de mayo, Tuxtla Gutiérrez, Chiapas"
)
val horary = "Lunes – Sabado 9 am – 5 pm"

//option 4
val curiosities = listOf(
    "1. El helado más grande del mundo pesaba más de 12 toneladas y se hizo en Estados\n" +
            "Unidos en 2014.",
    "2. El helado es uno de los postres más antiguos, se cree que se originó en la antigua\n" +
            "Persia hace más de 2,500 años.",
    "3. El récord mundial de comer la mayor cantidad de helado en un minuto es de 1.75\n" +
            "litros y fue establecido en 2014 por un hombre en China.",
    "4. La vainilla es el sabor de helado más popular en el mundo, seguido por el chocolate\n" +
            "y la fresa.",
    "5. El primer helado servido en los Estados Unidos fue hecho por George Washington y\n" +
            "Thomas Jefferson."
)

fun invalidOption(fuction: () -> Unit) {
    println("Has Seleccionado una opcion Invalida")
    fuction()
}

fun main() {
    println(name)
    println(welcome)
    menu.forEach() {
        println(it)
    }
    println("Ingrese la opcion deseada: (1 al 5)")
    val menuValue = readLine() ?: ""
    when (menuValue) {
        "1" -> menuIceCreams(iceCreams)
        "2" -> recomendation()
        "3" -> ubications(name)
        "4" -> curiosities()
        "5" -> stopProces()
        else -> invalidOption { main() }
    }
}


fun menuIceCreams(iceCreams: List<String>) {
    println("Helados")
    selectElemetIceCream(
        iceCreams, 0, "Te gustaria pedir algo?, " +
                "ingresa tu helado favorito"
    )
    selectElemetIceCream(sizes, 1, "Que tamaño te gustaria?")
    println("Te gustaria añadir un complemento?")
    val addComplement = readLine() ?: ""
    if (addComplement.lowercase() === "si") {
        selectElemetIceCream(
            complements, 2, "Ingresa Tu Complemento" +
                    "Favorito"
        )
    }
    finalOrder()
}

fun selectElemetIceCream(elements: List<String>, index: Int, message: String) {
    elements.forEach() { item ->
        println(item)
    }
    println(message)
    val item = readLine()?.lowercase() ?: ""
    val aux = elements.map { s: String -> s.lowercase() }
    if (item in aux) {
        order[index] = item
    } else {
        invalidOption { selectElemetIceCream(elements, index, message) }
    }

}

fun finalOrder() {
    println(
        "Tu Pedido es : un helado de ${order[0]} " +
                "Tamaño ${order[1]} ${order[2]}"
    )
    println("Es correcto?")
    val correctOrder = readLine()?.lowercase() ?: ""
    if (correctOrder == "si") {
        println("Gracias por comprar en $name")
    } else {
        wrongOrder()
    }
}

fun wrongOrder() {
    println("Te gustaria modificar o cancelar tu orden?")
    val wrongOrder = readLine()?.lowercase() ?: ""
    when (wrongOrder) {
        "cancelar" -> println(textCalcelOrder)
        "modificar" -> modifiedOrder(iceCreamElements)
        else -> invalidOption { wrongOrder() }
    }
}

fun modifiedOrder(iceCreamElements: List<String>) {
    println(textModifiedOrder)
    iceCreamElements.forEach() {
        println(it)
    }
    println("Que desea modificar de sus helado?")
    val elementToModify = readLine()?.lowercase() ?: ""
    val MODIFY_TEXT = "por cual deseas modificarlo?"
    when (elementToModify) {
        "helado" -> selectElemetIceCream(
            iceCreams,
            0,
            "Tu helado actual es ${order[0]} $MODIFY_TEXT"
        )

        "tamaño" -> selectElemetIceCream(
            sizes,
            1,
            "Tu tamaño actual es ${order[1]} $MODIFY_TEXT"
        )

        "complemento" -> selectElemetIceCream(
            complements,
            3,
            "Tu complemento actual es: ${order[2]} $MODIFY_TEXT"
        )

        else -> invalidOption { modifiedOrder(iceCreamElements) }
    }
    finalOrder()
}

fun recomendation() {
    optionsRecommendation.forEach() { option ->
        println(option)
    }
    println("Ingrese la opcion deseada (1 o 2)")
    val selectRecomendation = readLine() ?: ""
    when (selectRecomendation) {
        "1" -> popularIceCream()
        "2" -> recomendationForFruit()
        else -> invalidOption { recomendation() }
    }
}

fun popularIceCream() {
    println("helados populares")
    val popularIceCreams = listOf("Menta", "Fresa", "Chocolate")
    menuIceCreams(popularIceCreams)
}

fun recomendationForFruit() {
    val iceCreamFruits = mapOf(
        "Chocolate" to listOf("platano", "fresa"),
        "Vainilla" to listOf("durazno", "fresa"),
        "Fresa" to listOf("fresa", "mango"),
        "Menta" to listOf("mango", "platano"),
        "Cookies and Cream" to listOf("fresa", "platano"),
        "Rocky Road" to listOf("fresa", "cereza"),
        "Mango" to listOf("mango", "papaya"),
        "Napolitano" to listOf("fresa", "platano")
    )
    println("Frutas")
    fruits.forEach() { fruit ->
        println(fruit)
    }
    println("Ingrese la fruta a elegir")
    val fruitselect = readLine()?.lowercase() ?: ""
    val recomendation = mutableListOf<String>()
    //verificar si la fruta existe en el arreglo
    for ((helado, frutas) in iceCreamFruits) {
        //buscar nombres de helados en donde exista esa fruta
        if (fruitselect in frutas) {
            recomendation.add(helado)
        }
    }
    //si esta vacio
    if (recomendation.isEmpty()) {
        println("Lamentamos No tener una recomendacion para ti")
        main()
    } else {
        println("Los Helados recomendados Son: ")
        menuIceCreams(recomendation)
    }
}

fun ubications(name: String) {
    println("Horarios y Ubicaciones de: $name")
    ubications.forEach() {
        println()
        println("Direccion: $it")
        println("Horario de atencion: $horary")
    }
    exitOrMenu()
}

fun curiosities() {
    println("Curiosidades del Helado")
    curiosities.forEach() {
        println()
        println(it)
    }
    exitOrMenu()
}

fun stopProces() {
    println("Gracias por visitar $name.")
}

fun exitOrMenu() {
    println()
    println("Desea salir o ir al menu")
    val optionUbication = readLine()?.lowercase() ?: ""
    when (optionUbication) {
        "salir" -> stopProces()
        "menu" -> main()
        else -> invalidOption { exitOrMenu() }
    }
}