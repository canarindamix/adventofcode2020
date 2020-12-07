package Day5

class BinaryBoarding {

    File f = new File("../../resources/day5Input.txt")
    def lines = f.readLines()

    void phase1(){
        String rowsSequence
        String columnSequence
        int rowNumber
        int columnNumber
        int highestID = 0

        lines.forEach(){line ->
            rowsSequence = line.substring(0,7)
            columnSequence = line.substring(7)
            rowNumber = binarySearch(rowsSequence, 0, 127)
            columnNumber = binarySearch(columnSequence, 0, 7)
            if((rowNumber * 8 + columnNumber) > highestID){
                highestID = rowNumber * 8 + columnNumber
            }
        }
        println "Phase 1 answer is: " + highestID
    }


    void phase2(){
        List<Integer> passportList = new ArrayList<Integer>()
        String rowsSequence
        String columnSequence
        int rowNumber
        int columnNumber
        int missingID
        lines.forEach(){line ->
            rowsSequence = line.substring(0,7)
            columnSequence = line.substring(7)
            rowNumber = binarySearch(rowsSequence, 0, 127)
            columnNumber = binarySearch(columnSequence, 0, 7)
            passportList.add(rowNumber * 8 + columnNumber)
        }
        passportList.sort()

        for (int i = 0; i < passportList.size()-1; i++) {
            if (passportList.get(i) + 2== passportList.get(i+1)){
                missingID = passportList.get(i) + 1
            }
        }

        println "Phase 2 answer is: " + missingID
    }

    private int binarySearch(String input, int lowBound, int highBound) {

        input.toList().forEach() { indicator ->
            if ( indicator == 'F' | indicator == 'L') {
                highBound = highBound - (highBound - lowBound) / 2
            }
            if (indicator == 'B' | indicator == 'R') {
                lowBound = lowBound + (highBound - lowBound) / 2 + 1
            }
        }
        return highBound
    }
}