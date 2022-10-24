package com.example.physicsquiz

import android.content.Intent
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

    private val questionText: TextView by lazy { findViewById(R.id.txt_vw_question) }
    private val cheatAnswerText: TextView by lazy { findViewById(R.id.txt_cheat_answer) }


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
                correctAnswers
            }
            currentPosition++
            if (currentPosition <= questions.size)
                setQuestion()
            else
                questionText.text = points.toString()
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
                questionText.text = points.toString()
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
}


class Question(
    val question: String,
    val correctAnswer: Int
)

object Questions {
    val questions: List<Question> = listOf(
        Question("QUESTION 1", 0),
        Question("QUESTION 2", 0),
        Question("QUESTION 3", 0))
}