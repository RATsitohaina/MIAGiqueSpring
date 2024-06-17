###
GET http://localhost:9000/api/participant/epreuve/all

<> 2024-06-17T145903.200.json

### ERREUR PARTICIPER EPREUVE
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

###
POST http://localhost:9000/api/spectateur
Content-Type: application/json

{
"nom": "Spectateur_3",
"prenom": "Spectateur_3",
"email": "Spectateur_3",
"billets": []
}

<> 2024-06-17T151330.200.json

###
GET http://localhost:9000/api/spectateur/id/1

<> 2024-06-17T151346.200.json

###
GET http://localhost:9000/api/spectateur/prenomNomNom/Spectateur_1/Spectateur_1

<> 2024-06-17T151407.200.json

###
GET http://localhost:9000/api/spectateur/all

<> 2024-06-17T151415.200.json

###
DELETE http://localhost:9000/api/spectateur/delete/54

<> 2024-06-17T151431.200.txt

###
POST http://localhost:9000/api/spectateur/null
Content-Type: application/json

{
"nom": "Spectateur_null",
"prenom": "Spectateur_null",
"email": "Spectateur_null",
"billets": []
}

<> 2024-06-17T151511.200.json

###
GET http://localhost:9000/api/spectateur/epreuve/all

<> 2024-06-17T151520.200.json

### ERREUR RESVATION BILLET
GET http://localhost:9000/api/spectateur/reservationBillet/prenomNomNomEpreuve/Spectateur_2/Spectateur_2/Epreuve 1

<> 2024-06-17T151801.500.json

### ERREUR ANNULATION BILLET
GET http://localhost:9000/api/spectateur/annulationBillet/prenomNomNomIdBillet/Spectateur_1/Spectateur_1/3

<> 2024-06-17T152012.500.json

###
GET http://localhost:9000/api/spectateur/billet/id/1

<> 2024-06-17T152041.200.json

###
GET http://localhost:9000/api/spectateur/classement

<> 2024-06-17T152131.200.json