package ia.wit.groups_desislavahad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import timber.log.Timber
import timber.log.Timber.i

class SignUp : AppCompatActivity() {

    private lateinit var addName: EditText
    private lateinit var addEmail: EditText
    private lateinit var addPassword : EditText
    private lateinit var buttonSignUp: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        i("SignUp Activity started.")
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()
        addName = findViewById(R.id.add_name)
        addEmail = findViewById(R.id.add_email)
        addPassword = findViewById(R.id.add_password)
        buttonSignUp = findViewById(R.id.button_Signup)

        buttonSignUp.setOnClickListener(){

            val name = addName.text.toString()
            val email = addEmail.text.toString()
            val password = addPassword.text.toString()

            signUp(name,email, password)

        }

    }

    private fun signUp(name: String, email: String, password: String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //return to home

                    addUserToDatabase(name, email, mAuth.currentUser?.uid!!)
                    val intent = Intent(this@SignUp, Placemark_Activity:: class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@SignUp, "Error while logging in", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addUserToDatabase(name: String, email: String, uid: String){
        mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(User(name,email,uid))
    }

}