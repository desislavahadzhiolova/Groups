package ia.wit.groups_desislavahad.helpers

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import ia.wit.groups_desislavahad.R

fun showImagePicker(intentLauncher : ActivityResultLauncher<Intent>) {
    var chooseFile = Intent(Intent.ACTION_OPEN_DOCUMENT)
    chooseFile.type = "image/*"
    chooseFile = Intent.createChooser(chooseFile, "Select placemark image".toString())
    intentLauncher.launch(chooseFile)
}