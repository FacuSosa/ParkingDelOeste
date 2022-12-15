/*    import entidades.*
    import repositorios.*
    import java.security.InvalidParameterException
    import java.time.LocalDate
    import java.time.LocalTime

    fun main () {
        val repositorioUsuario = UsuarioRepositorio()
        val repositorioTicket = TicketRepositorio()
        val beneficios = Beneficio()
        val valorHora = 150.0

        var id:Long = 0
        var codigoTicket: Long = 0

        do {
            var opcion: Int? = null
            println("+++++++Estacionamiento del Oeste+++++")
            try{
                println("******Ingrese la opcion: ******")
                println("1-Registrarse")
                println("2-Loguearse")
                println("3-Salir")

                opcion = readLine()!!.toInt()
            }catch(_: Throwable){
            }

            when (opcion) {
                1 -> {

                    println("******Registrar usuario******")

                    try {
                        print("Ingrese su nombre: ")
                        val nombre = readLine().toString().toUpperCase()
                        estaVacio(nombre)
                        print("Ingrese su apellido: ")
                        val apellido = readLine().toString().toUpperCase()
                        estaVacio(apellido)
                        print("Ingrese su saldo: ")
                        val saldo = readLine()!!.toDouble()
                        val fechaAlta = LocalDate.now()


                        var nombreDeUsuario: String

                        do {
                            print("Cree un nombre de usuario: ")
                            nombreDeUsuario = readLine().toString()
                            if (repositorioUsuario.existe(nombreDeUsuario)) {
                                println("\n***Nombre de usuario ya existente***\n")
                            }
                        } while (repositorioUsuario.existe(nombreDeUsuario))

                        print("Cree una contraseña: ")
                        val contrasenaUsuario = readLine().toString()
                        val nuevoUsuario = Usuario(id ,nombre, apellido,saldo,fechaAlta,contrasenaUsuario, nombreDeUsuario)
                        id++
                        repositorioUsuario.agregar(nuevoUsuario)
                        println("--------------------------------------------------------------")
                        println("\t\tSe registro el Usuario correctamente")
                        println("--------------------------------------------------------------")
                    }catch (_: Throwable){
                        println("--------------------------------------------------------------")
                        println("\t\tERROR! Se ingreso un valor incorrecto")
                        println("--------------------------------------------------------------")

                    }


                }

                2 -> {
                    println("***Login***")
                    println("-------------------------")
                    print("Ingrese su nombre de usuario: ")
                    val usuarioNombre = readLine().toString()
                    print("Ingrese su contraseña: ")
                    val contrasena = readLine().toString()

                    if (repositorioUsuario.iniciar(usuarioNombre, contrasena) != null) {
                        val sesion = repositorioUsuario.iniciar(usuarioNombre, contrasena)!!

                        println("***Bienvenido ${sesion.nombre}***")

                        do {
                            println("1- Mostrar historial de Tickets")
                            println("2- Pagar")
                            println("3- Ver saldo")
                            println("4- Cerrar sesion")
                            opcion = readLine()!!.toInt()

                            when (opcion) {
                                1 -> {
                                    if(repositorioTicket.buscar(sesion.id).isEmpty()){
                                        println("--------------------------------------------------------------")
                                        println("\t\t\t\tNo hay tickets registrados")
                                        println("--------------------------------------------------------------")
                                    }else{
                                        println(repositorioTicket.buscar(sesion.id))
                                        repositorioTicket.imprimir(repositorioTicket.buscar(sesion.id))
                                    }
                                }
                                2 -> {
                                    try {
                                        println("Ingresa la hora en el que se estaciono:")
                                        print("Hora:")
                                        val horaIngreso = readLine()!!.toInt()
                                        print("Minutos: ")
                                        val minutoIngreso = readLine()!!.toInt()
                                        val horarioIngreso = LocalTime.of(horaIngreso, minutoIngreso)

                                        print("Ingrese las horas de estadia: ")
                                        val horasEstadia = readLine()!!.toInt()

                                        println("Ingrese la fecha de ingreso:")

                                        val fechaActual = LocalDate.now()
                                        print("Día: ")
                                        val dia = readLine()!!.toInt()
                                        print("Mes: ")
                                        val mes = readLine()!!.toInt()

                                        val fechaIngreso = LocalDate.of(fechaActual.year, mes ,dia)

                                        println("Ingrese patente del vehiculo: ")
                                        val patente = readLine()!!

                                        var tipoVehiculo: Int
                                        do{
                                            println("Ingrese el tipo de vehiculo: ")
                                            println("1. Automovil")
                                            println("2. Pesado")
                                            println("3. Ciclomotor")
                                            tipoVehiculo = readLine()!!.toInt()

                                            if(tipoVehiculo !in 1..3){
                                                println("Vehiculo incorrecto")
                                            }
                                        }while(tipoVehiculo !in 1..3)

                                        val montoBruto = valorHora * horasEstadia

                                        var costo = when(tipoVehiculo){
                                            1 -> {
                                                val vehiculo = Automovil()
                                                vehiculo.calcularRecarga(valorHora, horasEstadia, horarioIngreso)
                                            }
                                            2 ->{
                                                val vehiculo = Pesado()
                                                vehiculo.calcularRecarga(valorHora, horasEstadia, fechaIngreso)
                                            }
                                            else ->{
                                                val vehiculo = Ciclomotor()
                                                vehiculo.calcularRecarga(valorHora, horasEstadia)
                                            }
                                        }

                                        val fechaAlta = sesion.fechaAlta
                                        costo = beneficios.calcularBeneficio(fechaAlta, costo)

                                        if (costo > sesion.saldo) {
                                            println("--------------------------------------------------------------")
                                            println("\t\tNo se puedo realizar el pago por falta de saldo")
                                            println("--------------------------------------------------------------")
                                        } else {
                                            val nuevoTicket = Ticket(sesion.id, codigoTicket++, fechaIngreso, horarioIngreso, patente, horasEstadia, montoBruto, costo)
                                           sesion.saldo -= costo

                                            repositorioTicket.agregar(nuevoTicket)

                                            println("--------------------------------------------------------------")
                                            println("\t\tSe creo un ticket con el pago realizado")
                                            println("--------------------------------------------------------------")
                                        }
                                    } catch (e: RuntimeException) {
                                        println("--------------------------------------------------------------")
                                        println("\t\tERROR! Se ingreso un valor incorrecto")
                                        println("--------------------------------------------------------------")

                                    }
                                }
                                3 -> {
                                    println("Su saldo es: ${sesion.saldo}")
                                }
                                4 -> {
                                    println("***Cerrando sesion***")
                                }
                            }
                        }while(opcion != 4)
                    } else {
                        println("Error!! Nombre de usuario o contraseña incorrecta")
                    }
                }
                3 -> {
                    println("***Gracias por confiar en nosotros***")
                }
                else -> {
                    println("--------------------------------------------------------------")
                    println("\t\tERROR! Se ingreso un valor incorrecto")
                    println("--------------------------------------------------------------")
                }
            }
        } while (opcion != 3)
    }

    fun estaVacio(nombre: String?): Int {
        if (nombre.isNullOrBlank()) {
            throw InvalidParameterException()
        }
        return nombre.count(Char::isDigit)
    }

 */
