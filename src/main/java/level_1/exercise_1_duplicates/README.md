# Exercici 1 – Duplicats
**Descripció**: En aquest exercici faràs servir dues de les col·leccions més habituals de Java: ArrayList i HashSet. Practicaràs com afegir i modificar elements, i observaràs com es comporten aquestes col·leccions davant la presència de duplicats.

##  Enunciat de l'exercici
Crea una classe anomenada Month amb un atribut name, que emmagatzemi el nom del mes. Afegeix 11 objectes Month a un ArrayList, deixant fora "Agost". Insereix aquest mes a la posició corresponent i comprova que l’ordre dels mesos és correcte.

Converteix després l’ArrayList en un HashSet i verifica que no es permeten duplicats.

Finalment, recorre la col·lecció amb un bucle for i amb un Iterator.

## 🛠 Tecnologies
- Backend: Java

##  Instal·lació i Execució
1. Clonar el repositori: `git clone ...`
2. Execució de l'aplicació.
3. Proves: Executar el `Main()`.

## 📌 Anotacions
For sets default equals() uses object references to compare, it is imperative to override both equals() and hashCode().