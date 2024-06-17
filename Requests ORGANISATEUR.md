###
POST http://localhost:9000/api/organisateur
Content-Type: application/json

{
"nom": "Organisateur TEST",
"prenom": "Organisateur TEST",
"email": "Organisateur TEST",
"roleOrganisateur": false
}

<> 2024-06-17T140740.200.json

###
GET http://localhost:9000/api/organisateur/id/1

<> 2024-06-17T140902.200.json

###
GET http://localhost:9000/api/organisateur/prenomNom/Organisateur_1/Organisateur_1

<> 2024-06-17T140958.200.json

###
GET http://localhost:9000/api/organisateur/all

<> 2024-06-17T141017.200.json

###
DELETE http://localhost:9000/api/organisateur/delete/id/56

<> 2024-06-17T141059.200.txt

###
POST http://localhost:9000/api/organisateur/null
Content-Type: application/json

{
"nom": "orga null",
"prenom": "orga null",
"email": "organull",
"roleOrganisateur": true
}

<> 2024-06-17T141143.200.json

###
GET http://localhost:9000/api/organisateur/statistique/Organisateur_1/Organisateur_1

<> 2024-06-17T141225.200.json

###
POST http://localhost:9000/api/organisateur/resultat
Content-Type: application/json

{
"idEpreuve": 1,
"idParticipant": 1,
"temps": 0,
"position": 3
}

<> 2024-06-17T142042.200.json

###
GET http://localhost:9000/api/organisateur/resultat/all

<> 2024-06-17T142057.200.json

###
DELETE http://localhost:9000/api/organisateur/resultat/delete/54

<> 2024-06-17T142114.200.txt

###
GET http://localhost:9000/api/organisateur/resultat/id/1

<> 2024-06-17T142324.200.json

###
GET http://localhost:9000/api/organisateur/classement

<> 2024-06-17T142335.200.json

###
POST http://localhost:9000/api/organisateur/delegation
Content-Type: application/json

{
"idInfrastructureSportive": 1,
"nom": "Delegation 3",
"nbMedailleOr": 0,
"nbMedailleArgent": 0,
"nbMedailleBronze": 0
}

<> 2024-06-17T142458.200.json

###
GET http://localhost:9000/api/organisateur/delegation/id/54

<> 2024-06-17T142517.200.json

###
GET http://localhost:9000/api/organisateur/delegation/nom/Delegation 3

<> 2024-06-17T142536.200.json

###
GET http://localhost:9000/api/organisateur/delegation/all

<> 2024-06-17T142548.200.json

###
DELETE http://localhost:9000/api/organisateur/delegation/delete/54

<> 2024-06-17T142608.200.txt


###
POST http://localhost:9000/api/organisateur/participant
Content-Type: application/json

{
"nom": "Participant 3",
"prenom": "Participant 3",
"email": "Participant 3",
"delegation": {
"idInfrastructureSportive": 1,
"nom": "Delegation 1",
"nbMedailleOr": 1,
"nbMedailleArgent": 0,
"nbMedailleBronze": 1
},
"epreuveList": []
}

<> 2024-06-17T143705.200.json

###
GET http://localhost:9000/api/organisateur/participant/id/54

<> 2024-06-17T143723.200.json

###
GET http://localhost:9000/api/organisateur/participant/prenomNom/Participant 3/Participant 3

<> 2024-06-17T143744.200.json

###
GET http://localhost:9000/api/organisateur/participant/all

<> 2024-06-17T143754.200.json

###
DELETE http://localhost:9000/api/organisateur/participant/delete/54

<> 2024-06-17T143810.200.txt

###
POST http://localhost:9000/api/organisateur/participant/null
Content-Type: application/json

{
"nom": "Participant 3",
"prenom": "Participant 3",
"email": "Participant 3",
"delegation": {},
"epreuveList": []
}

<> 2024-06-17T143853.200.json

###
POST http://localhost:9000/api/organisateur/epreuve
Content-Type: application/json

{
"nomEpreuve": "Epreuve 3",
"dateEpreuve": "1970-01-01T00:00:00.009+00:00",
"nbPlacesDispo": 2,
"nbPlacesInit": 2,
"prixBillet": 200,
"billets": [],
"infrastructureAccueil": {
"idInfrastructureSportive": 1,
"nom": "Infrastructure 1",
"adresse": "Adresse infrastructure 1",
"capacite": 2
}
}

<> 2024-06-17T144243.200.json

###
PUT http://localhost:9000/api/organisateur/epreuve/modifier/Epreuve 3
Content-Type: application/json

{
"nomEpreuve": "Epreuve PUT 3",
"dateEpreuve": "1970-01-01T00:00:00.009+00:00",
"nbPlacesDispo": 2,
"nbPlacesInit": 2,
"prixBillet": 200
}

<> 2024-06-17T144403.200.json

###
DELETE http://localhost:9000/api/organisateur/epreuve/delete/54

<> 2024-06-17T144434.200.txt

###
GET http://localhost:9000/api/organisateur/epreuve/id/1

<> 2024-06-17T144453.200.json

###
GET http://localhost:9000/api/organisateur/epreuve/nom/Epreuve 1

<> 2024-06-17T144512.200.json

###
POST http://localhost:9000/api/organisateur/epreuve/null
Content-Type: application/json

{
"nomEpreuve": "EpreuveNULL",
"dateEpreuve": "1970-01-01T00:00:00.009+00:00",
"nbPlacesDispo": 1,
"nbPlacesInit": 1,
"prixBillet": 900
}

<> 2024-06-17T144602.200.json

###
POST http://localhost:9000/api/organisateur/infrastructure
Content-Type: application/json

{
"nom": "Infra",
"adresse": "Infra",
"capacite": 100
}

<> 2024-06-17T144730.200.json

###
GET http://localhost:9000/api/organisateur/infrastructure/id/54

<> 2024-06-17T144747.200.json

###
GET http://localhost:9000/api/organisateur/infrastructure/nom/Infra

<> 2024-06-17T144805.200.json

###
GET http://localhost:9000/api/organisateur/infrastructure/all

<> 2024-06-17T144813.200.json

###
DELETE http://localhost:9000/api/organisateur/infrastructure/delete/54

<> 2024-06-17T144833.200.txt

###
POST http://localhost:9000/api/organisateur/billet
Content-Type: application/json

{
"idEpreuve": 1,
"idSpectateur": 2,
"prix": 300,
"disponible": false,
"dateBillet": "1970-01-01T00:00:00.010+00:00"
}

<> 2024-06-17T144957.200.json

###
GET http://localhost:9000/api/organisateur/billet/id/1

<> 2024-06-17T144938.200.json

###
DELETE http://localhost:9000/api/organisateur/billet/delete/58

<> 2024-06-17T145049.200.txt

###
GET http://localhost:9000/api/organisateur/billet/all

<> 2024-06-17T145101.200.json

###
GET http://localhost:9000/api/organisateur/scanner/Controlleur_1/Controlleur_1/1/1

<> 2024-06-17T145655.200.txt

