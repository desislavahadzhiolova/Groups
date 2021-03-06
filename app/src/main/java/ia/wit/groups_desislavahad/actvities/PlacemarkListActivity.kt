package ia.wit.groups_desislavahad.actvities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import ia.wit.groups_desislavahad.Login
import ia.wit.groups_desislavahad.R
import ia.wit.groups_desislavahad.adapters.PlacemarkAdapter
import ia.wit.groups_desislavahad.adapters.PlacemarkListener
import ia.wit.groups_desislavahad.databinding.ActivityPlacemarkListBinding
import ia.wit.groups_desislavahad.databinding.CardPlacemarkBinding
import ia.wit.groups_desislavahad.main.MainApp
import ia.wit.groups_desislavahad.models.PlacemarkModel

class PlacemarkListActivity : AppCompatActivity(), PlacemarkListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivityPlacemarkListBinding
    private lateinit var refreshIntentLauncher: ActivityResultLauncher<Intent>
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlacemarkListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title

        app = application as MainApp

        mAuth = FirebaseAuth.getInstance()

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        loadPlacemarks()
        registerRefreshCallback()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       if(item.itemId == R.id.log_out){
           mAuth.signOut()
           finish()
           val intent = Intent(this, Login::class.java)
           intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
           startActivity(intent)
           return true
       }else if(item.itemId == R.id.item_add ){
           run {
               val launcherIntent = Intent(this, PlacemarkActivity::class.java)
               startActivityForResult(launcherIntent, 0)
           }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPlacemarkClick(placemark: PlacemarkModel) {
        val launcherIntent = Intent(this, PlacemarkActivity::class.java)
        launcherIntent.putExtra("placemark_edit", placemark)
        refreshIntentLauncher.launch(launcherIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        binding.recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun registerRefreshCallback() {
        refreshIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { loadPlacemarks() }
    }

    private fun loadPlacemarks() {
        showPlacemarks(app.placemarks.findAll())
    }

    fun showPlacemarks (placemarks: List<PlacemarkModel>) {
        binding.recyclerView.adapter = PlacemarkAdapter(placemarks, this)
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }
}


