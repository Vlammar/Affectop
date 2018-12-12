# Affectop
Travail projet gestion des affectations des options.
Ce programme est écrit en JAVA, J2EE, HTML.

## Version 1.0

## Nouveautées
Début de fonctionnalité

## Utilisation 
Utilisation de base :
Le service Affectop comprends deux utilisateurs différents : Etudiants, Professeur.

Manuel d'utilisation pour les étudiants :
Après avoir reçu un mail avec un token unique attribué, l'étudiant arrivera sur la page d'accueil du site réservée aux élèves. Il aura alors un aperçu des options disponibles ainsi que leurs description, qu'il devra classer par ordre de préférence, et valider ses choix. Ceux-ci seront alors envoyés dans la base de donnée afin d'être utilisés par l'algorithme d'affectation des options dans un second temps.

Manuel d'utilisation pour le professeur :
Après avoir reçu un mail avec un token unique attribué, l'enseignant arrivera sur la page d'accueil du site réservée aux enseignants. L'enseignant concerné devra alors fournir une liste d'élèves sous forme d'un fichier XLSX qui devra respecter le format suivant:

Nom | Prenom | Carte | Etape | Nom de l'étape | VET | Année | Courriel

Une fois la liste d'élèves ajoutée, il passera à la page suivante où il devra ajouter les options en spécifiant le nombre de groupes de l'option concernée, le nom de l'option, une description de l'option, ainsi que le mail de l'enseignant responsable de l'option. Ensuite, une fois les options concernées ajoutées, l'enseignant devra pour chaque élève si une option a déjà été validée ou pas ( notamment pour les élèves redoublants ). Il arrivera ensuite sur la page récapitulatif avec la liste des élèves concernés par l'option, la liste des options, ainsi que le mail modifiable par le professeur qui sera envoyé aux élèves afin qu'ils puissent trier les options par ordre de préférence. Enfin, on arrive sur la page aperçu qui sera mise à jour à chaque fois qu'un élève validera ses préférences d'options. Une fois que tous les élèves ont mis leurs préférences, l'enseignant pourra alors lancer l'algorithme d'affectation. Quand l'algorithme aura attribué automatiquement les options à chaque élève, l'enseignant pourra alors écrire et envoyer un mail à chaque étudiant et au secrétariat afin de confirmer l'affectation des options.


## Installation
Installation de tomcat
Prérequis:

JDK 11
Serveur Ubuntu 16.04
Création de l'utilisateur Tomcat
$ sudo groupadd tomcat
$ sudo useradd -s /bin/false -g tomcat -d /opt/tomcat tomcat

Tomcat installation
$ cd /tmp
$ curl -O <adresse du tar.gz>

Installation de tomcat dans /opt/tomcat $ sudo mkdir /opt/tomcat
$ sudo tar xzvf <nom du fichier>.tar.gz -C /opt/tomcat --strip-components=1

Mise à jour des permissions
$ cd /opt/tomcat
Donne les droits de groupe à tomcat
$ sudo chgrp -R tomcat /opt/tomcat
$ sudo chmod -R g+r conf
$ sudo chmod g+x conf
$ sudo chown -R tomcat webapps/ work/ temp/ logs/

Systemd service file
Mise à jour de jdk
$ sudo update-java-alternatives -l

Changer la ligne Environment=JAVA_HOME= par la path vers la version de jdk par exemple /usr/lib/jvm/java-1.11.0-openjdk-amd64
$ sudo nano /etc/systemd/system/tomcat.service
Recharger le deamon
$ sudo systemctl daemon-reload
Démarrer le service Tomcat $ sudo systemctl start tomcat Vérifier si le status est running $ sudo systemctl status tomcat

Parefeu
Tomcat utilise le port :8080 $ sudo ufw allow 8080

Vérifier si l'accès est possible http://server_domain_or_IP:8080 $ sudo systemctl enable tomcat

Installation de mysql
$ sudo apt-get install mysql-server``` Démarrer mysql $ systemctl start mysqlAutoriser le lancement de mysql au reboot$ systemctl enable mysql```

## Prérequis
- `JAVA 8`ou plus récent
- serveur tomcat9 ou +
- mysql

## Crédits
Auteurs:
- Valentin JABRE
- Mathieu VALLET
- Mohamed MOKADDEM
- Mohamed CHAABANE

