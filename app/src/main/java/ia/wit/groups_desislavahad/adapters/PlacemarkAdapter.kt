package ia.wit.groups_desislavahad.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ia.wit.groups_desislavahad.databinding.CardPlacemarkBinding
import ia.wit.groups_desislavahad.models.PlacemarkModel


class PlacemarkAdapter constructor(private var placemarks: List<PlacemarkModel>,
                                   private val listener: PlacemarkListener) :
    RecyclerView.Adapter<PlacemarkAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardPlacemarkBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val placemark = placemarks[holder.adapterPosition]
        holder.bind(placemark, listener)
    }

    override fun getItemCount(): Int = placemarks.size

    class MainHolder(private val binding : CardPlacemarkBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(placemark: PlacemarkModel, listener: PlacemarkListener) {
            binding.placemarkTitle.text = placemark.title
            binding.description.text = placemark.description
            binding.root.setOnClickListener { listener.onPlacemarkClick(placemark) }
        }
    }
}