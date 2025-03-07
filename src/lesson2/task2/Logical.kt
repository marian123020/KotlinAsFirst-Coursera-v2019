@file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.sqrt

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
    sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean {
    val first = number % 10
    val second = (number % 100 - number % 10) / 10
    val third = (number % 1000 - number % 100) / 100
    val four = (number - number % 1000) / 1000
    return first + second == third + four
}

/**
 * Простая
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean = x1 == x2 || y1 == y2 || abs(x1 - x2) == abs(y1 - y2)

/**
 * Простая
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int {
    var Febuary = 0
    //var lastTwoNumber = (year % 10) + ((year % 100) - (year % 10))
    if ((year % 400) == 0) {
        Febuary = 29
    } else if ((year % 100) == 0) {
        Febuary = 28
    } else if (year % 4 == 0) {
        Febuary = 29
    } else {
        Febuary = 28
    }
    return if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
        31;
    } else {
        if (month == 2) {
            Febuary
        } else {
            30;
        }
    }
}

/**
 * Средняя
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(
    x1: Double, y1: Double, r1: Double,
    x2: Double, y2: Double, r2: Double
): Boolean = (sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)) + r1) <= r2

/**
 * Средняя
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean {
    var a1 = 0
    var b1 = 0
    if (a > c || b > c) {
        if (b > c && a < b) {
            a1 = a
            b1 = c
        } else {
            a1 = c
            b1 = b
        }
    } else {
        a1 = a
        b1 = b
    }
    return (a1 <= r && b1 <= s) || (a1 <= s && b1 <= r)
}
