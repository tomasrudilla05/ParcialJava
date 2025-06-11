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

