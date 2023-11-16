package android.ifeanyi.aitrivia.core.utils

import android.ifeanyi.aitrivia.app.data.model.TriviaCategory
import android.ifeanyi.aitrivia.app.data.model.TriviaType

object Constants {
    val amountOptions = listOf("5", "10", "15", "20", "25")
    val difficultyOptions = listOf("Easy", "Medium", "Hard")
    val triviaCategories = listOf(
        TriviaCategory(27, "Animals"),
        TriviaCategory(31, "Anime"),
        TriviaCategory(25, "Art"),
        TriviaCategory(16, "Board game"),
        TriviaCategory(10, "Books"),
        TriviaCategory(32, "Cartoons"),
        TriviaCategory(26, "Celebrities"),
        TriviaCategory(29, "Comics"),
        TriviaCategory(18, "Computers"),
        TriviaCategory(11, "Film"),
        TriviaCategory(30, "Gadgets"),
        TriviaCategory(9, "General"),
        TriviaCategory(22, "Geography"),
        TriviaCategory(23, "History"),
        TriviaCategory(19, "Maths"),
        TriviaCategory(12, "Music"),
        TriviaCategory(13, "Musicals"),
        TriviaCategory(20, "Mythology"),
        TriviaCategory(24, "Politics"),
        TriviaCategory(15, "Video game"),
        TriviaCategory(17, "Science"),
        TriviaCategory(21, "Sports"),
        TriviaCategory(14, "Television"),
        TriviaCategory(28, "Vehicles")
    )
    val triviaTypes = listOf(
        TriviaType( "boolean","True or False"),
        TriviaType("multiple","Multiple choice")
    )
}