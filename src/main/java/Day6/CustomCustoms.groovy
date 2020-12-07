package Day6

class CustomCustoms {

    File f = new File("../../resources/day6Input.txt")
    def lines = f.readLines()

    void phase1(){

        Set<String> groupAnswers = new HashSet<String>()
        int sumOfCounts = 0

        lines.eachWithIndex(){ line,i ->

            line.toList().forEach(){answer ->
                groupAnswers.add(answer)
            }
            if(line.isEmpty() || i == lines.size()-1){
                sumOfCounts += groupAnswers.size()
                groupAnswers = new HashSet<String>()
            }
        }

        println "Phase 1 answer is: " + sumOfCounts
    }

    void phase2(){

        Map<String,Integer> groupAnswers = new HashMap<String,Integer>()
        int sumOfCounts = 0
        int nbOfPeopleInGroup = 0

        lines.eachWithIndex { line,i ->

            if(!line.isEmpty()){
                nbOfPeopleInGroup++
            }

            line.toList().forEach(){answer ->
                if(groupAnswers.containsKey(answer)){
                    groupAnswers.put(answer, groupAnswers[answer] +1)
                } else {
                    groupAnswers.put(answer,1)
                }
            }

            if(line.isEmpty() || i == lines.size()-1){
                groupAnswers.values().removeIf(v -> v != nbOfPeopleInGroup)
                sumOfCounts += groupAnswers.keySet().size()
                groupAnswers = new HashMap<String,Integer>()
                nbOfPeopleInGroup = 0
            }
        }

        println "Phase 2 answer is: " + sumOfCounts
    }
}