package castro.cristina.examen2_castrocristina

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ContactoActivity : AppCompatActivity() {
    private lateinit var btnLlamar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contacto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Obtener datos del Intent con manejo de null
        val nombre = intent.getStringExtra("nombre") ?: "Desconocido"
        val compania = intent.getStringExtra("compania") ?: "No disponible"
        val correo = intent.getStringExtra("correo") ?: "No disponible"
        val telefono = intent.getStringExtra("telefono") ?: "No disponible"

        // Asignar vistas
        val tvNombre = findViewById<TextView>(R.id.tv_nombre)
        val tvCompania = findViewById<TextView>(R.id.tv_compania)
        val tvCorreo = findViewById<TextView>(R.id.tv_correo)
        val tvTelefono = findViewById<TextView>(R.id.tv_numero)
        btnLlamar = findViewById(R.id.btn_llamar)

        // Mostrar datos en los TextView
        tvNombre.text = nombre
        tvCompania.text = compania
        tvCorreo.text = correo
        tvTelefono.text = telefono

        // Botón para llamar
        btnLlamar.setOnClickListener {
            finish()
        }

    }
}