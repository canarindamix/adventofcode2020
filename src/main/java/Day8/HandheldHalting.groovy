package Day8


class HandheldHalting {

    File f = new File("../../resources/day8Input.txt")
    def lines = f.readLines()
    int accumulator = 0

    void phase1(){

        Node root = new Node(null,"root")
        Node previousParent = root
        lines.forEach(){String line ->
            Map<String,String> instruction = new HashMap<String,String>()
            instruction.put("instruction",line.split(' ')[1])
            instruction.put("invokations","0")
            new Node(previousParent,line.split(' ')[0],instruction)
        }

        List instructionList = root.depthFirst()
        processInstruction(instructionList,1,"",0)

        println "Phase 1 answer is: " + accumulator
    }


    private void phase2(){
        Node root = new Node(null,"root")
        Node previousParent = root
        lines.forEach(){String line ->
            Map<String,String> instruction = new HashMap<String,String>()
            instruction.put("instruction",line.split(' ')[1])
            instruction.put("invokations","0")
            new Node(previousParent,line.split(' ')[0],instruction)
        }

        boolean loopExist = true

        for(int i=1; i<root.depthFirst().size() && loopExist; i++){
            accumulator = 0
            Node instr = root.depthFirst().get(i)
            if (instr.name() == "nop" && instr.attribute("instruction") != "+0"){
                Node rootClone = root.clone()
                loopExist = processInstruction(rootClone.depthFirst(),1,"jmp",i)
            } else if (instr.name() == "jmp") {
                Node rootClone = root.clone()
                loopExist = processInstruction(rootClone.depthFirst(),1,"nop",i)
            }
        }

        println "Phase 2 answer is: " + accumulator
    }

    // return true if loop was detected
    private boolean  processInstruction(List<Node> instructionList,int indexOfInstruction, String fix, int indexOfFixedInstr){

        boolean loopFound = false

        if (indexOfInstruction < instructionList.size()){
            Node instruction = instructionList.get(indexOfInstruction)

            int invokations = Integer.valueOf(instruction.attribute("invokations")) + 1
            if(invokations > 1){
                loopFound = true
                return loopFound
            }
            instruction.attributes().put("invokations",invokations.toString())
            if (instruction.name() == "acc"){
                String acc = instruction.attribute("instruction")
                if(acc[0] == "+"){
                    accumulator += Integer.valueOf(acc.replace('+',''))
                } else{
                    accumulator -= Integer.valueOf(acc.replace('-',''))
                }
                loopFound = processInstruction(instructionList,indexOfInstruction+1,fix,indexOfFixedInstr)
            } else if ((instruction.name() =="nop") || (fix == "nop" && indexOfInstruction==indexOfFixedInstr)){
                loopFound = processInstruction(instructionList,indexOfInstruction+1, fix,indexOfFixedInstr)
            } else if ((instruction.name() == "jmp") || (fix == "jmp" && indexOfInstruction==indexOfFixedInstr )){
                String jmp = instruction.attribute("instruction")
                if(jmp[0] == "+"){
                    loopFound = processInstruction(instructionList,indexOfInstruction + Integer.valueOf(jmp.replace('+','')),fix,indexOfFixedInstr)
                } else{
                    loopFound = processInstruction(instructionList,indexOfInstruction - Integer.valueOf(jmp.replace('-','')),fix,indexOfFixedInstr)
                }
            }
        }

        return loopFound
    }

}