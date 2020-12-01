package Day1

class ExpenseReport {

    File f = new File("../../resources/day1Input.txt")
    def lines = f.readLines()

    void phase1(){
    Integer result = 0
        lines.forEach(){ item1 ->
            lines.forEach() { item2 ->
                if (Integer.parseInt(item1) + Integer.parseInt(item2)  == 2020){
                    result = Integer.parseInt(item1) * Integer.parseInt(item2)
                }
            }
        }
        println  "Phase 1: Answer is " + result
    }

    void phase2(){
        Integer result = 0

        lines.forEach(){ item1 ->
            lines.forEach() { item2 ->
                lines.forEach() { item3 ->
                    if (Integer.parseInt(item1) + Integer.parseInt(item2) + Integer.parseInt(item3) == 2020){
                        result = Integer.parseInt(item1) * Integer.parseInt(item2) * Integer.parseInt(item3)

                    }
                }
            }
        }
        println  "Phase 2: Answer is " + result
    }
}