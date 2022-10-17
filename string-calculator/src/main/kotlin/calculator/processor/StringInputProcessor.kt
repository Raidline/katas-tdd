package calculator.processor


interface StringInputProcessor {

    fun process(input : String) : List<String>
    fun canHandle(input : String) : Boolean
}
