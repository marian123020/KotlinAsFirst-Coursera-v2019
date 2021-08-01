@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import kotlinx.html.InputType
import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.*

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    var result = ""
    if (age in 1..99) {
        if (age == 11 || age == 12 || age == 13 || age == 14) {
            result = "$age лет"
        } else if (age == 1 || (age % 10) == 1) {
            result = "$age год"
        } else if (age in 2..4 || (age % 10) > 1 && (age % 10) < 5) {
            result = "$age года"
        } else {
            result = "$age лет"
        }
    } else {
        if (age == 111)
            result = "$age лет"
        else if (((age % 100) % 10) == 1)
            result = "$age год"
        else
            result = "$age лет"
    }
    return result
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    var S = (t1 * v1 + t2 * v2 + t3 * v3) / 2;
    var time = 0.0
    if ((S - t1 * v1) >= 0.0) {
        S -= t1 * v1;
        time += t1.toDouble();
    } else if ((S - v1 * t1) < 0.0) {
        time += S / v1.toDouble();
        S = 0.0
    }
    if ((S - t2 * v2) >= 0.0) {
        S -= t2 * v2;
        time += t2.toDouble();
    } else if ((S - t2 * v2) < 0.0 && v3 == 0.0) {
        time += S / v2.toDouble();
        S = 0.0
    }
    if ((S - v3 * t3) >= 0.0 && v3 != 0.0) {
        S -= t3 * v3;
        time += t3.toDouble();
    } else if ((S - t3 * v3) < 0.0) {
        time += S / v3.toDouble();
        S = 0.0
    }
    return time
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    var result = 0
    var result1 = 0
    var result2 = 0
    if (kingX == rookX1 || kingY == rookY1) {
        result1 = 1
        result = 1
    }
    if (kingX == rookX2 || kingY == rookY2) {
        result2 = 2
        result = 2
    }
    if (result1 == 1 && result2 == 2) {
        result = 3
    }
    return result
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    var result = 0
    var result1 = 0
    var result2 = 0
    if (kingX == rookX || kingY == rookY) {
        result1 = 1
        result = 1
    }
    if (abs(kingX - bishopX) == abs(kingY - bishopY)) {
        result2 = 2
        result = 2
    }
    if (result1 == 1 && result2 == 2) {
        result = 3
    }
    return result
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    var result = 0
    var a1 = 0.0
    var b1 = 0.0
    var c1 = 0.0
    if (a > c || b > c) {
        if (b > c && a < b) {
            a1 = a
            b1 = c
            c1 = b
        } else {
            a1 = c
            b1 = b
            c1 = a
        }
    } else {
        a1 = a
        b1 = b
        c1 = c
    }
    if ((a1 + b1) > c1 && (a1 + c1) > b1 && (b1 + c1) > a1) {
        if ((a1 * a1 + b1 * b1) == (c1 * c1)) {
            result = 1;
        } else if ((a1 * a1 + b1 * b1) > (c1 * c1)) {
            result = 0;
        } else if ((a1 * a1 + b1 * b1) < (c1 * c1)) {
            result = 2;
        }
    } else {
        result = -1
    }
    return result
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    var result = 0
    if (a < c && b < d) {
        result = b - c
    } else if (a > c && b > d) {
        result = d - a
    } else if (c < a && b < d) {
        result = b - a
    } else if (a < c && b > d) {
        result = d - c
    } else if (a == c && b > a && d > a) {
        result = min(abs(b - a), abs(d - a))
    } else if (d == b && a < b && c < b) {
        result = min(abs(a - b), abs(c - b))
    }
    if (result < 0) {
        result = -1
    }
    return result
}
