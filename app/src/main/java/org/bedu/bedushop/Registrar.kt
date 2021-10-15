package org.bedu.bedushop

import android.app.ActivityOptions
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import androidx.core.app.ActivityCompat.startActivityForResult
import android.content.Intent
import android.text.TextUtils
import android.transition.Slide
import android.transition.TransitionInflater
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.material.textfield.TextInputEditText

class Registrar : AppCompatActivity() {

    private lateinit var name: TextInputEditText
    private lateinit var mail: TextInputEditText
    private lateinit var phone: TextInputEditText
    private lateinit var pass: TextInputEditText
    private lateinit var registro: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)


       /* //! TRANSITIONS A MEJORAR(SOLVED)
        val transitionXml = TransitionInflater.from(this).inflateTransition(R.transition.login).apply {
            excludeTarget(window.decorView.findViewById<View>(R.id.action_bar_container), true)
            excludeTarget(android.R.id.statusBarBackground, true)
            excludeTarget(android.R.id.navigationBarBackground, true)
        }
        window.exitTransition = transitionXml

        val transition = Slide(Gravity.RIGHT).apply {
            duration = 200
            excludeTarget(window.decorView.findViewById<View>(R.id.action_bar_container), true)
            excludeTarget(android.R.id.statusBarBackground, true)
            excludeTarget(android.R.id.navigationBarBackground, true)
        }

        window.enterTransition = transition

        //! TRANSITIONS A MEJORAR*/

        name= findViewById(R.id.completeNameR)
        mail= findViewById(R.id.editTextEmailR)
        phone=findViewById(R.id.phoneR)
        pass = findViewById(R.id.editPasswordR)

        registro = findViewById(R.id.btnRegistrar)

        // Funcion que valida los datos del formulario de registro
        fun validarForm(): Boolean {
            var esValido = true

            if (TextUtils.isEmpty(name.getText())) {
                name.error = getString(R.string.requerido)
                esValido = false
            } else name.error = null

            if (TextUtils.isEmpty(mail.getText())) {
                mail.error = getString(R.string.requerido)
                esValido = false
            } else mail.error = null

            if (TextUtils.isEmpty(phone.getText())) {
                phone.error = getString(R.string.requerido)
                esValido = false
            } else phone.error = null

            if (TextUtils.isEmpty(pass.getText())) {
                pass.error = getString(R.string.requerido)
                esValido = false
            } else pass.error = null

            return esValido
        }

        // Boton de registrar, valida el formulario y te envia el Inicio de Seccion
        registro.setOnClickListener{

            if(validarForm()){
                val intent=Intent(this, MainActivity::class.java).apply {  }
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
            }
            }
        }
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right)
    }
}


