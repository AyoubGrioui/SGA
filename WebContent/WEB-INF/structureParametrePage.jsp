<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Structure de l'association</title>
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
	rel="stylesheet" />
<link rel="stylesheet" href="assets/css/tailwind.output.css" />
<script
	src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js"
	defer></script>
<script src="assets/js/init-secretaire.js"></script>
</head>
<body>
	<div class="flex h-screen bg-gray-50 dark:bg-gray-900"
		:class="{ 'overflow-hidden': isSideMenuOpen}">
		<c:import url="Menu/sideMenuParametrePage.jsp"></c:import>
		<div class="flex flex-col flex-1">
			<c:import url="Menu/headerSecretaire.jsp"></c:import>
			<main class="h-full pb-16 overflow-y-auto">
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
				<form method="post" action="">
					<div class="container px-6 mx-auto grid">
						<h2
							class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">
							Structure de l'association</h2>

						<!-- General elements -->
						<h4
							class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300">
							Information</h4>
						<div
							class="px-4 py-3 mb-8 bg-white rounded-lg shadow-md dark:bg-gray-800">
							<!--Debut formulaire d'une nouvelle dépense-->
							<input name="idStructure"
								value="<c:out value="${structure.idStructure }"/>" hidden />
							<label class="block mt-1 text-sm"> <span
								class="text-gray-700 font-medium dark:text-gray-400"> Nom
									Structure </span> <input name="nomStructure"
								class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
								placeholder="Nom de l'association"
								value="<c:out value="${structure.nom }"/>" />
								<span class="text-xs text-red-600 dark:text-red-400"> <c:out
										value="${structureForm.erreurs['nomStructure']}" />
							</span>
							</label> <label class="block mt-4 text-sm"> <span
								class="text-gray-700 font-medium dark:text-gray-400">
									Date de création </span> <input name="dateCreationStructure"
								class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
								type="date" value="<c:out value="${structure.dateCreation }"/>" />
								<span class="text-xs text-red-600 dark:text-red-400"> <c:out
										value="${structureForm.erreurs['dateCreationStructure']}" />
							</span>
							</label> <label class="block mt-4 text-sm"> <span
								class="text-gray-700 font-medium dark:text-gray-400">
									Email </span> <input
								class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
								placeholder="email" name="emailStructure"
								value="<c:out value="${structure.email }"/>" />
								<span class="text-xs text-red-600 dark:text-red-400"> <c:out
										value="${structureForm.erreurs['emailStructure']}" />
							</span>
							</label> <label class="block mt-4 text-sm"> <span
								class="text-gray-700 font-medium dark:text-gray-400">
									Adresse de l'association </span> <textarea
									class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
									placeholder="Adresse" name="adresseStructure"><c:out
										value="${structure.adresse }" /></textarea> <span
								class="text-xs text-red-600 dark:text-red-400"> <c:out
										value="${structureForm.erreurs['adresseStructure']}" />
							</span>
							</label> <label class="block mt-4 text-sm"> <span
								class="text-gray-700 font-medium dark:text-gray-400">
									Site web </span> <input
								class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
								placeholder="www.test.com" name="siteWeb"
								value="<c:out value="${structure.siteWeb }"/>" />
								<span class="text-xs text-red-600 dark:text-red-400"> <c:out
										value="${structureForm.erreurs['siteWeb']}" />
							</span>
							</label> <label class="block mt-4 text-sm"> <span
								class="text-gray-700 font-medium dark:text-gray-400">
									Objectif </span> <input
								class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
								placeholder="Objectif" name="objectif"
								value="<c:out value="${structure.objectif }"/>" />
								<span class="text-xs text-red-600 dark:text-red-400"> <c:out
										value="${structureForm.erreurs['objectif']}" />
							</span>
							</label>

						</div>
						<div class="mb-8">
							<input type="submit" value="Enregistrer"
								class="px-5 py-3 font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple" />
						</div>
					</div>
				</form>
			</main>
		</div>
	</div>
</body>
</html>