🎯 Patrones de Diseño Aplicados en Excusas S.A.


🔗 Chain of Responsibility
Las excusas de un empleado son evaluadas en cadena por los siguientes roles:
👩‍💼 Recepcionista → 🧑‍💼 Supervisor → 🧑‍⚖️ Gerente → 👔 CEO

Cada encargado decide si puede manejar la excusa, y si no, la deriva al siguiente.


♟️ Strategy
Cada encargado evalúa excusas con una estrategia determinada:

✅ Normal: procesa la excusa según sus reglas.
😴 Vago: siempre deriva al siguiente sin evaluar.
🚀 Productivo: evalúa o deriva, y además notifica al CTO.


👤 Singleton
El Administrador de Prontuarios es un recurso único y compartido por todos los CEOs.
Solo puede haber una única instancia.

👀 Observer
Cuando un CEO registra un nuevo prontuario, el sistema:
Notifica automáticamente a todos los 👔 CEOs.

Diagrama UML, CASOS DE USO
https://lucid.app/lucidchart/301087c4-9154-4bfb-ad73-9c8f189b040b/edit?invitationId=inv_42b43c5b-6c1d-45a8-84e1-d35da55916d1&page=pp9nHDg3hOCU#

