package br.com.wobbu.restcountries.main

import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import br.com.wobbu.restcountries.R
import br.com.wobbu.restcountries.model.Countries
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.android.synthetic.main.item_countries.view.*
import java.text.DecimalFormat
import java.util.*


class CountriesAdapter() :
    RecyclerView.Adapter<CountriesAdapter.CustomViewHolder>(), Filterable {

    private var filteredList: ArrayList<Countries> = arrayListOf()
    private var CountriesList: ArrayList<Countries> = arrayListOf()
    private lateinit var activityContext: Activity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_countries, parent, false)
        return CustomViewHolder(activityContext, view)
    }

    fun loadCountries(items: ArrayList<Countries>) {
        filteredList = items
        CountriesList = items
    }

    fun setActivityContext(activity: Activity) {
        this.activityContext = activity
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        filteredList.sortBy { it.name }
        holder.bind(filteredList[position])
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                filteredList = if (charString.isEmpty()) {
                    CountriesList
                } else {
                    val list = CountriesList.filter { item ->
                        item.name.toLowerCase(Locale.getDefault())
                            .contains(charString.toLowerCase(Locale.getDefault()))
                    }
                    list as ArrayList<Countries>
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results!!.values as ArrayList<Countries>
                notifyDataSetChanged()
            }

        }
    }

    class CustomViewHolder(private val context: Activity, itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(item: Countries) {
            itemView.txt_name.text = item.name

            val population = DecimalFormat("#,###").format(item.population.toDouble())
            itemView.txt_population.text = "Population: ${population}"

            val flagUri = Uri.parse(item.flag)
            GlideToVectorYou.justLoadImage(context, flagUri, itemView.img_profile)
        }
    }
}