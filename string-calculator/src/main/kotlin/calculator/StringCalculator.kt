package calculator


class StringCalculator {

    fun calculate(input : String) : Int {

        if (input.isEmpty()) return 0

        val numbers = input.split(",")

        if (numbers.size == 1) {
            return numbers[0].toInt()
        }

        return numbers[0].toInt() + numbers[1].toInt()
    }
}
