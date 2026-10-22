fun main() {
    val book1 = Book("Мастер и Маргарита", "Булгаков", 1967, Genre.FICTION, BookStatus.AVAILABLE)
    val book2 = Book("Преступление и наказание", "Достоевский", 1866, Genre.FICTION, BookStatus.BORROWED)
    val book3 = Book("Краткая история времени", "Хокинг", 1988, Genre.SCIENTIFIC, BookStatus.AVAILABLE)
    val book4 = Book("Гарри Поттер", "Роулинг", 1997, Genre.FANTASY, BookStatus.RESERVED)
    val book5 = Book("Шерлок Холмс", "Конан Дойл", 1892, Genre.DETECTIVE, BookStatus.AVAILABLE)
    val book6 = Book("Автобиография", "Агата Кристи", 1977, Genre.BIOGRAPHY, BookStatus.ARCHIVED)
    val book7 = Book("Приключения Тома Сойера", "Марк Твен", 1876, Genre.CHILDREN, BookStatus.LOST)
    val book8 = Book("Дюна", "Фрэнк Герберт", 1965, Genre.FANTASY, BookStatus.AVAILABLE)
    val book9 = Book("Собачье сердце", "Булгаков", 1925, Genre.FICTION, BookStatus.AVAILABLE)
    val book10 = Book("Чертоги разума", "Курпатов", 2018, Genre.SCIENTIFIC, BookStatus.BORROWED)

    val catalog = listOf(book1, book2, book3, book4, book5, book6, book7, book8, book9, book10)
    printReport(catalog)
}

fun showBooks(catalog: List<Book>)
{
    println("№".padEnd(4) + "Название".padEnd(30) + "Автор".padEnd(20) + "Год".padEnd(8) + "Жанр".padEnd(15) + "Статус")
    for (i in 0 until catalog.size) {
        val book = catalog[i]
        val number = i + 1
        book.printRow(number)
    }
}

fun showStatistic(catalog: List<Book>)
{
    val totalBooks = catalog.size
    // счетчики для статусов
    var availableCount = 0
    var borrowedCount = 0
    var lostCount = 0
    var reservedCount = 0
    var archivedCount = 0

    // счетчики для жанров
    var fictionCount = 0
    var scientificCount = 0
    var fantasyCount = 0
    var detectiveCount = 0
    var biographyCount = 0
    var childrenCount = 0

    for (i in 0 until catalog.size) {
        val book = catalog[i]
        when (book.status) {
            BookStatus.AVAILABLE -> availableCount++
            BookStatus.BORROWED -> borrowedCount++
            BookStatus.LOST -> lostCount++
            BookStatus.RESERVED -> reservedCount++
            BookStatus.ARCHIVED -> archivedCount++
        }
        when (book.genre) {
            Genre.FICTION -> fictionCount++
            Genre.SCIENTIFIC -> scientificCount++
            Genre.FANTASY -> fantasyCount++
            Genre.DETECTIVE -> detectiveCount++
            Genre.BIOGRAPHY -> biographyCount++
            Genre.CHILDREN -> childrenCount++
        }
    }

    println("\nСтатистика книг в каждом статусе:")
    println("${BookStatus.AVAILABLE.displayName}: $availableCount (${String.format("%.0f", (availableCount.toDouble() / totalBooks) * 100)}%)")
    println("${BookStatus.BORROWED.displayName}: $borrowedCount (${String.format("%.0f", (borrowedCount.toDouble() / totalBooks) * 100)}%)")
    println("${BookStatus.LOST.displayName}: $lostCount (${String.format("%.0f", (lostCount.toDouble() / totalBooks) * 100)}%)")
    println("${BookStatus.RESERVED.displayName}: $reservedCount (${String.format("%.0f", (reservedCount.toDouble() / totalBooks) * 100)}%)")
    println("${BookStatus.ARCHIVED.displayName}: $archivedCount (${String.format("%.0f", (archivedCount.toDouble() / totalBooks) * 100)}%)")

    println("\nСтатистика книг в каждом жанре:")
    println("${Genre.FICTION.displayName}: $fictionCount (${String.format("%.0f", (fictionCount.toDouble() / totalBooks) * 100)}%)")
    println("${Genre.SCIENTIFIC.displayName}: $scientificCount (${String.format("%.0f", (scientificCount.toDouble() / totalBooks) * 100)}%)")
    println("${Genre.FANTASY.displayName}: $fantasyCount (${String.format("%.0f", (fantasyCount.toDouble() / totalBooks) * 100)}%)")
    println("${Genre.DETECTIVE.displayName}: $detectiveCount (${String.format("%.0f", (detectiveCount.toDouble() / totalBooks) * 100)}%)")
    println("${Genre.BIOGRAPHY.displayName}: $biographyCount (${String.format("%.0f", (biographyCount.toDouble() / totalBooks) * 100)}%)")
    println("${Genre.CHILDREN.displayName}: $childrenCount (${String.format("%.0f", (childrenCount.toDouble() / totalBooks) * 100)}%)")
}

fun Years(catalog: List<Book>)
{
    val totalBooks = catalog.size
    if (totalBooks == 0)
    {
        println("Каталог пуст!")
        return
    }
    var oldestBook = catalog[0]
    var newestBook = catalog[0]
    var totalYearsSum = 0

    for (i in 0 until catalog.size) {
        val book = catalog[i]
        totalYearsSum += book.publYear
        if (book.publYear < oldestBook.publYear) {
            oldestBook = book
        }
        if (book.publYear > newestBook.publYear) {
            newestBook = book
        }
    }
    println("\nСамая старая книга: ${oldestBook.name} (${oldestBook.publYear})")
    println("Самая новая книга: ${newestBook.name} (${newestBook.publYear})")

    val averageYear = totalYearsSum.toDouble() / totalBooks
    println("Средний год: ${String.format("%.0f", averageYear)}")
}

fun filterGenre (catalog: List<Book>)
{
    println("\nВведите название жанра для поиска (например, FANTASY или fantasy):")
    val inputGenre = readln().uppercase()
    println("\n$inputGenre:")
    println("№".padEnd(4) + "Название".padEnd(30) + "Автор".padEnd(20) + "Год".padEnd(8) + "Жанр".padEnd(15) + "Статус")
    println("-".repeat(85))

    var numFilter = 0
    for (i in 0 until catalog.size) {
        val book = catalog[i]
        if (book.genre.name == inputGenre) {
            book.printRow(numFilter)
            numFilter++
        }
    }

    if (numFilter == 0) {
        println("Книг с таким жанром не найдено.")
    }

}

fun availableBooks (catalog: List<Book>)
{
    println("Список всех доступных книг:")
    println("№".padEnd(4) + "Название".padEnd(30) + "Автор".padEnd(20) + "Год".padEnd(8) + "Жанр".padEnd(15) + "Статус")
    var numFilters = 0
    for (i in 0 until catalog.size) {
        val book = catalog[i]
        if (book.status == BookStatus.AVAILABLE) {
            book.printRow(numFilters)
            numFilters++
        }
    }
    if (numFilters == 0)
    {
        println("Нет доступных книг!")
    }
}

fun afterYear (catalog: List<Book>)
{
    println("Введите год:")
    val year = readln().toInt()
    var numYears = 0
    for (i in 0 until catalog.size)
    {
        val book = catalog[i]
        if(book.publYear > year)
        {
            book.printRow(numYears)
            numYears++
        }
    }
    if (numYears == 0)
    {
        println("Нет книг, выпущенных после введенного года!")
    }
}


fun borrowBook(catalog: List<Book>) {
    println("Введите номер книги для выдачи:")
    val index = readln().toInt()
    if (index !in 0 until catalog.size) {
        println("Ошибка: Книги с таким номером не существует.")
        return
    }
    val book = catalog[index]
    when (book.status) {
        BookStatus.AVAILABLE -> {
            book.status = BookStatus.BORROWED
            println("Книга \"${book.name}\" успешно выдана.")
        }
        BookStatus.BORROWED -> println("Не удалось выдать: книга уже выдана другому читателю.")
        BookStatus.RESERVED -> println("Не удалось выдать: книга сейчас зарезервирована.")
        BookStatus.LOST -> println("Не удалось выдать: книга утеряна.")
        BookStatus.ARCHIVED -> println("Не удалось выдать: книга находится в архиве.")
    }
}

fun returnBook (catalog: List<Book>)
{
    println("Введите номер книги для возврата:")
    val index1 = readln().toInt()
    if (index1 !in 0 until catalog.size) {
        println("Ошибка: Книги с таким номером не существует.")
        return
    }
    val book = catalog[index1]
    when (book.status)
    {
        BookStatus.BORROWED ->  {book.status = BookStatus.AVAILABLE; println("Книга успешно возвращена.")}
        BookStatus.AVAILABLE -> println("Книга уже возвращена.")
        BookStatus.RESERVED -> println("Эта книга не числится как выданная (её текущий статус: ${book.status}).")
        BookStatus.LOST -> println("Эта книга не числится как выданная (её текущий статус: ${book.status}).")
        BookStatus.ARCHIVED -> println("Эта книга не числится как выданная (её текущий статус: ${book.status}).")
    }
}

fun lostBook (catalog: List<Book>)
{
    println("Введите номер книги для регистрации потери:")
    val index2 = readln().toInt()
    if (index2 !in 0 until catalog.size) {
        println("Ошибка: Книги с таким номером не существует.")
        return
    }
    val book = catalog[index2]
    when (book.status)
    {
        BookStatus.BORROWED -> {book.status = BookStatus.LOST; println("Книга успешно зарегистрирована как потерянная.")}
        BookStatus.AVAILABLE -> {book.status = BookStatus.LOST; println("Книга успешно зарегистрирована как потерянная.")}
        BookStatus.RESERVED -> {book.status = BookStatus.LOST; println("Книга успешно зарегистрирована как потерянная.")}
        BookStatus.LOST -> {println("Книга уже потеряна.")}
        BookStatus.ARCHIVED -> {book.status = BookStatus.LOST; println("Книга успешно зарегистрирована как потерянная.")}
    }
}

fun reserveBook (catalog: List<Book>)
{
    println("Введите номер книги для резервирования:")
    val index3 = readln().toInt()
    if (index3 !in 0 until catalog.size) {
        println("Ошибка: Книги с таким номером не существует.")
        return
    }
    val book = catalog[index3]
    when(book.status)
    {
        BookStatus.BORROWED -> println("Эту книгу нельзя зарезервировать! (её текущий статус: ${book.status}).")
        BookStatus.AVAILABLE -> {book.status = BookStatus.RESERVED; println("Книга успешно зарезервирована.")}
        BookStatus.RESERVED -> println("Эта книга уже зарезервирована.")
        BookStatus.LOST -> println("Нельзя зарезервировать потерянную книгу!")
        BookStatus.ARCHIVED -> println("Нельзя зарезервировать эту книгу! Она заархивирована.")
    }
}

fun printReport (catalog: List<Book>)
{
    println("=== ОТЧЁТ ПО КАТАЛОГУ ===")
    println("Всего книг: ${catalog.size}")
    println()
    if (catalog.size == 0) {
        println("Каталог пуст.")
        return
    }
    showStatistic(catalog)
    Years(catalog)
}