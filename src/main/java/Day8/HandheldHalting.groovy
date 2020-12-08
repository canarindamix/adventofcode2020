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
        processInstruction(instructionList,1)

        println "Phase 1 answer is: " + accumulator
    }

    private void processInstruction(List<Node> instructionList,int indexOfInstruction){

        Node instruction = instructionList.get(indexOfInstruction)

        int invokations = Integer.valueOf(instruction.attribute("invokations")) + 1
        if(invokations > 1){
            return
        }
        instruction.attributes().put("invokations",invokations.toString())
        if (instruction.name() == "acc"){
            String acc = instruction.attribute("instruction")
            if(acc.getAt(0) == "+"){
                accumulator += Integer.valueOf(acc.replace('+',''))
            } else{
                accumulator -= Integer.valueOf(acc.replace('-',''))
            }
            processInstruction(instructionList,indexOfInstruction+1)
        } else if (instruction.name() =="nop"){
            processInstruction(instructionList,indexOfInstruction+1)
        } else if (instruction.name() == "jmp"){
            String jmp = instruction.attribute("instruction")
            if(jmp.getAt(0) == "+"){
                processInstruction(instructionList,indexOfInstruction + Integer.valueOf(jmp.replace('+','')))
            } else{
                processInstruction(instructionList,indexOfInstruction - Integer.valueOf(jmp.replace('-','')))
            }
        }
    }

}