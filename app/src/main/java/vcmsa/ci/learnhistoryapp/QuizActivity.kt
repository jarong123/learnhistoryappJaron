package vcmsa.ci.learnhistoryapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizActivity : AppCompatActivity() {
    //add true false statements
    private val questions = arrayOf(
        "South Africa's national flower is the Protea.",
        "The Titanic sank in 1994.",
        "South Africa became a democratic country in 1987.",
        "The great wall of China was built 2000 years ago.",
        "The Pyramids were built as tombs for pharoahs."
    )
    //track questions
    private val answers = booleanArrayOf(true, false, false, true, true)
    private var score = 0
    private var index = 0
    private var answered = false

    private lateinit var tvQuestion: TextView
    private lateinit var btnTrue: Button
    private lateinit var btnFalse: Button
    private lateinit var btnNext: Button
    private lateinit var tvFeedback: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvQuestion = findViewById(R.id.tvQuizQuestion)
        btnTrue = findViewById(R.id.btnTrue)
        btnFalse = findViewById(R.id.btnFalse)
        btnNext = findViewById(R.id.btnNext)
        tvFeedback = findViewById(R.id.tvFeedback)

        loadQuestion()

        //checks answers
        btnTrue.setOnClickListener { if (!answered) checkAnswer(true) }
        btnFalse.setOnClickListener { if (!answered) checkAnswer(false) }

        //changes question
        btnNext.setOnClickListener {
            index++
            if (index < questions.size) {
                loadQuestion()
            } else {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("score", score)
                intent.putExtra("questions", questions)
                intent.putExtra("answers", answers)
                startActivity(intent)
                finish()
            }
        }
    }

    //displays question
    private fun loadQuestion() {
        tvQuestion.text = questions[index]
        tvFeedback.text = ""
        answered = false
    }

    //compares answers
    private fun checkAnswer(userAnswer: Boolean) {
        answered = true
        val correct = answers[index]
        if (userAnswer == correct) {
            score++
            tvFeedback.text = "Correct!"
        } else {
            tvFeedback.text = "Incorrect!"
        }
    }
}