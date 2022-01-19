package ia.wit.groups_desislavahad.actvities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import ia.wit.groups_desislavahad.Login
import ia.wit.groups_desislavahad.R
import ia.wit.groups_desislavahad.databinding.ActivityPlacemarkBinding
import ia.wit.groups_desislavahad.main.MainApp
import ia.wit.groups_desislavahad.models.PlacemarkModel

import timber.log.Timber
import timber.log.Timber.i



class PlacemarkActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    private lateinit var binding: ActivityPlacemarkBinding
    var placemark = PlacemarkModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_placemark)
        binding = ActivityPlacemarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()

        app = application as MainApp
        i("Placemark activity started.")

        binding.btnAdd.setOnClickListener() {
            placemark.title = binding.placemarkTitle.text.toString()
            placemark.description = binding.description.text.toString()
            if (placemark.title.isNotEmpty()) {
                app.placemarks.add(placemark.copy())
                i("add Button Pressed: ${placemark}")
                for (i in app.placemarks.indices) {
                    i("Placemark[$i]:${app.placemarks[i]}")
                }
            }
            else {
                Snackbar
                    .make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.logout){
            mAuth.signOut()
            val intent = Intent(this@PlacemarkActivity, Login:: class.java)
            finish()
            startActivity(intent)
            return true
        }
        return true
    }
}