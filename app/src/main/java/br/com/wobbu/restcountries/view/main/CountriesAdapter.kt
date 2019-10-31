package br.com.wobbu.restcountries.view.main

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import br.com.wobbu.restcountries.R
import br.com.wobbu.restcountries.model.Country
import br.com.wobbu.restcountries.view.countryDetail.CountryDetailActivity
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.android.synthetic.main.item_countries.view.*
import java.text.DecimalFormat
import java.util.*


class CountriesAdapter() :
    RecyclerView.Adapter<CountriesAdapter.CustomViewHolder>(), Filterable {

    private var filteredList: ArrayList<Country> = arrayListOf()
    private var CountriesList: ArrayList<Country> = arrayListOf()
    private lateinit var activityContext: Activity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_countries, parent, false)
        return CustomViewHolder(activityContext, view)
    }

    fun loadCountries(items: ArrayList<Country>) {
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
                    list as ArrayList<Country>
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results!!.values as ArrayList<Country>
                notifyDataSetChanged()
            }

        }
    }

    class CustomViewHolder(private val context: Activity, itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(item: Country) {
            itemView.txt_name.text = item.name

            val population = DecimalFormat("#,###").format(item.population.toDouble())
            itemView.txt_population.text = "Population: $population"

            val flagUri = Uri.parse(item.flag)
            GlideToVectorYou.justLoadImage(context, flagUri, itemView.img_flag)

            itemView.setOnClickListener {
                val intent = Intent(context, CountryDetailActivity::class.java)
                intent.putExtra("country", item)
                context.startActivity(intent)
            }
        }
    }
}