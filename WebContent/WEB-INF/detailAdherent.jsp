<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Detail du membre</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Informations de l'Adérent</title>
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
	<div class="flex items-center min-h-screen p-6 bg-gray-50">
		<div
			class="flex-1 h-full max-w-4xl mx-auto overflow-hidden bg-white rounded-lg shadow-xl dark:bg-gray-800">
			<div class="flex flex-col overflow-y-auto md:flex-row">
				<div class="flex items-center justify-center p-6 sm:p-12 md:w-1/2">
					<div class="w-full">

						<div class="mb-4">
							<span class="text-gray-700 dark:text-gray-400">Nom : </span>
							<c:out value="${adherent.nom}" />
						</div>

						<div class="mb-4">
							<span class="text-gray-700 dark:text-gray-400">Prenom : </span>
							<c:out value="${adherent.prenom}" />
						</div>

						<div class="mb-4">
							<span class="text-gray-700 dark:text-gray-400">Email : </span>
							<c:out value="${adherent.email}" />
						</div>


						<div class="mb-4">
							<span class="text-gray-700 dark:text-gray-400">CIN : </span>
							<c:out value="${adherent.cin}" />
						</div>

						<div class="mb-4">
							<span class="text-gray-700 dark:text-gray-400">Date
								de naissance : </span>
							<c:out value="${adherent.dateNaissance}" />
						</div>

						<div class="mb-4">
							<span class="text-gray-700 dark:text-gray-400">Lieu
								de naissance : </span> <span class="text-gray-700 dark:text-gray-400"><c:out
									value="${adherent.lieuNaissance}" /></span>
						</div>

						<div class="mb-4">
							<span class="text-gray-700 dark:text-gray-400">Numéro
								de téléphone : </span> <span class="text-gray-700 dark:text-gray-400"><c:out
									value="${adherent.telephone}" /></span>
						</div>

						<div class="mb-4">
							<span class="text-gray-700 dark:text-gray-400">Date
								d'adhésion : </span>
							<c:out value="${adherent.dateAdhesion}" />
						</div>

						<div class="mb-4">
							<span class="text-gray-700 dark:text-gray-400">Profession
								: </span> <span class="text-gray-700 dark:text-gray-400"><c:out
									value="${adherent.profession}" /></span>
						</div>

						<div class="mb-4">
							<span class="text-gray-700 dark:text-gray-400">Sexe : </span>
							<c:out value="${adherent.sexe}" />
						</div>

						<div class="mt-8">
							<button value="load"
								onclick="window.location='<c:url value="/listeDesAdherents"></c:url>'"
								class="flex items-center justify-between px-4 py-2 text-sm font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple">
								<svg class="w-4 h-4 mr-4 -ml-1" fill="currentColor"
									aria-hidden="true" xmlns="http://www.w3.org/2000/svg"
									viewBox="0 0 20 20">
                    <path d="M15 19l-7-7 7-7" clip-rule="evenodd"
										fill-rule="evenodd"></path>
                  </svg>
								<span>retour</span>
							</button>
						</div>
					</div>
				</div>

				<div class="flex items-center justify-center p-6 sm:p-12 md:w-1/2">
					<div class="w-full">
						<div class="mb-4">
							<div class="mb-4">
								<h4
									class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300">
									Adresse de l'adhérent :</h4>
							</div>

							<span class="text-gray-700 dark:text-gray-400">Adresse : </span>
							<c:out value="${adherent.adresse}" />
						</div>

						<h4
							class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300">
							Fonction :</h4>
						<div class="mt-4 mb-4">
							<span class="text-gray-700 dark:text-gray-400">Role : </span>
							<c:out value="${adherent.ligneFonction.fonction.role}" />
						</div>

						<div class="mb-4">
							<span class="text-gray-700 dark:text-gray-400">Date
								de début : </span>
							<c:out value="${adherent.ligneFonction.dateDebut}" />
						</div>
						<div class="mb-4">
							<span class="text-gray-700 dark:text-gray-400">Date
								de fin : </span>
							<c:out value="${adherent.ligneFonction.dateFin}" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>