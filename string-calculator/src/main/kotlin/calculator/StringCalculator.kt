package calculator

import calculator.aggregator.StringAggregator


class StringCalculator(private val aggregator: StringAggregator) {

    fun calculate(input : String) : Int {

        if (input.isEmpty()) return 0

        val numbers = aggregator.aggregate(input)

        return numbers.sumOf { parseInput(it) }
    }

    private fun parseInput(number : String) = number.toInt()
}
