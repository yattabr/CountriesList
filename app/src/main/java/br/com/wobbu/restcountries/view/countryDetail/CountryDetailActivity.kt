package br.com.wobbu.restcountries.view.countryDetail

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.wobbu.restcountries.R
import br.com.wobbu.restcountries.model.Country
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_country_detail.*
import kotlinx.android.synthetic.main.item_countries.view.*
import java.text.DecimalFormat

class CountryDetailActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var country: Country

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        country = intent.getSerializableExtra("country") as Country

        initUI()
    }

    private fun initUI() {
        txt_country_name.text = "Country - ${country.name}"
        txt_capital.text = "Capital - ${country.capital}"

        val population = DecimalFormat("#,###").format(country.population.toDouble())
        txt_population.text = "Population - $population"

        val flagUri = Uri.parse(country.flag)
        GlideToVectorYou.justLoadImage(this, flagUri, img_flag)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if (country.latlng.isNotEmpty()) {
            val location = LatLng(country.latlng[0], country.latlng[1])
            mMap.addMarker(MarkerOptions().position(location).title(country.name))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 2f))
        }
    }
}
