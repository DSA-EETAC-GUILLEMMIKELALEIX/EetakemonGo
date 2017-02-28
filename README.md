Gestión de Eetakemon
Implementad un programa de consola en Java que permita realizar una gestión de Eetakemons con las siguientes opciones:

Añadir eetakemon
Borrar eetakemon por Id
Listar todos los eetakemon
Buscar por nombre
La clase Eetakemon nos modela un eetakemon y su información básica, según lo acordado en clase lo mínimo seria:

id: int
nombre: String
nivel: int
Para esta primera versión los Eetakemons no serán persistentes (se perderán cada vez que apaguemos el programa) y los almacenaremos en una lista en memoria, por ejemplo usando la clase ArrayList.

Enviad el enlace al repositorio de github donde tenéis el código de vuestro programa.

Consideraciones:
1. El identificador tiene que ser generado. Es decir cuando añadamos un nuevo eetakemon NO nos puede pedir que identificador tiene, el sistema tiene que generarle uno correcto automáticamente (por ejemplo de forma incremental).

2. Organizad el código en 3 paquetes: modelo, vista y controlador. Así vuestro código inicial debería tener tres clases una en cada paquete, aunque podéis añadir más si lo necesitáis.

El paquete modelo contiene las clases que representan las entidades de vuestro sistema, en nuestro caso concreto como mínimo una clase Eetakemon.

El paquete vista contiene las clases que se encargan de la interacción con el usuario del sistema. En nuestra caso, como es una aplicación de consola utilizaremos System.in y System.out para interactuar con el usuario. Cualquier clase que utilice estos métodos tiene que estar en este paquete. A priori la clase Main debería estar aquí.

El paquete controlador implementa la lógica del sistema. Para ello, recibirá ordenes de la vista y le devolverá los resultados pertinentes para que esta los muestre al usuario. Durante el procesamiento utilizara las clases del modelo que necesite. Cada una de las funcionalidades del sistema estará representada como un método de una clase aquí.

Por ejemplo:

void añadirEetakemon(Eetakemon e);
bool borrarEetakemonPorId(int id);
List<Eetakemon> listarTodos();
Eetakemon buscarPorNombre(String nombre);

Bola Extra:

Implementar una búsqueda adicional que no busque por nombre exacto sino por aproximado, es decir buscar por "an" me encontraria los eetakemon juan y animalote.
