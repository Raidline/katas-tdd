package calculator.aggregator

import calculator.processor.StringInputProcessor
import calculator.processor.StringNewLineInputProcessor

class StringProcessorAggregate(private val commaProcessor: StringInputProcessor,
                               private val newLineProcessor: StringNewLineInputProcessor) : StringAggregator {

    override fun aggregate(input: String): List<String> {

        if (input.trim().length == 1) {
            return listOf(input)
        }

        val commaNumbers = mutableListOf<String>()

        if (commaProcessor.canHandle(input)) {
            commaNumbers.addAll(commaProcessor.process(input))
        }

        if (commaNumbers.isEmpty() && newLineProcessor.canHandle(input)) {
            return aggregateOnlyNewLine(input)
        }

        return aggregateCommaAndNewLine(commaNumbers)
    }

    private fun aggregateCommaAndNewLine(commaNumbers: MutableList<String>) : List<String> {
        val numbers = mutableListOf<String>()

        commaNumbers.forEach {
            if (newLineProcessor.canHandle(it)) {
                numbers.addAll(newLineProcessor.process(it))
            } else {
                numbers.add(it)
            }
        }

        return numbers
    }

    private fun aggregateOnlyNewLine(input: String): List<String> {
        return newLineProcessor.process(input)
    }
}
