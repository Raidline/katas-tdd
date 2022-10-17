package calculator.processor

private const val COMMA_DELIMITER = ","

class StringCommaInputProcessor : StringInputProcessor {

    override fun process(input: String): List<String> {
        return input.split(COMMA_DELIMITER)
    }

    override fun canHandle(input: String): Boolean {
        return input.contains(COMMA_DELIMITER)
    }
}
