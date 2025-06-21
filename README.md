#  Trabajo Práctico Java 2025

 _Algoritmos y Estructuras de Datos II_  
 _Universidad CAECE – Mar del Plata_  
 _1er Cuatrimestre – Año 2025_  
 _Trabajo Práctico Grupal_

 ## Grupo:

- Andreani Theo
- Fernandez Unanue Morena
- Gilardi Luca
- Ramunno Agustín

---

##  Descripción del Problema

Una empresa organiza un festival de música de varios días con múltiples escenarios, zonas comunes
y zonas restringidas. Las personas que asisten o trabajan en el festival tienen credenciales con
permisos de acceso a ciertas zonas del complejo. El objetivo del sistema es permitir el seguimiento
de los movimientos de cada persona, controlar accesos autorizados, y generar reportes de asistencia
y uso del espacio. 

- Cargar la configuración del evento desde archivos.
- Registrar y controlar accesos de personas a zonas según sus permisos.
- Emitir reportes de concurrencia y actividad.
- Validar y mantener la trazabilidad de cada persona en el predio.

---

##  Objetivos del Proyecto

 Modelar zonas, personas y accesos con POO.  
 Cargar datos desde archivo XML.  
 Verificar consistencia y emitir informe de errores.  
 Consultar movimientos y accesos por persona.  
 Simular movimientos entre zonas según permisos.  
 Generar reportes en pantalla y archivo.

---

##  Conceptos Aplicados

-  Encapsulamiento, herencia y polimorfismo
-  Uso de contenedores Java
-  Serialización para persistencia
-  Enumeraciones
-  Validaciones con excepciones personalizadas
-  Separación entre lógica de negocio y presentación
-  Generación de informes a archivos de texto

---
##  Funcionalidades Implementadas

###  Carga desde XML
- Zonas, personas y accesos desde `festival.xml`
- Validación de referencias cruzadas
- Ordenamiento alfabético por código de zona
- Informe de errores generado si los hay → `errores_carga.txt`

###  Movimientos
- Validación de acceso según permisos y capacidad
- Registro de intentos fallidos o exitosos
- Trazabilidad completa por persona

###  Reportes
-  Zonas por concurrencia descendente 
-  Listado de stands con responsable y empleados
-  Salida por consola y archivo (`reporte_zonas.txt`, `reporte_stands.txt`)

---

##  Archivos de Entrada

El archivo `festival.xml` debe contener:

```xml
<festival>
  <zonas>
    <escenario>...</escenario>
    <backstage>...</backstage>
    <patioDeComidas>...</patioDeComidas>
    <stand>...</stand>
  </zonas>
  <personas>
    <artista>...</artista>
    <comerciante>...</comerciante>
    <asistente>...</asistente>
    <staffOrganizador>...</stafOrganizador>
  </personas>
  <accesos>
    <acceso>...</acceso>
  </accesos>
</festival>

