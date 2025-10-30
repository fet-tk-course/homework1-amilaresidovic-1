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


fun analizaJezikaVerzija1(programeri:List<Programer>):Map<String,Int>{
    return programeri.flatMap{it.jezici}.groupingBy {it}.eachCount()
}
fun analizaJezikaVerzija2(programeri: List<Programer>):Map<String,Int> {
    val mapa = mutableMapOf<String, Int>()

    for(i in programeri){
        for(j in i.jezici){
            mapa[j]=mapa.getOrDefault(j,0)+1
        }
    }
    return mapa
}

//Za prosjecnoIskustvoVerzija1 sam koristila Chat GPT kako bih vidjela na koji nacin kreirati
// podatke kao parove i kako koristiti mapValues za izracun prosjeka

fun prosjecnoIskustvoVerzija1(programeri:List<Programer>):Map<String,Double>{
    return programeri.flatMap{i->i.jezici.map{it to i.godineIskustva}}
        .groupBy { it.first }.mapValues { it.value.map{j->j.second}.average() }
}

fun prosjecnoIskustvoVerzija2(programeri:List<Programer>):Map<String,Double> {
    val suma = mutableMapOf<String, Double>()
    val brojac = mutableMapOf<String, Int>()

    for (i in programeri) {
        for (j in i.jezici) {
            suma[j] = suma.getOrDefault(j, 0.0) + i.godineIskustva
            brojac[j] = brojac.getOrDefault(j, 0) + 1
        }
    }
    val prosjek = mutableMapOf<String, Double>()
    for (j in suma.keys) {
        prosjek[j] = suma[j]!! / brojac[j]!!
    }
    return prosjek
}



