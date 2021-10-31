package com.example.notefullappviewmodel

import android.content.DialogInterface
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//deals with viewModel

class MainActivity : AppCompatActivity() {

    //my views
    lateinit var edNotes: EditText
    lateinit var btnSumbmit: Button

    //my android architecture
    lateinit var myViewModelV: myViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //my views
        edNotes=findViewById(R.id.edNots)
        btnSumbmit=findViewById(R.id.btnSubmit)

        myViewModelV= ViewModelProvider(this)
            .get(myViewModel::class.java)

        myViewModelV.geyAllnote().observe(this, {
            rv_main.adapter=RecycelerAdapter(this, it)
            rv_main.layoutManager=LinearLayoutManager(this)
        })

        btnSumbmit.setOnClickListener(){

            if(edNotes.text.isNotEmpty()){
                myViewModelV.insertNote(NoresTable(0,edNotes.text.toString()))
            }else
                Toast.makeText(applicationContext, "please fill the missing entry!", Toast.LENGTH_SHORT).show()
            edNotes.text.clear()
        }//end btnSum listener

    }

    fun dilogfun(note:NoresTable) {
        val build = AlertDialog.Builder(this)
        val update = EditText(this)
        update.hint = " enter new note for update "
        build.setCancelable(false)
            .setPositiveButton("save", DialogInterface.OnClickListener() { _, _ ->
                myViewModelV.updatetNote(NoresTable(note.id,update.text.toString()))
            })
            .setNegativeButton("deny", DialogInterface.OnClickListener {
                    dialog,_->dialog.cancel()
            })
        val alert = build.create()
        alert.setTitle("Update Note")
        alert.setView(update)
        alert.show()
    }

}//end class