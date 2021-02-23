<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Erreur interne du serveur</title>
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
		<div class="flex flex-col flex-1 mt-16">
			<main class="h-full pb-16 overflow-y-auto">
				<div class="container flex flex-col items-center px-6 mx-auto">
					<svg class="w-12 h-12 mt-8 text-purple-200" xmlns="http://www.w3.org/2000/svg" fill="none"
						viewBox="0 0 24 24" stroke="currentColor">
  						<path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
							d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z" />
					</svg>
					<h1 class="text-6xl font-semibold text-gray-700 dark:text-gray-200">
						500</h1>
					<p class="text-gray-700 dark:text-gray-300">
						Erreur interne du serveur. Veuillez
						<button
							class="text-purple-600 hover:underline dark:text-purple-300"
							onclick="history.back()">revenir en arrière.</button>
						.
					</p>
				</div>
			</main>
		</div>
	</div>
</body>
</html>