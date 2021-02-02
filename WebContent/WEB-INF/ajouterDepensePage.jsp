<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Ajouter une dépense</title>
    <link
      href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="./assets/css/tailwind.output.css" />
    <script
      src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js"
      defer
    ></script>
    <script src="./assets/js/init-secretaire.js"></script>
  </head>
  <body>
    <div
      class="flex h-screen bg-gray-50 dark:bg-gray-900"
      :class="{ 'overflow-hidden': isSideMenuOpen}"
    >
      <c:import url="Menu/sideMenuDepense.jsp"></c:import>
      <div class="flex flex-col flex-1">
        <c:import url="Menu/headerSecretaire.jsp"></c:import>

        <main class="h-full pb-16 overflow-y-auto">
          <form method="post" action="<c:url value="/ajouterDepense"/>">
            <div class="container px-6 mx-auto grid">
              <h2
                class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200"
              >
                Ajouter une dépense
              </h2>

              <!-- General elements -->
              <h4
                class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300"
              >
                Formulaire dépense
              </h4>
              <div
                class="px-4 py-3 mb-8 bg-white rounded-lg shadow-md dark:bg-gray-800"
              >
                <!--Debut formulaire d'une nouvelle d�pense-->
                <label class="block mt-1 text-sm">
                  <span class="text-gray-700 font-medium dark:text-gray-400">
                    Montant dépensé
                  </span>
                  <input
                    class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                    placeholder="Montant dépensé"
                    name="montantDepense"
                    value="<c:out value="${depense.montant}"/>"
                  />
                  <span class="text-xs text-red-600 dark:text-red-400">
                    <c:out value="${depenseForm.erreurs['montant']}" />
                  </span>
                </label>
                <label class="block mt-4 text-sm">
                  <span class="text-gray-700 font-medium dark:text-gray-400">
                    Date de dépense
                  </span>
                  <input
                    class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                    type="date"
                    name="dateDepense"
                    value="<c:out value="${depense.dateDepense}"/>"
                  />
                  <span class="text-xs text-red-600 dark:text-red-400">
                    <c:out value="${depenseForm.erreurs['dateDepense']}" />
                  </span>
                </label>
                <label class="block mt-4 text-sm">
                  <span class="text-gray-700 font-medium dark:text-gray-400">
                    Type de dépense
                  </span>
                  <input
                    class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                    placeholder="Type de dépense"
                    name="typeDepense"
                    value="<c:out value="${depense.typeDepense}"/>"
                  />
                  <span class="text-xs text-red-600 dark:text-red-400">
                    <c:out value="${depenseForm.erreurs['typeDepense']}" />
                  </span>
                </label>
              </div>
              <div class="mb-8">
                <input
                  type="submit"
                  value="Enregistrer"
                  class="px-5 py-3 font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
                />
              </div>
            </div>
          </form>
        </main>
      </div>
    </div>
  </body>
</html>
