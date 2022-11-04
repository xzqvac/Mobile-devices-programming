package com.example.physicsquiz

import android.annotation.SuppressLint
import android.content.Intent
import android.content.Intent.CATEGORY_BROWSABLE
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView


const val EXTRA_MESSAGE = "com.example.physicsquiz.MESSAGE"

class MainActivity : AppCompatActivity() {
    private var currentPosition: Int = 1
    private val questions: List<Question> = Questions.questions
    private var points: Int = 0
    private var correctAnswers: Int = 0
    private var amountOfFraud: Int = 0

    private val answerTrue: Button by lazy { findViewById(R.id.btn_true) }
    private val answerFalse: Button by lazy { findViewById(R.id.btn_false) }
    private val answerCheat: Button by lazy { findViewById(R.id.btn_cheat) }
    private val search: Button by lazy { findViewById(R.id.btn_search) }

    private val questionText: TextView by lazy { findViewById(R.id.txt_vw_question) }
    private val cheatAnswerText: TextView by lazy { findViewById(R.id.txt_cheat_answer) }

    private val url = "https://www.google.pl/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("currentPosition_state")
            points = savedInstanceState.getInt("points_state")
            correctAnswers = savedInstanceState.getInt("CorrectAnswers_state")
            amountOfFraud = savedInstanceState.getInt("Frauds_state")
        }

        setQuestion()



        answerTrue.setOnClickListener {
            val question = questions[currentPosition - 1]
            if (question.correctAnswer == 1) {
                points += 10
                correctAnswers++
            }
            currentPosition++
            if (currentPosition <= questions.size)
                setQuestion()
            else
                showResult()
        }

        answerFalse.setOnClickListener {
            val question = questions[currentPosition - 1]
            if (question.correctAnswer == 0) {
                points += 10
                correctAnswers++
            }
            currentPosition++
            if (currentPosition <= questions.size)
                setQuestion()
            else
                showResult()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("points_state", points)
        outState.putInt("currentPosition_state", currentPosition)
        outState.putInt("CorrectAnswers_state", correctAnswers)
        outState.putInt("Frauds_state", amountOfFraud)

    }

    fun startCheatingActivity(view: View) {
        amountOfFraud++
        points -= 15
        val question = questions[currentPosition - 1]
        val answer = if (question.correctAnswer == 1) "TRUE" else "FALSE"
        val intent = Intent(this, CheatActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, answer)
        }
        startActivity(intent)
    }

    private fun setQuestion() {
        val question = questions[currentPosition - 1]
        questionText.text = question.question
    }

    @SuppressLint("SetTextI18n")
    private fun showResult() {
        questionText.text = "Result: $points\nCorrect: $correctAnswers\nCheated: $amountOfFraud"
        answerTrue.visibility = View.GONE
        answerFalse.visibility = View.GONE
        answerCheat.visibility = View.GONE
        search.visibility = View.GONE
    }

    @SuppressLint("QueryPermissionsNeeded")
    fun search(view: View) {
        val url = "https://www.google.pl/"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)

    }

}


class Question(
    val question: String,
    val correctAnswer: Int
)

object Questions {
    val questions: List<Question> = listOf(
        Question("Magnes ma 2 bieguny", 1),
        Question("Jeden z biegunów to N", 1),
        Question("Miedź jest izolatorem?", 0) ,
        Question("Neutrony są elektryczie obojętne", 1),
        Question("Drewno jest przewodnikiem", 0),
        Question("Proton ma ładunek dodatni", 1),
        Question("Elektron ma ładunek ujemny", 1),
        Question("Rtęć jest metalem", 1),
        Question("Jednostka napięcia to Volt", 1),
        Question("Jednostka rezystancji to Amper", 0))
}