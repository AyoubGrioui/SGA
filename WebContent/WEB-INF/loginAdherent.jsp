<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login Adherent</title>
    <link
      href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="assets/css/tailwind.output.css" />
    <script
      src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js"
      defer
    ></script>
    <script src="assets/js/init-alpine.js"></script>
  </head>
  <body>
    <div class="flex items-center min-h-screen p-6 bg-gray-50 dark:bg-gray-900
    :class="{ 'overflow-hidden': isSideMenuOpen }" ">
    <div
      class="flex-1 h-full max-w-4xl mx-auto overflow-hidden bg-white rounded-lg shadow-xl dark:bg-gray-800"
    >
      <div class="flex flex-col overflow-y-auto md:flex-row">
        <!--image-->
        <div class="h-32 md:h-auto md:w-1/2">
          <!--image en light mode-->
          <img
            aria-hidden="true"
            class="object-cover w-full h-full dark:hidden"
            src="assets/img/login-adherent-light.jpg"
            alt="Office"
          />
          <!--image en dark mode-->
          <img
            aria-hidden="true"
            class="hidden object-cover w-full h-full dark:block"
            src="assets/img/login-adherent-dark.jpg"
            alt="Office"
          />
        </div>
        <!------dark mode / light mode buttons----->

        <!--------------------------------------------------------------------------------------------->

        <!--login-->

        <div class="flex items-center justify-center p-6 sm:p-12 md:w-1/2">
          <div class="w-full">
            <!-------------------------------------------formulaire---------------------------------------------->
            <form method="post" action="<c:url value="/loginAdherent"/>">
              <h1
                class="mb-4 text-xl font-semibold text-gray-700 dark:text-gray-200"
              >
                Authentification Adhérent
              </h1>
              <label class="block text-sm"
                ><!------email------>
                <span class="text-gray-700 dark:text-gray-400">Email</span>
                <input
                  type="email"
                  name="userEmail"
                  class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                  value="<c:out value="${user.email }"/>"                
                />
                <span class="text-xs text-red-600 dark:text-red-400">
                    <c:out value="${loginAdherentForm.erreurs['userEmail']}" />
                  </span>
              </label>
              <label class="block mt-4 text-sm"
                ><!------mot de passe------>
                <span class="text-gray-700 dark:text-gray-400"
                  >Mot de passe</span
                >
                <input
                  type="password"
                  name="userPassword"
                  class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                  s'
                  value="<c:out value="${user.motDePasse }"/>"
                  
                />
                	<span class="text-xs text-red-600 dark:text-red-400">
                    <c:out value="${loginAdherentForm.erreurs['userPassword']}" />
                  </span>
                	
              </label>
              	
              <input
                class="block w-full px-4 py-2 mt-4 uppercase text-sm font-medium leading-5 text-center text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
                type="submit"
                value="s'authentifier"
              />

               <div class="flex mt-8 text-sm">
                <label class="flex items-center dark:text-gray-400">
                  <input
                    type="checkbox"
                    class="text-purple-600 form-checkbox focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
                    name = "memoire"
                  />
                  <span class="ml-2">
                    Se souvenir de moi 
                  </span>
                </label>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    <div class="">
      <button
        class="rounded-md focus:outline-none focus:shadow-outline-purple"
        @click="toggleTheme"
      >
        <template x-if="!dark">
          <svg
            class="w-5 h-5"
            aria-hidden="true"
            fill="currentColor"
            viewBox="0 0 20 20"
          >
            <path
              d="M17.293 13.293A8 8 0 016.707 2.707a8.001 8.001 0 1010.586 10.586z"
            ></path>
          </svg>
        </template>
        <template x-if="dark">
          <svg
            class="w-5 h-5"
            aria-hidden="true"
            fill="currentColor"
            viewBox="0 0 20 20"
          >
            <path
              fill-rule="evenodd"
              d="M10 2a1 1 0 011 1v1a1 1 0 11-2 0V3a1 1 0 011-1zm4 8a4 4 0 11-8 0 4 4 0 018 0zm-.464 4.95l.707.707a1 1 0 001.414-1.414l-.707-.707a1 1 0 00-1.414 1.414zm2.12-10.607a1 1 0 010 1.414l-.706.707a1 1 0 11-1.414-1.414l.707-.707a1 1 0 011.414 0zM17 11a1 1 0 100-2h-1a1 1 0 100 2h1zm-7 4a1 1 0 011 1v1a1 1 0 11-2 0v-1a1 1 0 011-1zM5.05 6.464A1 1 0 106.465 5.05l-.708-.707a1 1 0 00-1.414 1.414l.707.707zm1.414 8.486l-.707.707a1 1 0 01-1.414-1.414l.707-.707a1 1 0 011.414 1.414zM4 11a1 1 0 100-2H3a1 1 0 000 2h1z"
              clip-rule="evenodd"
            ></path>
          </svg>
        </template>
      </button>
    </div>
    </div>
  </body>
</html>
