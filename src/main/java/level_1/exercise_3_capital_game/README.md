# Exercici 1 – Duplicats

##  Enunciat de l'exercici
En aquest exercici posarem en pràctica la lectura de fitxers, l’ús de col·leccions com HashMap i la interacció amb l’usuari mitjançant un petit joc de preguntes.

A partir del fitxer countries.txt (consulta l’apartat de recursos), que conté parelles de país i capital separades per comes, el programa ha de llegir totes les dades i emmagatzemar-les en un HashMap<String, String>, on la clau és el nom del país i el valor, la seva capital.

Un cop carregades les dades, el programa demanarà el nom de l’usuari/ària i començarà el joc.

El funcionament consisteix a realitzar 10 preguntes, escollint aleatòriament 10 països diferents del HashMap. En cada ronda, es mostrarà el nom d’un país i l’usuari/ària haurà d’introduir el nom de la seva capital. Si la resposta és correcta (tenint en compte majúscules i minúscules si cal), es sumarà un punt a la seva puntuació. Al final de les 10 preguntes, es mostrarà la puntuació total obtinguda.

En finalitzar les 10 preguntes, el programa guardarà el nom de l’usuari/ària i la seva puntuació en un fitxer anomenat classificacio.txt.

## 🛠 Tecnologies
- Backend: Java

##  Instal·lació i Execució
1. Clonar el repositori: `git clone ...`
2. Execució de l'aplicació.
3. Proves: Executar el `Main()`.

