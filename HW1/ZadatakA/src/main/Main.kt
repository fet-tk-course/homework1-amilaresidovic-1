interface Osoba{
    fun getImePrezime():String
    fun getZemlja():String
}

open class Programer(val ime:String,val prezime:String,val godineIskustva:Int,val oznakaZemlje:String,
                     val programskiJezici:List<String>):Osoba{
    init{
        if(ime.isEmpty()){
            throw Exception("Ime ne smije biti prazno")
        }
        if(godineIskustva<0){
            throw Exception("Broj godina ne smije biti negativan")
        }
        if(programskiJezici.isEmpty()){
            throw Exception("Lista jezika ne smije biti prazna")
        }
    }

    val jezici:List<String> = programskiJezici.map{it.lowercase()}

    override fun getImePrezime(): String {
        return "$ime $prezime"
    }
    override fun getZemlja(): String {
        return oznakaZemlje
    }
}

class BackendDeveloper(ime:String,prezime:String,godineIskustva: Int,oznakaZemlje: String,
   programskiJezici: List<String>,val backendFramework:String ):Programer(ime,prezime,godineIskustva, oznakaZemlje,programskiJezici)

class FrontendDeveloper(ime: String,prezime: String,godineIskustva: Int,oznakaZemlje: String,
    programskiJezici: List<String>,val frontendFramework:String):Programer(ime,prezime,godineIskustva,oznakaZemlje,programskiJezici)


