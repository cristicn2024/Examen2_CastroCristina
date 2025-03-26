package castro.cristina.examen2_castrocristina

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: ContactosAdapter
    private var contactos = arrayListOf<Contacto>()

    // Nuevo Activity Result API
    private val agregarContactoLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val nombre = data?.getStringExtra("nombre") ?: return@registerForActivityResult
            val compania = data?.getStringExtra("compania") ?: ""
            val correo = data?.getStringExtra("correo") ?: ""
            val telefono = data?.getStringExtra("telefono") ?: ""

            // Crear un nuevo contacto
            val nuevoContacto = Contacto(nombre, compania, correo, telefono)
            contactos.add(nuevoContacto)  // Agregar el contacto a la lista
            adapter.notifyDataSetChanged() // Actualizar el adapter para reflejar los cambios
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView_contactos)
        adapter = ContactosAdapter(this, contactos)
        listView.adapter = adapter

        val btnAgregar = findViewById<Button>(R.id.btn_agregar)
        btnAgregar.setOnClickListener {
            val intent = Intent(this, AgregarActivity::class.java)
            agregarContactoLauncher.launch(intent) // Lanzar la actividad de agregar contacto
        }
    }

    // Adaptador para mostrar la lista de contactos
    class ContactosAdapter(
        private val contexto: Context?,
        private var contactos: ArrayList<Contacto>
    ) : BaseAdapter() {

        private val colores = listOf("#FF5733", "#33FF57", "#3357FF", "#FF33A1", "#FFC300")
        private val colorMap = mutableMapOf<String, String>()

        override fun getCount(): Int = contactos.size

        override fun getItem(position: Int): Any = contactos[position]

        override fun getItemId(position: Int): Long = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val contacto = contactos[position]
            val inflador = LayoutInflater.from(contexto)
            val vista = inflador.inflate(R.layout.contacto_view, null)

            val icon = vista.findViewById<ImageView>(R.id.btn_icon)
            val nombre = vista.findViewById<TextView>(R.id.contacto_nombre)
            val compania = vista.findViewById<TextView>(R.id.contacto_compania)
            val btnEliminar = vista.findViewById<ImageButton>(R.id.botonEliminar)

            // Obtén un color aleatorio de la lista
            val color = colorMap.getOrPut(contacto.nombre) { colores.random() }

            // Cambiar el color del vector usando setColorFilter
            icon.setColorFilter(Color.parseColor(color))

            nombre.text = contacto.nombre.first().toString()
            compania.text = contacto.compania

            // Configuración del click en un contacto
            vista.setOnClickListener {
                val intent = Intent(contexto, ContactoActivity::class.java).apply {
                    putExtra("nombre", contacto.nombre)
                    putExtra("compania", contacto.compania)
                    putExtra("correo", contacto.correo)
                    putExtra("telefono", contacto.telefono)
                }
                contexto?.startActivity(intent)
            }

            // Eliminar contacto al hacer click en el botón de eliminar
            btnEliminar.setOnClickListener {
                contactos.removeAt(position)
                notifyDataSetChanged() // Refrescar la lista después de eliminar
            }

            return vista
        }

    }

}