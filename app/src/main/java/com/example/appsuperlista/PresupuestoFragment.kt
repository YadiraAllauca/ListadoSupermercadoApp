package com.example.appsuperlista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var presupuesto1: TextView


/**
 * A simple [Fragment] subclass.
 * Use the [PresupuestoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PresupuestoFragment : Fragment() {
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

        parentFragmentManager.setFragmentResultListener("key",this) { key, bundle ->
            val result = bundle.getString("presupuestofinal")
            if (!result.toString().equals("")){
                presupuesto1.setText("$"+result.toString())
            }else{
                presupuesto1.setText("$0")
            }


            // Do something with the result..
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presupuesto1 = view.findViewById(R.id.txtPresupuesto) as TextView;
        boton = view.findViewById(R.id.btnActPres)
        boton.setOnClickListener {
            val newFragment = CambiarPresupuestoFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.lyfrag2, newFragment).commit()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_presupuesto, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PresupuestoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PresupuestoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}