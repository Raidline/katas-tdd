package calculator


class StringCalculator {

    fun calculate(input : String) : Int {

        if (input.isEmpty()) return 0

        val numbers = input.split(",")

        if (numbers.size == 1) {
            return parseInput(numbers[0])
        }

        return numbers.sumOf { parseInput(it) }
    }

    private fun parseInput(number : String) = number.toInt()
}
