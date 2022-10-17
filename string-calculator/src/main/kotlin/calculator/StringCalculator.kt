package calculator

import calculator.aggregator.StringAggregator


class StringCalculator(private val aggregator: StringAggregator) {

    fun calculate(input : String) : Int {

        if (input.isEmpty()) return 0

        val numbers = aggregator.aggregate(input)

        if (numbers.size == 1) {
            return parseInput(numbers[0])
        }

        return numbers.sumOf { parseInput(it) }
    }

    private fun parseInput(number : String) = number.toInt()
}
