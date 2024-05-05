package com.example.pc1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val etExParcial: EditText = findViewById(R.id.txtNotaExParcial)
        val etExFinal: EditText = findViewById(R.id.txtNotaExFinal)
        val etPEP: EditText = findViewById(R.id.txtPEP)

        val btnCalcular: Button = findViewById(R.id.btnCalcular)

        btnCalcular.setOnClickListener{
            this.sendMessage(etExParcial.text.toString(),etExFinal.text.toString(),etPEP.text.toString())
        }
    }
    private fun sendMessage(exParcial: String,exFinal: String,pep: String){
        val porcentParcial = 0.20
        val porcentFinal = 0.20
        val porcentPEP = 0.60

        val NotaFinal =   (exParcial.toDouble() * porcentParcial) + (exFinal.toDouble() * porcentFinal) + (pep.toDouble() * porcentPEP)

        var resultado = ""
        if (NotaFinal > 10.5){
            resultado = "Aprobado"
        }else{
            resultado = "Desaprobado"
        }

        val intent = Intent(this, ResultadoActivity::class.java)
        intent.putExtra("resultado",resultado)
        intent.putExtra("notaFinal",NotaFinal.toString())
        startActivity(intent)
    }
}
