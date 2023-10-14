package modelo

object AlmacenUsuarios {
    var usuarios = ArrayList<Usuario>()
    fun aniadirPersona(p:Usuario){
        usuarios.add(p)
    }
}