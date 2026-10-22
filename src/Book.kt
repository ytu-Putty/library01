class Book(val name: String = "Nameless", val creator: String = "Nameless", val publYear: Int = 1990, val genre: Genre = Genre.DETECTIVE, var status: BookStatus = BookStatus.AVAILABLE) {


    fun printRow(rowNumber: Int) {
        val numStr = rowNumber.toString() .padEnd(4)
        val nameStr = this.name.padEnd(30)
        val authorStr = this.creator.padEnd(20)
        val yearStr = this.publYear.toString().padEnd(8)
        val genreStr = this.genre.name.padEnd(15)
        val statusStr = this.status.displayName
        println(numStr + nameStr + authorStr + yearStr + genreStr + statusStr)
    }
}