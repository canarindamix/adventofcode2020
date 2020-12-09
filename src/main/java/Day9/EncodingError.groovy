package Day9


class EncodingError {

    File f = new File("../../resources/day9Input.txt")
    def lines = f.readLines()

    void phase1(){

        List<Long> fullList = new ArrayList<Long>()
        lines.forEach(){String line ->
            fullList.add(line.toLong())
        }

        int preambleSize = 25

        for (int i = preambleSize; i<fullList.size(); i++){
            Long numberToTest = fullList.get(i)

            List<Long> precedingNumbers = fullList.subList(i-preambleSize,i)

            boolean matchFound = false
            for(int j=0;j<precedingNumbers.size()-1 && !matchFound;j++){
                for(int k=j+1; k<precedingNumbers.size() && !matchFound;k++){
                    if (precedingNumbers.get(j) + precedingNumbers.get(k) == numberToTest){
                        matchFound=true
                    }
                }
            }

            if(!matchFound){
                println "Phase 1 answer is: " + numberToTest
                break
            }
        }

    }

    void phase2(){

        Double targetSum = 18272118

        List<Long> fullList = new ArrayList<Long>()
        lines.forEach(){String line ->
            fullList.add(line.toLong())
        }

        Double sum = 0
        for (int i=0; i<fullList.size()-1;i++){
            sum = fullList.get(i)
            for (int j=i+1;(j<fullList.size()) && sum < targetSum;j++){
                sum+=fullList.get(j)
                if (sum == targetSum){
                    println "Phase 2 answer is: " + (fullList.subList(i,j+1).min() + fullList.subList(i,j+1).max())
                    break
                }
            }
        }
    }
}