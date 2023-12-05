package com.example.appsuperlista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.appclasebasedatos.database.AppDataBase
import com.example.appclasebasedatos.model.Pendiente
import com.example.appsuperlista.databinding.FragmentRegistrarBinding
import java.util.concurrent.Executors

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegistrarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegistrarFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var  binding: FragmentRegistrarBinding
    private lateinit var vista: View
    private lateinit var boton: Button
    private val appDataBase: AppDataBase by lazy{
        AppDataBase.getInstance(requireContext())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        binding = FragmentRegistrarBinding.inflate(layoutInflater)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_registrar, container, false)
        addF()
        return vista
        //return inflater.inflate(R.layout.fragment_registrar, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            RegistrarFragment().apply {
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun addF() {
        boton = vista.findViewById(R.id.btnGuardarPendiente)
        boton.setOnClickListener {
            val articulo = vista.findViewById<EditText>(R.id.ingresoArticulo).text.toString()
            val cantidad = vista.findViewById<EditText>(R.id.ingresoCantidad).text.toString()
            val limite = vista.findViewById<EditText>(R.id.ingresoLimite).text.toString()
            val supermercado =
                vista.findViewById<EditText>(R.id.ingresoSupermercado).text.toString()
            val precio = vista.findViewById<EditText>(R.id.ingresoPrecio).text.toString()
            if(!articulo.isEmpty()) {
                val pendiente = Pendiente(0, articulo, cantidad, limite, supermercado, "$" + precio)
                Toast.makeText(context, "Registrado", Toast.LENGTH_LONG).show()
                add(pendiente)
                vista.findViewById<EditText>(R.id.ingresoArticulo).setText("")
                vista.findViewById<EditText>(R.id.ingresoCantidad).setText("")
                vista.findViewById<EditText>(R.id.ingresoLimite).setText("")
                vista.findViewById<EditText>(R.id.ingresoSupermercado).setText("")
                vista.findViewById<EditText>(R.id.ingresoPrecio).setText("")
            }else{
                Toast.makeText(context, "Complete todos los datos", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun add(pendiente: Pendiente) {
        Executors.newSingleThreadExecutor().execute {
            appDataBase.pendienteDao().insert(pendiente)
        }
    }


}