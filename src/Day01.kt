fun main() {
  fun getLeftList(input: List<String>): MutableList<Int> {
    val leftList = mutableListOf<Int>()
    // get the numbers out of the file
    for (line in input) {
      var firstNumber: Int
      val lineAsSequence = line.toCharArray()
      val firstNumberAsSequence = CharArray(5)

      for (i in 0..4) {
        firstNumberAsSequence[i] = lineAsSequence[i]
      }
      firstNumber = String(firstNumberAsSequence).toInt()

      leftList.add(firstNumber)
    }
    leftList.sort()

    return leftList
  }

  fun getRightList(input: List<String>): MutableList<Int> {
    val rightList = mutableListOf<Int>()

    // get the numbers out of the file
    for (line in input) {
      var secondNumber: Int
      val lineAsSequence = line.toCharArray()
      val secondNumberAsSequence = CharArray(5)

      for (i in 8..12) {
        secondNumberAsSequence[i - 8] = lineAsSequence[i]
      }
      secondNumber = String(secondNumberAsSequence).toInt()

      // add second value of line to rightList
      rightList.add(secondNumber)
    }

    rightList.sort()

    return rightList
  }

  /** What is the total distance between your lists? */
  fun part1(input: List<String>): Int {

    var totalDistance = 0

    val leftList = getLeftList(input)
    val rightList = getRightList(input)

    for (i in 1..input.size) {
      var difference = leftList[i - 1] - rightList[i - 1]

      if (difference < 0) {
        difference = difference * -1
      }

      totalDistance += difference
    }

    return totalDistance
  }

  /**
   * Calculate a total similarity score by adding up each number in the left list after multiplying
   * it by the number of times that number appears in the right list.
   */
  fun part2(input: List<String>): Int {
    val leftList = getLeftList(input)
    val rightList = getRightList(input)

    var similarityScore = 0
    var occurrence = 0

    for (i in input.indices) {
      for (n in input.indices) {
        if (leftList[i] == rightList[n]) {
          occurrence += 1
        }
      }
      val value = leftList[i] * occurrence
      occurrence = 0
      similarityScore += value
    }

    return similarityScore
  }

  // Read the input from the `src/Day01.txt` file.
  val input = readInput("Day01_input")
  part1(input).println()
  part2(input).println()
}
