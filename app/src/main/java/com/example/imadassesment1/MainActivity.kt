package com.example.imadassesment1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    //only when the user has installed the app
    private lateinit var inputTime: EditText
    private lateinit var sparkButton: Button
    private lateinit var reset: Button
    private lateinit var title: TextView
    private lateinit var suggestion: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //reads from the xml file
        inputTime = findViewById(R.id.inputTime)
        sparkButton = findViewById(R.id.SparkButton)
        title = findViewById(R.id.Title)
        suggestion = findViewById(R.id.Suggestion)
        reset = findViewById(R.id.reset)
        
        setupListeners()
    }

    private fun setupListeners() {//reads when the user has clicked the button
        sparkButton.setOnClickListener {
            val input = inputTime.text.toString()
            val spark = getSpark(input)
            showSpark(spark)
        }

        // Clear the input field
        reset.setOnClickListener {
            inputTime.text.clear()
        }
    }

    data class Spark(val title: String, val message: String, val colorResId: Int)

    private fun getSpark(input: String): Spark {
        val cleanInput = input.lowercase().trim()//reads when the use has entered the correct text

//when statment
        return when {
            cleanInput.contains("morning") -> Spark("🌅 Morning", "Send 'Good morning! 🌞' to family", R.color.white)
            cleanInput.contains("mid") -> Spark("☕ Mid-Morning", "Text colleague: 'Thank you! 🙏'", R.color.white)
            cleanInput.contains("afternoon") -> Spark("😂 Afternoon", "Share funny meme with friend", R.color.white)
            cleanInput.contains("snack") -> Spark("🍎 Snack Time", "'Thinking of you!' 💭", R.color.white)
            cleanInput.contains("dinner") -> Spark("🍽️ Dinner", "5-min catch-up call ❤️", R.color.white)
            cleanInput.contains("night") -> Spark("🌙 Night", "Comment on friend's post ✨", R.color.white)
            else -> Spark("❓ Oops!", "Try: morning, afternoon, dinner, etc.", R.color.white)
        }
    }
//shows suggestions after the user has entered the correct text
    //Ready to Explode will display as a message  when the user presses the spark button
    private fun showSpark(spark: Spark) {
        title.text = spark.title
        suggestion.text = spark.message
        title.setTextColor(ContextCompat.getColor(this, spark.colorResId))
        Snackbar.make(findViewById(android.R.id.content), "✨ Ready to EXPLODE!", Snackbar.LENGTH_SHORT).show()
    }
}
