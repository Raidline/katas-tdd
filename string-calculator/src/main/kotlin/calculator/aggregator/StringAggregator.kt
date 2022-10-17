package calculator.aggregator

/**
 * @author
 **/
sealed interface StringAggregator {
    fun aggregate(input: String): List<String>
}
