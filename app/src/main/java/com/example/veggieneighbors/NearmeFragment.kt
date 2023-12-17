package com.example.veggieneighbors


import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.signature.ObjectKey
import com.example.veggieneighbors.GBRecyclerAdapter.ViewHolder
import com.example.veggieneighbors.databinding.FragmentNearmeBinding
import com.example.veggieneighbors.databinding.GbPostBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NearmeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NearmeFragment : Fragment(), OnMapReadyCallback  {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var mapView: MapView? = null
    private lateinit var mMap: GoogleMap

    private lateinit var binding: FragmentNearmeBinding

    private val a = LatLng(37.6260748, 127.07979149)
    private val b = LatLng(37.62602666, 127.07872522)

    private var locationArrayList: ArrayList<LatLng>? = null

    val GBPostList = mutableListOf<GBPostData>()
    //
    // Initialize Firebase Firestore
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNearmeBinding.inflate(inflater, container, false)
        val view = binding.root

        // Initialize the MapView
        mapView = view.findViewById(R.id.mapView)
        mapView?.onCreate(savedInstanceState)
        mapView?.getMapAsync(this)

        locationArrayList = ArrayList()
        locationArrayList!!.add(a)
        locationArrayList!!.add(b)

        // Fetch data from Firebase Firestore
        fetchDataFromFirestore()

        return view

    }
    // Fetch data from Firebase Firestore
    private fun fetchDataFromFirestore() {
        val postsCollection = db.collection("GB Posts") // Change this to your Firestore collection name

        postsCollection.get()
            .addOnSuccessListener { result ->
                for (document in result) {

                    Log.d("documentdocument".toString(),"$document")
                    // Retrieve data from Firestore document
                    val title = document.getString("title") ?: ""
                    val username = document.getString("username") ?: ""
                    val unit = document.getString("unit") ?: ""
                    val price = document.getString("price") ?: ""
                    val participate = document.getString("participate") ?: ""
                    val img = document.getString("img") ?: ""
                    val description = document.getString("description") ?: ""
                    val region = document.getString("region") ?: ""

                    // Create GBPostData instance and add it to the list
                    val postData = GBPostData(title, username, price, unit, participate, region, "", img, description)
                    GBPostList.add(postData)
                }

                // Call onMapReady after fetching data to update the map
                mapView?.getMapAsync(this)
            }
            .addOnFailureListener { exception ->
                Log.w("ree", "Error getting documents.", exception)
            }
    }



    //==>
    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }


    private fun getLocationFromAddress(strAddress: String): LatLng? {
        val coder = Geocoder(requireContext())
        val address: List<Address>?

        try {
            address = coder.getFromLocationName(strAddress, 5)
            if (address == null) {
                return null
            }
            val location = address[0]
            return LatLng(location.latitude, location.longitude)
        } catch (e: Exception) {
            return null
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        for (post in GBPostList) {
            val location = getLocationFromAddress(post.region)
            if (location != null) {
                val marker = mMap.addMarker(MarkerOptions().position(location).title(post.title))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
                mMap.animateCamera(CameraUpdateFactory.zoomTo(10.0f))
                marker.tag = post // Attach GBPostData as a tag to the marker
            }
        }
        mMap.uiSettings.isZoomControlsEnabled = true

        // Set a marker click listener
        mMap.setOnMarkerClickListener { marker ->
            val selectedPost = marker.tag as? GBPostData
            if (selectedPost != null) {
                // Open GBDetails activity with the selected GBPostData
                val intent = Intent(requireContext(), GBDetails::class.java).apply {
                    putExtra("title", selectedPost.title)
                    putExtra("username", selectedPost.username)
                    putExtra("unit", selectedPost.unit)
                    putExtra("price", selectedPost.price)
                    putExtra("productId", selectedPost.productId)
                    putExtra("participate", selectedPost.participate)
                    putExtra("img", selectedPost.img)
                    putExtra("description", selectedPost.description)
                    putExtra("region", selectedPost.region)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                startActivity(intent)
                true // Consume the click event
            } else {
                false // Marker click event not consumed
            }
        }
    }



    //<==
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NearmeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NearmeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

