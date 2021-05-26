Fichier rédigé le 08/07/2011 à Nancy par Sonia Ben Ticha

mises à jour :18/07/2011 à Nancy,
 25/01/2012 à Nancy
 02/04/2013 à Tunis
 06/06/2013 à Nancy
07/07/2014


Les films sélectionnés sont les films résultats de l'intersection de MouvieLens 1M (http://www.grouplens.org/node/12) avec 
les films de HetRec2011 (http://ir.ii.uam.es et http://ir.ii.uam.es/hetrec2011) et vérifiant :
   - les films ayant une origine se trouvant dans l'ontologie (extrait de W3C)
   - les films ayant un réalisateur 
   - les fimls ayant des acteurs (rang 1 et 2)
   - les users ayant effectué au moins 20 votes 
   - les votes sont ceux du jeu MovieLen 1M 
   

Le jeu de données a été généré à partir de la BD access ML1MHetRec2011 et plus précisément les  macros : 
le fichier des votes est trié de facon aléatoire


Les identifiants ne sont pas ceux de MovieLens mais des identifiants générés par ACCESS pour ne pas avoir de trous
	nb user: 6020, identifiant NumSeqUser variant entre 1 et 6027
	nb films:3552 , identifiant numSeqMovie variant entre 1 et 3559
	nb genres : 19 identifiant GenreID (celui de Movie Lens)
	nb origine : 44, identifiant NumSeqOrigine variant de 1 à 44 ou origineontologie={feuilleOntologie} inter { origineontologie des films}
	nb de votes:  992071 votes
	nb director: 1825, identifiant NumSeqDirector 
	nb acteurs : 4237, identifiant NumSeqActor variant de 1 à 4237, seulement les acteurs de rang 1 ou 2 dans les films

ce jeu contient des films n'ayant pas de tags	

REMARQUES:
	1)Les fichiers ayant ut20base dans leur nom doivent être générés après la séparation de la base de données des votes en Apprentissage et Test.
	2) Les fichiers ne contiennent pas une 1ère ligne décrivant le format du fichier, sauf si c'est mentionné

	
Les fichiers:
* Export_RatingSeqAleat.txt:  fichiers des votes (rating) correspondant à la table Export_RatingSeqAleat, est trié de façon aléatoire sur NumSeqUser pour pouvoir le diviser en deux jeux: apprentissage(80%)
 et test(20%) 
  	Format ---> <userNumSeq \t MovieNumseq \t Rating \t TimeStamp \t UserId \t MovieId> , userId et MovieId sont les identifiants de MovieLens1M
	
* Export_RatingML1MHetRecNumSeqTrie.txt:  fichiers des votes (rating) est trié par ordre croissant des UserNumSeq et ordre décroissant de timestamps, 
donc pour chaque user on a la liste de ses votes par ordre chronologique décroissant	
	Format ---> <userNumSeq \t MovieNumseq \t Rating \t TimeStamp \t UserId \t MovieId> trié par ordre croissant de UserNumSeq et décroissant TimeStamp=> pour chaque user on a la liste chronologique de ses votes
	
* Export_MovieGenreNumSeq.txt: les genres de chaque film, est généré par la requête : Export_MovieGenreNumSeq, trié par NumSeqMovie(item)
	Format ---> <NumSeqMovie \t GenreID  \t weight \t movieID>
	
* Export_MovieGenreNumSeqUt20base.txt: le nombre de votes par genre à partir de la base d'apprentissage Ut20.base, est généré par la requête : Export_MovieGenreNumSeqUt20base, trié par NumSeqMovie(item)
	Format ---> <MovieNumSeq \t GenreNumSeq	\t NombVotes  \t weight \t	GenreID	\t movieID> la 1ère ligne contient le format du fichier
	
* Export_MovieOrigineNumSeq.txt: les origines des films, est généré par la requête : Export_MovieOrigineNumSeq; trié par NumSeqMovie(item)
	Format ---> <NumSeqMovie  \t NumSeqOrigine  \t weight \t origineOntologie  \t movieID>

*Export_MovieOrigineNumSeqUt20Base.txt:nb de votes par origine à partir de la base d'apprentissage Ut20.base : Export_MovieOrigineNumSeqUt20Base; trié par NumSeqMovie(item)
	Format ---> <NumSeqMovie  \t NumSeqOrigine  \t NombVotes  \t weight \t origineOntologie \t movieID>  la 1ère ligne contient le format du fichier
	
* Export_MovieDirectorNumSeq.txt: les réalisateurs des films, est généré par la requête : Export_MovieDirectorNumSeq; trié par NumSeqMovie(item)
	Format ---> <NumSeqMovie  \t NumSeqDirector \t weight \t movieID \t DirectorID> , weight=1,sans ligne avec nom colonnes

*EExport_MovieDirectNumSeqUt20Base.txt: nb de votes par réalisateurs des films, est généré par la requête : Export_MovieDirectorNumSeqUt20Base; trié par NumSeqMovie(item)
	Format ---> <NumSeqMovie  \t NumSeqDirector \t NombVotes  \t weight \t movieID \t DirectorID> la 1ère ligne contient le format du fichier
	
* Export_MovieActorNumSeq.txt: les acteurs de rang 1 et 2 des films, est généré par la requête : Export_MovieActorNumSeq; trié par NumSeqMovie(item)
	Format ---> <NumSeqMovie  \t NumSeqActor \t weight \t movieID \t ActorID \t Ranking> sans ligne avec nom colonnes, weight=1	


* Export_MovieActorNumSeqUt20Base.txt: les acteurs de rang 1 et 2 des films, est généré par la requête : Export_MovieActorNumSeqUt20Base; trié par NumSeqMovie(item)
	Format ---> <NumSeqMovie  \t NumSeqActor \t NombVotes \t weight \t movieID \t ActorID \t Ranking> 1ere ligne avec nom colonnes, weight=1	
	
* Export_MoviekeyWordNumSeq.txt:  keyword des films est généré par la requête : Export_MovieKeyWordNumSeq; trié par NumSeqMovie(item)
	Format ---> <NumSeqMovie  \t NumSeqKeyWord \t weight \t movieID \t KeyWordID > sans ligne avec nom colonnes, weight=1		
	
*Export_MovieTagNumSeqUt20Base.txt:  tag des films est généré par la requête : Export_MovieTagNumSeqUt20Base; trié par NumSeqMovie(item)
	Format ---> <NumSeqMovie  \t NumSeqTag  \t NombVotes \t weight \t movieID \t TagID > sans ligne avec nom colonnes, weight=1		
	
* Export_MovieOrigineNbVotes.txt : le fichier origine de chaque film, seule les origines figurant dans l'ontologie sont pris en compte
 il est généré à partir de la requête Export_MovieOrigineNbVotes et est trié par origineOntologie
	Format ---> <NumSeqMovie \t origineOntologie \t nbVotes \t NumSeqOrigineOntologie\t MovieId> trié par origineOntologie, sans ligne avec nom colonnes
	
*Export_MovieOrigineNbVotes_uT20base.txt : le fichier origine de chaque film avec le nb de votes par film, fichier extrait à partir de la base d'apprentissage
uT20.base (renomé uT20base.txt pour l'imporation  dans ACCESS)
 il est généré à partir de la requête Export_MovieOrigineNbVotes_uT20base et est trié par origineOntologie.
 Remarque : ce fichier ne peut être généré qu'après avoir généré le fichier uT20.base voir ci-dessous
 
	Format ---> <NumSeqMovie \t origineOntologie \t nbVotes \t NumSeqOrigineOntologie\t MovieId> trié par origineOntologie	
	
*Export_OntologieNbfils.txt	: le fichier contenant l'ontologie de W3C
	Format ----> <codeNoeud \t Libelle \t NoeudAscendant \t nbFils \t codePays \t type> 
	avec type=F: feuille; type=N:noeud, ascendant=-1 alors racine. Seuls les origines des films se trouvant dans Export_MovieOrigineNbVotes.txt sont présents
	dans ce fichier
	
*Export_OntologieTotNbfils.txt	: le fichier contenant l'ontologie de W3C. N'est pas utilisé dans les algos
	Format ----> <codeNoeud \t Libelle \t NoeudAscendant \t nbFils \t codePays \t type> 
	avec type=F: feuille; type=N:noeud, ascendant=-1 alors racine. Toute l'ontologie est représentée, même des origines de pays ne figurant 
	pas dans Export_MovieOrigineNbVotes.txt
	
* NbUserRatings.txt :nb de votes par users. Utilisé pour la génération de la base apprentissage et test à partir du fichier Rating
	Format ----> NumSeqUser;nbVotes

* Export_MovieNumSeq.txt:fichier de correspondance entre l'ID film de movieLens1M et numSeqMovie généré à partir de la table Export_MovieNumSeq
	Format ----> NumSeqMovie \t IdMovieLens   	la 1ère ligne contient le format du fichier.
	
* Export_OrigineNumSeq.txt : fichier de correspondance entre  numSeqOrigine OrigineOntologie; origine est le code pays
	Format ----> NumSeqOrigine \t OrigineOntologie \t origine		la 1ère ligne contient le format du fichier.
	
* Export_UserNumSeq.txt: fichier de correspondance entre usedId de MovieLens 1M et NumSeqUser
	Format ----> NumSeqUser \t IdUserMovieLens
	
*Export_ActorNumSeq.txt: fichier de correspondance entre NumSeqActor et AcorID, Table Export_ActorNumSeq
	Format ---> <NumSeqActor \t ActorID >	la 1ère ligne contient le format du fichier. 

*Export_DirectorNumSeq.txt: fichier de correspondance entre NumSeqDirector et DirectorID, Table Export_DirectorNumSeq
	Format ---> <DirectorNumSeq \t DirectorID >	la 1ère ligne contient le format du fichier. 
	

*Export_TagNumSeq.txt: fichier de correspondance entre NumSeqTag et TagID, Table Export_TagNumSeq
	Format ---> <TagNumSeq \t TagID \t valeurTag>	la 1ère ligne contient le format du fichier. 

		
	
-------------------------------------  Création base apprentissage et base test à partir de base des votes ------------------------	
allbut.pl  -- The script that generates training and test sets where (perl)
              all but n  of a users ratings are in the training data.
			  
allbut20.pl: permet de générer un jeu apprentissage et test avec le jeu test contenant 20% des votes de chaque user (perl)	


*********** A partir du fichier 	Export_RatingSeqAleat.txt (trié aléatoirement) : génère 5 jeux u1 à u5 pour une validation croisée*****************
u1.base    -- The data sets u1.base and u1.test through u5.base and u5.test
u1.test       are 80%/20% splits of the Export_RatingSeqAleat.txt into training and test data.
u2.base       Each of u1, ..., u5 have disjoint test sets; this if for
u2.test       5 fold cross validation (where you repeat your experiment
u3.base       with each training and test set and average the results).
u3.test       These data sets can be generated from Export_RatingSeqAleat.txt by GenererJeuxAleat.sh.
u4.base
u4.test
u5.base
u5.test


ua.base    -- The data sets ua.base, ua.test, ub.base, and ub.test
ua.test       split the u data into a training set and a test set with
ub.base       exactly 10 ratings per user in the test set.  The sets
ub.test       ua.test and ub.test are disjoint.  These data sets can
              be generated from Export_RatingSeqAleat.txt by GenererJeuxAleat.sh.

u20.base   -- le jeu de données Export_RatingSeqAleat.txt est divisé en u20.base (apprentissage) et u20.test(test)
u20.test	 le fichier test u20.test contient 20% des votes de chaque user, ce jeu est généré par le script perl allbut20.pl en utilisant le fichier
			Export_NbUserRatings.txt qui donne le nb de votes par user



GenererJeuxAleat.sh     -- A shell script pour la générations de tous les jeux de données à partir du fichier des votes Export_RatingSeqAleat.txt.



**********A partir du fichier des votes trié Export_RatingML1MHetRecNumSeqTrie.txt***************************

GenererJeuxTrie.sh     --  A shell script pour la générations de tous les jeux de données à partir du fichier des votes  Export_RatingML1MHetRecNumSeqTrie.txt

Pour chaque user, les votes du  fichier test (.test) sont postérieurs à ceux du fichier apprenstissage (.base) puisqu'ils sont ordonnés selon 
un ordre décroissant du timestamps

uT.base    -- The data sets exactly 10 ratings per user in the test set.  These data sets can
              be generated from Export_RatingSeqTrie.txt by GenererJeuxT.sh. (équivalent à ua.* dans MovieLens100k)
uT.test			  

uT20.base   -- le jeu de données Export_RatingSeqTrie.txt est divisé en u20.base (apprentissage) et u20.test(test)
uT20.test	 le fichier test u20.test contient 20% des derniers votes effectués par chaque user, ce jeu est généré par le script perl allbut20.pl 
			à partir du fichier de vote Export_RatingSeqTrie.txt en utilisant le fichier NbUserRatings.txt qui donne le nb de votes par user	

	
	
