package com.hftamayo.kotlinfbasecrudadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.hftamayo.kotlinfbasecrudadmin.databinding.ActivityDeleteBinding

class DeleteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deleteButton.setOnClickListener {
            val vehicleNumber = binding.referenceVehicleNumber.text.toString()
            if(vehicleNumber.isNotEmpty()){
                deleteData(vehicleNumber)
            } else {
                Toast.makeText(this, "Please enter a valid vehicle number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteData(vehicleNumber: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
        databaseReference.child(vehicleNumber).removeValue().addOnSuccessListener {
            binding.referenceVehicleNumber.text.clear()
            Toast.makeText(this, "Record deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Unable to Delete", Toast.LENGTH_SHORT).show()
        }
    }
}