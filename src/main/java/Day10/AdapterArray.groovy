package Day10


class AdapterArray {

    File f = new File("../../resources/day10Input.txt")
    def lines = f.readLines()

    void phase1(){

        List<Integer> adapters = new ArrayList<Integer>()
        int oneJoltsDifferencesCounter = 0
        int threeJoltsDifferencesCounter = 0

        int currentJoltage = 0

        lines.forEach(){
            adapters.add(it.toInteger())
        }

        //add integrated adapter
        adapters.sort().add(adapters.max() + 3)

        while(adapters.size() > 0){
            List<Integer> listOfUsedTransfo = new ArrayList<Integer>()
            for (int i = 0;i<adapters.size();i++){
                Integer adapter = adapters.getAt(i)
                int diff = adapter - currentJoltage
                if(diff <=3 || diff >= 1){
                    currentJoltage = adapter
                    if (diff == 3){
                        threeJoltsDifferencesCounter++
                    } else if (diff == 1){
                        oneJoltsDifferencesCounter++
                    }
                    listOfUsedTransfo.add(adapter)
                }
            }
            adapters.removeAll(listOfUsedTransfo)
        }

        println "Phase 1 answer is: " + oneJoltsDifferencesCounter + " one jolt diffs and " + threeJoltsDifferencesCounter + " three jolts diff"


    }
}