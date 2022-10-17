package calculator.processor

private const val NEWLINE_DELIMITER = "\n"

class StringNewLineInputProcessor : StringInputProcessor {

    override fun process(input: String): List<String> {
        return input.split(NEWLINE_DELIMITER)
    }

    override fun canHandle(input: String): Boolean {
        return input.contains("\n")
    }


}
