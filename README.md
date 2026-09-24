# product-management

API CRUD complète : Spring Boot + Spring Data JPA + PostgreSQL, avec
pipeline CI/CD (GitHub Actions).

## Endpoints

| Méthode | URL                     | Description              |
|---------|-------------------------|---------------------------|
| GET     | /api/products           | Liste tous les produits   |
| GET     | /api/products/{id}      | Récupère un produit       |
| POST    | /api/products           | Crée un produit           |
| PUT     | /api/products/{id}      | Met à jour un produit     |
| DELETE  | /api/products/{id}      | Supprime un produit       |

Exemple de body pour POST/PUT :
```json
{
  "name": "Clavier mécanique",
  "description": "Switches rouges",
  "price": 299.00,
  "quantity": 15
}
```

## Lancer en local

**Option 1 — avec Docker Compose (app + PostgreSQL) :**
```bash
docker-compose up --build
```

**Option 2 — PostgreSQL déjà installé en local :**
Adapte `src/main/resources/application.properties` si besoin, puis :
```bash
mvn spring-boot:run
```

## Tests

Les tests (`ProductServiceTest`) utilisent Mockito, donc aucune base de
données réelle n'est nécessaire pour les lancer :
```bash
mvn test
```

## Pipeline CI/CD

Le fichier `.github/workflows/ci.yml` :
1. **Build & Test** : compile et lance les tests à chaque push/PR sur `main`
2. **Build image Docker** : construit l'image (dépend du job précédent)

### Mettre en place

```bash
git init
git add .
git commit -m "Initial commit: CRUD Spring Boot + PostgreSQL + CI/CD"
git branch -M main
git remote add origin <URL_DE_TON_REPO>
git push -u origin main
```

Va ensuite dans l'onglet **Actions** de ton repo pour voir le pipeline
tourner.

## Pour aller plus loin

- Ajouter `docker push` vers Docker Hub/ghcr.io (secrets GitHub)
- Ajouter la pagination sur `GET /api/products`
- Ajouter Swagger/OpenAPI (`springdoc-openapi`)
- Déploiement automatique (Render, Railway, ou VPS via SSH)
