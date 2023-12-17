package com.example.veggieneighbors

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.veggieneighbors.databinding.FragmentCartBinding
import com.example.veggieneighbors.databinding.FragmentHomeBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

//private lateinit var binding: FragmentCartBinding
private val db = FirebaseFirestore.getInstance()
private val GBPostList = mutableListOf<GBPostData>()

/**
 * A simple [Fragment] subclass.
 * Use the [CartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CartFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentHomeBinding

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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        // Fetch data from Firestore
        fetchDataFromFirestore()

        return view
    }

    /*
    private fun fetchDataFromFirestore() {
        val postsCollection = db.collection("GB Posts")

        postsCollection.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    // Retrieve data from Firestore document
                    val username = document.getString("username") ?: ""

                    // Display username in the TextView
                    displayUsername(username)
                }
            }
            .addOnFailureListener { exception ->
                Log.w("ree", "Error getting documents.", exception)
            }
    }

     */

    private fun displayUsername(username: String) {
        // Find the TextView by ID
        val nameTextView: TextView? = view?.findViewById(R.id.name_text)

        // Update the TextView with the username
        nameTextView?.text = username
    }


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

                    displayUsername(username)
                }

            }

            .addOnFailureListener { exception ->
                Log.w("ree", "Error getting documents.", exception)
            }

        Log.d("123342","$postsCollection")

    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CartFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CartFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }








}