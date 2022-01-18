package ia.wit.groups_desislavahad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.ActionCodeEmailInfo
import com.google.firebase.auth.FirebaseAuth
import timber.log.Timber

class SignUp : AppCompatActivity() {

    private lateinit var addName: EditText
    private lateinit var addEmail: EditText
    private lateinit var addPassword : EditText
    private lateinit var buttonSignUp: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        Timber.i("SignUp Activity started.")
        setContentView(R.layout.activity_sign_up)

        mAuth = FirebaseAuth.getInstance()
        addName = findViewById(R.id.add_name)
        addEmail = findViewById(R.id.add_email)
        addPassword = findViewById(R.id.add_password)
        buttonSignUp = findViewById(R.id.button_Signup)

        buttonSignUp.setOnClickListener(){
            val email = addEmail.text.toString()
            val password = addPassword.text.toString()

            signUp(email, password)

        }

    }

    private fun signUp(email: String, password: String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //return to home
                    val intent = Intent(this@SignUp, MainActivity:: class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@SignUp, "Error while logging in", Toast.LENGTH_SHORT).show()
                }
            }
    }

}