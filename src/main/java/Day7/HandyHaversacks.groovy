package Day7


class HandyHaversacks {

    File f = new File("../../resources/day7Input.txt")
    def lines = f.readLines()
    int shinyCount = 0

    void phase1(){

        String bagColour
        String bagAllowedContent
        Map<String,List> rules = new HashMap<String,List>()

        lines.forEach(){line ->
            bagColour = line.split('bags contain ')[0].trim()
            bagAllowedContent = line.split('bags contain ')[1]
            List<String> list = new ArrayList<String>()
            bagAllowedContent.tokenize(',').forEach(){item ->
                String colour = item.replaceAll('[0-9]|(bags)|(bag)|(\\.)','').trim()
                list.add(colour)
            }
            rules.put(bagColour,list)
        }

        rules.keySet().forEach(){String colour ->
            if (processShiny(rules.get(colour),rules,false)){
                shinyCount++
            }
        }
        println "Phase 1 answer is: " + shinyCount
    }

    private boolean processColor(List content, Map rules, boolean  shinyFound){

        for (int i=0;i<content.size()&&!shinyFound;i++){
            String colour = content.get(i)
            if(colour == "shiny gold"){
                shinyFound = true
                return shinyFound
            }  else {
                if(rules.get(colour) != null){
                    shinyFound = processColor(rules.get(colour),rules,shinyFound)
                }
            }
        }

        return shinyFound
    }

    void phase2(){

        String bagColour
        String bagAllowedContent
        Map<String,List> rules = new HashMap<String,List>()

        lines.forEach(){line ->
            bagColour = line.split('bags contain ')[0].trim()
            bagAllowedContent = line.split('bags contain ')[1]
            List<String> list = new ArrayList<String>()
            bagAllowedContent.tokenize(',').forEach(){item ->
                String colour = item.replaceAll('(bags)|(bag)|(\\.)','').trim()
                list.add(colour)
            }
            rules.put(bagColour,list)
        }

       Node root = new Node(null,"shiny gold")
       buildTree(root,rules)

       println "Phase 2 answer is: " + (root.depthFirst().size()-1)
    }

    private buildTree(Node node, Map<String,List> rules){
        rules.get(node.name()).forEach(){String numberAndColor ->
            if (numberAndColor!="no other"){
                int numberOfBags = Integer.valueOf(numberAndColor.split(' ')[0])
                while(numberOfBags > 0){
                    Node child = new Node(node,numberAndColor.split(' ')[1] + ' ' + numberAndColor.split(' ')[2])
                    numberOfBags = numberOfBags - 1
                }
            }
        }
        node.children().forEach(){Node child ->
            buildTree(child,rules)
        }
    }
}