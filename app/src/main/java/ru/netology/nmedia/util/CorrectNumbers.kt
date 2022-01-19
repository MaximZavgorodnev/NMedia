package ru.netology.nmedia.util

object CorrectNumbers {
    fun correct(numberLikes1: Int) : String {
        val numberLikes: Double
        val number = when(numberLikes1) {
            in 1000..9_999 -> {
                numberLikes = numberLikes1.toDouble()
                val tisich = (numberLikes/1000).toInt()
                val des = ((numberLikes/1000)%1*10).toInt()
                return "$tisich.$des K"}
            in 10_000..999_999 ->{
                numberLikes = numberLikes1.toDouble()
                val tisich = (numberLikes/1000).toInt()
                return "$tisich K"
            }
            in 1_000_000..9_999_999_999 ->{
                numberLikes = numberLikes1.toDouble()
                val mil = (numberLikes/1000000).toInt()
                val tis = ((numberLikes/1000000)%1*10).toInt()
                return "$mil.$tis M"}
            else -> "$numberLikes1"
        }
        return number
    }
}