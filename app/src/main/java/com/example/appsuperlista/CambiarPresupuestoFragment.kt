package com.example.appsuperlista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var presupuesto: EditText
/**
 * A simple [Fragment] subclass.
 * Use the [CambiarPresupuestoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CambiarPresupuestoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var boton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presupuesto = view.findViewById(R.id.txtEditarPresupuesto)
        boton= view.findViewById(R.id.btnGuardarPresupuesto)
        boton.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("presupuestofinal", presupuesto.text.toString())
            parentFragmentManager.setFragmentResult("key",bundle)
            Toast.makeText(context,"Presupuesto actualizado", Toast.LENGTH_SHORT).show()
            val newFragment = PresupuestoFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.lyfrag2, newFragment).commit()

        }



    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cambiar_presupuesto, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CambiarPresupuestoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CambiarPresupuestoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}