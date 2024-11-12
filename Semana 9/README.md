Apuntes:

Transient -> PAra que no se serialice (guarde) un campo. Por ejemplo la contraseña
Comprobar que las clases/sublcases que quiera serializarlo se puedan. Ya que si tienen transient no se podra

Serializar:
    FileOutputStream fileOutputStream = new FileOutputStream("miObjeto.bin");
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
    objectOutputStream.writeObject(miObjeto);
    objectOutputStream.close();


Deserializar:
    FileInputStream fileInputStream = new FileInputStream("miObjeto.bin");
    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
    // Miclase es para hacer casting (down casting)
    MiClase miObjeto = (MiClase) objectInputStream.readObject();
    objectInputStream.close();


// Ejercicio -> Opciones:
1 al 4 -> Operaciones CRUD con listado (hagamoslo de manera simple)
Exportar e importar lo que nos centraremos
