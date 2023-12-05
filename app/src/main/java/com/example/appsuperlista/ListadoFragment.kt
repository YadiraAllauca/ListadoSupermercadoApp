package com.example.appsuperlista

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appclasebasedatos.database.AppDataBase
import com.example.appclasebasedatos.model.Pendiente
import com.example.appclasebasedatos.utils.Constants
import com.example.appsuperlista.databinding.ActivityMainBinding
import com.example.appsuperlista.databinding.FragmentListadoBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.concurrent.Executors

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var articulo: String
private lateinit var supermercado: String
private lateinit var precio: String
private lateinit var boton: FloatingActionButton
private lateinit var pendientePrueba: Pendiente
/**
 * A simple [Fragment] subclass.
 * Use the [ListadoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListadoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var recicler: RecyclerView
    private lateinit var adapter: PendienteAdapter
    private var pendientes: List<Pendiente> = listOf()

    private val appDataBase: AppDataBase by lazy{
        AppDataBase.getInstance(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_listado, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        recicler = view.findViewById(R.id.rvListado)
        recicler.layoutManager = layoutManager
        recicler.setHasFixedSize(true)
        adapter = PendienteAdapter(pendientes)
        recicler.adapter = adapter
        appDataBase.pendienteDao().getPendientes().observe(viewLifecycleOwner, { pendientes ->
            adapter.updateListPets(pendientes)
        })
        adapter.setOnClickPendiente={
          articulo = adapter.articulo
            supermercado = adapter.supermercado
            precio = adapter.precio
            val bundle = Bundle()
            bundle.putString("articulof", articulo)
            bundle.putString("supermercadof", supermercado)
            bundle.putString("preciof", precio)
            parentFragmentManager.setFragmentResult("key_item",bundle)
            val newFragment = DetalleFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.lyfrag2, newFragment).commit()

        }

        boton = view.findViewById(R.id.btnAgregar)
        boton.setOnClickListener {
            //Toast.makeText(requireContext(),"{${pendientePrueba.articulo}}", Toast.LENGTH_SHORT).show()
            val newFragment = RegistrarFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.lyfrag2, newFragment).commit()
        }
        adapter.setOnClickListenerDelete={ val builder = android.app.AlertDialog.Builder(context)
                builder.setTitle("Se eliminará el registro")
                builder.setMessage("¿Desea continuar?")
                builder.setNegativeButton("Cancelar", android.content.DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
                builder.setPositiveButton("Aceptar", android.content.DialogInterface.OnClickListener { dialog, which ->
                    Executors.newSingleThreadExecutor().execute {
                        if (pendientes != null) {
                            appDataBase.pendienteDao().delete(it)
                        }
                    }
                })
                builder.show()
        }
        cargar()


    }
    fun cargar(){
        Executors.newSingleThreadExecutor().execute {
            pendientePrueba = appDataBase.pendienteDao().getPendienteById(1)

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListadoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListadoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}