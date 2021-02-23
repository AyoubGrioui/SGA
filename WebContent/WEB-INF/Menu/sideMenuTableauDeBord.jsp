<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<!----------------------- Menu ------------------------->
	<aside
		class="z-20 hidden w-64 overflow-y-auto bg-white dark:bg-gray-800 md:block flex-shrink-0">
		<div class="py-4 text-gray-500 dark:text-gray-400">
			<h1 class="ml-6 text-lg font-bold text-gray-800 dark:text-gray-200">
				<c:out value="${structure.nom }" />
			</h1>
			<!--------------------- tableau de bord -------------------->
			<ul class="mt-6">
				<li class="relative px-6 py-3"><span
					class="absolute inset-y-0 left-0 w-1 bg-purple-600 rounded-tr-lg rounded-br-lg"
					aria-hidden="true"></span> <a
					class="inline-flex items-center w-full text-sm font-semibold text-gray-800 transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200 dark:text-gray-100"
					href="<c:url value="/indexPresident"></c:url>">
						<svg class="w-5 h-5" aria-hidden="true" fill="none"
							stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
							viewBox="0 0 24 24" stroke="currentColor">
                  <path
								d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6"></path>
                </svg> <span class="ml-4">Tableau de bord</span>
				</a></li>
			</ul>
			<ul>
				<!--------------------- Donateurs -------------------->
				<li class="relative px-6 py-3"><a
					class="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
					href="<c:url value="/listeDesDonateurs"></c:url>">
						<svg class="w-5 h-5" aria-hidden="true" fill="none"
							stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
							xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
							stroke="currentColor">
                  <path
								d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z"></path>
                  <path
								d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z"></path>
                </svg> <span class="ml-4">Les donateurs</span>
				</a></li>
				<!--------------------- Adherent -------------------->
				<li class="relative px-6 py-3">
					<button
						class="inline-flex items-center justify-between w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
						@click="togglePagesMenu1" aria-haspopup="true">
						<span class="inline-flex items-center"> <svg
								class="w-5 h-5" aria-hidden="true" fill="none"
								stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
								xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
								stroke="currentColor">
                    <path
									d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"></path>
                  </svg> <span class="ml-4">Adhérent</span>
						</span>
						<svg class="w-4 h-4" aria-hidden="true" fill="currentColor"
							viewBox="0 0 20 20">
                  <path fill-rule="evenodd"
								d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z"
								clip-rule="evenodd"></path>
                </svg>
					</button>
					<template x-if="isPagesMenuOpen1">
						<ul x-transition:enter="transition-all ease-in-out duration-300"
							x-transition:enter-start="opacity-25 max-h-0"
							x-transition:enter-end="opacity-100 max-h-xl"
							x-transition:leave="transition-all ease-in-out duration-300"
							x-transition:leave-start="opacity-100 max-h-xl"
							x-transition:leave-end="opacity-0 max-h-0"
							class="p-2 mt-2 space-y-2 overflow-hidden text-sm font-medium text-gray-500 rounded-md shadow-inner bg-gray-50 dark:text-gray-400 dark:bg-gray-900"
							aria-label="submenu">

							<li
								class="px-2 py-1 transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200">
								<a class="w-full"
								href="<c:url value="/ajouterAdherent"></c:url>">Ajouter un
									adhérent</a>
							</li>
							<li
								class="px-2 py-1 transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200">
								<a class="w-full"
								href="<c:url value="/listeDesAdherents"></c:url>">La liste
									des adhérents</a>
							</li>
						</ul>
					</template>
				</li>
				<!--------------------- Les entrées / Sorties -------------------->
				<li class="relative px-6 py-3">
					<button
						class="inline-flex items-center justify-between w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
						@click="togglePagesMenu2" aria-haspopup="true">
						<span class="inline-flex items-center"> <svg
								class="w-5 h-5" aria-hidden="true" fill="none"
								stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
								xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
								stroke="currentColor">
                    <path
									d="M3 10h18M7 15h1m4 0h1m-7 4h12a3 3 0 003-3V8a3 3 0 00-3-3H6a3 3 0 00-3 3v8a3 3 0 003 3z"></path>
                  </svg> <span class="ml-4">Les entrées/sorties</span>
						</span>
						<svg class="w-4 h-4" aria-hidden="true" fill="currentColor"
							viewBox="0 0 20 20">
                  <path fill-rule="evenodd"
								d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z"
								clip-rule="evenodd"></path>
                </svg>
					</button>
					<template x-if="isPagesMenuOpen2">
						<ul x-transition:enter="transition-all ease-in-out duration-300"
							x-transition:enter-start="opacity-25 max-h-0"
							x-transition:enter-end="opacity-100 max-h-xl"
							x-transition:leave="transition-all ease-in-out duration-300"
							x-transition:leave-start="opacity-100 max-h-xl"
							x-transition:leave-end="opacity-0 max-h-0"
							class="p-2 mt-2 space-y-2 overflow-hidden text-sm font-medium text-gray-500 rounded-md shadow-inner bg-gray-50 dark:text-gray-400 dark:bg-gray-900"
							aria-label="submenu">

							<li
								class="px-2 py-1 transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200">
								<a class="w-full"
								href="<c:url value="/listeDesEntrees"></c:url>">Les entrées</a>
							</li>
							<li
								class="px-2 py-1 transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200">
								<a class="w-full"
								href="<c:url value="/listeDesSorties"></c:url>">Les sorties</a>
							</li>
						</ul>
					</template>
				</li>

			</ul>

		</div>
	</aside>

	<!-- Mobile sideMenu -->
	<!-- ---------------------------------------- -->
	<div x-show="isSideMenuOpen"
		x-transition:enter="transition ease-in-out duration-150"
		x-transition:enter-start="opacity-0"
		x-transition:enter-end="opacity-100"
		x-transition:leave="transition ease-in-out duration-150"
		x-transition:leave-start="opacity-100"
		x-transition:leave-end="opacity-0"
		class="fixed inset-0 z-10 flex items-end bg-black bg-opacity-50 sm:items-center sm:justify-center"></div>
	<aside
		class="fixed inset-y-0 z-20 flex-shrink-0 w-64 mt-16 overflow-y-auto bg-white dark:bg-gray-800 md:hidden"
		x-show="isSideMenuOpen"
		x-transition:enter="transition ease-in-out duration-150"
		x-transition:enter-start="opacity-0 transform -translate-x-20"
		x-transition:enter-end="opacity-100"
		x-transition:leave="transition ease-in-out duration-150"
		x-transition:leave-start="opacity-100"
		x-transition:leave-end="opacity-0 transform -translate-x-20"
		@click.away="closeSideMenu" @keydown.escape="closeSideMenu">
		<div class="py-4 text-gray-500 dark:text-gray-400">
			<h1 class="ml-6 text-lg font-bold text-gray-800 dark:text-gray-200">
				Nom Association</h1>
			<!--------------------- tableau de bord -------------------->
			<ul class="mt-6">
				<li class="relative px-6 py-3"><span
					class="absolute inset-y-0 left-0 w-1 bg-purple-600 rounded-tr-lg rounded-br-lg"
					aria-hidden="true"></span> <a
					class="inline-flex items-center w-full text-sm font-semibold text-gray-800 transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200 dark:text-gray-100"
					href="<c:url value="/indexPresident"></c:url>">
						<svg class="w-5 h-5" aria-hidden="true" fill="none"
							stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
							viewBox="0 0 24 24" stroke="currentColor">
                  <path
								d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6"></path>
                </svg> <span class="ml-4">Tableau de bord</span>
				</a></li>
			</ul>
			<ul>
				<!--------------------- Donateurs -------------------->
				<li class="relative px-6 py-3"><a
					class="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
					href="<c:url value="/listeDesDonateurs"></c:url>">
						<svg class="w-5 h-5" aria-hidden="true" fill="none"
							stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
							xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
							stroke="currentColor">
                  <path
								d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z"></path>
                  <path
								d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z"></path>
                </svg> <span class="ml-4">Les donateurs</span>
				</a></li>
				<!--------------------- Adherent -------------------->
				<li class="relative px-6 py-3">
					<button
						class="inline-flex items-center justify-between w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
						@click="togglePagesMenu1" aria-haspopup="true">
						<span class="inline-flex items-center"> <svg
								class="w-5 h-5" aria-hidden="true" fill="none"
								stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
								xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
								stroke="currentColor">
                    <path
									d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"></path>
                  </svg> <span class="ml-4">Adhérent</span>
						</span>
						<svg class="w-4 h-4" aria-hidden="true" fill="currentColor"
							viewBox="0 0 20 20">
                  <path fill-rule="evenodd"
								d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z"
								clip-rule="evenodd"></path>
                </svg>
					</button>
					<template x-if="isPagesMenuOpen1">
						<ul x-transition:enter="transition-all ease-in-out duration-300"
							x-transition:enter-start="opacity-25 max-h-0"
							x-transition:enter-end="opacity-100 max-h-xl"
							x-transition:leave="transition-all ease-in-out duration-300"
							x-transition:leave-start="opacity-100 max-h-xl"
							x-transition:leave-end="opacity-0 max-h-0"
							class="p-2 mt-2 space-y-2 overflow-hidden text-sm font-medium text-gray-500 rounded-md shadow-inner bg-gray-50 dark:text-gray-400 dark:bg-gray-900"
							aria-label="submenu">

							<li
								class="px-2 py-1 transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200">
								<a class="w-full"
								href="<c:url value="/ajouterAdherent"></c:url>">Ajouter un
									adhérent</a>
							</li>
							<li
								class="px-2 py-1 transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200">
								<a class="w-full"
								href="<c:url value="/listeDesAdherents"></c:url>">La liste
									des adhérents</a>
							</li>
						</ul>
					</template>
				</li>
				<!--------------------- Les entrées / Sorties -------------------->
				<li class="relative px-6 py-3">
					<button
						class="inline-flex items-center justify-between w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
						@click="togglePagesMenu2" aria-haspopup="true">
						<span class="inline-flex items-center"> <svg
								class="w-5 h-5" aria-hidden="true" fill="none"
								stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
								xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
								stroke="currentColor">
                    <path
									d="M3 10h18M7 15h1m4 0h1m-7 4h12a3 3 0 003-3V8a3 3 0 00-3-3H6a3 3 0 00-3 3v8a3 3 0 003 3z"></path>
                  </svg> <span class="ml-4">Les entrées/sorties</span>
						</span>
						<svg class="w-4 h-4" aria-hidden="true" fill="currentColor"
							viewBox="0 0 20 20">
                  <path fill-rule="evenodd"
								d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z"
								clip-rule="evenodd"></path>
                </svg>
					</button>
					<template x-if="isPagesMenuOpen2">
						<ul x-transition:enter="transition-all ease-in-out duration-300"
							x-transition:enter-start="opacity-25 max-h-0"
							x-transition:enter-end="opacity-100 max-h-xl"
							x-transition:leave="transition-all ease-in-out duration-300"
							x-transition:leave-start="opacity-100 max-h-xl"
							x-transition:leave-end="opacity-0 max-h-0"
							class="p-2 mt-2 space-y-2 overflow-hidden text-sm font-medium text-gray-500 rounded-md shadow-inner bg-gray-50 dark:text-gray-400 dark:bg-gray-900"
							aria-label="submenu">

							<li
								class="px-2 py-1 transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200">
								<a class="w-full"
								href="<c:url value="/listeDesEntrees"></c:url>">Les entrées</a>
							</li>
							<li
								class="px-2 py-1 transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200">
								<a class="w-full"
								href="<c:url value="/listeDesSorties"></c:url>">Les sorties</a>
							</li>
						</ul>
					</template>
				</li>

			</ul>

		</div>
	</aside>
</body>
</html>