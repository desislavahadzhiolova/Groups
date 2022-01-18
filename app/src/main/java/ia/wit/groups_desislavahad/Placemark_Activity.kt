package ia.wit.groups_desislavahad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import timber.log.Timber
import timber.log.Timber.i


class Placemark_Activity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placemark)

        mAuth = FirebaseAuth.getInstance()
        Timber.plant(Timber.DebugTree())
        i("Placemark activity started.")

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.logout){
            mAuth.signOut()
            val intent = Intent(this@Placemark_Activity, Login:: class.java)
            finish()
            startActivity(intent)
            return true
        }
        return true
    }
}