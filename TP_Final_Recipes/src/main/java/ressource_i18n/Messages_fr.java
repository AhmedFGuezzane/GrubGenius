/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ressource_i18n;

import java.util.ListResourceBundle;

/**
 *
 * @author ahmed
 * This is the internationalization page in French (fr)
 */
public class Messages_fr extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return contents;
    }
    
    private Object[][] contents = 
    {
        // NAVBAR 
        
        {"navbar.categories.title", "Catégories"}, 
        {"navbar.categories.breakfast", "Déjeuners"},
        {"navbar.categories.meals", "Plats principaux"},
        {"navbar.categories.desserts", "Desserts"},
        {"navbar.restrictions", "Restrictions"},
        {"navbar.restrictions.keto", "Keto"},
        {"navbar.restrictions.vegan", "Végétalien"},
        {"navbar.restrictions.glutenFree", "Sans gluten"},
        {"navbar.topPicks", "Sélections de choix"},
        {"navbar.searchBar", "Recherche par ingrédient"},
        {"navbar.language","Langue"},
        {"navbar.loginButton", "Connexion"},
        {"navbar.myAccountButton","Mon compte"},
        
        // NEWSLETTER 
        
        {"newsletter.title","Abonnez-vous à l'info-lettre !"},
        {"newsletter.text","Restez informé : découvrez des recettes de saison, des astuces de cuisine et des critiques d'équipement fiables."},
        {"newsletter.conditions","En fournissant votre email ci-dessus, vous acceptez nos <u>Conditions d'utilisation</u> et notre <u>Politique de confidentialité</u>."},
        {"newsletter.emailPlaceholder","Entrez votre email"},
        {"newsletter.submitButton","Soumettre"},
        
        // INDEX - EXPLORE RECIPES BY CATEGORIES 
        
        {"exploreSection.title","Explorez les recettes par catégorie"},
        {"exploreSection.category1","Hiver"},
        {"exploreSection.category2","Soupes"},
        {"exploreSection.category3","Végétarien"},
        {"exploreSection.category4","Dîner"},
        {"exploreSection.button","Toutes les recettes"},
        
        // RECIPES SEARCH - FILTERS 
        
        
        {"rs.filters.title", "Filtres"},

        {"rs.filters.diet.title", "Régime"},
        {"rs.filters.diet.vegetarian", "Végétarien"},
        {"rs.filters.diet.dairyFree", "Sans produits laitiers"},
        {"rs.filters.diet.glutenFree", "Sans gluten"},
        {"rs.filters.diet.nutFree", "Sans noix"},

        {"rs.filters.meat.title", "Viande"},
        {"rs.filters.meat.chicken", "Poulet"},
        {"rs.filters.meat.beef", "Boeuf"},
        {"rs.filters.meat.salmon", "Saumon"},
        {"rs.filters.meat.lamb", "Agneau"},

        {"rs.filters.vegetables.title", "Légumes"},
        {"rs.filters.vegetables.apple", "Pomme"},
        {"rs.filters.vegetables.tomato", "Tomate"},
        {"rs.filters.vegetables.carrot", "Carotte"},
        {"rs.filters.vegetables.mushrooms", "Champignons"},

        {"rs.filters.type.title", "Type"},
        {"rs.filters.type.dessert", "Dessert"},
        {"rs.filters.type.breakfast", "Petit-déjeuner"},
        {"rs.filters.type.meal", "Repas"},

        // RECIPE PAGE 
        
        {"recipe.addGrocery", "Ajouter à la liste de courses"},
        {"recipe.alreadyInGrocery", "Déjà dans la liste de courses"},
        {"recipe.servings", "Portions"},
        {"recipe.time", "Temps"},
        {"recipe.difficulty", "Difficulté"},
        {"recipe.rating", "Note"},
        {"recipe.ingredients", "Ingrédients"},
        {"recipe.recipe", "Recette"},
        {"recipe.step", "Étape"},
        
        // MY ACCOUNT PAGE
        
        {"myaccount.groceryList", "Liste de courses"},
        {"myaccount.ingredientsList", "Liste d'ingrédients"},
        {"myaccount.email", "E-mail"},
        {"myaccount.status", "Statut"},
        {"myaccount.status.active", "Actif"},
        {"myaccount.status.pending", "En attente d'approbation"},
        {"myaccount.securityQuestion", "Question de sécurité"},
        {"myaccount.securityAnswer", "Réponse de sécurité"},
        {"myaccount.logOut", "Se déconnecter"},
        {"myaccount.grocerySettings", "Paramètres de courses"},
        {"myaccount.grocerySettings.reset.text", "Réinitialiser la liste d'épicerie"},
        {"myaccount.grocerySettings.reset.button", "Réinitialiser"},
        {"myaccount.grocerySettings.print.text", "Imprimer la liste des ingrédients"},
        {"myaccount.grocerySettings.print.button", "Imprimer"},
        {"myaccount.dashboard.button", "Tableau de bord"},
            
            
        //LOGIN PAGE
        {"login.title", "Connexion"},
        {"login.username", "Nom d'utilisateur"},
        {"login.password", "Mot de passe"},
        {"login.button", "Se connecter"},
        {"login.text", "Vous n'avez pas de compte ?"},
        {"login.register", "S'inscrire"},
        {"login.return", "Retour au site principal"},   
        {"login.errorMessage", "Vous avez le mauvais mot de passe. Veuillez réessayer."},  
        {"login.registerSuccess", "Votre compte est en attente d'approbation."}, 
            
        //REGISTER PAGE
        {"register.title", "Inscription"},
        {"register.username", "Nom d'utilisateur"},
        {"register.firstname", "Prénom"},
        {"register.lastname", "Nom de famille"},
        {"register.email", "E-mail"},
        {"register.password", "Mot de passe"},
        {"register.confirm", "Confirmer le mot de passe"},
        {"register.securityQ", "Question de sécurité"},
        {"register.securityA", "Réponse à la question de sécurité"},
        {"register.button", "S'inscrire"},
        {"register.text", "Vous avez déjà un compte ?"},
        {"register.login", "Se connecter"},
        {"register.return", "Retour au site principal"},
        {"register.errorMessage.passwords", "Les mots de passe ne correspondent pas."},  
        {"register.errorMessage.username", "Le nom d'utilisateur que vous avez choisi n'est pas disponible."},  
        {"register.errorMessage.error", "Une erreur inattendue s'est produite, veuillez réessayer."},  

        
        //DASHBOARD PANEL 
        {"panel.title", "GRUBGENIUS"},
        {"panel.dashtitle", "Tableau de bord"},
        {"panel.approve", "Approuver les comptes"},
        {"panel.delete", "Supprimer les comptes"},
        {"panel.add", "Ajouter des comptes"},
        {"panel.create", "Créer une recette"},
        {"panel.deleterecipe", "Supprimer une recette"},
        {"panel.text", "Bienvenue sur le Dashboard"},
        
        //DASHBOARD APPROVE
        {"approve.nav", "Approuver les comptes"},
        {"approve.username", "Nom d'utilisateur"},
        {"approve.firstname", "Prénom"},
        {"approve.lastname", "Nom de famille"},
        {"approve.email", "E-mail"},
        {"approve.actions", "Actions"},
        {"approve.buttonA", "Approuver"},
        {"approve.buttonD", "Supprimer"},

        //DASHBOARD DELETE
        {"delete.nav", "Supprimer un compte"},
        {"delete.username", "Nom d'utilisateur"},
        {"delete.firstname", "Prénom"},
        {"delete.lastname", "Nom de famille"},
        {"delete.email", "E-mail"},
        {"delete.actions", "Actions"},
        {"delete.button", "Supprimer"},

        //DASHBOARD ADD
        {"add.nav", "Créer un compte"},
        {"add.username", "Nom d'utilisateur"},
        {"add.firstname", "Prénom"},
        {"add.lastname", "Nom de famille"},
        {"add.email", "E-mail"},
        {"add.password", "Mot de passe"},
        {"add.confirm", "Confirmer le mot de passe"},
        {"add.securityQ", "Question de sécurité"},
        {"add.securityA", "Réponse à la question de sécurité"},
        {"add.checkbox", "Activer le compte immédiatement"},
        {"add.button", "Créer un compte"},

        //DASHBOARD CREATE
        {"create.nav", "Créer une recette"},

        //DASHBOARD DELETE RECIPE 
        {"deleterecipe.nav", "Supprimer une recette"},
        
    };
    
}
