<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Modifier Adhérent</title>
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
	rel="stylesheet" />
<link rel="stylesheet" href="assets/css/tailwind.output.css" />
<script
	src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js"
	defer></script>
<script src="assets/js/init-alpine.js"></script>
</head>
<body>
	<div class="flex h-screen bg-gray-50 dark:bg-gray-900"
		:class="{ 'overflow-hidden': isSideMenuOpen}">
		<c:import url="Menu/sideMenuAdherent.jsp"></c:import>
		<div class="flex flex-col flex-1">
			<c:import url="Menu/headerMenu.jsp" />
			<main class="h-full pb-16 overflow-y-auto">
				<form method="POST" action="<c:url value="/modifierAdherent"/>">
					<c:if test="${successMsg !=null}">
						<div
							class="bg-green-100 border-l-4 border-green-500 text-green-700 p-4"
							role="alert">
							<p class="font-bold">Succès</p>
							<p>
								<c:out value="${successMsg}" />
							</p>
						</div>
					</c:if>
					<c:if test="${erreurMsg != null}">
						<div class="bg-red-100 border-l-4 border-red-500 text-red-700 p-4"
							role="alert">
							<p class="font-bold">Erreur</p>
							<p>
								<c:out value="${erreurMsg}" />
							</p>
						</div>
					</c:if>
					<div class="container px-6 mx-auto grid">
						<!-------- Titre --------->
						<h2
							class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">
							Modifier un adhérent</h2>

						<!-- ------------ -->
						<h4
							class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300">
							Informations Standard</h4>

						<div
							class="px-4 py-3 mb-8 bg-white rounded-lg shadow-md dark:bg-gray-800">
							<input type="text" hidden="hidden" name="idAdherent"
								value="<c:out value="${adherent.idAdherent}"/>" />
							<label class="block mt-3 text-sm"> <span
								class="text-gray-700 dark:text-gray-400">Nom</span> <input
								type="text" name="nomAdherent"
								class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
								placeholder="Nom" value="<c:out value="${adherent.nom}"/>" />
								<span class="text-xs text-red-600 dark:text-red-400"> <c:out
										value="${erreurs['nomAdherent']}" />
							</span>
							</label> <label class="block mt-4 text-sm"> <span
								class="text-gray-700 dark:text-gray-400">Prenom</span> <input
								type="text" name="prenomAdherent"
								class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
								placeholder="Prenom" value="<c:out value="${adherent.prenom}"/>" />
								<span class="text-xs text-red-600 dark:text-red-400"> <c:out
										value="${erreurs['prenomAdherent']}" />
							</span>
							</label> <label class="block mt-4 text-sm"> <span
								class="text-gray-700 dark:text-gray-400">Email</span> <input
								type="text" name="emailAdherent"
								class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
								placeholder="*******@gmail.com"
								value="<c:out value="${adherent.email}"/>" />
								<span class="text-xs text-red-600 dark:text-red-400"> <c:out
										value="${erreurs['emailAdherent']}" />
							</span>
							</label> <label class="block mt-4 text-sm"> <span
								class="text-gray-700 dark:text-gray-400">Mot
									de passe</span> <input type="text" name="motDePasseAdherent"
								class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
								placeholder="" value="<c:out value="${adherent.motDePasse}"/>" />
								<span class="text-xs text-red-600 dark:text-red-400"> <c:out
										value="${erreurs['motDePasseAdherent']}" />
							</span>
							</label> <label class="block mt-4 text-sm"> <span
								class="text-gray-700 dark:text-gray-400">CIN</span> <input
								type="text" name="cinAdherent"
								class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
								placeholder="CIN" value="<c:out value="${adherent.cin}"/>" />
								<span class="text-xs text-red-600 dark:text-red-400"> <c:out
										value="${erreurs['cinAdherent']}" />
							</span>
							</label> <label class="block mt-4 text-sm"> <span
								class="text-gray-700 dark:text-gray-400">Lieu
									de naissance</span> <input type="text" name="lieuNaissanceAdherent"
								class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
								placeholder=""
								value="<c:out value="${adherent.lieuNaissance}"/>" />
								<span class="text-xs text-red-600 dark:text-red-400"> <c:out
										value="${erreurs['lieuNaissanceAdherent']}" />
							</span>
							</label> <label class="block mt-4 text-sm"> <span
								class="text-gray-700 dark:text-gray-400">Date
									de naissance</span> <input type="date" name="dateNaissanceAdherent"
								class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
								placeholder=""
								value="<c:out value="${adherent.dateNaissance}"/>" />
								<span class="text-xs text-red-600 dark:text-red-400"> <c:out
										value="${erreurs['dateNaissanceAdherent']}" />
							</span>
							</label> <label class="block mt-4 text-sm"> <span
								class="text-gray-700 dark:text-gray-400">Numéro
									de téléphone</span> <input type="text" name="telephoneAdherent"
								class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
								placeholder="" value="<c:out value="${adherent.telephone}"/>" />
								<span class="text-xs text-red-600 dark:text-red-400"> <c:out
										value="${erreurs['telephoneAdherent']}" />
							</span>
							</label> <label class="block mt-4 text-sm"> <span
								class="text-gray-700 dark:text-gray-400">Adresse</span> <input
								type="text" name="adresseAdherent"
								class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
								placeholder="" value="<c:out value="${adherent.adresse}"/>" />
								<span class="text-xs text-red-600 dark:text-red-400"> <c:out
										value="${erreurs['adresseAdherent']}" />
							</span>
							</label> <label class="block mt-4 text-sm"> <span
								class="text-gray-700 dark:text-gray-400">Date
									d'adhésion</span> <input type="date" name="dateadhesionAdherent"
								class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
								placeholder="" value="<c:out value="${adherent.dateAdhesion}"/>" />
								<span class="text-xs text-red-600 dark:text-red-400"> <c:out
										value="${erreurs['dateadhesionAdherent']}" />
							</span>
							</label> <label class="block mt-4 text-sm"> <span
								class="text-gray-700 dark:text-gray-400">Profession</span>
								<input type="text" name="professionAdherent"
								class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
								placeholder="" value="<c:out value="${adherent.profession}"/>" />
								<span class="text-xs text-red-600 dark:text-red-400"> <c:out
										value="${erreurs['professionAdherent']}" />
							</span>
							</label> <label class="block mt-4 text-sm"> <span
								class="text-gray-700 dark:text-gray-400">Image</span> <input
								type="file" name="photoAdherent" class="block mt-2"
								placeholder="" />
							</label>

							<div class="mt-4 mb-4 text-sm">
								<span class="text-gray-700 dark:text-gray-400"> Sexe </span>
								<div class="mt-2">
									<label
										class="inline-flex items-center text-gray-600 dark:text-gray-400">
										<input type="radio"
										class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
										name="sexeAdherent" value="Femme" checked="checked" />
										<span class="ml-2">Femme</span>
									</label> <label
										class="inline-flex items-center ml-6 text-gray-600 dark:text-gray-400">
										<input type="radio"
										class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
										name="sexeAdherent" value="homme" /> <span
										class="ml-2">Homme</span>
									</label>
								</div>
							</div>
						</div>

						<!-- Fonction -->
						<h4
							class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300">
							Fonction</h4>
						<div
							class="px-4 py-3 mb-8 bg-white rounded-lg shadow-md dark:bg-gray-800">
							<input type="text" hidden="hidden" name="idLigneFonction"
								value="<c:out value="${adherent.ligneFonction.idLigneFonction}"/>" />
							<label class="block mt-4 text-sm"> <span
								class="text-gray-700 dark:text-gray-400"> Role </span> <select
								name="roleFonction"
								class="block w-full mt-1 text-sm dark:text-gray-300 dark:border-gray-600 dark:bg-gray-700 form-select focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray">
									<option><c:out
											value="${adherent.ligneFonction.fonction.role}" /></option>
									<option>President(e)</option>
									<option>Tresorier</option>
									<option>Secretaire</option>
									<option>Autre</option>
							</select> <span class="text-xs text-red-600 dark:text-red-400"> <c:out
										value="${erreurs['roleFonction']}" />
							</span>
							</label> <label class="block mt-4 text-sm"> <span
								class="text-gray-700 dark:text-gray-400">Date
									Début</span> <input type="date" name="dateDebutLigneFonction"
								class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
								placeholder=""
								value="<c:out value="${adherent.ligneFonction.dateDebut}"/>" />
								<span class="text-xs text-red-600 dark:text-red-400"> <c:out
										value="${erreurs['dateDebutLigneFonction']}" />
							</span>
							</label> <label class="block mt-4 text-sm"> <span
								class="text-gray-700 dark:text-gray-400">Date Fin</span> <input
								type="date" name="dateFinLigneFonction"
								class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
								placeholder=""
								value="<c:out value="${adherent.ligneFonction.dateFin}"/>" />
								<span class="text-xs text-red-600 dark:text-red-400"> <c:out
										value="${erreurs['dateFinLigneFonction']}" />
							</span>
							</label> <label class="bl ock mt-4 text-sm">
						</div>

						<div class="mb-8">
							<input type="submit" value="Valider"
								class="px-5 py-3 font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple" />
						</div>
					</div>
				</form>
			</main>

		</div>
	</div>
</body>
</html>
