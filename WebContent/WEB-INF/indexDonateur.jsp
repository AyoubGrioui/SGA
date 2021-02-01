<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Tableau de bord</title>
    <link
      href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="assets/css/tailwind.output.css" />
    <script
      src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js"
      defer
    ></script>
    <script src="assets/js/init-secretaire.js"></script>
  </head>
  <body>
    <div
      class="flex h-screen bg-gray-50 dark:bg-gray-900"
      :class="{ 'overflow-hidden': isSideMenuOpen}"
    >
      <div class="flex flex-col flex-1 w-full">
      	<c:import url="Menu/headerDonateur.jsp"></c:import>
        <main class="h-full pb-16 overflow-y-auto">
           <div class="Menu/container grid px-6 mx-auto">
            <h2
              class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200"
            >
              Liste de vos dons
            </h2>
            <h4
              class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300"
            >
              Information sur les dons
            </h4>
            <!-- Tableau -->
            <div class="w-full overflow-hidden rounded-lg shadow-xs">
              <div class="w-full overflow-x-auto">
                <table class="w-full whitespace-no-wrap">
                  <thead>
                    <tr
                      class="text-xs font-semibold tracking-wide text-center text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800"
                    >
                      <th class="px-4 py-3">Date</th>
                      <th class="px-4 py-3">Description</th>
                      <th class="px-4 py-3">Montant</th>
                      <th class="px-4 py-3">Type de paiement</th>
                      <th class="text-left px-4 py-3">Actions</th>
                    </tr>
                  </thead>
                  <tbody
                    class="bg-white divide-y dark:divide-gray-700 dark:bg-gray-800"
                  >
                    <tr class="text-gray-700 dark:text-gray-400 text-center">
                      <td class="px-4 py-3 text-sm">12/12/2012</td>
                      <td class="px-4 py-3 text-sm">Description don</td>
                      <td class="px-4 py-3 text-sm">2121212</td>
                      <td class="px-4 py-3 text-sm">chéque</td>
                      <td class="px-1 py-3">
                        <div class="flex items-center space-x-4 text-sm">
                          <button
                            class="flex items-center justify-between px-2 py-2 text-sm font-medium leading-5 text-purple-600 rounded-lg dark:text-gray-400 focus:outline-none focus:shadow-outline-gray"
                            aria-label="Details"
                            @click="openModal"
                          >
                          
                            <svg
                              class="w-6 h-6"
                              fill="none"
                              stroke="currentColor"
                              viewBox="0 0 24 24"
                              xmlns="http://www.w3.org/2000/svg"
                            >
                              <path
                                stroke-linecap="round"
                                stroke-linejoin="round"
                                stroke-width="2"
                                d="M10 6H6a2 2 0 00-2 2v10a2 2 0 002 2h10a2 2 0 002-2v-4M14 4h6m0 0v6m0-6L10 14"
                              ></path>
                            </svg>
                          </button>
                        </div>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
              <div
                class="grid px-4 py-3 text-xs font-semibold tracking-wide text-gray-500 uppercase border-t dark:border-gray-700 bg-gray-50 sm:grid-cols-9 dark:text-gray-400 dark:bg-gray-800"
              ></div>
            </div>
          </div>
        </main>
       </div>
      </div>
          <!--  detail des entrées -->
    <div
      x-show="isModalOpen"
      x-transition:enter="transition ease-out duration-150"
      x-transition:enter-start="opacity-0"
      x-transition:enter-end="opacity-100"
      x-transition:leave="transition ease-in duration-150"
      x-transition:leave-start="opacity-100"
      x-transition:leave-end="opacity-0"
      class="fixed inset-0 z-30 flex items-end bg-black bg-opacity-50 sm:items-center sm:justify-center"
    >
      <!-- -------------- -->
      <div
        x-show="isModalOpen"
        x-transition:enter="transition ease-out duration-150"
        x-transition:enter-start="opacity-0 transform translate-y-1/2"
        x-transition:enter-end="opacity-100"
        x-transition:leave="transition ease-in duration-150"
        x-transition:leave-start="opacity-100"
        x-transition:leave-end="opacity-0  transform translate-y-1/2"
        @click.away="closeModal"
        @keydown.escape="closeModal"
        class="w-full px-6 py-4 overflow-hidden bg-white rounded-t-lg dark:bg-gray-800 sm:rounded-lg sm:m-4 sm:max-w-xl"
        role="dialog"
        id="modal"
      >
        <header class="flex justify-end">
          <button
            class="inline-flex items-center justify-center w-6 h-6 text-gray-400 transition-colors duration-150 rounded dark:hover:text-gray-200 hover: hover:text-gray-700"
            aria-label="close"
            @click="closeModal"
          >
            <svg
              class="w-4 h-4"
              fill="currentColor"
              viewBox="0 0 20 20"
              role="img"
              aria-hidden="true"
            >
              <path
                d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z"
                clip-rule="evenodd"
                fill-rule="evenodd"
              ></path>
            </svg>
          </button>
        </header>
        <!-- detail contenu -->
        <div class="mt-4 mb-6">
          <!--titre -->
          <p
            class="flex justify-center pt-2 mb-2 pb-4 text-lg bg-gray-100 w-16 h-8 rounded font-semibold text-gray-700 dark:text-gray-800"
          >
            Détail
          </p>
          <!-- description -->
          <h4
            class="mb-4 text-md font-semibold text-gray-600 dark:text-gray-300"
          >
            Donateur
          </h4>

          <div class="mb-4">
            <span class="font-semibold text-gray-700 dark:text-gray-400"
              >Nom du donateur :
            </span>
           <span class="text-gray-700 dark:text-gray-300">
            <c:out value="test"/>
           </span>
          </div>

          <div class="mb-4">
            <span class="font-semibold text-gray-700 dark:text-gray-400"
              >Date du don :
            </span>
            <span class="text-gray-700 dark:text-gray-300">
            <c:out value="test" />
            </span>
          </div>

          <div class="mb-4">
            <span class="font-semibold text-gray-700 dark:text-gray-400"
              >Description :
            </span>
            <span class="text-gray-700 dark:text-gray-300">
            <c:out value="test@gmail.com" />
            </span>
          </div>

          <div class="mb-4">
            <span class="font-semibold text-gray-700 dark:text-gray-400"
              >Montant :
            </span>
            <span class="text-gray-700 dark:text-gray-300">
            <c:out value="JK90000" />
            </span>
          </div>

          <div class="mb-4">
            <span class="font-semibold text-gray-700 dark:text-gray-400"
              >Type de paiement :
            </span>
            <span class="text-gray-700 dark:text-gray-300"
              ><c:out value="+212000000"
            /></span>
          </div>

          <!-- If else pour les champs correspondant à un type de paiement -->
          <!-- ----------- -->
          
        </div>
        <footer
          class="flex flex-col items-center justify-end px-6 py-3 -mx-6 -mb-4 space-y-4 sm:space-y-0 sm:space-x-6 sm:flex-row bg-gray-50 dark:bg-gray-800"
        >
          <button
            @click="closeModal"
            class="w-full px-5 py-3 text-sm font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg sm:w-auto sm:px-4 sm:py-2 active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
          >
            Cancel
          </button>
        </footer>
      </div>
    </div>
  </body>
</html>