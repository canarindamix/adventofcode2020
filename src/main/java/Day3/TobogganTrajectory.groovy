package Day3

class TobogganTrajectory {

    File f = new File("../../resources/day3Input.txt")
    def lines = f.readLines()

    void phase1(){
        Integer numberOfTreesEncountered = findNbOfTreesOnPath(3,1)
        println  "Phase 1: Answer is " + numberOfTreesEncountered
    }

    private Integer findNbOfTreesOnPath(Integer stepsToTheRight, Integer stepsDown) {
        Integer numberOfTreesEncountered = 0
        Integer startingPointForNextRow = 0
        Integer currentLineNumber = 0

        lines.forEach() { line ->
            if (currentLineNumber%stepsDown == 0){
                if (line.getAt(startingPointForNextRow) == '#') {
                    numberOfTreesEncountered++
                }
                startingPointForNextRow = (startingPointForNextRow + stepsToTheRight) % line.size()
            }
            currentLineNumber++
        }
        return numberOfTreesEncountered
    }

    void phase2(){
        Long treesOnPath1 = findNbOfTreesOnPath(1,1)
        Long treesOnPath2 = findNbOfTreesOnPath(3,1)
        Long treesOnPath3 = findNbOfTreesOnPath(5,1)
        Long treesOnPath4 = findNbOfTreesOnPath(7,1)
        Long treesOnPath5 = findNbOfTreesOnPath(1,2)

        println  "Phase 2: Answer is " + treesOnPath1 * treesOnPath2 * treesOnPath3 * treesOnPath4 * treesOnPath5
    }
}