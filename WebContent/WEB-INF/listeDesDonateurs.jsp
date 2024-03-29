<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>liste des donateurs</title>
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
		<c:import url="Menu/sideMenuDonateurs.jsp"></c:import>
		<div class="flex flex-col flex-1">
			<c:import url="Menu/headerMenu.jsp"></c:import>

			<main class="h-full pb-16 overflow-y-auto">
				<div class="container grid px-6 mx-auto">
					<c:if test="${successMsg !=null}">
						<div
							class="bg-green-100 border-l-4 border-green-500 text-green-700 p-4"
							role="alert">
							<p class="font-bold">Succès</p>
							<p>
								<c:out value="${successMsg}" />
							</p>
							${successMsg =null}
						</div>
					</c:if>
					<c:if test="${erreurMsg != null}">
						<div class="bg-red-100 border-l-4 border-red-500 text-red-700 p-4"
							role="alert">
							<p class="font-bold">Erreur</p>
							<p>
								<c:out value="${erreurMsg}" />
							</p>
							${erreurMsg =null}
						</div>
					</c:if>
					<h2
						class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">
						Liste des donateurs</h2>

					<h4
						class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300">
						Information sur les donateurs</h4>
					<!-- Tableau -->
					<div class="w-full overflow-hidden rounded-lg shadow-xs">
						<div class="w-full overflow-x-auto">
							<table class="w-full whitespace-no-wrap">
								<thead>
									<tr
										class="text-xs font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
										<th class="px-4 py-3">Nom</th>
										<th class="px-4 py-3">Email</th>
										<th class="px-4 py-3">Téléphone</th>
										<th class="px-4 py-3">Adresse</th>
										<th class="px-4 py-3">Actions</th>
									</tr>
								</thead>
								<tbody
									class="bg-white divide-y dark:divide-gray-700 dark:bg-gray-800">

									<c:forEach items="${donateurList}" var="donateur"
										varStatus="boucle">
										<tr class="text-gray-700 dark:text-gray-400">
											<td class="px-4 py-3">
												<div class="flex items-center text-sm">
													<div>
														<p class="font-semibold">
															<c:out value="${donateur.nom}" />
														</p>
													</div>
												</div>
											</td>
											<td class="px-4 py-3 text-sm"><c:out
													value="${donateur.email}" /></td>
											<td class="px-4 py-3 text-sm"><c:out
													value="${donateur.telephone}" /></td>
											<td class="px-4 py-3 text-sm"><c:out
													value="${donateur.adresse}" /></td>
											<td class="px-4 py-3">
												<div class="flex items-center space-x-4 text-sm">
													<a
														href="<c:url value="/modifierDonateur"><c:param name="donneurID" value="${donateur.idDonneur}" /></c:url>">
														<button
															class="flex items-center justify-between px-2 py-2 text-sm font-medium leading-5 text-purple-600 rounded-lg dark:text-gray-400 focus:outline-none focus:shadow-outline-gray"
															aria-label="Edit">
															<svg class="w-5 h-5" aria-hidden="true"
																fill="currentColor" viewBox="0 0 20 20">
                          <path
																	d="M13.586 3.586a2 2 0 112.828 2.828l-.793.793-2.828-2.828.793-.793zM11.379 5.793L3 14.172V17h2.828l8.38-8.379-2.83-2.828z"></path>
                        </svg>
														</button> <a
														href="<c:url value="/envoyerMail"><c:param name="donneurID" value="${donateur.idDonneur}" /></c:url>">
															<button
																class="flex items-center justify-between px-2 py-2 text-sm font-medium leading-5 text-purple-600 rounded-lg dark:text-gray-400 focus:outline-none focus:shadow-outline-gray"
																aria-label="Edit">
																<svg class="w-6 h-6" fill="none" stroke="currentColor"
																	viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
																	<path stroke-linecap="round" stroke-linejoin="round"
																		stroke-width="2"
																		d="M13 9l3 3m0 0l-3 3m3-3H8m13 0a9 9 0 11-18 0 9 9 0 0118 0z"></path></svg>
															</button> <a
															href="<c:url value="/supprimerDonneur"><c:param name="donneurID" value="${donateur.idDonneur}" /></c:url>">

																<button
																	class="flex items-center justify-between px-2 py-2 text-sm font-medium leading-5 text-purple-600 rounded-lg dark:text-gray-400 focus:outline-none focus:shadow-outline-gray"
																	aria-label="Delete">
																	<svg class="w-5 h-5" aria-hidden="true"
																		fill="currentColor" viewBox="0 0 20 20">
                          <path fill-rule="evenodd"
																			d="M9 2a1 1 0 00-.894.553L7.382 4H4a1 1 0 000 2v10a2 2 0 002 2h8a2 2 0 002-2V6a1 1 0 100-2h-3.382l-.724-1.447A1 1 0 0011 2H9zM7 8a1 1 0 012 0v6a1 1 0 11-2 0V8zm5-1a1 1 0 00-1 1v6a1 1 0 102 0V8a1 1 0 00-1-1z"
																			clip-rule="evenodd"></path>
                        </svg>
																</button>
														</a>
												</div>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div
							class="grid px-4 py-3 text-xs font-semibold tracking-wide text-gray-500 uppercase border-t dark:border-gray-700 bg-gray-50 sm:grid-cols-9 dark:text-gray-400 dark:bg-gray-800"></div>
					</div>
				</div>
			</main>
		</div>
	</div>
</body>
</html>
