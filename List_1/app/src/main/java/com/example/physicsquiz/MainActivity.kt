package com.example.physicsquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var currentPosition = 1
    private val questions: List<Question> = Questions.questions
    private var selectedAnswerPosition = 0
    private var points = 0
    private var correctAnswer = 0
    private var amountOfFraud = 0

    private val questionText: TextView by lazy { findViewById(R.id.txt_vw_question)}
    private val answerTrue: Button by lazy { findViewById(R.id.btn_true)}
    private val answerFalse: Button by lazy { findViewById(R.id.btn_false)}
    private val answerCheat: Button by lazy { findViewById(R.id.btn_cheat)}



    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setQuestion()

        answerTrue.setOnClickListener {
            if (selectedAnswerPosition == 0) {
                currentPosition++
                if (currentPosition <= questions.size)
                    setQuestion()
                else
                    Toast.makeText(this, "Quiz completed", Toast.LENGTH_SHORT).show()
            }
            else {
                val question = questions[currentPosition - 1]
                if (question.correctAnswer == 1)
                    points++
            }
        }

        answerFalse.setOnClickListener{
            if (selectedAnswerPosition == 0) {
                currentPosition++
                if (currentPosition <= questions.size)
                    setQuestion()
                else
                    Toast.makeText(this, "Quiz completed", Toast.LENGTH_SHORT).show()
            }
            else {
                val question = questions[currentPosition - 1]
                if (question.correctAnswer == 0)
                    points++
            }
        }


    }
    fun startCheatingActivity(view: View){
        val intent = Intent(this, CheatActivity::class.java)
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
        Question("QUESTION 1", 1),
        Question("QUESTION 2", 0),
        Question("QUESTION 3", 1))
}