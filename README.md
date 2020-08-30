# Prueba Employee BackEnd

### Aplicacion
Es un Restful API, con las acciones CRUD de crear, editar y eliminar Empleados, con Nombre y Cargo (función); en este caso consta de un contenedor en Spring para los servicios Rest, y un Contenedor Docker para proveer de la base de datos, en este caso MySQL

#### Funcionamiento de App en Angular

##### Listar empleados
En un grid se mostrará la lista de Empleados existentes, si no hay registros, se mostrará un mensaje indicando que no hay registros.
![Alt text](https://github.com/sebas1790/test-bdb-frontend/blob/master/img/inicio.png "Inicio")

##### Registrar empleados
Al dar clic en Crear empleado, se mostrará un formulario para ingresar un nuevo empleado
![Alt text](https://github.com/sebas1790/test-bdb-frontend/blob/master/img/crear.png "Crear")
![Alt text](https://github.com/sebas1790/test-bdb-frontend/blob/master/img/create-comp.png "Crear Comp")

##### Actualizar empleados
Al dar clic en Editar en cualquier registro del grid, se mostrará un formulario para actualizar el empleado con los datos registrados
![Alt text](https://github.com/sebas1790/test-bdb-frontend/blob/master/img/edit.png "Edit")
![Alt text](https://github.com/sebas1790/test-bdb-frontend/blob/master/img/edit-comp.png "Edit Comp")

##### Eliminar empleados
Al dar clic en Eliminar en cualquier registro del grid, se mostrará un mensaje de confirmación antes de eliminarlo
![Alt text](https://github.com/sebas1790/test-bdb-frontend/blob/master/img/delete-confirm.png "Delete")
![Alt text](https://github.com/sebas1790/test-bdb-frontend/blob/master/img/delete-comp.png "Delete Comp")

## MySQL en Docker
- Crear el contenedor de MySQL en: docker run -d -p 33060:3306 --name mysql-db -e MYSQL_ROOT_PASSWORD=secret mysql
- Limpiar procesos de MySQL antes de crear un volumen para persistir los datos: docker rm -f mysql-db
- Creamos un volumen para el motor: docker volume create mysql-db-data
- Levantamos nuevamente el Docker y agregamos el volumen con la opcion --mount: docker run -d -p 33060:3306 --name mysql-db  -e MYSQL_ROOT_PASSWORD=secret --mount src=mysql-db-data,dst=/var/lib/mysql mysql
- Verificamos que el contenedor esté arriba:

![Alt text](https://github.com/sebas1790/test-bdb-frontend/blob/master/img/docker.png "Docker")

## Clave de usuario root de MySql
Las credenciales del MySQL se pueden modificar dentro del archivo de configuración de la aplicación en Spring Boot
 src/main/resources -> application.properties -> spring.datasource.password
Colocar la misma que se utilizó en el parametro del Docker: "MYSQL_ROOT_PASSWORD"

## Clonar y ejecutar
- Realizar git clone del repositorio.
- Abrir proyecto 'restSrv-app' con spring tools suite.
- Asegurar que el contenedor del Docker con el MySQL está corriendo.
- Ejecutar aplicación en spring tools suite: 'restSrv-app'.
- Seguir con los pasos indicados en el FrontEnd para levantar el Angular...
