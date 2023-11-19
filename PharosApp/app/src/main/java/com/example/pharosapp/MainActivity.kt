package com.example.pharosapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.pharosapp.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

import java.io.File




class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private lateinit var firebaseauth : FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    val TAG = "ACSCO"
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseauth = FirebaseAuth.getInstance()
        //------------------------------ Autenticación con username y password ------------------------------------
        binding.btnSignup.setOnClickListener {
            if (binding.edtEmail.text.isNotEmpty() && binding.edtUsername.text.isNotEmpty() && binding.edtPassword.text.isNotEmpty()){
                firebaseauth.createUserWithEmailAndPassword(binding.edtEmail.text.toString(),binding.edtPassword.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        irHome(it.result?.user?.email?:"",Proveedor.BASIC)  //Esto de los interrogantes es por si está vacío el email, que enviaría una cadena vacía.
                    } else {
                        showAlert("Error registrando al usuario.")
                    }
                }.addOnFailureListener{
                    Toast.makeText(this, "Conexión no establecida", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                showAlert("Rellene los campos")
            }
        }

        binding.btnLogin.setOnClickListener {
            if (binding.edtEmail.text.isNotEmpty() && binding.edtUsername.text.isNotEmpty() && binding.edtPassword.text.isNotEmpty()){
                firebaseauth.signInWithEmailAndPassword(binding.edtEmail.text.toString(),binding.edtPassword.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        irHome(it.result?.user?.email?:"",Proveedor.BASIC)  //Esto de los interrogantes es por si está vacío el email.
                    } else {
                        showAlert()
                    }
                }.addOnFailureListener{
                    Toast.makeText(this, "Conexión no establecida", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                showAlert("Rellene los campos")
            }
        }


        //------------------ Login Google -------------------
        //------------------------------- -Autenticación Google --------------------------------------------------
        //se hace un signOut por si había algún login antes.
        firebaseauth.signOut()
        //clearGooglePlayServicesCache()

        //esta variable me conecta con  google. y todo este bloque prepara la ventana de google que se destripa en el loginInGoogle
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.your_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this,gso)
        binding.btGoogle.setOnClickListener {
            loginEnGoogle()
        }
    }

    //******************************* Para el login con Google ******************************
    //--------
    private fun loginEnGoogle(){
        //este método es nuestro.
        val signInClient = googleSignInClient.signInIntent
        launcherVentanaGoogle.launch(signInClient)
        //milauncherVentanaGoogle.launch(signInClient)
    }


    //con este launcher, abro la ventana que me lleva a la validacion de Google.
    private val launcherVentanaGoogle =  registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        //si la ventana va bien, se accede a las propiedades que trae la propia ventana q llamamos y recogemos en result.
        if (result.resultCode == Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            manejarResultados(task)
        }
    }

    //es como una muñeca rusa, vamos desgranando, de la ventana a task y de task a los datos concretos que me da google.
    private fun manejarResultados(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful){
            val account : GoogleSignInAccount? = task.result
            if (account != null){
                actualizarUI(account)
            }
        }
        else {
            Toast.makeText(this,task.exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    //esta funcion actualiza o repinta la interfaz de usuario UI.
    private fun actualizarUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        //pido un token, y con ese token, si todo va bien obtengo la info.
        firebaseauth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful){
                //hacer account. y ver otras propiedades interesantes.
                irHome(account.email.toString(),Proveedor.GOOGLE, account.displayName.toString())
            }
            else {
                Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }




    //************************************** Funciones auxiliares **************************************
    //*********************************************************************************
    private fun showAlert(msg:String = "Se ha producido un error autenticando al usuario"){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(msg)
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    //*********************************************************************************
    private fun irHome(email:String, provider:Proveedor, nombre:String = "Usuario"){
        Log.e(TAG,"Valores: ${email}, ${provider}, ${nombre}")
        val homeIntent = Intent(this, Home::class.java).apply {
            putExtra("email",email)
            putExtra("provider",provider.name)
            putExtra("nombre",nombre)
        }
        startActivity(homeIntent)
    }


    override fun onDestroy() {
        super.onDestroy()
        FirebaseAuth.getInstance().signOut()
    }



}