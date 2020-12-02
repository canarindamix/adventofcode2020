package Day2

class PasswordPolicy {

    File f = new File("../../resources/day2Input.txt")
    def lines = f.readLines()

    void phase1(){
        Integer numberOfValidPasswords = 0
        Integer lowBound = 0
        Integer highBound = 0
        String password
        String mandatoryLetter

        lines.forEach(){line ->
            lowBound = Integer.parseInt(line.split(':')[0].split(' ')[0].split('-')[0])
            highBound = Integer.parseInt(line.split(':')[0].split(' ')[0].split('-')[1])
            mandatoryLetter = line.split(':')[0].split(' ')[1]
            password = line.split(':')[1]

            if(password.count(mandatoryLetter) >= lowBound && password.count(mandatoryLetter) <=highBound){
                numberOfValidPasswords++
            }
        }
        println  "Phase 1: Answer is " + numberOfValidPasswords
    }

    void phase2(){
        Integer numberOfValidPasswords = 0
        Integer firstPosition = 0
        Integer secondPosition = 0
        String password
        String mandatoryLetter

        lines.forEach(){line ->
            firstPosition = Integer.parseInt(line.split(':')[0].split(' ')[0].split('-')[0])
            secondPosition = Integer.parseInt(line.split(':')[0].split(' ')[0].split('-')[1])
            mandatoryLetter = line.split(':')[0].split(' ')[1]
            password = line.split(':')[1]

            if(password.getAt(firstPosition).concat(password.getAt(secondPosition)).count(mandatoryLetter) == 1){
                numberOfValidPasswords++
            }
        }
        println  "Phase 2: Answer is " + numberOfValidPasswords
    }
}