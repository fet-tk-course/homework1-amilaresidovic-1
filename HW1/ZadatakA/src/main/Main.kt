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

//Za prosjecnoIskustvoVerzija1 sam koristila ChatGPT kako bih vidjela na koji nacin kreirati
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

fun filtriranje(programeri: List<Programer>,framework:String):List<Programer>{
    val lista=mutableListOf<Programer>()

    for(i in programeri){
        if(i is BackendDeveloper){
            if(i.backendFramework==framework){
                lista.add(i)
            }
        }
        if(i is FrontendDeveloper){
            if(i.frontendFramework==framework){
                lista.add(i)
            }
        }
    }
    return lista
}

fun ispis(programer:Programer):String{
    val imePrezime=programer.getImePrezime()
    val jezici=programer.jezici.joinToString (", ")

    if(programer is BackendDeveloper){
        return "$imePrezime - Backend developer - jezici: $jezici - framework: ${programer.backendFramework}"

    }
    else if(programer is FrontendDeveloper){
        return "$imePrezime - Frontend developer - jezici: $jezici - framework: ${programer.frontendFramework}"
    }
    else{
        return "Nepoznato"
    }
}
fun main(){
    val programeri=listOf(
        BackendDeveloper("Amila","Residovic",2,"BH",listOf("Kotlin","Java"),"Spring Boot"),
        BackendDeveloper("Emina","Jusufovic",5,"BH",listOf("Kotlin","Python"),"Spring Boot"),
        BackendDeveloper("Amina","Hasic",3,"DE",listOf("Java","Python"),"Ktor"),
        FrontendDeveloper("Ajla","Arnaut",4,"HR",listOf("JavaScript","TypeScript"),"React"),
        FrontendDeveloper("Maid","Idrizovic",6,"BH",listOf("JavaScript","HTML"),"Vue.js")
    )

    println("Validacija podataka")
    try{
        Programer("","Residovic",5,"BH",listOf("Kotlin"))
    }
    catch(e: Exception){
        println("Provjera 1: ${e.message}")
    }
    try{
        Programer("Ajla","Arnaut",-5,"BH",listOf("Kotlin"))
    }
    catch(e: Exception){
        println("Provjera 2: ${e.message}")
    }
    try{
        Programer("Emina","Jusufovic",5,"BH",listOf())
    }
    catch(e: Exception){
        println("Provjera 3: ${e.message}")
    }
    println()

    println("Prikaz svih programera")
    for(i in programeri){
        println(ispis(i))
    }
    println()

    println("Analiza jezika: Verzija 1")
    val jezici1=analizaJezikaVerzija1(programeri)
    for(i in jezici1.keys){
        println("$i: ${jezici1[i]} programera")
    }
    println()

    println("Analiza jezika: Verzija 2")
    val jezici2=analizaJezikaVerzija2(programeri)
    for(i in jezici2.keys){
        println("$i: ${jezici2[i]} programera")
    }
    println()

    println("Prosjecno iskustvo: Verzija 1")
    val iskustvo1=prosjecnoIskustvoVerzija1(programeri)
    for(i in iskustvo1.keys){
        println("$i: ${iskustvo1[i]} godina")
    }
    println()

    println("Prosjecno iskustvo: Verzija 2")
    val iskustvo2=prosjecnoIskustvoVerzija2(programeri)
    for(i in iskustvo2.keys){
        println("$i: ${iskustvo2[i]} godina")
    }
    println()

    println("Filtriranje za Spring Boot")
    val filter=filtriranje(programeri,"Spring Boot")
    for(i in filter){
        println(ispis(i))
    }

}



