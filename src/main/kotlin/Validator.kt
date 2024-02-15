package ai.sahaj

class Validator {
    fun validate(ticket: Array<Array<Int?>>, announcedValues: List<Int>, claim: Claim): Boolean {
        val firstRowValues = mutableListOf<Int>()
        val lastRowValues = mutableListOf<Int>()
        val earlyFiveValues = mutableListOf<Int>()
        val allValues = ticket[0].toMutableList()
        allValues.addAll(ticket[1])
        allValues.addAll(ticket[2])
        var flag = announcedValues.count()
        var canClaimFirstRow = true
        var canClaimLastRow = true
        var canClaimEarlyFive = true

        for (i in announcedValues) {
            when (i) {
                in ticket[0] -> {
                    firstRowValues.add(i)
                    earlyFiveValues.add(i)
                }
                in ticket[2] -> {
                    lastRowValues.add(i)
                    earlyFiveValues.add(i)
                }
                in ticket[1] -> {
                    earlyFiveValues.add(i)
                }
                !in allValues -> {
                    println("Not Found") // Eat 5 star - Do Nothing
                }
            }
            flag -= 1
            if(firstRowValues.count() == 5 && canClaimFirstRow) {
                canClaimFirstRow = flag == 0
                firstRowValues.clear()
            } else if(lastRowValues.count() == 5 && canClaimLastRow) {
                canClaimLastRow = flag == 0
                lastRowValues.clear()
            } else if(earlyFiveValues.count() == 5 && canClaimEarlyFive) {
                canClaimEarlyFive = flag == 0
                earlyFiveValues.clear()
            }
        }
        return (canClaimFirstRow && claim == Claim.TOP_ROW) || (canClaimLastRow && claim == Claim.BOTTOM_ROW) || (canClaimEarlyFive && claim == Claim.EARLY_FIVE)
    }

}
