# ProjetL3
 Gestionnaire de production <br />
L3 Miage, projet de programmation<br />
1 Présentation générale<br />
1.1 Objectif<br />
Ce projet concerne une application dédiée à la gestion de la production d’une usine. L’application devra permettre de connaître les stocks d’une usine, de planifier les achats et surtout de programmer l’activité des différentes
chaînes de production.<br />
1.2 Développement<br />
L’application sera développée de manière incrémentale. La première étape constitue le coeur de l’application,
en particulier la structure de données. Les étapes suivantes concernent l’ajout et l’amélioration de fonctionnalités.
L’application est une application de bureau (par opposition à une application web). Les données seront stockées
sous forme de fichiers et chargés/sauvegardés à la demande de l’utilisateur.<br />
1.3 Client<br />
Les utilisateurs de l’application sont les responsables de production. Leur tâche est de décider de la production
de l’usine en fonction des commandes et des opportunités de vente. L’outil a pour but de les aider à choisir la
meilleure solution de production hebdomadaire. En particulier, il doit leur permettre de tester des programmes
de production et de déterminer l’efficience et l’efficacité de ces programmes.<br />
A plus long terme (hors du cadre de ce projet), l’application pourra intégrer des algorithmes d’optimisation.
2 Principes généraux<br />
L’usine utilise des matières premières et les transforme dans ses chaînes de production pour obtenir des produits. Certains de ces produits peuvent être également utilisés en entrée de certains chaînes de production afin
d’obtenir des produits plus complexes.<br />
Les données nécessaire à l’application sont présentées sous forme de fichiers .csv. Dans un premier temps ces
fichiers contiendront uniquement les informations essentielles. Ces fichiers seront ultérieurement enrichis pour
répondre aux besoins de nouvelles fonctionnalités.<br />
Il est tout à fait possible d’ajouter de nouveaux fichiers si nécessaire au cours du développement de l’application
tout comme de renommer/modifier les fichiers existants, voire d’en modifier le format. Vous êtes également libre
de modifier l’organisation des fichiers.<br />
2.1 Matières premières et produits<br />
Les matières premières et les produits sont dénommés ci-après élément et décrits dans un fichier commun
elements.csv. Les informations initialement utilisées pour chaque élément sont :<br />
— un code unique<br />
— un nom<br />
— une quantité (stock)<br />
— une unité de mesure du stock (litres, unités, kilogrammes. . .)<br />
2.2 Chaînes de production<br />
Les chaînes de productions sont décrites dans un fichier chaines.csv. Chaque chaîne prend une certaine
quantités d’éléments en entrée et produit un (ou plusieurs) élément (possiblement différents) en sortie.<br />
Le fonctionnement productif d’une chaîne est décrit par son niveau d’activation. Lorsque celui ci est de 0, la
chaîne ne produit rien, lorsqu’il est de 1 elle consomme et produit ce qui est donnée dans sa description. Lorsque
le niveau d’activation est de n (non nul), elle consomme et produit n fois plus d’éléments.<br />
Le niveau d’activation est la variable que l’opérateur doit pouvoir modifier pour tester les différentes options
de production.<br />
La chaîne de production est caractérisée par :<br />
— un code unique<br />
— un nom<br />
— liste des éléments en entrée et leurs quantités<br />
— liste des éléments en sortie et leurs quantités<br />
2.3 Achats/Ventes/Demande<br />
Chaque élément peut avoir un prix d’achat et/ou de vente. De plus l’usine peut avoir reçu un certain nombre
de commandes de certains éléments. L’ensemble de ces données est décrit dans le fichier prix.csv.<br />
Il comporte :<br />
— le code de l’élément concerné<br />
— le prix d’achat (NA si achat impossible)<br />
— le prix de vente (NA si l’élément n’est pas à la vente)<br />
— la quantité commandée<br />
2.4 Calculs<br />
Lorsque l’opérateur a entré les niveaux d’activité de chaque chaîne de production, l’application doit fournir un
résultat permettant d’évaluer la production envisagée. Il faudra pour cela donner deux indicateurs : une indicateur
de valeur et un indicateur de commande<br />
Indicateur de valeur Cet indicateur com.example.projetinit.donne une estimation financière de la rentabilité de la production envisagée.Il est calculé comme suit :<br />
— Les achats sont ajoutés au stock.<br />
— Chaque chaîne de production soustrait du stocks les éléments en entrée correspondant à son niveau d’activation choisi.<br />
— Chaque chaîne ajoute aux stocks les élément produits correspondant à son niveau d’activation.<br />
— Les stocks sont maintenant examinés. Si un élément a un stock négatif, la production est marquée comme
impossible<br />
— Si la production est possible, son efficacité est mesurée en ajoutant les valeurs de vente de tous les stocks
disponibles (parmi ceux qui ont un prix de vente) après production et en soustrayant le montant total des
achats à effectuer.<br />
Indicateur de commande Cet indicateur affiche le pourcentage des commandes qui sont satisfaites.<br />
3 Utilisation et ergonomie<br />
3.1 Scénario d’utilisation<br />
Nous présentons ici une exemple d’utilisation typique de l’application dans sa version initiale.<br />
— Ouverture de l’application<br />
— Chargement des données<br />
— Visualisation de l’état des stocks<br />
— Itération d’essais de production :<br />
— saisie des niveaux d’activité des chaînes de production<br />
— saisie des achats<br />
— visualisation du résultat<br />
— Lorsque l’opérateur est satisfait de son résultat, com.example.projetinit.export du résultat (sous la forme de votre choix).<br />
3.2 Ergonomie<br />
Il est nécessaire de faciliter la tâche de l’utilisateur.En particulier les opérations de modification des niveaux
d’activation et des achats doivent pouvoir être réalisée en un minimum d’actions.<br />
Il est souhaitable de minimiser le nombre d’écrans afin que la recherche d’information n’engendre pas un trop
grand nombre d’action de l’utilisateur.<br />
La lisibilité du ou des écrans les plus utilisés est primordiale.<br />
4 Fonctionnalités envisagées<br />
Vous devrez pensez votre projet afin qu’il soit modulaire afin que toute modification impacte un minimum le
code de l’application.<br />
Afin d’orienter les choix lors de la conception de l’application, il est souhaitable de connaître les potentielles
futures fonctionnalités. Ci-dessous se trouve une liste non exhaustive de fonctionnalités envisagées.<br />
-Sauvegarde des plans de production :sauvegarder les niveaux d’activations et les achats permetrait à l’utilisateur de tester plusieurs solutions et de les comparer.<br />
-Sessions : gestions de sessions, regroupant les infomations initiales du projet et le ou les plans de production
envisagés.<br />
-Prise en compte du temps : la production sur chaque chaîne est cadencée. Cela a un impact sur la possibilité
de réaliser effectivement certains programmes de production. Les éléments achetés ne sont également pas livrés
immédiatement.<br />
-Production sur plusieurs semaines : étude de la gestion des stocks sur plusieurs semaines pour une planification à plus long terme. Suivi de l’état des stocks.<br />
-Gestion des différents moyens de stockage : suivant leur nature (liquides, emballés. . .), les différents éléments
sont stockés différemment (en cuve, entreposés). Il faudra également prendre en compte les disponibilités de stockage.<br />
-Historique et indicateurs : mémoriser l’historique des semaines précédentes (stocks et productions) afin d’afficher des indicateurs sur les évolution de production.<br />
-Gestion des personnels qualifiés : le fonctionnement des chaînes de production repose sur la présence de personnel qualifié. Leurs effectifs constituent une contrainte supplémentaire à prendre en compte dans l’évaluation
des options de production.
