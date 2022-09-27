package com.omerakcinar.artbookodev

import android.content.Context.MODE_PRIVATE
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.omerakcinar.artbookodev.databinding.FragmentArtListBinding
import com.omerakcinar.artbookodev.databinding.FragmentNewArtBinding

class ArtListFragment : Fragment() {
    private lateinit var artAdapter : ArtAdapter
    private lateinit var artList : ArrayList<Art>
    private var _binding : FragmentArtListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArtListBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        artList = ArrayList<Art>()
        artAdapter = ArtAdapter(artList)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = artAdapter

        try {
            val database = requireContext().openOrCreateDatabase("arts",MODE_PRIVATE,null)

            val cursor = database.rawQuery("SELECT * FROM arts",null)
            val artNameIndex =cursor.getColumnIndex("artname")
            val idIndex = cursor.getColumnIndex("id")

            while (cursor.moveToNext()){
                val artName =cursor.getString(artNameIndex)
                val id =cursor.getInt(idIndex)
                val art = Art(artName,id)
                artList.add(art)
            }

            artAdapter.notifyDataSetChanged()

            cursor.close()

        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}