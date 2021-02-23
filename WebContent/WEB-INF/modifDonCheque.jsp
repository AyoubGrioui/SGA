<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Modifier un don</title>
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
	rel="stylesheet" />
<link rel="stylesheet" href="./assets/css/tailwind.output.css" />
<script
	src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js"
	defer></script>
<script src="./assets/js/init-secretaire.js"></script>
</head>
<body>
	<div class="flex h-screen bg-gray-50 dark:bg-gray-900"
		:class="{ 'overflow-hidden': isSideMenuOpen}">
		<c:import url="Menu/sideMenuDonateurPage.jsp"></c:import>
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
				<form method="POST" action="<c:url value="/modifierDon"/>">
					<div class="container px-6 mx-auto grid">
						<h2
							class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">
							Modifier le don</h2>
						<!-- Don inputs -->
						<h4
							class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300">
							Formulaire du don</h4>
						<div
							class="px-4 py-3 mb-8 bg-white rounded-lg shadow-md dark:bg-gray-800">
							<input type="text" hidden="hidden" name="idDon"
								value="<c:out value="${don.idDon}"/>" /> <input
								type="text" hidden="hidden" name="typeDon" value="cheque" />
							<label class="mt-2 block text-sm"> <span
								class="text-gray-700 font-medium dark:text-gray-400">Nom
									du donateur : </span> <span
								class="text-gray-700 font-bold dark:text-gray-100"><c:out
										value="${don.donneur.nom}" /> </span>
							</label> <label class="mt-4 block text-sm"> <span
								class="text-gray-700 font-medium dark:text-gray-400">Date
							</span> <input
								class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
								type="date" name="dateDon"
								value="<c:out value="${don.dateDon}" />" />
								<span class="text-xs text-red-600 dark:text-red-400"> <c:out
										value="${donForm.erreurs['dateDon']}" />
							</span>
							</label> <label class="block mt-4 text-sm"> <span
								class="text-gray-700 font-medium dark:text-gray-400">
									Type de paiement </span> <select
								class="block w-full mt-1 text-sm dark:text-gray-300 dark:border-gray-600 dark:bg-gray-700 form-select focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
								name="" disabled>
									<option value="cheque">Chèque</option>
									<option value="espece">Espèce</option>
									<option value="versement">Versement</option>
							</select>
							</label>
							<div id="cheque">
								<label class="mt-4 block text-sm"> <span
									class="text-gray-700 font-medium dark:text-gray-400">Numéro
										du compte banquaire </span> <input
									class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
									placeholder="Numéro du compte banquaire"
									name="numeroCompteBanqueDonCheque"
									value="<c:out value="${don.numeroCompteBanque}" />" />
									<span class="text-xs text-red-600 dark:text-red-400"> <c:out
											value="${donForm.erreurs['numeroCompteBanqueDonCheque']}" />
								</span>
								</label> <label class="mt-4 block text-sm"> <span
									class="text-gray-700 font-medium dark:text-gray-400">Date
										du chèque </span> <input
									class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
									type="date" name="dateChequeDonCheque"
									value="<c:out value="${don.dateCheque}" />" />
									<span class="text-xs text-red-600 dark:text-red-400"> <c:out
											value="${donForm.erreurs['dateChequeDonCheque']}" />
								</span>
								</label> <label class="mt-4 block text-sm"> <span
									class="text-gray-700 font-medium dark:text-gray-400">Date
										du dépot </span> <input
									class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
									type="date" name="dateDepotDonCheque"
									value="<c:out value="${don.dateDepot}" />" />
									<span class="text-xs text-red-600 dark:text-red-400"> <c:out
											value="${donForm.erreurs['dateDepotDonCheque']}" />
								</span>
								</label> <label class="mt-4 block text-sm"> <span
									class="text-gray-700 font-medium dark:text-gray-400">Nom
										de la banque </span> <input
									class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
									placeholder="nom banque" name="nomBanqueDonCheque"
									value="<c:out value="${don.nomBanque}" />" />
									<span class="text-xs text-red-600 dark:text-red-400"> <c:out
											value="${donForm.erreurs['nomBanqueDonCheque']}" />
								</span>
								</label>
							</div>
							<label class="mt-4 block text-sm"> <span
								class="text-gray-700 font-medium dark:text-gray-400">Montant
							</span> <input
								class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
								name="montant" value="<c:out value="${don.montant}" />" />
								<span class="text-xs text-red-600 dark:text-red-400"> <c:out
										value="${donForm.erreurs['montant']}" />
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
