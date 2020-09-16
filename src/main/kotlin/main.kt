fun addBrackets(corners: Boolean, center: Boolean, openBrackets: MutableList<Char>, string: String?): String {
    var closeBrackets: MutableList<Char> = mutableListOf()
    for (i in openBrackets) {
        when(i) {
            '(' -> closeBrackets.add(')')
            '[' -> closeBrackets.add(']')
            '{' -> closeBrackets.add('}')
        }
    }
    var stringWithBrackets: String = ""
    var bracketCounter: Int = 0
    val l = openBrackets.size
    if (corners) {
        stringWithBrackets += openBrackets[bracketCounter]
        bracketCounter++
        bracketCounter %= l
    }
    if (string != null) {
        for (c in string.take(string.length / 2)) {
            stringWithBrackets += c
            stringWithBrackets += openBrackets[bracketCounter]
            bracketCounter++
            bracketCounter %= l
        }
        if (center && string.length % 2 == 0) {
            bracketCounter += l - 1
            bracketCounter %= l
            stringWithBrackets += closeBrackets[bracketCounter]
            stringWithBrackets += string[string.length / 2]
            bracketCounter += l - 1
            bracketCounter %= l
        } else {
            if (string.length % 2 == 1) {
                bracketCounter += l - 1
                bracketCounter %= l
                stringWithBrackets += string[string.length / 2]
                stringWithBrackets += closeBrackets[bracketCounter]
                stringWithBrackets += string[string.length / 2 + 1]
                bracketCounter += l - 1
                bracketCounter %= l
            } else {
                stringWithBrackets = stringWithBrackets.dropLast(1)
                stringWithBrackets += string[string.length / 2]
                bracketCounter++
                bracketCounter %= l
            }
        }
        for (c in string.takeLast(string.length / 2 - 1)) {
            stringWithBrackets += closeBrackets[bracketCounter]
            stringWithBrackets += c
            bracketCounter += l - 1
            bracketCounter %= l
        }
    }
    if (corners) {
        stringWithBrackets += closeBrackets[bracketCounter]
    }
    return stringWithBrackets
}

fun main() {
    println("Input target string")
    var string = readLine()
    while (string == "") {
        println("Please input not empty string")
        println("Input target string")
        string = readLine()
    }
    var corners: String? = ""
    while (corners != "1" && corners != "2") {
        println("Do you want to put brackets on the outside?\n1 - Yes\n2 - No")
        corners = readLine()
        if (corners != "1" && corners != "2") {
            println("Wrong input format. Please try again")
        }
    }
    var center: String? = ""
    while (center != "1" && center != "2") {
        println("Do you want to put brackets in the middle?\n1 - Yes\n2 - No")
        center = readLine()
        if (center != "1" && center != "2") {
            println("Wrong input format. Please try again")
        }
    }
    var flag = true
    var openBrackets: MutableList<Char> = mutableListOf()
    while (flag) {
        println("Input what brackets you want to use and in what order with no spaces\nFor example if you want to use " +
                "( and [ input ([ and if you want to use ( { and [ in that order input ({[")
        val bracketString = readLine()
        flag = false
        if (bracketString != null) {
            openBrackets = mutableListOf()
            for (i in bracketString) {
                if (!(i == '(' || i == '[' || i == '{')) {
                    flag = true
                    println("Wrong input format. Please try again")
                } else {
                    openBrackets.add(i)
                }
            }
        }
    }
    print(addBrackets(corners = (corners == "1"), center = (center == "1"),
            openBrackets = openBrackets, string = string))
}