# Requette possible pour EPREUVE :
### POST :
#### Creer une epreuve : POST http://localhost:9000/api/epreuve
{
    "nomEpreuve": "Epreuve 1",
    "dateEpreuve": "2024-06-06T14:45:30.000+0000",
    "infrastructureAccueil":
    {
        "id": 1,
        "nom": "Test",
        "adresse": "test",
        "capacite": 100,
        "epreuveList": null
    },
    "nbPlacesDispo": 35
}

### GET :
#### Cherhcher epreuve : GET http://localhost:9000/api/epreuve/{{id}}

#### Lister spectateur d'une epreuve : GET http://localhost:9000/api/epreuve/{{id}}/spectateur

# Requette possible pour INFRASTRUCTURESPORTIVE :
### POST :
#### Creer une infrastructure sportive : POST http://localhost:9000/api/infrastructuresportive
{
    "id": 1,
    "nom": "Test",
    "adresse": "test",
    "capacite": 100,
    "epreuveList": null
}

### GET :
#### Chercher une infrastructure sportive : GET http://localhost:9000/api/infrastructuresportive/{{id}}

#### Lister les épreuves d'une infrastructure sportive : GET http://localhost:9000/api/infrastructuresportive/{{id}}/epreuve

# Requette possible pour SPECTATEUR :
### POST :
#### Creer un spectateur : POST http://localhost:9000/api/spectateur
{
    "nom": "RAZANAJATOVO",
    "prenom": "Tsitohaina",
    "email": "testmail"
}

### GET :
#### Chercher un spectateur : GET http://localhost:9000/api/spectateur/{{id}}

#### Supprimer un spectateur : GET http://localhost:9000/api/spectateur/delete/{{id}}

#### Lister les épreuves d'un spectateur : GET http://localhost:9000/api/spectateur/{{id}}/epreuves

# Requette possible pour UTILISATEUR :
### POST :
#### Creer un utilisateur : POST http://localhost:9000/api/utilisateur
{
    "nom": "RAZANAJATOVO",
    "prenom": "Tsitohaina",
    "email": "testmail"
}

### GET :
#### Chercher un utilisateur : GET http://localhost:9000/api/utilisateur/{{id}}
