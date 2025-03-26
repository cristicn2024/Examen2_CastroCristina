package castro.cristina.examen2_castrocristina

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AgregarActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etCompania: EditText
    private lateinit var etCorreo: EditText
    private lateinit var etTelefono: EditText
    private lateinit var btnGuardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar)

        etNombre = findViewById(R.id.et_nombreCompleto)
        etCompania = findViewById(R.id.et_compania)
        etCorreo = findViewById(R.id.etCorreo)
        etTelefono = findViewById(R.id.etTelefono)
        btnGuardar = findViewById(R.id.btn_guardar)

        btnGuardar.setOnClickListener {
            // Crear un objeto Contacto
            val contacto = Contacto(
                nombre = etNombre.text.toString(),
                compania = etCompania.text.toString(),
                correo = etCorreo.text.toString(),
                telefono = etTelefono.text.toString()
            )

            // Crear un intent para enviar los datos a MainActivity
            val intent = Intent().apply {
                putExtra("nombre", contacto.nombre)
                putExtra("compania", contacto.compania)
                putExtra("correo", contacto.correo)
                putExtra("telefono", contacto.telefono)
            }

            setResult(Activity.RESULT_OK, intent)
            finish() // Finaliza esta actividad y vuelve a MainActivity
        }

    }

}