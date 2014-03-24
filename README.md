#JNI Calculator
A basic calculator that utilizes *Java Native Interface* (JNI) to execute function of a C library. The purpose of JNI Calculator is to demonstrate how Java can utilize JNI to communicate with native libraries.

## Requirements

###Compilation Requirements

The Makefile utilizes `gcc` and `javac` as the default compilers but these can be changed by changint the variables `CC` and `JC` respectively.
The Makefile also requires a packaging utility to create the jar file and uses `jar` by default but it can also be changed in the variable `JAR`.

###Runtime Requirements

Java SE 7 or greater is required to run the application since there are calls to methods that were not present in earlier versions.

##Compilation

###Build System (Make)
The included Makefile contains the following build targets and task:

* `make` will build the C library, compile the Java sources and create a jar file with all the resources.
* `make clean` will delete the `bin` and `build` directories

###About Platform Dependent Headers
Two Java headers are required to compile the C library that is accessed through JNI. The first header is *jni.h* located in the `$JAVA_HOME/include` directory. The second header, *jni_md.h*, is located in a different directory depending on the platform. Usually the locations are the following:

* Linux `$JAVA_HOME/include/linux`
* OS X `$JAVA_HOME/include/darwin`
* Windows `$JAVA_HOME/include/win32`

The Makefile will detect the platform and include the corresponding directory for the *jni_md.h* header. If `$JAVA_HOME` is not defined or the platform dependent directory cannot be determined the compilation of the C library will fail.

##Author
Federico Jhoel Salas González<br/>
IC8041 Desarrollo de Aplicaciones para Dispositivos Móviles<br/>
Andrei Fuentes Leiva<br/>
Jeff Schmidt Peralta<br/>
Tecnológico de Costa Rica