package ia.wit.groups_desislavahad
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber


class Login : AppCompatActivity() {

    private lateinit var addEmail: EditText
    private lateinit var addPassword : EditText
    private lateinit var buttonLogin: Button
    private lateinit var buttonSignUp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Timber.plant(Timber.DebugTree())
        Timber.i("Login Activity started.")


        addEmail = findViewById(R.id.add_email)
        addPassword = findViewById(R.id.add_password)
        buttonLogin = findViewById(R.id.button_Login)
        buttonSignUp = findViewById(R.id.button_Signup)

        buttonSignUp.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

    }
}