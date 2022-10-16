class FizzBuzz {


    fun printFromValue(value: Int): String {

        var result =  ""

        if (isMultipleOf3(value)) {
            result += "Fizz"
        }

        if (isMultipleOf5(value)) {
            result += "Buzz"
        }

        if (result.isEmpty()) {
            return value.toString()
        }

        return result
    }

    private fun isMultipleOf5(value: Int) = value % 5 == 0

    private fun isMultipleOf3(value: Int) = value % 3 == 0
}
