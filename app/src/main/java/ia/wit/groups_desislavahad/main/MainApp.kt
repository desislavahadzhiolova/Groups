package ia.wit.groups_desislavahad.main

import android.app.Application
import ia.wit.groups_desislavahad.models.PlacemarkJSONStore
import ia.wit.groups_desislavahad.models.PlacemarkModel
import ia.wit.groups_desislavahad.models.PlacemarkStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    lateinit var placemarks: PlacemarkStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        placemarks = PlacemarkJSONStore(applicationContext)
        i("Placemark started")
    }
}