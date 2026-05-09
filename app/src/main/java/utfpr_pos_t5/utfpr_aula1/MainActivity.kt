package utfpr_pos_t5.utfpr_aula1

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.pow

// -------------------------------------------------------------- Acessando Recursos
//------------------------ Usando activity_principal.xml
//@drawable/icon
//@string/hello
//@id/btCalcular
//@layout/activity_principal

//Usando PrincipalActivity.kt
//
//R.drawable.icon
//R.string.hello
//R.id.btCalcular
//R.layout.activity_principal

class MainActivity : AppCompatActivity() {
    private lateinit var etWeight: EditText
    private lateinit var etHeight: EditText
    private lateinit var tvResult: TextView
    private lateinit var btCalculate: Button
    private lateinit var btClear: Button

    private fun initView() {
        etWeight = findViewById(R.id.etWeight)
        etHeight = findViewById(R.id.etHeight)
        tvResult = findViewById(R.id.tvResult)
        btCalculate = findViewById(R.id.btCalculate)
        btClear = findViewById(R.id.btClear)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initView()

        Log.e("Lifecycle", "Error")
        Log.w("Lifecycle", "Warning")

        btCalculate.setOnClickListener {
            btCalculateOnClick()
        }

        //Quando é click longo, no final, true no retorno, manter pressionado nao executa a funçaoo.
        btCalculate.setOnLongClickListener {
            Toast.makeText(
                this,
                "Botão para calcular o IMC",
                Toast.LENGTH_SHORT
            ).show()
            true
        }

        btClear.setOnClickListener{
            btClearOnClick()
        }
    }

    private fun btClearOnClick() {
        etWeight.setText("")
        etWeight.requestFocus()
        etHeight.setText("")
        tvResult.text = "IMC: "
    }

    // é possivel copiar codigo java e ao colar, a IDE converte para Kotlin automaticamente
    private fun btCalculateOnClick() {
        val weight = etWeight.text.toString().toDoubleOrNull()
        val heightCm = etHeight.text.toString().toDoubleOrNull()

        if (weight == null) {
            etWeight.error = "Digite um peso válido"
            return
        }

        if (heightCm == null) {
            etHeight.error = "Digite uma altura válida"
            return
        }

        val heightMeters = heightCm / 100.0
        val imc = weight / heightMeters.pow(2.0)

        tvResult.text = "IMC: %.2f".format(imc)
    }
}