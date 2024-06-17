
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

### ATTENTION erreur si date d'inscription < 10j
GET http://localhost:9000/api/spectateur/reservationBillet/prenomNomNomEpreuve/Spectateur_2/Spectateur_2/Epreuve 1


### ATTENTION erreur si date d'annulation < 3j
GET http://localhost:9000/api/spectateur/annulationBillet/prenomNomNomIdBillet/Spectateur_1/Spectateur_1/3


###
GET http://localhost:9000/api/spectateur/billet/id/1

<> 2024-06-17T152041.200.json

###
GET http://localhost:9000/api/spectateur/classement

<> 2024-06-17T152131.200.json