
###
GET http://localhost:9000/api/participant/epreuve/all

<> 2024-06-17T145903.200.json

### ATTENTION 
### erreur si Date < 10 jours
### participant déjà inscrit
GET http://localhost:9000/api/participant/inscrire/prenomNomNomEpreuve/Participant_1/Participant_1/Epreuve 1

<> 2024-06-17T151033.500.json

###
GET http://localhost:9000/api/participant/desengager/prenomNomNomEpreuve/Participant_1/Participant_1/Epreuve 1

<> 2024-06-17T151020.200.txt

###
GET http://localhost:9000/api/participant/resultat/all

<> 2024-06-17T151049.200.json

###
GET http://localhost:9000/api/participant/classement

<> 2024-06-17T151058.200.json