@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var result = 0
    var n1 = n
    if (n1 < 0) {
        n1 *= -1
    }
    do {
        result++
        n1 /= 10
    } while (n1 > 0)
    return result
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var f1 = 0
    var f2 = 1
    var c = 0
    if (n == 0) {
        return f1
    }
    for (n1 in 2..n) {
        c = f1 + f2
        f1 = f2
        f2 = c
    }
    return f2
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun gcd(m: Int, n: Int): Int {
    return if (n == 0) {
        m
    } else {
        gcd(n, m % n)
    }
}

fun lcm(m: Int, n: Int): Int = (m / gcd(m, n)) * n

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var mindivisor = 0
    for (n1 in 2..n) {
        if (n % n1 == 0) {
            mindivisor = n1
            break
        }
    }
    return mindivisor
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var maxdivisor = 0
    var n2 = n / 2
    for (n1 in n2 downTo 1 step 1) {
        if (n % n1 == 0) {
            maxdivisor = n1
            break
        }
    }
    return maxdivisor
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var iscoprime = gcd(m, n)
    return iscoprime == 1
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    var T = false
    for (a in m..n) {
        T = sqrt(a.toDouble()) % 1 == 0.0
        if (T) {
            break
        }
    }
    return T
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var x1 = x
    var count = 0
    while (x1 != 1) {
        if (x1 % 2 == 0) {
            x1 /= 2
            count++
        } else {
            x1 *= 3
            x1 += 1
            count++
        }
    }
    return count
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */

fun sin(x: Double, eps: Double): Double {
    var x1 = x
    var sum = 0.0
    var i = 1
    while (x1 >= PI * 2) {
        x1 -= PI * 2
    }
    while (x1 <= -PI * 2) {
        x1 += PI * 2
    }
    var n = x1
    do {
        sum += n
        n *= ((-1 * x1 * x1) / ((2 * i) * (2 * i + 1)))
        i++
    } while (abs(n) > eps)
    return sum
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double = sin(x + PI / 2.0, eps)

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var n1 = n
    var result = 0
    while (n1 != 0) {
        result = (result * 10 + (n1 % 10))
        n1 /= 10
    }
    return result
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    var n1 = n
    var result = 0
    while (n1 != 0) {
        result = (result * 10 + (n1 % 10))
        n1 /= 10
    }
    return result == n
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var bol = false
    var n1 = n
    var first = 0
    first = (first * 10 + (n1 % 10))
    n1 /= 10
    var second = 0
    var a = 1
    while (n1 != 0) {
        second = (n1 % 10) // 10 * a
        if (first != second) {
            bol = true
            break
        }
        n1 /= 10
        a++
    }
    return bol
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var result = 0
    var count = 0
    for (a in 1..1000000) {
        var number = a * a
        var count1 = 0
        var temp = number
        while (temp != 0) {
            count1++
            temp /= 10
        }
        temp = number
        count1--
        for (x in count1 downTo 1 step 1) {
            var number1 = 0
            var number2 = 0
            for (x1 in 1..x) {
                number1 = temp / 10
                temp /= 10
            }
            number2 = temp
            for (x1 in 1..x) {
                number2 *= 10
            }
            count++
            if (count == n) {

                result = temp
            }
            temp = number - number2
            number = temp
        }
        if (number in 0..9) {
            count++
            if (count == n) {
                return number
            }
        }
        if(count >= n)
        {
            break
        }
    }
    return result
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var result = 0
    var count = 0
    for (a in 1..50) {
        var number = fib(a)
        var count1 = 0
        var temp = number
        while (temp != 0) {
            count1++
            temp /= 10
        }
        temp = number
        count1--
        for (x in count1 downTo 1 step 1) {
            var number1 = 0
            var number2 = 0
            for (x1 in 1..x) {
                number1 = temp / 10
                temp /= 10
            }
            number2 = temp
            for (x1 in 1..x) {
                number2 *= 10
            }
            count++
            if (count == n) {

                result = temp
            }
            temp = number - number2
            number = temp
        }
        if (number in 0..9) {
            count++
            if (count == n) {
                return number
            }
        }
    }
    return result
}
