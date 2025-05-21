package vcmsa.ci.learnhistoryapp

import android.app.AlertDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //code starts here
        val tvScore = findViewById<TextView>(R.id.tvScore)
        val tvMessage = findViewById<TextView>(R.id.tvMessage)
        val btnReview = findViewById<Button>(R.id.btnReview)
        val btnExit = findViewById<Button>(R.id.btnExit)

        //gets score
        val score = intent.getIntExtra("score", 0)
        val questions = intent.getStringArrayExtra("questions")
        val answers = intent.getBooleanArrayExtra("answers")

        //shows score
        tvScore.text = "You scored $score out of 5"

        //shows feedback
        tvMessage.text = if (score >= 3) "Great job!" else "Keep practising!"

        //pop up message with feedback
        btnReview.setOnClickListener {
            val builder = StringBuilder()
            if (questions != null && answers != null) {
                for (i in questions.indices) {
                    builder.append("${i + 1}. ${questions[i]}\nAnswer: ${if (answers[i]) "True" else "False"}\n\n")
                }
            }
            AlertDialog.Builder(this)
                .setTitle("Review Answers")
                .setMessage(builder.toString())
                .setPositiveButton("OK", null)
                .show()
        }

        //terminates app
        btnExit.setOnClickListener {
            finishAffinity()
        }
    }
}