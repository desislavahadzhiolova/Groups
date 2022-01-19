package ia.wit.groups_desislavahad
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import ia.wit.groups_desislavahad.actvities.PlacemarkActivity
import timber.log.Timber
import timber.log.Timber.i


class Login : AppCompatActivity() {

    private lateinit var addEmail: EditText
    private lateinit var addPassword : EditText
    private lateinit var buttonLogin: Button
    private lateinit var buttonSignUp: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Timber.plant(Timber.DebugTree())
        i("Login Activity started.")


        mAuth= FirebaseAuth.getInstance()

        addEmail = findViewById(R.id.add_email)
        addPassword = findViewById(R.id.add_password)
        buttonLogin = findViewById(R.id.button_Login)
        buttonSignUp = findViewById(R.id.button_Signup)

        buttonSignUp.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        buttonLogin.setOnClickListener{
            val email = addEmail.text.toString()
            val password = addPassword.text.toString()

            login(email,password)
        }


    }

    private fun login(email: String, password: String){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@Login, PlacemarkActivity:: class.java)
                    finish()
                    startActivity(intent)

                } else {
                   Toast.makeText(this@Login, "User does not exist", Toast.LENGTH_SHORT).show()
                }
            }
    }


}
