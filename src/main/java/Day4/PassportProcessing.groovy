package Day4

class PassportProcessing {

    File f = new File("../../resources/day4Input.txt")
    def lines = f.readLines()

    void phase1(){

        Integer nbOfFieldsInCurrentPassport = 0
        Integer nbOfValidPassports = 0

        lines.forEach(){line ->

            if(line.size() == 0){
                if(nbOfFieldsInCurrentPassport == 7){
                    nbOfValidPassports++
                }
                nbOfFieldsInCurrentPassport = 0
            } else {
                List<String> fields = line.tokenize(' ')
                fields.forEach(){field ->
                    if (!field.contains('cid')){
                        nbOfFieldsInCurrentPassport++
                    }
                }
            }
        }
        println "Phase 1 answer is: " + nbOfValidPassports
    }

    void phase2(){

        Integer nbOfValidFieldsInCurrentPassport = 0
        Integer nbOfValidPassports = 0

        lines.forEach(){line ->

            if(line.size() == 0){
                if(nbOfValidFieldsInCurrentPassport == 7){
                    nbOfValidPassports++
                }
                nbOfValidFieldsInCurrentPassport = 0
            } else {
                List<String> fields = line.tokenize(' ')
                fields.forEach(){field ->

                    if (field.contains('byr')){
                        Integer byr = Integer.valueOf(field.split(':')[1])
                        if ( byr >= 1920 && byr <= 2002){
                            nbOfValidFieldsInCurrentPassport++
                        }
                    }
                    if (field.contains('iyr')){
                        Integer iyr = Integer.valueOf(field.split(':')[1])
                        if ( iyr >= 2010 && iyr <= 2020){
                            nbOfValidFieldsInCurrentPassport++
                        }
                    }
                    if (field.contains('eyr')){
                        Integer eyr = Integer.valueOf(field.split(':')[1])
                        if ( eyr >= 2020 && eyr <= 2030){
                            nbOfValidFieldsInCurrentPassport++
                        }
                    }
                    if (field.contains('hgt')){
                        if (field.endsWith('cm')){
                            Integer hgt = Integer.valueOf((field.split(':')[1]).replace('cm',''))
                            if (hgt >= 150 && hgt <= 193){
                                nbOfValidFieldsInCurrentPassport++
                            }
                        }
                        if (field.endsWith('in')){
                            Integer hgt = Integer.valueOf((field.split(':')[1]).replace('in',''))
                            if (hgt >= 59 && hgt <= 76){
                                nbOfValidFieldsInCurrentPassport++
                            }
                        }
                    }
                    if (field.contains('hcl')){
                        String hcl = field.split(':')[1]
                        if(hcl.matches('^#[a-f|0-9]{6}$')){
                            nbOfValidFieldsInCurrentPassport++
                        }
                    }
                    if (field.contains('ecl')){
                        String ecl = field.split(':')[1]
                        if(ecl.matches('^(amb|blu|brn|gry|grn|hzl|oth)$')){
                            nbOfValidFieldsInCurrentPassport++
                        }
                    }
                    if (field.contains('pid')){
                        String pid = field.split(':')[1]
                        if(pid.matches('^[0-9]{9}$')){
                            nbOfValidFieldsInCurrentPassport++
                        }
                    }
                }
            }
        }
        println "Phase 2 answer is: " + nbOfValidPassports
    }


}