package ia.wit.groups_desislavahad.actvities


import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import ia.wit.groups_desislavahad.R
import ia.wit.groups_desislavahad.databinding.ActivityPlacemarkBinding
import ia.wit.groups_desislavahad.helpers.showImagePicker
import ia.wit.groups_desislavahad.main.MainApp
import ia.wit.groups_desislavahad.models.PlacemarkModel
import timber.log.Timber
import timber.log.Timber.i

class PlacemarkActivity : AppCompatActivity() {


    private lateinit var binding: ActivityPlacemarkBinding
    var placemark = PlacemarkModel()
    lateinit var app: MainApp
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    //val IMAGE REQUEST = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var edit = false

        setContentView(R.layout.activity_placemark)
        binding = ActivityPlacemarkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title


        app = application as MainApp

        i("Placemark activity started.")

        if (intent.hasExtra("placemark_edit")) {
            edit = true
            placemark = intent.extras?.getParcelable("placemark_edit")!!
            binding.placemarkTitle.setText(placemark.title)
            binding.description.setText(placemark.description)
            binding.btnAdd.setText("Save")
            Picasso.get()
                .load(placemark.image)
                .into(binding.placemarkImage)
            if(placemark.image != Uri.EMPTY){
                binding.chooseImage.setText(R.string.change_placemark_image)
            }
        }

        binding.btnAdd.setOnClickListener() {
            placemark.title = binding.placemarkTitle.text.toString()
            placemark.description = binding.description.text.toString()

            if (placemark.title.isEmpty()) {
                Snackbar.make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }else{
                if (edit) {
                    app.placemarks.update(placemark.copy())
                }else{
                    app.placemarks.create(placemark.copy())
                }
            }
            i("add Button Pressed: $placemark")
            setResult(RESULT_OK)
            finish()
        }

        binding.chooseImage.setOnClickListener {
            showImagePicker(imageIntentLauncher)
        }
        registerImagePickerCallback()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_placemark, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")
                            placemark.image = result.data!!.data!!
                            Picasso.get()
                                .load(placemark.image)
                                .into(binding.placemarkImage)
                            binding.chooseImage.setText(R.string.change_placemark_image)
                        }
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }
}





