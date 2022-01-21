package ia.wit.groups_desislavahad
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.ktx.Firebase
import ia.wit.groups_desislavahad.actvities.PlacemarkActivity
import ia.wit.groups_desislavahad.actvities.PlacemarkListActivity
import timber.log.Timber
import timber.log.Timber.i


class Login : AppCompatActivity() {

    private lateinit var addEmail: EditText
    private lateinit var addPassword : EditText
    private lateinit var buttonLogin: Button
    private lateinit var buttonSignUp: Button
    private lateinit var mAuth: FirebaseAuth


    private companion object {
        private const val TAG = "LoginActivity"
        private const val RC_GOOGLE_SIGN_IN = 4926
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Timber.plant(Timber.DebugTree())
        i("Login Activity started.")
        supportActionBar?.hide()

        val btnSignIn = findViewById<SignInButton>(R.id.btnSignIn)

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
         val client: GoogleSignInClient = GoogleSignIn.getClient(this,gso)



        btnSignIn.setOnClickListener{
            val singInIntent = client.signInIntent
            startActivityForResult(singInIntent, RC_GOOGLE_SIGN_IN)
        }


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

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user == null){
            Log.w(TAG, "User is null, not going to navigate")
            return
        }
        startActivity(Intent(this, PlacemarkListActivity::class.java))
        finish()

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = mAuth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
    }



    private fun login(email: String, password: String){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@Login, PlacemarkListActivity:: class.java)
                    finish()
                    startActivity(intent)

                } else {
                   Toast.makeText(this@Login, "User does not exist", Toast.LENGTH_SHORT).show()
                }
            }
    }


}
