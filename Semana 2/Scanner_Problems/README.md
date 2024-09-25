En este ejercicio se nos plantean las siguientes cuestiones:

¿Qué ocurre con algunos métodos nextXXX() de la clase Scanner y el salto de línea final? Realiza un programa que reproduce esta problemática solicitando la edad al usuario con nextInt() y justo después el nombre con nextLine(). ¿Cómo podrías resolverlo? 

## Problema entre nextInt() y nextLine()

La clase Scanner en Java tiene un comportamiento particular con algunos métodos como nextInt(), nextDouble(), etc., en relación con el manejo de los saltos de línea (caracteres \n o Enter). Este comportamiento puede generar problemas cuando se intenta leer cadenas de texto con nextLine() justo después de usar nextInt() u otros métodos similares.

## ¿Qué ocurre?
Métodos como nextInt(), nextDouble(), etc., leen el número pero no consumen el carácter de nueva línea \n que se genera cuando el usuario presiona "Enter". Por lo tanto, si usas nextLine() inmediatamente después de nextInt(), nextLine() recogerá ese carácter de nueva línea residual y no permitirá que el usuario ingrese el siguiente dato correctamente.

Como resultado, el programa no espera la entrada del usuario para el nextLine() y simplemente lee la línea vacía que quedó después de la entrada numérica.

## ¿Solucion?
Una solución simple es consumir el salto de línea que quedó en el búfer después de nextInt(), usando un nextLine() adicional. De esta manera, el siguiente nextLine() funcionará correctamente para leer la entrada del usuario.

