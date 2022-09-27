package com.omerakcinar.artbookodev

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.omerakcinar.artbookodev.databinding.FragmentShowArtBinding

class ShowArtFragment : Fragment() {
    private var _binding: FragmentShowArtBinding? = null
    private val binding get() = _binding!!
    private lateinit var database : SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowArtBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.deleteButton.setOnClickListener { deleteArt(view) }


        arguments?.let {
            val selectedId = ShowArtFragmentArgs.fromBundle(it).id
            database = requireContext().openOrCreateDatabase("arts", Context.MODE_PRIVATE,null)
            val cursor = database.rawQuery("SELECT * FROM arts WHERE id = ?", arrayOf(selectedId.toString()))

            var artNameIx = cursor.getColumnIndex("artname")
            var artistnameIx = cursor.getColumnIndex("artistname")
            var yearIx = cursor.getColumnIndex("year")
            var imageIx = cursor.getColumnIndex("image")

            while (cursor.moveToNext()){
                binding.artNameShow.text = cursor.getString(artNameIx)
                binding.artistNameShow.text = cursor.getString(artistnameIx)
                binding.yearShow.text = cursor.getString(yearIx)

                val byteArray = cursor.getBlob(imageIx)
                val bitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
                binding.showImageView.setImageBitmap(bitmap)
            }
            cursor.close()
        }
    }

    fun deleteArt(view: View){
        arguments?.let {
            val selectedId = ShowArtFragmentArgs.fromBundle(it).id
            val alert = AlertDialog.Builder(requireContext())
            alert.setTitle("Delete")
            alert.setMessage("Are you sure?")
            alert.setPositiveButton("Delete",DialogInterface.OnClickListener { dialogInterface, i ->
                database.execSQL("DELETE FROM arts WHERE id = ${selectedId}")
                Toast.makeText(requireContext(),"Deleted",Toast.LENGTH_LONG).show()
                val intent = Intent(requireContext(),MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) //Önceki açık aktiviteler kapanır.
                startActivity(intent)
            })
            alert.setNegativeButton("Cancel",DialogInterface.OnClickListener { dialogInterface, i ->
                Toast.makeText(requireContext(),"Canceled",Toast.LENGTH_LONG).show()
            })
            alert.show()

        }

    }








    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}