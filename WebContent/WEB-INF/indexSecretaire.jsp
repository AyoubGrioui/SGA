<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Association - page d'accueil</title>
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
		:class="{ 'overflow-hidden': isSideMenuOpen }">
		<c:import url="Menu/sideMenuDashBoardSecretaire.jsp"></c:import>

		<div class="flex flex-col flex-1 w-full">
			<c:import url="Menu/headerSecretaire.jsp"></c:import>

			<main class="h-full overflow-y-auto">
				<div class="container px-6 mx-auto grid">
					<h2
						class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">
						Tableau de bord</h2>
					<!-- Cards -->
					<div class="grid gap-6 mb-8">
						<!-- Card -->
						<div
							class="flex items-center p-4 bg-white rounded-lg shadow-xs dark:bg-gray-800">
							<div
								class="p-3 mr-4 text-orange-500 bg-orange-100 rounded-full dark:text-orange-100 dark:bg-orange-500">
								<svg class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20">
                    <path
										d="M13 6a3 3 0 11-6 0 3 3 0 016 0zM18 8a2 2 0 11-4 0 2 2 0 014 0zM14 15a4 4 0 00-8 0v3h8v-3zM6 8a2 2 0 11-4 0 2 2 0 014 0zM16 18v-3a5.972 5.972 0 00-.75-2.906A3.005 3.005 0 0119 15v3h-3zM4.75 12.094A5.973 5.973 0 004 15v3H1v-3a3 3 0 013.75-2.906z"></path>
                  </svg>
							</div>
							<div>
								<p
									class="mb-2 text-sm font-medium text-gray-600 dark:text-gray-400">
									Total des adhérents</p>
								<p
									class="text-lg font-semibold text-gray-700 dark:text-gray-200">
									<c:out value="${adherentSize}" default="0" />
								</p>
							</div>
						</div>
						<!-- Card -->
						<div
							class="flex items-center p-4 bg-white rounded-lg shadow-xs dark:bg-gray-800">
							<div
								class="p-3 mr-4 text-green-500 bg-green-100 rounded-full dark:text-green-100 dark:bg-green-500">
								<svg class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20">
                    <path fill-rule="evenodd"
										d="M4 4a2 2 0 00-2 2v4a2 2 0 002 2V6h10a2 2 0 00-2-2H4zm2 6a2 2 0 012-2h8a2 2 0 012 2v4a2 2 0 01-2 2H8a2 2 0 01-2-2v-4zm6 4a2 2 0 100-4 2 2 0 000 4z"
										clip-rule="evenodd"></path>
                  </svg>
							</div>
							<div>
								<p
									class="mb-2 text-sm font-medium text-gray-600 dark:text-gray-400">
									Totale des dépenses</p>
								<p
									class="text-lg font-semibold text-gray-700 dark:text-gray-200">
									<c:out value="${depenseMontant}" default="0" />
									MAD
								</p>
							</div>
						</div>
						<!-- Card -->
						<div
							class="flex items-center p-4 bg-white rounded-lg shadow-xs dark:bg-gray-800">
							<div
								class="p-3 mr-4 text-blue-500 bg-blue-100 rounded-full dark:text-blue-100 dark:bg-blue-500">
								<svg class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20">
                    <path fill-rule="evenodd"
										d="M4 4a2 2 0 00-2 2v4a2 2 0 002 2V6h10a2 2 0 00-2-2H4zm2 6a2 2 0 012-2h8a2 2 0 012 2v4a2 2 0 01-2 2H8a2 2 0 01-2-2v-4zm6 4a2 2 0 100-4 2 2 0 000 4z"
										clip-rule="evenodd"></path>
                  </svg>
							</div>
							<div>
								<p
									class="mb-2 text-sm font-medium text-gray-600 dark:text-gray-400">
									Nombre de dons</p>
								<p
									class="text-lg font-semibold text-gray-700 dark:text-gray-200">
									<c:out value="${donSize}" default="0" />
								</p>
							</div>
						</div>
					</div>
					<div
						class="flex items-center p-4 bg-white rounded-lg shadow-xs dark:bg-gray-800">
						<div
							class="p-3 mr-4 text-blue-500 bg-blue-100 rounded-full dark:text-blue-100 dark:bg-blue-500">
							<svg class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20">
                    <path fill-rule="evenodd"
									d="M4 4a2 2 0 00-2 2v4a2 2 0 002 2V6h10a2 2 0 00-2-2H4zm2 6a2 2 0 012-2h8a2 2 0 012 2v4a2 2 0 01-2 2H8a2 2 0 01-2-2v-4zm6 4a2 2 0 100-4 2 2 0 000 4z"
									clip-rule="evenodd"></path>
                  </svg>
						</div>
						<div>
							<p
								class="mb-2 text-sm font-medium text-gray-600 dark:text-gray-400">
								Total des dons</p>
							<p class="text-lg font-semibold text-gray-700 dark:text-gray-200">
								<c:out value="${donMontant}" default="0" />
							</p>
						</div>
					</div>
				</div>
		</div>
		</main>
	</div>
	</div>
</body>
</html>
