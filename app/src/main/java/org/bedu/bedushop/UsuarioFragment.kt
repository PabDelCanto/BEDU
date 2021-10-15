package org.bedu.bedushop

import RecyclerAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_recycler.*
import okhttp3.*
import org.json.JSONObject
import org.w3c.dom.Text
import java.io.IOException
import kotlin.random.Random


class UsuarioFragment : Fragment() {

    private lateinit var txtUser : TextView
    private lateinit var txtEmail : TextView
    private lateinit var imgUser : ImageView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_usuario, container, false)
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerOptions)
        recycler.adapter = RecyclerAdapterOpciones(listOf(
            Opciones("Mis direcciones", R.drawable.ic_location,R.drawable.arrow),
            Opciones("Metodo de pago", R.drawable.credit_card,R.drawable.arrow),
            Opciones("Pedidos", R.drawable.history,R.drawable.arrow),
            Opciones("Notificaciones", R.drawable.notificacion,R.drawable.arrow),
            Opciones("Cambiar contraseña", R.drawable.lock,R.drawable.arrow)
        ))

        txtUser = view.findViewById(R.id.namePerfil)
        txtEmail = view.findViewById(R.id.emailPerfil)
        imgUser = view.findViewById(R.id.imgPerfil)

        //Recuperamos datos del bundle desde la actividad shop
        val userMail = this.arguments?.getString(USER_EMAIL_SHOP)
        val userFullName = this.arguments?.getString(USER_FULL_NAME_SHOP)
        val userAvatar = this.arguments?.getString(USER_AVATAR_SHOP)

        //Asignamos los datos
        txtUser.text = userFullName
        txtEmail.text = userMail
        Picasso.with(context).load(userAvatar).into(imgUser)


        return view
    }


}
/*

*/