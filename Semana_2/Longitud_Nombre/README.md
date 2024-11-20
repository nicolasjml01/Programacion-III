Programa que calcula la longitud del nombre y del apellido y lo muestra por pantalla con el siguiente formato:

    *********************************
    * Nombre         * Apellidos    *
    *********************************
    * Gustavo Adolfo * Bécquer      *
    *********************************
Nota:
System.out.printf("* %-"+ anchoNombre + "s * %-"+ anchoApellidos + "s *\n", nombre, apellidos);
- %-Ns permite: imprimir el valor de la cadena (%s) con un ancho mínimo de N, y el guion - indica que el texto debe estar alineado a la izquierda.
