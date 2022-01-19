package ia.wit.groups_desislavahad.adapters

import ia.wit.groups_desislavahad.models.PlacemarkModel

interface PlacemarkListener {
    fun onPlacemarkClick(placemark: PlacemarkModel)
}